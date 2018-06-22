package xor.bcmc.taxii2.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class DeserializerUtil {
    public static Object get(JsonObject json, String field, Class type) throws JsonParseException {
        JsonElement e = json.get(field);
        if (e == null)
            throw new JsonParseException("Missing field " + field);
        if (!e.getClass().equals(type))
            throw new JsonParseException("Field value is " + e.getClass() + " but expected " + type);
        return e;
    }
}
