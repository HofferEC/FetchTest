FROM openjdk:17-alpine
MAINTAINER hoffer.ec
COPY target/FetchTest-0.0.1-SNAPSHOT.jar fetch-server-1.0.0.jar
ENTRYPOINT ["java","-jar","fetch-server-1.0.0.jar"]