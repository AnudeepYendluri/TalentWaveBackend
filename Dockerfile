FROM maven:3.8.2-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests
FROM openjdk:17-jdk-slim
COPY --from=build /target/talent-docker.jar sample.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","sample.jar"]