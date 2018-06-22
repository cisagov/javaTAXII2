package xor.bcmc.taxii2.resources;

import com.google.gson.annotations.Expose;
import xor.bcmc.taxii2.Identifiable;
import xor.bcmc.taxii2.validation.Errors;

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
    private List<String> mediaTypes = new ArrayList<>();


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
        this.mediaTypes.addAll(mediaTypes);
        return this;
    }

    public ManifestEntry withMediaType(String mediaType)
    {
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
}

