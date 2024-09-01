package com.personal.HTTPServer;

import lombok.Getter;

import java.net.URI;

@Getter
public abstract class StatusLine {
    protected ProtocolVersion version;
    protected URI uri;
    protected Method method;

    public abstract String toString();
}
