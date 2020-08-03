spring.datasource.url= jdbc:mysql://localhost:3306/keymanager?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false
spring.datasource.username= eshop1
spring.datasource.password= 123

java -jar keymanager-1.0.jar

## Spring Boot File Upload / Download Rest API Example

**KeyManager**: [Uploading an Downloading files with Spring Boot]

> **This branch demonstrates how to store the files in MySQL database.**

## Steps to Setup

**1. Clone the repository** 

```bash

```

**2. Configure MySQL database**

Create a MySQL database named `keymanager`, and change the username and password in `src/main/resources/application.properties` as per your MySQL
installation -

```properties
spring.datasource.username= <YOUR MYSQL USERNAME>
spring.datasource.password= <YOUR MYSQL PASSWORD>
```

**3. Run the app using maven**

```bash
cd skeymanager
mvn spring-boot:run
```

That's it! The application can be accessed at `http://localhost:8080`.

You may also package the application in the form of a jar and then run the jar file like so -

```bash
mvn clean package
java -jar target/keymanager-1.0.jar
```