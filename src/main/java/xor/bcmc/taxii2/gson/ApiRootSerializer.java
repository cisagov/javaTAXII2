package xor.bcmc.taxii2.gson;

import com.google.gson.*;
import xor.bcmc.taxii2.resources.ApiRoot;

import java.lang.reflect.Type;
import java.util.List;


public class ApiRootSerializer implements JsonSerializer<ApiRoot> {
    /**
     * Omit serialization of ID field
     */
    @Override
    public JsonElement serialize(ApiRoot src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject apiRoot = new JsonObject();
        apiRoot.addProperty("description", src.getDescription());
        apiRoot.addProperty("max_content_length", src.getMaxContentLength());
        apiRoot.addProperty("title", src.getTitle());
        List<String> versionsList = src.getVersions();
        if (versionsList != null && !versionsList.isEmpty()) {
            JsonArray versions = new JsonArray(versionsList.size());
            for (String version : versionsList) {
                versions.add(version);
            }
            apiRoot.add("versions", versions);
        }
        return apiRoot;
    }
}
