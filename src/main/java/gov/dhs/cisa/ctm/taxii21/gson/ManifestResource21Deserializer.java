package gov.dhs.cisa.ctm.taxii21.gson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import gov.dhs.cisa.ctm.taxii21.resources.ManifestEntry21;
import gov.dhs.cisa.ctm.taxii21.resources.ManifestResource21;

import static gov.dhs.cisa.ctm.taxii2.gson.DeserializerUtil.get;

import java.lang.reflect.Type;
import java.util.List;

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