FROM adoptopenjdk/openjdk11:alpine-jre
RUN apk --no-cache add curl
EXPOSE 3800
ARG JAR_FILE=cpanel-service/target/cpanel-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
