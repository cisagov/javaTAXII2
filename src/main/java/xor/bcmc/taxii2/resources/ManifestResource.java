package xor.bcmc.taxii2.resources;

import com.google.gson.annotations.Expose;
import xor.bcmc.taxii2.validation.Errors;

import java.util.ArrayList;
import java.util.List;

public class ManifestResource extends TaxiiResource {

    @Expose
    private List<ManifestEntry> objects = new ArrayList<>();

    public ManifestResource(){
    }

    public ManifestResource(List<ManifestEntry> objects) {
        this.objects = objects;
    }

    public ManifestResource withObject( ManifestEntry object)
    {
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
}

