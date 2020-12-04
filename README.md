# Kafka Challenge
Implementation example of Spring Boot applications to produce/consume messages to/from Kafka following Hexagonal Architecture.

### Prerequisites
- Java 15
- Git
- Docker
- [Confluent cp-all-in-one](https://github.com/confluentinc/cp-all-in-one/tree/6.0.0-post/cp-all-in-one)

### Running locally
Kafka-challenge is a Spring Boot application built using Maven. You can build a jar file and run it from the command line:
```shell
# Clone
git clone https://github.com/aalbiach/kafka-challenge.git

# Build all modules
cd kafka-challenge
./mvnw package

# Run producer
cd ./producer
./mvnw spring-boot:run

# Run consumer
cd ./consumer
./mvnw spring-boot:run
```

### Test messages
You can find a test requests in [http](http) directory in _cURL_ and _Jetbrains HTTP Request_ formats.
