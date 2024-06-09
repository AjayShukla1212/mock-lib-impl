# Stage 1: Build the agent JAR
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /build
COPY . /build
RUN mvn clean package

# Stage 2: Prepare the runtime environment
FROM openjdk:17-jdk-slim
VOLUME /opt/agent
COPY --from=build /build/target/hypertest-0.0.1-SNAPSHOT.jar /opt/agent/hypertest.jar

# Install unzip
RUN apt-get update && apt-get install -y unzip

CMD ["bash"]