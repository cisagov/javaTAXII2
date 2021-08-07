package gov.dhs.cisa.ctm.taxii21.gson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import gov.dhs.cisa.ctm.taxii21.resources.ManifestEntry21;

import static gov.dhs.cisa.ctm.taxii2.gson.DeserializerUtil.get;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;

public class ManifestEntry21Deserializer implements JsonDeserializer<ManifestEntry21> {
    @Override
    public ManifestEntry21 deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        if (!jsonElement.isJsonObject())
            throw new JsonParseException(ManifestEntry21.class.getSimpleName() + " must be JsonObject");

        ManifestEntry21 manifestEntry = new ManifestEntry21();
        JsonObject json = jsonElement.getAsJsonObject();

        for (String key : json.keySet()) {
            switch (key){
                case "id":
                    manifestEntry.setId(
                            ((JsonPrimitive)get(json, key, JsonPrimitive.class)).getAsString());
                    break;
                case "date_added":
                    manifestEntry.setDateAdded(
                            context.deserialize(((JsonPrimitive)get(json, key, JsonPrimitive.class)), new TypeToken<ZonedDateTime>(){}.getType()));
                    break;
                case "version":
                    manifestEntry.setVersion(
                            ((JsonPrimitive)get(json, key, JsonPrimitive.class)).getAsString());
                    break;
                case "media_type":
                    manifestEntry.setMediaType(
                            ((JsonPrimitive)get(json, key, JsonPrimitive.class)).getAsString());
                default:
                    manifestEntry.withCustomProperty(key, json.get(key));
                    break;
            }
        }

        return manifestEntry;
    }
}