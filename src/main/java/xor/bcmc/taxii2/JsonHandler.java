package xor.bcmc.taxii2;

import com.google.gson.*;
import xor.bcmc.taxii2.resources.TaxiiResource;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class JsonHandler {

    private static JsonHandler instance;
    private static Gson gson;

    private JsonHandler() {
        // Configure TAXII serialization and deserialization
        GsonBuilder builder = new GsonBuilder();
        builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        builder.setDateFormat("YYYY-MM-DD'T'HH:mm:ss[.s+]Z");
        //From: https://github.com/gkopff/gson-javatime-serialisers
        builder.registerTypeAdapter(ZonedDateTime.class, new JsonDeserializer<ZonedDateTime>() {
            @Override
            public ZonedDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                return DateTimeFormatter.ISO_DATE_TIME.parse(json.getAsString(), ZonedDateTime::from);
            }
        })
        .registerTypeAdapter(ZonedDateTime.class, new JsonSerializer<ZonedDateTime>() {
            @Override
            public JsonElement serialize(ZonedDateTime src, Type typeOfSrc, JsonSerializationContext context)
            {
                return new JsonPrimitive(DateTimeFormatter.ISO_DATE_TIME.format(src));
            }
        });
        gson = builder.create();
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
