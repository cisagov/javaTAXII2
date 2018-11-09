package xor.bcmc.taxii2;

import com.google.gson.*;
import xor.bcmc.taxii2.gson.*;
import xor.bcmc.taxii2.resources.*;
import xor.bcmc.taxii21.gson.*;
import xor.bcmc.taxii21.resources.*;

import java.lang.reflect.Type;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public class JsonHandler {

    private static JsonHandler instance;
    private static Gson gson;

    private JsonHandler() {
        // Configure TAXII serialization and deserialization
        GsonBuilder builder = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .excludeFieldsWithoutExposeAnnotation();

        //From: https://github.com/gkopff/gson-javatime-serialisers
        builder.registerTypeAdapter(Date.class, new DateAdapter())
                .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeTypeAdapter())
                .registerTypeAdapter(List.class, new ListAdapter())

                .registerTypeAdapter(ApiRoot.class, new ApiRootDeserializer())
                .registerTypeAdapter(ApiRoot21.class, new ApiRoot21Deserializer())
                .registerTypeAdapter(Collection.class, new CollectionDeserializer())
                .registerTypeAdapter(Collections.class, new CollectionsDeserializer())
                .registerTypeAdapter(Discovery.class, new DiscoveryDeserializer())
                .registerTypeAdapter(ManifestEntry21.class, new ManifestEntry21Deserializer())
                .registerTypeAdapter(ManifestResource21.class, new ManifestResource21Deserializer())
                .registerTypeAdapter(ManifestEntry.class, new ManifestEntryDeserializer())
                .registerTypeAdapter(ManifestResource.class, new ManifestResourceDeserializer())
                .registerTypeAdapter(StatusResource.class, new StatusResourceDeserializer())
                .registerTypeAdapter(StatusResource.class, new StatusResource21Deserializer());

        gson = builder.create();
    }

    public Gson getGson() {
        return gson;
    }

    public static JsonHandler getInstance() {
        if (instance == null) {
            synchronized (JsonHandler.class) {
                if (instance == null) {
                    instance = new JsonHandler();
                }
            }
        }
        return instance;
    }

    public String toJson(Object object) {
        return gson.toJson(object);
    }

    public <T extends TaxiiResource> T fromJson(String json, Class<T> type) {
        return gson.fromJson(json, type);
    }

    public <T extends TaxiiResource> JsonElement toJsonElement(T taxiiResource) {
        return gson.toJsonTree(taxiiResource);
    }

    /**
     * According to TAXII 2 spec, Section 2 Data Types: empty lists are prohibited
     */
    class ListAdapter implements JsonSerializer<List<?>> {
        @Override
        public JsonElement serialize(List<?> src, Type typeOfSrc, JsonSerializationContext context) {
            if (src == null || src.isEmpty()) // exclusion is made here. Null objects aren't serialized
                return null;

            JsonArray array = new JsonArray(src.size()); //This will throw an exception for GSON 2.4. Update to 2.8.2
            for (Object child : src) {
                array.add(context.serialize(child));
            }
            return array;
        }
    }

    /**
     * Adapter for Serializing/Deserializing Date objects to/from JSON
     */
    private static class DateAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {
        @Override
        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
            ZonedDateTime date = ZonedDateTime.ofInstant(src.toInstant(), ZoneId.of("Z"));
            return new JsonPrimitive(date.toString());
        }

        @Override
        public Date deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            ZonedDateTime date = ZonedDateTime.parse(json.getAsString());
            return Date.from(date.toInstant());
        }
    }

    /**
     * Adapter for Serializing/Deserializing ZonedDateTime objects to/from JSON
     */
    private static class ZonedDateTimeTypeAdapter implements JsonSerializer<ZonedDateTime>, JsonDeserializer<ZonedDateTime> {
        @Override
        public ZonedDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return ZonedDateTime.parse(json.getAsString());
        }

        @Override
        public JsonElement serialize(ZonedDateTime src, Type type, JsonSerializationContext jsonSerializationContext) {
            ZonedDateTime date = src.withZoneSameInstant(ZoneId.of("Z"));
            return new JsonPrimitive(date.toString());
        }
    }
}
