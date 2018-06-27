package xor.bcmc.taxii2.gson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import xor.bcmc.taxii2.resources.ApiRoot;

import java.lang.reflect.Type;
import java.util.List;

import static xor.bcmc.taxii2.gson.DeserializerUtil.get;

public class ApiRootSerializer implements JsonSerializer<ApiRoot> {
    @Override
    public JsonElement serialize(ApiRoot src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject apiRoot = new JsonObject();
        apiRoot.addProperty("description", src.getDescription());
        apiRoot.addProperty("max_content_length", src.getMaxContentLength());
        apiRoot.addProperty("title", src.getTitle());
        if (src.getVersions() != null && !src.getVersions().isEmpty()) {
            JsonArray versions = new JsonArray();
            for (String version : src.getVersions()) {
                versions.add(version);
            }
            apiRoot.add("versions", versions);
        }
        return apiRoot;
    }
}
