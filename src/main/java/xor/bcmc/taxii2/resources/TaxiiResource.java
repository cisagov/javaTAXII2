package xor.bcmc.taxii2.resources;

import xor.bcmc.taxii2.JsonHandler;

public abstract class TaxiiResource {

    public String toJson() {
        return JsonHandler.getInstance().toJson(this);
    }

    public abstract <T extends TaxiiResource> T fromJson(String json);

    public <T extends TaxiiResource> T fromJson(String json, Class<T> type) {
        return JsonHandler.getInstance().fromJson(json, type);
    }
}
