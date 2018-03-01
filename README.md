# Spring / Kafka Example

A simple **Spring Boot** application which tests **Apache Kafka** integration.

## Setting up Kafka

Download the Kafka binaries from: https://kafka.apache.org/

### Run Kafka and create a new topic

Extract the binaries, and run Apache **Zookeper** and Apache **Kafka**, then create a topic named `test`

_Unix-based systems:_

    bin/zookeeper-server-start.sh config/zookeeper.properties
    bin/kafka-server-start.sh config/server.properties
    bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test

_Windows:_

    bin\windows\zookeeper-server-start.bat config\zookeeper.properties
    bin\windows\kafka-server-start.bat config\server.properties
    bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test

### Test Kafka

#### Listen for messages

Listen for messages on test topic (since the beginning) and output them to the console:

_Unix-based systems:_

    bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning

_Windows:_

    bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test --from-beginning

#### Send messages

This starts a new command-line client, sending every line of input you write to the test topic:

_Unix-based systems:_

    bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning

_Windows:_

    bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic text
    
## Build and run the application

### Build with gradle

    gradlew clean build


### Run with gradle

    gradlew bootrun 
    
### Run with Java directly (alternative to running with gradle)
    
    java -jar build/libs/spring-kafka-example-1.0.0-SNAPSHOT.jar
    
The application will write a message to the Kafka topic, log received messages, and print a list of all messages on the topic.
