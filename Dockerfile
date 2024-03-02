# Stage 1: Build with Maven
FROM maven:3.8.6-openjdk-11 AS maven
WORKDIR /usr/src/app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ src/
RUN mvn package -DskipTests

# Stage 2: Create the final image
FROM openjdk:11-jre-slim
ARG JAR_FILE=library-app-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app/
COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "library-app-0.0.1-SNAPSHOT.jar"]
