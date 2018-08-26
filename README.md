# Krungsri Assignment
Assignment for Krungsri 

Microservice using Spring Boot and JPA



#### Requirement

- [JDK 10](http://www.oracle.com/technetwork/java/javase/downloads/jdk10-downloads-4416644.html) and [JRE 10](http://www.oracle.com/technetwork/java/javase/downloads/jre10-downloads-4417026.html)
- Maven
- MySQL



### Database

Create MySQL database name 'krungsri_user' and 'krungsri_auth' 

username='root'

password=''



###Eureka Server (Naming Server)

#####run

```
mvn package -DskipTests && java -jar target/eureka-server-0.0.1-SNAPSHOT.jar
```



### User Service

#####run

```
mvn package && java -jar target/user-service-0.0.1-SNAPSHOT.jar
```

#####test

```
mvn package && java -jar target/user-service-0.0.1-SNAPSHOT-tests.jar
```



###Auth Service

#####run

```
mvn package && java -jar target/auth-service-0.0.1-SNAPSHOT.jar
```

#####test

```
mvn package && java -jar target/auth-service-0.0.1-SNAPSHOT-tests.jar
```



###Zuul (API Gateway)

#####run

```

```



