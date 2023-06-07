#
# Build stage
#
FROM maven:3.8.3-openjdk-17 AS build
ENV HOST_DB=192.168.1.173
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM maven:3.8.3-openjdk-17
EXPOSE 8080:8080
COPY --from=build /home/app/target/*.jar .
ENTRYPOINT ["java","-jar", "CockPit-0.0.1-SNAPSHOT.jar"]


# Publishing PORTS
# docker run -p 8082:8080
