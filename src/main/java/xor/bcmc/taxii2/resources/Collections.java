package xor.bcmc.taxii2.resources;

import com.google.gson.annotations.Expose;
import xor.bcmc.taxii2.validation.Errors;

import java.util.List;

public class Collections extends TaxiiResource {

    @Expose
    private List<Collection> collections;

    public Collections() {
    }

    public Collections(List<Collection> collections) {
        this.collections = collections;
    }

    public List<Collection> getCollections() {
        return collections;
    }

    public void setCollections(List<Collection> collections) {
        this.collections = collections;
    }

    @Override
    public Errors validate() {
        return null;
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
