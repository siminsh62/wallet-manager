# WALLET MICROSERVICE

## Technology used
```
- Java 8+ 
- Spring Boot 
- H2
- Docker 
- Maven 
- Junit
```
## How to run

git
```
git clone https://github.com/siminsh62/shoppingcart
```
Build
```
mvn clean install 
```
```
spring boot run 
```

##  Scalability
We can containerize each microservice using Docker and create an image. Kubernetes has the capability to 
manage containers.There are different ways for it.

## Concurrency
A very powerful way to handle concurrency by design in event-driven services is to use the ability to route events to 
specific partitions. Since each partition is only consumed by only one instance, we can route each set of events to
specific instances depending on the routing key.

