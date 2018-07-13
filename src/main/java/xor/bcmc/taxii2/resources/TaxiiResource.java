package xor.bcmc.taxii2.resources;

import com.google.gson.JsonElement;
import xor.bcmc.taxii2.JsonHandler;
import xor.bcmc.taxii2.validation.Validatable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class TaxiiResource implements Serializable, Validatable {
    protected Map<String, JsonElement> customProperties;

    public TaxiiResource withCustomProperty(String field, JsonElement value) {
        if (customProperties == null) {
            customProperties = new HashMap<>();
        }
        this.customProperties.put(field, value);
        return this;
    }

    public Map<String, JsonElement> getCustomProperties() {
        return customProperties;
    }

    public String toJson() {
        return JsonHandler.getInstance().getGson().toJson(this);
    }

    public String toString() {
        return this.toJson();
    }

    public JsonElement toJsonElement() {
        return JsonHandler.getInstance().toJsonElement(this);
    }

    /*

        It is strongly recommended that subclasses implement
        public static Subclass fromJson(String json) {
            return JsonHandler.getInstance().fromJson(json, Subclass.class);
        }

     */
}
