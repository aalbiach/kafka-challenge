# Kafka Challenge
Implementation example of Spring Boot applications to produce/consume messages to/from Kafka following Hexagonal Architecture.

### Prerequisites
- Java 15
- Git
- Docker

### Running locally
Kafka-challenge is a Spring Boot application built using Maven. You can build a jar file and run it from the command line:
```shell
# Clone
git clone https://github.com/aalbiach/kafka-challenge.git

# Run confluent platform
cd kafka-challenge/cp-all-in-one
docker-compose up -d

# Build all modules
cd kafka-challenge
./mvnw package

# Run producer
cd kafka-challenge/producer
./mvnw spring-boot:run

# Run consumer
cd kafka-challenge/consumer
./mvnw spring-boot:run

# Build and deploy docker image for producer
cd kafka-challenge/producer
./mvnw spring-boot:build-image

# Build and deploy docker image for consumer
cd kafka-challenge/consumer
./mvnw spring-boot:build-image
```

### Test messages
You can find a test requests in [http](http) directory in _cURL_ and _Jetbrains HTTP Request_ formats.
