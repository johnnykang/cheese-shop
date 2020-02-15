FROM gradle:jdk8 as builder
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN ./gradlew assembleServerAndClient


FROM openjdk:8u171-alpine3.7
EXPOSE 8080
COPY --from=builder /home/gradle/src/server/build/libs/*-all.jar /app/server.jar
WORKDIR /app
CMD java ${JAVA_OPTS} -jar server.jar
