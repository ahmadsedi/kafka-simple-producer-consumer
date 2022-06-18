# Kafka Producer/Consumer in Java
This is a dockerized Maven-based simple Kafka producer/consumer application. Both producer and consumer use "simple-topic" 
topic with minimum configuration to work with Kafka.
## Project Structure
* BasicConsumer: consumes events.
* BasicProducer: producer events into topic in 1s intervals.
* Dockerfile-consumer: docker file to create consumer image.
* Dockerfile-producer: docker file to create producer image.
* docker-compose.yml: contains all images which are needed to run the project, they include Kafka/Zookeeper, producer, 
and consumer.
## Requirements
You need to install Java 16, Maven, and docker-compose. 
## How to run
Run "mvn clean package" in the project's root directory
run "docker-compose up -d"
run "docker-compose logs consumer-service" and "docker-compose logs producer-service" to see how they communicate! 
  