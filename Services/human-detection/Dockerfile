FROM adoptopenjdk/openjdk11:alpine-jre
RUN apk --no-cache add curl
EXPOSE 31600
ARG JAR_FILE=human-detection/target/human-detection-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
