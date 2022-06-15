FROM gradle:7.4.2-jdk17-alpine
FROM openjdk:17.0.2
WORKDIR /usr/src/app
COPY . .
RUN gradle build
ENTRYPOINT ["java", "-jar", "build/libs/33kit-0.0.1-SNAPSHOT.jar"]

# FROM gradle:7.4.2-jdk17-alpine
# RUN mkdir -p /usr/src/app
# WORKDIR /usr/src/app
# COPY . /usr/src/app
# RUN gradle build

# FROM openjdk:17.0.2
# RUN mkdir -p /usr/src/app
# WORKDIR /usr/src/app
# COPY --from=0 /usr/src/app/build/libs  /usr/src/app
# ENTRYPOINT ["java", "-jar", "33kit-0.0.1-SNAPSHOT.jar"]
