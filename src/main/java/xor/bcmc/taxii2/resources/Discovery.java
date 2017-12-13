package xor.bcmc.taxii2.resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import xor.bcmc.taxii2.Identifiable;
import xor.bcmc.taxii2.JsonHandler;

import java.util.ArrayList;
import java.util.List;

public class Discovery extends TaxiiResource implements Identifiable<String> {

    @Expose
    private String title;

    @Expose
    private String description;

    @Expose
    private String contact;

    @Expose
    @SerializedName("default")
    private String defaultApiRoot;

    @Expose
    private List<String> apiRoots = new ArrayList<String>();

    public Discovery(String title, List<String> apiRoots) {
        this.title = title;
        this.apiRoots = apiRoots;
    }

    public Discovery() {
    }

    public static Discovery fromJson(String json) {
        return JsonHandler.getInstance().fromJson(json, Discovery.class);
    }

    public String getTitle() {
        return title;
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

    public void setTitle(String title) {
        this.title = title;
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

    public Discovery withTitle(String title) {
        this.title = title;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discovery discovery = (Discovery) o;

        if (!title.equals(discovery.title)) return false;
        if (description != null ? !description.equals(discovery.description) : discovery.description != null)
            return false;
        if (contact != null ? !contact.equals(discovery.contact) : discovery.contact != null) return false;
        if (defaultApiRoot != null ? !defaultApiRoot.equals(discovery.defaultApiRoot) : discovery.defaultApiRoot != null)
            return false;
        return apiRoots.equals(discovery.apiRoots);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        result = 31 * result + (defaultApiRoot != null ? defaultApiRoot.hashCode() : 0);
        result = 31 * result + apiRoots.hashCode();
        return result;
    }

    public String getId() {
        return getTitle();
    }

    public void setId(String id) {
        this.setTitle(id);
    }
}
