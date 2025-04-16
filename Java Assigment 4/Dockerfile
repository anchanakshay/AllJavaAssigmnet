FROM maven:3.6.3-openjdk-11-slim AS BUILDER
ARG VERSION=0.0.1-SNAPSHOT
WORKDIR /build/
COPY pom.xml /build/
COPY src /build/src/

RUN mvn clean package

ADD target/StudentDBMS-0.0.1-SNAPSHOT.jar spring-boot-student.jar
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ARG JAR_FILE=target/*.jar
ENTRYPOINT ["java","-jar","spring-boot-student.jar"]
