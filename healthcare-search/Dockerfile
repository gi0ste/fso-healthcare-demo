# Use an official OpenJDK runtime as a parent image
FROM openjdk:8-jre-alpine

# set shell to bash
ì
RUN apk update && apk add bash

# Copy the fat jar into the container at /app
COPY /target/search-search-server.jar /
COPY /offers-hc.json /

# Make port 8080 available to the world outside this container
EXPOSE 5051/tcp

# Run jar file when the container launches
CMD ["java", "-jar", "fso-search-server.jar", "5051", "./offers-hc.json"]
