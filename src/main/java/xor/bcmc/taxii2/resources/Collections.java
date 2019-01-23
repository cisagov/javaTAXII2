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
        return new Errors();
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Collections that = (Collections) o;

        return collections != null ? equal(this.collections, that.collections) : that.collections == null;
    }

    private boolean equal(List<Collection> thisCollections, List<Collection> thatCollections){
        if ((thisCollections == null && thatCollections != null) || (thisCollections != null && thatCollections == null))
            return false; //One is null and the other is not

        if (thisCollections == null)
            return true; //both collections are null

        for (Collection collection : thisCollections){
            boolean found = false;
            for (Collection collection_ : thatCollections){
                if (collection.getId().equals(collection_.getId())){
                    found = true;
                    break;
                }
            }
            if (!found)
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return collections != null ? collections.hashCode() : 0;
    }
}
