# Stage 1: Build the agent JAR
FROM maven:3.8.4-openjdk-11-slim AS build
WORKDIR /build
COPY . /build
RUN mvn clean package

# Stage 2: Prepare the runtime environment
FROM openjdk:11-jdk-slim
VOLUME /opt/agent
COPY --from=build /build/target/agent-0.0.1-SNAPSHOT.jar /opt/agent/agent.jar

# Install unzip
RUN apt-get update && apt-get install -y unzip

CMD ["bash"]

