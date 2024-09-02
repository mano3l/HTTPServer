# HTTP Server

This project is a simple HTTP server implemented in Java. It handles HTTP requests and serves static content.

## Project Structure

- `app/src/main/java/com/personal/HTTPServer/Server.java`: Main server class that handles client connections and processes HTTP requests.
- `app/src/main/java/com/personal/HTTPServer/response/HTTPResponse.java`: Class responsible for creating HTTP responses.
- `app/src/main/java/com/personal/HTTPServer/request/HTTPRequest.java`: Class representing an HTTP request.
- `app/src/main/java/com/personal/HTTPServer/response/Entity.java`: Class representing an entity (content) to be served.
- `app/src/main/java/com/personal/HTTPServer/response/EntityMapper.java`: Class for mapping URIs to entities.

## Getting Started

### Prerequisites

- Java 11 or higher
- Gradle

### Running the Server
To start the server, run the following command:

```sh
./gradlew run
```

### Usage
The server serves static content based on predefined URIs. For example:  
`http://localhost:8080/hello` will return a simple HTML message: 
```html
<h1>Hello, World!</h1>
```

## Future implementations
- Handle other methods: `POST`, `PUT`, `DELETE`.
- Concurrency
- Basic security: Input validation, rate limiting, security headers, etc.
