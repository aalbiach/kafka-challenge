package com.kafka.consumer.shared.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.kafka.DefaultKafkaProducerFactoryCustomizer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.LoggingProducerListener;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.converter.MessagingMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
@EnableKafka
@RequiredArgsConstructor
public class KafkaConfiguration {

    private final KafkaProperties properties;

    @Bean
    public KafkaTemplate<String, Object> template(
            ProducerFactory<String, Object> producerFactory,
            ProducerListener<String, Object> producerListener,
            RecordMessageConverter messageConverter) {

        KafkaTemplate<String, Object> kafkaTemplate = new KafkaTemplate<>(producerFactory);
        kafkaTemplate.setMessageConverter(messageConverter);
        kafkaTemplate.setProducerListener(producerListener);
        kafkaTemplate.setDefaultTopic(properties.getTemplate().getDefaultTopic());

        return kafkaTemplate;
    }

    @Bean
    public ProducerFactory<String, Object> producerFactory(
            ObjectProvider<DefaultKafkaProducerFactoryCustomizer> customizers) {

        DefaultKafkaProducerFactory<String, Object> factory = new DefaultKafkaProducerFactory<>(properties.buildProducerProperties());

        String transactionIdPrefix = properties.getProducer().getTransactionIdPrefix();
        if (transactionIdPrefix != null) {
            factory.setTransactionIdPrefix(transactionIdPrefix);
        }
        customizers.orderedStream().forEach((customizer) -> customizer.customize(factory));

        return factory;
    }

    @Bean
    public ProducerListener<String, Object> producerListener() {
        return new LoggingProducerListener<>();
    }

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(properties.buildConsumerProperties());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> kafkaListenerContainerFactory(
            ConsumerFactory<String, Object> consumerFactory,
            RecordMessageConverter messageConverter,
            ErrorHandler errorHandler) {

        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setMessageConverter(messageConverter);
        factory.setErrorHandler(errorHandler);

        return factory;
    }

    @Bean
    public ErrorHandler errorHandler(DeadLetterPublishingRecoverer deadLetterPublishingRecoverer) {
        return new SeekToCurrentErrorHandler(deadLetterPublishingRecoverer, new FixedBackOff(1000L, 2));
    }

    /*@Bean
    public ErrorHandler errorHandler() {
        return new LoggingErrorHandler();
    }*/

    /**
     * Configure the {@link DeadLetterPublishingRecoverer} to publish poison pill bytes to a dead letter topic:
     * "topic-name.DLT".
     */
    @Bean
    public DeadLetterPublishingRecoverer deadLetterPublishingRecoverer(KafkaOperations<String, Object> template) {
        return new DeadLetterPublishingRecoverer(template);
    }

    @Bean
    public RecordMessageConverter messageConverter() {
        return new MessagingMessageConverter();
    }

}
