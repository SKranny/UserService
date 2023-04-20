# Java Spring project "Social networking site"
### User-service
## Description
User-service is responsible for all functionality related to users of our social network, except authorization. For example: 
- user registration
- deleting user accounts
- retrieving account information and many others.
## Technologies
- Java version 11
- Spring Framework
- Flyway
- Lombok
- Mapstruct
- Spring Data JPA
- PostgreSQL
- Spring Security
- Spring Cloud OpenFeign
- Spring Cloud Netflix Eureka
- JWT(JsonWebToken)
- Nexus repository
- Kafka
- Swagger OpenApi
- JUnit
## Technical description
### How to run the application on your device:
1. (Pre-configuring the PostgreSQL database) Specify in the application.yaml file, or in the environment variables in your IDE, the required application configuration parameters to run:
    - SERVER_PORT (The port of your application. Specify it manually if you are not going to use the default port: 8086)
    - DB_PASSWORD (Password for the database)
    - DB_URL (The address of the database your application connects to. You should specify it manually if you are not going to use default postgresql url: jdbc:postgresql://localhost:5432/friend_service)
    - SECRET_KEY (Your application's secret key. This is needed to protect your service which uses JWT technology)
    - EUREKA_URI (Address of your Eureka server. Specify it if you are not going to use the default address: http://localhost:8081/eureka)
