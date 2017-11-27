package xor.bcmc.taxii2.resources;

import xor.bcmc.taxii2.Identifiable;

import java.util.List;

/**
 * <a href="https://www.oasis-open.org/committees/download.php/59353/TAXII2.0Specification-draft3.pdf">ApiRoot Resource</a>
 */
public class ApiRoot extends TaxiiResource implements Identifiable<String> {

    private String title;
    private String description;
    private List<String> versions;
    // TODO TAXII 2.1
    // private List<Channel> channels;
    // private List<Collection> collections;
    private int maxContentLength = 0;

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

    public <T extends TaxiiResource> T fromJson(String json) {
        return (T) super.fromJson(json, this.getClass());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiRoot apiRoot = (ApiRoot) o;

        if (maxContentLength != apiRoot.maxContentLength) return false;
        if (!title.equals(apiRoot.title)) return false;
        if (description != null ? !description.equals(apiRoot.description) : apiRoot.description != null) return false;
        return versions.equals(apiRoot.versions);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + versions.hashCode();
        result = 31 * result + maxContentLength;
        return result;
    }

    public String getId() {
        return this.getTitle();
    }

    public void setId(String id) {
        this.setTitle(id);
    }

}
