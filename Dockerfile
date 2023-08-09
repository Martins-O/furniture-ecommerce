# Use a Java-enabled base image
#FROM openjdk:20
#
#LABEL authors="Martins-O"
#
#ARG JAR_FILE=target/*.jar
#
### Set the working directory inside the container
#WORKDIR /app
#
## Copy the application JAR file from the host to the container
#COPY target/furniture-api-0.0.1-SNAPSHOT.jar app.jar
#
#EXPOSE 8080
#
## Define the entry point command to execute the application JAR
#ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM openjdk:20

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN chmod +x mvnw
RUN ./mvnw dependency:resolve


COPY src ./src

EXPOSE 8080

CMD ["./mvnw", "spring-boot:run"]

