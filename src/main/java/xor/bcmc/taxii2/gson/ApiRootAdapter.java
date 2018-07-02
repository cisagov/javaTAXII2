package xor.bcmc.taxii2.gson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import xor.bcmc.taxii2.resources.ApiRoot;

import java.lang.reflect.Type;
import java.util.List;

import static xor.bcmc.taxii2.gson.DeserializerUtil.get;

public class ApiRootAdapter implements JsonSerializer<ApiRoot>, JsonDeserializer<ApiRoot> {
    @Override
    public ApiRoot deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        if (!jsonElement.isJsonObject())
            throw new JsonParseException(ApiRoot.class.getSimpleName() + " must be JsonObject");

        ApiRoot apiRoot = new ApiRoot();
        JsonObject json = jsonElement.getAsJsonObject();

        for (String key : json.keySet()) {
            switch (key){
                case "id":
                    apiRoot.setId(
                            ((JsonPrimitive)get(json, key, JsonPrimitive.class)).getAsString());
                    break;
                case "title":
                    apiRoot.setTitle(
                            ((JsonPrimitive)get(json, key, JsonPrimitive.class)).getAsString());
                    break;
                case "description":
                    apiRoot.setDescription(
                            ((JsonPrimitive)get(json, key, JsonPrimitive.class)).getAsString());
                    break;
                case "versions":
                    apiRoot.setVersions(
                            context.deserialize(((JsonArray)get(json, key, JsonArray.class)), new TypeToken<List<String>>(){}.getType()));
                    break;
                case "max_content_length":
                    apiRoot.setMaxContentLength(
                            ((JsonPrimitive)get(json, key, JsonPrimitive.class)).getAsInt());
                    break;
                default:
                    apiRoot.withCustomProperty(key, json.get(key));
                    break;
            }
        }

        return apiRoot;
    }

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
