package xor.bcmc.taxii2.resources;

import com.google.gson.JsonElement;
import xor.bcmc.taxii2.JsonHandler;

public abstract class TaxiiResource {

    public String toJson() {
        return JsonHandler.getInstance().toJson(this);
    }

    public String toString() {
        return this.toJson();
    }

    public abstract <T extends TaxiiResource> T fromJson(String json);

    //TODO This should be static. Right now it requires making an instance object that this method will then make another instance of
    public <T extends TaxiiResource> T fromJson(String json, Class<T> type) {
        return JsonHandler.getInstance().fromJson(json, type);
    }

    public JsonElement toJsonElement() {
        return JsonHandler.getInstance().toJsonElement(this);
    }
}
