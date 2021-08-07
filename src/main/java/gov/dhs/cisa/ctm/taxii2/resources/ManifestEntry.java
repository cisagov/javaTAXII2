package gov.dhs.cisa.ctm.taxii2.resources;

import com.google.gson.annotations.Expose;

import gov.dhs.cisa.ctm.taxii2.Identifiable;
import gov.dhs.cisa.ctm.taxii2.validation.Errors;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class ManifestEntry extends TaxiiResource implements Identifiable<String> {
    @Expose
    private String id;
    @Expose
    private ZonedDateTime date_added;    // Collection RecToObjectRec
    @Expose
    private List<String> versions;
    @Expose
    private List<String> mediaTypes;


    public ManifestEntry(){
    }

    public ManifestEntry(String id, ZonedDateTime date_added, List<String> mediaTypes, List<String> versions) {
        this.id = id;
        this.date_added = date_added;
        this.mediaTypes = mediaTypes;
        this.versions = versions;
    }

    public ManifestEntry(String id, ZonedDateTime date_added, String mediaType, List<String> versions) {
        this.id = id;
        this.date_added = date_added;
        this.withMediaType( mediaType);
        this.versions = versions;
    }


    // Getters
    public String getId(){
        return this.id;
    }

    public ZonedDateTime getDateAdded()
    {
        return this.date_added;
    }

    public List<String> getMediaTypes() {
        return mediaTypes;
    }

    public List<String> getVersions() {
        return versions;
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

    public ManifestEntry withMediaTypes(List<String> mediaTypes)
    {
        if (this.mediaTypes == null)
            this.mediaTypes = new ArrayList<>();

        this.mediaTypes.addAll(mediaTypes);
        return this;
    }

    public ManifestEntry withMediaType(String mediaType)
    {
        if (mediaTypes == null)
            mediaTypes = new ArrayList<>();

        this.mediaTypes.add( mediaType );
        return this;
    }

    public void setVersions(List<String> versions) {
        this.versions = versions;
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

        ManifestEntry that = (ManifestEntry) o;

        if (!id.equals(that.id)) return false;
        if (date_added != null ? !date_added.isEqual(that.date_added) : that.date_added != null) return false;
        if (versions != null ? !versions.equals(that.versions) : that.versions != null) return false;
        return mediaTypes != null ? mediaTypes.equals(that.mediaTypes) : that.mediaTypes == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (date_added != null ? date_added.hashCode() : 0);
        result = 31 * result + (versions != null ? versions.hashCode() : 0);
        result = 31 * result + (mediaTypes != null ? mediaTypes.hashCode() : 0);
        return result;
    }
}

