package xor.bcmc.taxii2.gson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import xor.bcmc.taxii2.resources.ManifestEntry;
import xor.bcmc.taxii2.resources.ManifestResource;

import java.lang.reflect.Type;
import java.util.List;

import static xor.bcmc.taxii2.gson.DeserializerUtil.get;

public class ManifestResourceDeserializer implements JsonDeserializer<ManifestResource> {
    @Override
    public ManifestResource deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        if (!jsonElement.isJsonObject())
            throw new JsonParseException(ManifestResource.class.getSimpleName() + " must be JsonObject");

        ManifestResource manifestResource = new ManifestResource();
        JsonObject json = jsonElement.getAsJsonObject();

        for (String key : json.keySet()) {
            switch (key){
                case "objects":
                    manifestResource.setObjects(
                            context.deserialize(((JsonArray)get(json, key, JsonArray.class)), new TypeToken<List<ManifestEntry>>(){}.getType()));
                    break;
                default:
                    manifestResource.withCustomProperty(key, json.get(key));
                    break;
            }
        }

        return manifestResource;
    }
}
