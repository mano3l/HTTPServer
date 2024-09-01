package com.personal.HTTPServer.request;

import com.personal.HTTPServer.Method;
import com.personal.HTTPServer.ProtocolVersion;
import com.personal.HTTPServer.StatusLine;
import lombok.Getter;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Getter
public class HTTPRequest {

    private final String req;
    private StatusLine statusLine;
    private Map<String, String> headers = new HashMap<>();

    public HTTPRequest(String request) {
        this.req = request;
        parse();
    }

    private void parse() {
        statusLine = parseStatusLine();
        headers = parseHeader();
    }

    private StatusLine parseStatusLine() {
        String[] statusLineAttr = req.split("\r\n")[0].split(" ");
        return StatusLineReq.builder()
                .method(Method.valueOf(statusLineAttr[0]))
                .uri(URI.create(statusLineAttr[1]))
                .version(ProtocolVersion.fromString(statusLineAttr[2].replace("HTTP/", "")))
                .build();
    }

    private Map<String, String> parseHeader() {
        String headerSection = req.replace(statusLine + "\r\n", "");
        Map<String, String> header = new HashMap<>();
        for (String line : headerSection.split("\r\n")) {
            if (line.isEmpty()) break;
            String[] headerLine = line.split(": ");
            header.put(headerLine[0], headerLine[1]);
        }
        return header;
    }
}
