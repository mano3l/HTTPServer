package com.personal.HTTPServer.response;

import com.personal.HTTPServer.util.FileUtil;
import lombok.Getter;

import java.io.File;
import java.net.URI;
import java.util.Map;

@Getter
public class EntityMapper {
    private Map<URI, Entity> entities;

    public EntityMapper(Map<URI, Entity> entities) {
        this.entities = entities;
    }

    public Entity findEntity(URI uri) {
        String basePath = new File("").getAbsolutePath();
        Entity pageNotFound = new Entity(FileUtil.fileToString(basePath + "\\src\\main\\java\\com\\personal\\HTTPServer\\page\\notfound.html"));
        return entities.getOrDefault(uri, pageNotFound);
    }

    public URI findEntityUri(Entity entity) {
        for (Map.Entry<URI, Entity> entry : entities.entrySet()) {
            if (entry.getValue().equals(entity)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void addEntity(URI uri, Entity entity) {
        entities.put(uri, entity);
    }

    public boolean entityExists(URI uri) {
        return entities.containsKey(uri);
    }
}
