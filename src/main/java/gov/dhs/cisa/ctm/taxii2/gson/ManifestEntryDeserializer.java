package gov.dhs.cisa.ctm.taxii2.gson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import gov.dhs.cisa.ctm.taxii2.resources.ManifestEntry;

import static gov.dhs.cisa.ctm.taxii2.gson.DeserializerUtil.get;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.util.List;

public class ManifestEntryDeserializer implements JsonDeserializer<ManifestEntry> {
    @Override
    public ManifestEntry deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        if (!jsonElement.isJsonObject())
            throw new JsonParseException(ManifestEntry.class.getSimpleName() + " must be JsonObject");

        ManifestEntry manifestEntry = new ManifestEntry();
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
                case "versions":
                    manifestEntry.setVersions(
                            context.deserialize(((JsonArray)get(json, key, JsonArray.class)), new TypeToken<List<String>>(){}.getType()));
                    break;
                case "media_types":
                    manifestEntry.withMediaTypes(
                            context.deserialize(((JsonArray)get(json, key, JsonArray.class)), new TypeToken<List<String>>(){}.getType()));
                default:
                    manifestEntry.withCustomProperty(key, json.get(key));
                    break;
            }
        }

        return manifestEntry;
    }
}
