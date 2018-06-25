package xor.bcmc.taxii2.gson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import xor.bcmc.taxii2.resources.Collection;
import xor.bcmc.taxii2.resources.Discovery;

import java.lang.reflect.Type;
import java.util.List;

import static xor.bcmc.taxii2.gson.DeserializerUtil.get;

public class DiscoveryDeserializer implements JsonDeserializer<Discovery> {
    @Override
    public Discovery deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        if (!jsonElement.isJsonObject())
            throw new JsonParseException(Discovery.class.getSimpleName() + " must be JsonObject");

        Discovery discovery = new Discovery();
        JsonObject json = jsonElement.getAsJsonObject();

        for (String key : json.keySet()) {
            switch (key){
                case "title":
                    discovery.setTitle(
                            ((JsonPrimitive)get(json, key, JsonPrimitive.class)).getAsString());
                    break;
                case "description":
                    discovery.setDescription(
                            ((JsonPrimitive)get(json, key, JsonPrimitive.class)).getAsString());
                    break;
                case "contact":
                    discovery.setContact(
                            ((JsonPrimitive)get(json, key, JsonPrimitive.class)).getAsString());
                    break;
                case "default":
                    discovery.setDefaultApiRoot(
                            ((JsonPrimitive)get(json, key, JsonPrimitive.class)).getAsString());
                    break;
                case "api_roots":
                    discovery.setApiRoots(
                            context.deserialize(((JsonArray)get(json, key, JsonArray.class)), new TypeToken<List<String>>(){}.getType()));
                    break;
                default:
                    discovery.withCustomProperty(key, json.get(key));
                    break;
            }
        }

        return discovery;
    }
}
