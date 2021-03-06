package gov.dhs.cisa.ctm.taxii2.resources;

import com.google.gson.JsonElement;

import gov.dhs.cisa.ctm.taxii2.JsonHandler;
import gov.dhs.cisa.ctm.taxii2.validation.Validatable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class TaxiiResource implements Serializable, Validatable {
    protected Map<String, JsonElement> customProperties = new HashMap<>();

    public TaxiiResource withCustomProperty(String field, JsonElement value) {
        this.customProperties.put(field, value);
        return this;
    }

    public Map<String, JsonElement> getCustomProperties() {
        return customProperties;
    }

    public String toJson() {
        return JsonHandler.gson.toJson(this);
    }

    public String toString() {
        return this.toJson();
    }

    public JsonElement toJsonElement() {
        return JsonHandler.gson.toJsonTree(this);
    }

    /*

        It is strongly recommended that subclasses implement
        public static Subclass fromJson(String json) {
            return JsonHandler.gson.fromJson(json, Subclass.class);
        }

     */
}
