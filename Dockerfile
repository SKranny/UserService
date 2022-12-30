FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /build
ADD ./target/PersonService-0.0.1-SNAPSHOT.jar ./person-service.jar
EXPOSE 8083
CMD java -jar person-service.jar
