package xor.bcmc.taxii2.resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import xor.bcmc.taxii2.Identifiable;
import xor.bcmc.taxii2.JsonHandler;
import xor.bcmc.taxii2.validation.Errors;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Collection extends TaxiiResource implements Identifiable<String> {
    public static final String RESOURCE_TYPE = "Collection";

    @Expose
    private String id;

    @Expose
    private String title;

    @Expose
    private String description;

    @Expose
    @SerializedName("can_read")
    private Boolean canRead;

    @Expose
    @SerializedName("can_write")
    private Boolean canWrite;

    @Expose
    private List<String> mediaTypes = new ArrayList<>();

    public Collection() {
    }

    public Collection(Collection collection) {
        this.id = collection.getId();
        this.title = collection.getTitle();
        this.canRead = collection.getCanRead();
        this.canWrite = collection.getCanWrite();
        this.mediaTypes = collection.getMediaTypes();
        this.description = collection.getDescription();
    }

    public Collection(String id, String title, boolean canRead, boolean canWrite) {
        this.id = id;
        this.title = title;
        this.canRead = canRead;
        this.canWrite = canWrite;
    }

    public static Collection fromJson(String json) {
        return JsonHandler.getInstance().fromJson(json, Collection.class);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Boolean canRead() {
        return canRead;
    }

    public Boolean canWrite() {
        return canWrite;
    }

    public Boolean isCanRead() {
        return canRead;
    }

    public Boolean isCanWrite() {
        return canWrite;
    }

    public Boolean getCanRead() {
        return canRead;
    }

    public Boolean getCanWrite() {
        return canWrite;
    }

    public List<String> getMediaTypes() {
        return mediaTypes;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCanRead(Boolean canRead) {
        this.canRead = canRead;
    }

    public void setCanWrite(Boolean canWrite) {
        this.canWrite = canWrite;
    }

    public void setMediaTypes(List<String> mediaTypes) {
        this.mediaTypes = mediaTypes;
    }

    public Collection withId(String id) {
        this.id = id;
        return this;
    }

    public Collection withTitle(String title) {
        this.title = title;
        return this;
    }

    public Collection withDescription(String description) {
        this.description = description;
        return this;
    }

    public Collection withCanRead(Boolean canRead) {
        this.canRead = canRead;
        return this;
    }

    public Collection withCanWrite(Boolean canWrite) {
        this.canWrite = canWrite;
        return this;
    }

    public Collection withMediaTypes(List<String> mediaTypes) {
        this.mediaTypes = mediaTypes;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collection that = (Collection) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(canRead, that.canRead) &&
                Objects.equals(canWrite, that.canWrite) &&
                Objects.equals(mediaTypes, that.mediaTypes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, canRead, canWrite, mediaTypes);
    }

    @Override
    public Errors validate() {
        Errors errors = new Errors();
        errors.rejectIfNullOrEmpty("id", this.id);
        errors.rejectIfNullOrEmpty("title", this.title);
        return errors;
    }

    @Override
    public boolean isValid() {
        return validate().isEmpty();
    }
}
