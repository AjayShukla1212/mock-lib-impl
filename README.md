# Mock Library Implementation

## Description

Welcome to the Mocking Library project! This project showcases how to effectively mock Java libraries in a Spring Boot application using a Java agent with Byte Buddy. It's a comprehensive guide to setting up and running a mock environment for your Java applications.



## Structure

Here's a quick rundown of the project structure:

- `app/`: Contains the Spring Boot application.
- `agent/`: Houses the Java agent that intercepts and mocks library functions.
- `docker-compose.yml`: Docker Compose configuration to run both the application and the agent seamlessly.

## How to Build and Run

Follow these steps to get the project up and running:

1. Build the Docker image for Agent:

    ```bash
    docker-compose build agent
    ```
2. Build the Docker image for App:
   
    ```bash
    docker-compose build app
    ```

2. Start the containers:

    ```bash
    docker-compose up
    ```

3. Test the application by sending a POST request:

    ```bash
    curl -X POST http://localhost:8080/api/createNewPost -H "Content-Type: application/json" -d '{"post_name":"test-post","post_contents":"This is a test post"}'
    ```

## Environment Variables

To configure the application, you'll need to set the following environment variables:

- `DB_HOST`: The host address of your database.
- `DB_PORT`: The port number your database is listening on.
- `DB_USERNAME`: The username for your database.
- `DB_PASSWORD`: The password for your database.
- `HT_MODE`: The mode for the Java agent. It can be set to either `RECORD` or `REPLAY`.
