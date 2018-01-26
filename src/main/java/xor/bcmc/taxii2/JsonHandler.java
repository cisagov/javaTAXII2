package xor.bcmc.taxii2;

import com.google.gson.*;
import xor.bcmc.taxii2.resources.TaxiiResource;

import java.lang.reflect.Type;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

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
        builder
            .registerTypeAdapter(ZonedDateTime.class, new JsonDeserializer<ZonedDateTime>() {
                @Override
                public ZonedDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                    return ZonedDateTime.parse(json.getAsString());
                }
            })
            .registerTypeAdapter(ZonedDateTime.class, new JsonSerializer<ZonedDateTime>() {
                @Override
                public JsonElement serialize(ZonedDateTime src, Type typeOfSrc, JsonSerializationContext context) {
                    ZonedDateTime date = src.withZoneSameInstant(ZoneId.of("Z"));
                    return new JsonPrimitive(date.toString());
                }
            })
            .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                @Override
                public Date deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                    ZonedDateTime date = ZonedDateTime.parse(json.getAsString());
                    return Date.from(date.toInstant());
                }
            })
            .registerTypeAdapter(Date.class, new JsonSerializer<Date>() {
                @Override
                public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
                    ZonedDateTime date = ZonedDateTime.ofInstant(src.toInstant(), ZoneId.of("Z"));
                    return new JsonPrimitive(date.toString());
                }
            });

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

}
