package com.personal.HTTPServer;

public enum ProtocolVersion {
    HTTP_1_0(1.0),
    HTTP_1_1(1.1),
    HTTP_2(2.0);

    private final Double version;

    ProtocolVersion(Double version) {
        this.version = version;
    }

    public Double getVersion() {
        return version;
    }

    public static ProtocolVersion fromString(String version) {
        for (ProtocolVersion v : ProtocolVersion.values()) {
            if (v.getVersion().toString().equals(version)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No enum constant for version: " + version);
    }

    public String toString() {
        return "HTTP/" + version;
    }
}
