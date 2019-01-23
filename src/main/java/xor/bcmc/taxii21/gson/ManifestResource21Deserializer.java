package xor.bcmc.taxii21.gson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import xor.bcmc.taxii21.resources.ManifestEntry21;
import xor.bcmc.taxii21.resources.ManifestResource21;

import java.lang.reflect.Type;
import java.util.List;

import static xor.bcmc.taxii2.gson.DeserializerUtil.get;

public class ManifestResource21Deserializer implements JsonDeserializer<ManifestResource21> {
    @Override
    public ManifestResource21 deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        if (!jsonElement.isJsonObject())
            throw new JsonParseException(ManifestResource21.class.getSimpleName() + " must be JsonObject");

        ManifestResource21 manifestResource = new ManifestResource21();
        JsonObject json = jsonElement.getAsJsonObject();

        for (String key : json.keySet()) {
            switch (key){
                case "objects":
                    manifestResource.setObjects(
                            context.deserialize(((JsonArray)get(json, key, JsonArray.class)), new TypeToken<List<ManifestEntry21>>(){}.getType()));
                    break;
                default:
                    manifestResource.withCustomProperty(key, json.get(key));
                    break;
            }
        }

        return manifestResource;
    }
}