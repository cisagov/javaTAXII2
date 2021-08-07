package gov.dhs.cisa.ctm.taxii2.gson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import gov.dhs.cisa.ctm.taxii2.resources.Collection;

import static gov.dhs.cisa.ctm.taxii2.gson.DeserializerUtil.get;

import java.lang.reflect.Type;
import java.util.List;

public class CollectionDeserializer implements JsonDeserializer<Collection> {
    @Override
    public Collection deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        if (!jsonElement.isJsonObject())
            throw new JsonParseException(Collection.class.getSimpleName() + " must be JsonObject");

        Collection collection = new Collection();
        JsonObject json = jsonElement.getAsJsonObject();

        for (String key : json.keySet()) {
            switch (key){
                case "id":
                    collection.setId(
                            ((JsonPrimitive)get(json, key, JsonPrimitive.class)).getAsString());
                    break;
                case "title":
                    collection.setTitle(
                            ((JsonPrimitive)get(json, key, JsonPrimitive.class)).getAsString());
                    break;
                case "description":
                    collection.setDescription(
                            ((JsonPrimitive)get(json, key, JsonPrimitive.class)).getAsString());
                    break;
                case "can_read":
                    collection.setCanRead(
                            ((JsonPrimitive)get(json, key, JsonPrimitive.class)).getAsBoolean());
                    break;
                case "can_write":
                    collection.setCanWrite(
                            ((JsonPrimitive)get(json, key, JsonPrimitive.class)).getAsBoolean());
                    break;
                case "media_types":
                    collection.setMediaTypes(
                            context.deserialize(((JsonArray)get(json, key, JsonArray.class)), new TypeToken<List<String>>(){}.getType()));
                    break;
                default:
                    collection.withCustomProperty(key, json.get(key));
                    break;
            }
        }

        return collection;
    }
}
