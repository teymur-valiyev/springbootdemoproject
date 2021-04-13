FROM openjdk:11.0.8-jdk AS MAVEN_BUILD
MAINTAINER Teymur Valiyev <teymur.veliyev001@gmail.com>

ARG SPRING_ACTIVE_PROFILE=local

WORKDIR /build/

COPY pom.xml .
COPY mvnw .
COPY src src
COPY .git .git
COPY .mvn .mvn

RUN ./mvnw install -B -e -Dspring.profiles.active=$SPRING_ACTIVE_PROFILE -DskipTests

FROM openjdk:11.0.8-jre

ARG SPRING_ACTIVE_PROFILE=local
ENV ENV_SPRING_ACTIVE_PROFILE=$SPRING_ACTIVE_PROFILE

WORKDIR /app
RUN mkdir -p /app/files
COPY --from=MAVEN_BUILD /build/target/*.jar /app/app.jar
COPY ./entrypoint.sh .

RUN chmod +x /app/entrypoint.sh

ENTRYPOINT ["bash","/app/entrypoint.sh"]