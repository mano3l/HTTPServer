package com.personal.HTTPServer.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Entity {

    private String content;

    public Entity(String content) {
        this.content = content;
    }

    public String toString() {
        return content;
    }
}
