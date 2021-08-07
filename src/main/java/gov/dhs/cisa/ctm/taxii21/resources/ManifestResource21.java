package gov.dhs.cisa.ctm.taxii21.resources;

import com.google.gson.annotations.Expose;

import gov.dhs.cisa.ctm.taxii2.resources.TaxiiResource;
import gov.dhs.cisa.ctm.taxii2.validation.Errors;

import java.util.ArrayList;
import java.util.List;

/**
 * The only difference between this TAXII 2.1 resource and the TAXII 2.0
 * resource is that the manifest-entry resource changed, requiring a new class.
 */
public class ManifestResource21 extends TaxiiResource {

    @Expose
    private List<ManifestEntry21> objects;

    public ManifestResource21(){
    }

    public ManifestResource21(List<ManifestEntry21> objects) {
        this.objects = objects;
    }

    public ManifestResource21 withObject(ManifestEntry21 object)
    {
        if (objects == null)
            objects = new ArrayList<>();

        objects.add( object );
        return this;
    }

    // Getters
    public List<ManifestEntry21> getObjects() {
        return objects;
    }

    public void setObjects(List<ManifestEntry21> objects) {
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

        ManifestResource21 that = (ManifestResource21) o;

        return objects != null ? objects.equals(that.objects) : that.objects == null;
    }

    @Override
    public int hashCode() {
        return objects != null ? objects.hashCode() : 0;
    }
}
