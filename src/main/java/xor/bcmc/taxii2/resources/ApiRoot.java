package xor.bcmc.taxii2.resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import xor.bcmc.taxii2.Constants;
import xor.bcmc.taxii2.Identifiable;
import xor.bcmc.taxii2.JsonHandler;
import xor.bcmc.taxii2.validation.Errors;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <a href="https://www.oasis-open.org/committees/download.php/59353/TAXII2.0Specification-draft3.pdf">ApiRoot Resource</a>
 */
public class ApiRoot extends TaxiiResource implements Identifiable<String> {

    @Expose(serialize = false)
    private String id;

    @Expose
    private String title;

    @Expose
    private String description;

    @Expose
    private List<String> versions;

    @Expose
    private int maxContentLength;

    // TODO TAXII 2.1
    // private List<Channel> channels;
    // private List<Collection> collections;

    /**
     * Construct an ApiRoot resource
     *
     * @param title A human readable text/plain name used to identify this API instance. This is not the name of
     *                    this API Root that is found in the URL.
     * @param versions Lists the versions of TAXII that this API Root is compatible with. taxii-2.0 MUST be included in
     *                 this list to indicate conformance with this specification.
     */
    public ApiRoot(String title, List<String> versions) {
        this.title = title;
        this.versions = versions;
    }

    public ApiRoot() {
    }

    public static ApiRoot fromJson(String json) {
        return JsonHandler.getInstance().fromJson(json, ApiRoot.class);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getVersions() {
        return versions;
    }

    public int getMaxContentLength() {
        return maxContentLength;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVersions(List<String> versions) {
        this.versions = versions;
    }

    public void setMaxContentLength(int maxContentLength) {
        this.maxContentLength = maxContentLength;
    }

    public ApiRoot withTitle(String title) {
        this.title = title;
        return this;
    }

    public ApiRoot withDescription(String description) {
        this.description = description;
        return this;
    }

    public ApiRoot withVersions(List<String> versions) {
        this.versions = versions;
        return this;
    }

    public ApiRoot withMaxContentLength(int maxContentLength) {
        this.maxContentLength = maxContentLength;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiRoot apiRoot = (ApiRoot) o;
        return maxContentLength == apiRoot.maxContentLength &&
                Objects.equals(id, apiRoot.id) &&
                Objects.equals(title, apiRoot.title) &&
                Objects.equals(description, apiRoot.description) &&
                Objects.equals(versions, apiRoot.versions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, versions, maxContentLength);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ApiRoot withId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public Errors validate() {
        Errors errors = new Errors();
        errors.rejectIfNullOrEmpty("title", this.title);
        errors.rejectIfNullOrEmpty("versions", this.versions);
        errors.rejectIfNotContains("versions", this.versions, Constants.TAXII_20);
        return errors;
    }

    @Override
    public boolean isValid() {
        return validate().isEmpty();
    }
}
