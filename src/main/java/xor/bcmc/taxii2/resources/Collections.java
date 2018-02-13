package xor.bcmc.taxii2.resources;

import java.util.List;

public class Collections {
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
}
