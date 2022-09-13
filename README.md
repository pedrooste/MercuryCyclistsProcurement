# CSIT318-Project

Welcome to CSIT318 project repo for Mercury Cyclists Procurement Microservice

## Maven install commands
## install dependencies
mvn clean install

## Build
mvn -B package --file pom.xml

## Run
mvn run

##Kafka setup
Before the application is started we must start the kafka environment and create the topic.
For windows

###Start zookeeper
`.\bin\windows\zookeeper-server-start.bat config\zookeeper.properties`

###Start kafka broker
`.\bin\windows\kafka-server-start.bat config\server.properties`

###Create topic
`.\bin\windows\kafka-topics.bat --create --topic backorder --bootstrap-server localhost:9092`

###Test send a topic
`.\bin\windows\kafka-console-producer.bat --topic backorder --bootstrap-server localhost:9092`

`{"id": 55, "productId" : 1, "test" : "extra"}`