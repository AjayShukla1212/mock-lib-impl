
FROM maven:3.8.4-openjdk-11-slim AS agent
WORKDIR /agent
COPY agent /agent
RUN mvn clean package


FROM maven:3.8.4-openjdk-11-slim AS app
WORKDIR /app
COPY app /app


FROM openjdk:11-jdk-slim
VOLUME /lib/agent


COPY --from=agent /agent/target/mock-lib-impl-agent-0.0.1-SNAPSHOT.jar /lib/agent/mock-lib-impl-agent.jar

COPY --from=app /app/target/mock-lib-impl-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java","-javaagent:/lib/agent/mock-lib-impl-agent.jar", "-jar", "/app.jar"]
