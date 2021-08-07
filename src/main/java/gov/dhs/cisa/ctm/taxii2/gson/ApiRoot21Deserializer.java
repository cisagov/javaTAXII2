package gov.dhs.cisa.ctm.taxii2.gson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import gov.dhs.cisa.ctm.taxii21.resources.ApiRoot21;

import static gov.dhs.cisa.ctm.taxii2.gson.DeserializerUtil.get;

import java.lang.reflect.Type;
import java.util.List;

public class ApiRoot21Deserializer implements JsonDeserializer<ApiRoot21> {
    @Override
    public ApiRoot21 deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        if (!jsonElement.isJsonObject())
            throw new JsonParseException(ApiRoot21.class.getSimpleName() + " must be JsonObject");

        ApiRoot21 apiRoot = new ApiRoot21();
        JsonObject json = jsonElement.getAsJsonObject();

        for (String key : json.keySet()) {
            switch (key){
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
}
