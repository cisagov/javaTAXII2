package xor.bcmc.taxii2.resources;

import com.google.gson.annotations.Expose;
import xor.bcmc.taxii2.validation.Errors;

import java.util.ArrayList;
import java.util.List;

public class ManifestResource extends TaxiiResource {

    private List<ManifestEntry> objects;

    public ManifestResource(){
    }

    public ManifestResource(List<ManifestEntry> objects) {
        this.objects = objects;
    }

    public ManifestResource withObject( ManifestEntry object)
    {
        if (objects == null)
            objects = new ArrayList<>();

        objects.add( object );
        return this;
    }

    // Getters
    public List<ManifestEntry> getObjects() {
        return objects;
    }

    public void setObjects(List<ManifestEntry> objects) {
        this.objects = objects;
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

        ManifestResource that = (ManifestResource) o;

        return objects != null ? objects.equals(that.objects) : that.objects == null;
    }

    @Override
    public int hashCode() {
        return objects != null ? objects.hashCode() : 0;
    }
}

