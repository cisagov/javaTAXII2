package xor.bcmc.taxii2.gson;

import com.google.common.base.CaseFormat;
import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import xor.bcmc.taxii2.resources.TaxiiResource;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class TaxiiResourceDeserializer implements JsonDeserializer {
    protected static <T extends TaxiiResource> T build (JsonElement jsonElement, T emptyObject, JsonDeserializationContext context)
            throws JsonParseException {

        // All TaxiiResources are JsonObjects
        if(!jsonElement.isJsonObject())
            throw new JsonParseException(emptyObject.getClass().getSimpleName() + " must be JsonObject");


        // Collect this objects fields into an easily
        // accessible map
        Map<String, Field> fieldMap = new HashMap<>();

        for (Field field : emptyObject.getClass().getDeclaredFields()){
            SerializedName annotation = field.getAnnotation(SerializedName.class);
            if (annotation != null){
                fieldMap.put(annotation.value(), field);
            } else {
                String jsonField = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName());
                fieldMap.put(jsonField, field);
            }
        }

        for (Field field : emptyObject.getTaxiiResourceFields()){
            SerializedName annotation = field.getAnnotation(SerializedName.class);
            if (annotation != null){
                fieldMap.put(annotation.value(), field);
            } else {
                String jsonField = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName());
                fieldMap.put(jsonField, field);
            }
        }


        // Iterate through the fields in the json and
        // assign their values to the provided TaxiiResource
        // object, "emptyObject"
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        for (String key : jsonObject.keySet()) {
            JsonElement jsonValue = jsonObject.get(key);

            Field field = fieldMap.get(key);
            if (field != null) {
                // If the field exists in the object, deserialize the
                // value and save it to the field
                field.setAccessible(true);
                try {
                    field.set(emptyObject,
                            context.deserialize(jsonValue, field.getType()));
                } catch (IllegalAccessException e) {
                    throw new JsonParseException("Error accessing field='" + field.getName() + "' of " + emptyObject.getClass().getSimpleName() + ": " + e);
                }

            } else {
                // If the field doesn't exist in the object, save it
                // to the customProperties field.
                Field customPropertiesField = fieldMap.get("custom_properties");
                customPropertiesField.setAccessible(true);
                try {
                    ((Map<String, JsonElement>)customPropertiesField.get(emptyObject))
                            .put(key, jsonValue);
                } catch (IllegalAccessException e) {
                    throw new JsonParseException("Error accessing field='" + customPropertiesField.getName() + "' of " + emptyObject.getClass().getSimpleName() + ": " + e);
                }

            }
        }
        return emptyObject;
    }
}
