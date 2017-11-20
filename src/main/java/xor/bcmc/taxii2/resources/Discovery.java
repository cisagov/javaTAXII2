package xor.bcmc.taxii2.resources;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Discovery extends TaxiiResource {

    private String displayName;
    private String description;
    private String contact;

    @SerializedName("default")
    private String defaultApiRoot;

    private List<String> apiRoots;

    public Discovery(String displayName, List<String> apiRoots) {
        this.displayName = displayName;
        this.apiRoots = apiRoots;
    }

    public Discovery() {
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public String getContact() {
        return contact;
    }

    public String getDefaultApiRoot() {
        return defaultApiRoot;
    }

    public List<String> getApiRoots() {
        return apiRoots;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setDefaultApiRoot(String defaultApiRoot) {
        this.defaultApiRoot = defaultApiRoot;
    }

    public void setApiRoots(List<String> apiRoots) {
        this.apiRoots = apiRoots;
    }

    public Discovery withDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public Discovery withDescription(String description) {
        this.description = description;
        return this;
    }

    public Discovery withContact(String contact) {
        this.contact = contact;
        return this;
    }

    public Discovery withDefaultApiRoot(String defaultApiRoot) {
        this.defaultApiRoot = defaultApiRoot;
        return this;
    }

    public Discovery withApiRoots(List<String> apiRoots) {
        this.apiRoots = apiRoots;
        return this;
    }

    public <T extends TaxiiResource> T fromJson(String json) {
        return (T) super.fromJson(json, this.getClass());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discovery discovery = (Discovery) o;

        if (!displayName.equals(discovery.displayName)) return false;
        if (description != null ? !description.equals(discovery.description) : discovery.description != null)
            return false;
        if (contact != null ? !contact.equals(discovery.contact) : discovery.contact != null) return false;
        if (defaultApiRoot != null ? !defaultApiRoot.equals(discovery.defaultApiRoot) : discovery.defaultApiRoot != null)
            return false;
        return apiRoots.equals(discovery.apiRoots);
    }

    @Override
    public int hashCode() {
        int result = displayName.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        result = 31 * result + (defaultApiRoot != null ? defaultApiRoot.hashCode() : 0);
        result = 31 * result + apiRoots.hashCode();
        return result;
    }
}
