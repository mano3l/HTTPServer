package com.personal.HTTPServer.request;

import com.personal.HTTPServer.Method;
import com.personal.HTTPServer.ProtocolVersion;
import com.personal.HTTPServer.StatusLine;
import lombok.Builder;
import lombok.Getter;

import java.net.URI;

@Getter
@Builder
public class StatusLineReq extends StatusLine {
    private ProtocolVersion version;
    private URI uri;
    private Method method;

    @Override
    public String toString() {
        return method + " " + uri + " " + version;
    }
}
