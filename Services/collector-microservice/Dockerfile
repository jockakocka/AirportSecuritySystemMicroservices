FROM adoptopenjdk/openjdk11:alpine-jre
RUN apk --no-cache add curl
EXPOSE 31700
ARG JAR_FILE=collector-microservice/target/collector-microservice-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
