package com.personal;

import lombok.Getter;
import lombok.ToString;

import java.net.URI;
import java.util.List;

@ToString
@Getter
public class HTTPRequest {
    private Method method;
    private URI uri;
    private Double version;

    public HTTPRequest parse(String req) {
        List<String> attributes = List.of(req.split(" "));
        this.method = Method.valueOf(attributes.getFirst());
        this.uri = URI.create(attributes.get(1));
        this.version = Double.valueOf(attributes.get(2).replace("HTTP/", ""));
        return this;
    }
}
