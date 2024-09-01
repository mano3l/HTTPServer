package com.personal.HTTPServer.response;

import com.personal.HTTPServer.Method;
import com.personal.HTTPServer.ProtocolVersion;
import com.personal.HTTPServer.StatusLine;
import lombok.Builder;
import lombok.Getter;

import java.net.URI;

@Getter
@Builder
public class StatusLineRes extends StatusLine {
    private ProtocolVersion version;
    private URI uri;
    private Status status;
    private Method method;

    @Override
    public String toString() {
        return version + " " + status.getCode() + " " + status.getReason();
    }
}
