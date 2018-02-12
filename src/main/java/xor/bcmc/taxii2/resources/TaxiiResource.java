package xor.bcmc.taxii2.resources;

import com.google.gson.JsonElement;
import xor.bcmc.taxii2.JsonHandler;
import xor.bcmc.taxii2.validation.Validatable;

import java.io.Serializable;

public abstract class TaxiiResource implements Serializable, Validatable {

    public String toJson() {
        return JsonHandler.getInstance().toJson(this);
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
