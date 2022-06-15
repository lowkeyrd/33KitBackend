FROM gradle:7.4.2-jdk17-alpine
# FROM openjdk:17.0.2
WORKDIR /usr/src/app
COPY . .
RUN gradle build
ENTRYPOINT ["java", "-jar", "build/libs/33kit-0.0.1-SNAPSHOT.jar"]
