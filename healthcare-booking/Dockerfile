#FROM openjdk:11-jre-slim
#FROM openjdk:14-alpine
FROM eclipse-temurin:18.0.1_10-jre-alpine
ARG JAR_FILE=target/*.jar
COPY /target/bookingService-1.0.jar /
EXPOSE 5001/tcp
ENTRYPOINT ["java","-jar","bookingService-1.0.jar"]