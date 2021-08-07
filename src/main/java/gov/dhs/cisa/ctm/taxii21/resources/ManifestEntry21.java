package gov.dhs.cisa.ctm.taxii21.resources;

import com.google.gson.annotations.Expose;

import gov.dhs.cisa.ctm.taxii2.Identifiable;
import gov.dhs.cisa.ctm.taxii2.resources.TaxiiResource;
import gov.dhs.cisa.ctm.taxii2.validation.Errors;

import java.time.ZonedDateTime;

/**
 * The TAXII 2.1 version of a manifest-entry
 * The only distinction is that this captures the metadata of a single version
 * of an object.
 * Whereas TAXII 2.0 manifest-entry includes which versions of the object are
 * available and what media types the object is available in.
 */
public class ManifestEntry21 extends TaxiiResource implements Identifiable<String> {
    @Expose
    private String id;
    @Expose
    private ZonedDateTime date_added;    // Collection RecToObjectRec
    @Expose
    private String version;
    @Expose
    private String mediaType;


    public ManifestEntry21(){
    }

    public ManifestEntry21(String id, ZonedDateTime date_added, String mediaType, String version) {
        this.id = id;
        this.date_added = date_added;
        this.mediaType = mediaType;
        this.version = version;
    }


    // Getters
    public String getId(){
        return this.id;
    }

    public ZonedDateTime getDateAdded()
    {
        return this.date_added;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getVersion() {
        return version;
    }

    // Setters
    public void setId(String id){
        this.id = id;
    }

    @Override
    public Identifiable<String> withId(String id) {
        this.id = id;
        return this;
    }

    public void setDateAdded(ZonedDateTime date)
    {
        this.date_added = date;
    }

    public void setMediaType(String mediaType)
    {
        this.mediaType = mediaType;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public Errors validate() {
        Errors errors = new Errors();
        errors.rejectIfNullOrEmpty("id", this.id);
        return errors;
    }

    @Override
    public boolean isValid() {
        return validate().isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ManifestEntry21 that = (ManifestEntry21) o;

        if (!id.equals(that.id)) return false;
        if (date_added != null ? !date_added.isEqual(that.date_added) : that.date_added != null) return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;
        return mediaType != null ? mediaType.equals(that.mediaType) : that.mediaType == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (date_added != null ? date_added.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (mediaType != null ? mediaType.hashCode() : 0);
        return result;
    }
}