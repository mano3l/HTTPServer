package com.personal.HTTPServer;

import com.personal.HTTPServer.request.HTTPRequest;
import com.personal.HTTPServer.response.Entity;
import com.personal.HTTPServer.response.EntityMapper;
import com.personal.HTTPServer.response.HTTPResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class Server {

    public static final InetAddress ADDRESS;
    public static final int PORT = 8080;

    static {
        try {
            ADDRESS = Inet4Address.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                handleClient(serverSocket);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleClient(ServerSocket serverSocket) {
        try (Socket clientSocket = serverSocket.accept();
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            Map<URI, Entity> entities = createEntities();
            EntityMapper mapper = new EntityMapper(entities);

            StringBuilder request = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                request.append(line).append("\r\n");

                if (line.isEmpty()) {
                    if (request.toString().toLowerCase().contains("content-length")) {
                        int contentLength = getContentLength(request.toString());
                        char[] body = new char[contentLength];
                        in.read(body, 0, contentLength);
                        request.append(body);
                    }
                    HTTPRequest formatedReq = new HTTPRequest(request.toString());
                    HTTPResponse response = new HTTPResponse(formatedReq, mapper);
                    out.write(response.toString());
                    out.flush();
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<URI, Entity> createEntities() {
        Map<URI, Entity> entities = new HashMap<>();
        entities.put(URI.create("/hello"), new Entity("<h1>Hello, World!</h1>"));
        entities.put(URI.create("/favicon.ico"), new Entity("<h1>Hello, World!</h1>"));
        return entities;
    }

    private static int getContentLength(String request) {
        String[] headers = request.split("\r\n");
        for (String header : headers) {
            if (header.toLowerCase().startsWith("content-length:")) {
                return Integer.parseInt(header.split(":")[1].trim());
            }
        }
        return 0;
    }
}