package com.personal.HTTPServer.response;

import com.personal.HTTPServer.ProtocolVersion;
import com.personal.HTTPServer.StatusLine;
import com.personal.HTTPServer.request.HTTPRequest;
import lombok.Getter;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Getter
public class HTTPResponse {

    private Entity entity;
    private EntityMapper entityMapper;
    private StatusLine statusLine;
    private Map<String, String> headers = new HashMap<>();
    private HTTPRequest request;

    public HTTPResponse(HTTPRequest req, EntityMapper mapper) {
        this.request = req;
        this.entityMapper = mapper;
        this.entity = this.entityMapper.findEntity(req.getStatusLine().getUri());
        createResponse();
    }

    private void createResponse() {
        URI uri = this.request.getStatusLine().getUri();
        ProtocolVersion version = this.request.getStatusLine().getVersion();

        if (!this.entityMapper.entityExists(uri)) {
            this.statusLine = StatusLineRes.builder()
                    .version(version)
                    .status(Status.NOT_FOUND)
                    .build();
            return;
        }

        this.statusLine = StatusLineRes.builder()
                .version(version)
                .status(Status.OK)
                .build();

        int bodyLength = this.entity.getContent().getBytes(StandardCharsets.UTF_8).length;
        if (bodyLength > 0) {
            this.headers.put("Content-Length", String.valueOf(bodyLength));
        }
    }

    @Override
    public String toString() {
        StringBuilder response = new StringBuilder();
        response.append(statusLine).append("\r\n");

        headers.forEach((key, value) -> response.append(key).append(": ").append(value).append("\r\n"));

        response.append("\r\n").append(this.entity);
        return response.toString();
    }
}