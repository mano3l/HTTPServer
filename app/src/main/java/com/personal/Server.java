package com.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

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
        try (ServerSocket serverSocket = new ServerSocket(PORT);
             Socket clientSocket = serverSocket.accept();
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String req = in.readLine();
            var httpReq = new HTTPRequest();
            System.out.println(httpReq.parse(req));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
