package xor.bcmc.taxii2.gson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import xor.bcmc.taxii2.resources.ManifestEntry;
import xor.bcmc.taxii2.resources.ManifestResource;
import xor.bcmc.taxii2.resources.StatusFailure;
import xor.bcmc.taxii2.resources.StatusResource;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.util.List;

import static xor.bcmc.taxii2.gson.DeserializerUtil.get;

public class StatusResourceDeserializer implements JsonDeserializer<StatusResource> {
    @Override
    public StatusResource deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        if (!jsonElement.isJsonObject())
            throw new JsonParseException(StatusResource.class.getSimpleName() + " must be JsonObject");

        StatusResource statusResource = new StatusResource();
        JsonObject json = jsonElement.getAsJsonObject();

        for (String key : json.keySet()) {
            switch (key){
                case "id":
                    statusResource.setId(
                            ((JsonPrimitive)get(json, key, JsonPrimitive.class)).getAsString());
                    break;
                case "status":
                    statusResource.setStatus(
                            StatusResource.StatusEnum.valueOf(((JsonPrimitive)get(json, key, JsonPrimitive.class)).getAsString().toUpperCase()));
                    break;
                case "request_timestamp":
                    statusResource.setRequestTimestamp(
                            context.deserialize(((JsonPrimitive)get(json, key, JsonPrimitive.class)), new TypeToken<ZonedDateTime>(){}.getType()));
                    break;
                case "total_count":
                    statusResource.setTotalCount(
                            ((JsonPrimitive)get(json, key, JsonPrimitive.class)).getAsInt());
                    break;
                case "success_count":
                    statusResource.setSuccessCount(
                            ((JsonPrimitive)get(json, key, JsonPrimitive.class)).getAsInt());
                    break;
                case "successes":
                    statusResource.setSuccesses(
                            context.deserialize(((JsonArray)get(json, key, JsonArray.class)), new TypeToken<List<String>>(){}.getType()));
                    break;
                case "failure_count":
                    statusResource.setFailureCount(
                            ((JsonPrimitive)get(json, key, JsonPrimitive.class)).getAsInt());
                    break;
                case "failures":
                    statusResource.setFailures(
                            context.deserialize(((JsonArray)get(json, key, JsonArray.class)), new TypeToken<List<StatusFailure>>(){}.getType()));
                    break;
                case "pending_count":
                    statusResource.setPendingCount(
                            ((JsonPrimitive)get(json, key, JsonPrimitive.class)).getAsInt());
                    break;
                case "pendings":
                    statusResource.setPendings(
                            context.deserialize(((JsonArray)get(json, key, JsonArray.class)), new TypeToken<List<String>>(){}.getType()));
                    break;
                default:
                    statusResource.withCustomProperty(key, json.get(key));
                    break;
            }
        }

        return statusResource;
    }
}
