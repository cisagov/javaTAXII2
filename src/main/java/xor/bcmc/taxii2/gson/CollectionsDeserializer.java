package xor.bcmc.taxii2.gson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import xor.bcmc.taxii2.resources.Collection;
import xor.bcmc.taxii2.resources.Collections;

import java.lang.reflect.Type;
import java.util.List;

import static xor.bcmc.taxii2.gson.DeserializerUtil.get;

public class CollectionsDeserializer implements JsonDeserializer<Collections> {
    @Override
    public Collections deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        if (!jsonElement.isJsonObject())
            throw new JsonParseException(Collections.class.getSimpleName() + " must be JsonObject");

        Collections collections = new Collections();
        JsonObject json = jsonElement.getAsJsonObject();

        for (String key : json.keySet()) {
            if ("collections".equals(key)) {
                collections.setCollections(
                        context.deserialize(((JsonArray) get(json, key, JsonArray.class)), new TypeToken<List<Collection>>() {}.getType()));
            } else {
                collections.withCustomProperty(key, json.get(key));
            }
        }

        return collections;
    }
}
