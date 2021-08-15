FROM openjdk:16
MAINTAINER macedoldm
WORKDIR E:/aplicacoes

ARG JAR_FILE=build/libs/tour-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
