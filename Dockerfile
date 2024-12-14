FROM maven:3.8.5-openjdk-17-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:17-alpine
MAINTAINER hoffer.ec
COPY --from=build /home/app/target/FetchTest-0.0.1-SNAPSHOT.jar receipt-server.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","receipt-server.jar"]
