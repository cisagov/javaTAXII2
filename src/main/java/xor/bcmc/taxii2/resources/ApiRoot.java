package xor.bcmc.taxii2.resources;

import com.google.gson.annotations.Expose;
import xor.bcmc.taxii2.Identifiable;
import xor.bcmc.taxii2.JsonHandler;

import java.util.List;

/**
 * <a href="https://www.oasis-open.org/committees/download.php/59353/TAXII2.0Specification-draft3.pdf">ApiRoot Resource</a>
 */
public class ApiRoot extends TaxiiResource implements Identifiable<String> {
    public static final String TAXII_VERSION_20 = " taxii-2.0";

    @Expose
    private String title;

    @Expose
    private String description;

    @Expose
    private List<String> versions;

    @Expose
    private int max_content_length = 0;

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

    public int getMax_content_length() {
        return max_content_length;
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

    public void setMax_content_length(int max_content_length) {
        this.max_content_length = max_content_length;
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
        this.max_content_length = maxContentLength;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiRoot apiRoot = (ApiRoot) o;

        if (max_content_length != apiRoot.max_content_length) return false;
        if (!title.equals(apiRoot.title)) return false;
        if (description != null ? !description.equals(apiRoot.description) : apiRoot.description != null) return false;
        return versions.equals(apiRoot.versions);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + versions.hashCode();
        result = 31 * result + max_content_length;
        return result;
    }

    public String getId() {
        return this.getTitle();
    }

    public void setId(String id) {
        this.setTitle(id);
    }

}
