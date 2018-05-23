package xor.bcmc.taxii2.resources;

import com.google.gson.annotations.Expose;
import xor.bcmc.taxii2.validation.Errors;

import java.util.ArrayList;
import java.util.List;

public class ManifestResource extends TaxiiResource {

    @Expose
    private List<ManifestEntry> objects;

    public ManifestResource(){
    }

    public ManifestResource(List<ManifestEntry> objects) {
        this.objects = objects;
    }

    public void addObject( ManifestEntry object)
    {
        if ( objects == null )
        {
            objects = new ArrayList<ManifestEntry>();
        }
        objects.add( object );
    }

    // Getters
    public List<ManifestEntry> getObjects() {
        return objects;
    }

    public void setMediaTypes(List<ManifestEntry> objects) {
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

