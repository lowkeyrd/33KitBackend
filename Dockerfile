FROM gradle:7.4.2-jdk17-alpine
FROM openjdk:17.0.2
WORKDIR /usr/src/app
COPY . .
RUN gradle build
ENTRYPOINT ["java", "-jar", "build/libs/33kit-0.0.1-SNAPSHOT.jar"]

# FROM gradle:7.4.2-jdk17-alpine
# WORKDIR /usr/src/workspace
# COPY . .
# RUN gradle build

# FROM openjdk:17.0.2
# WORKDIR /usr/src/app
# COPY --from=0 /usr/src/workspace/build/libs .
# ENTRYPOINT ["java", "-jar", "33kit-0.0.1-SNAPSHOT.jar"]
