package xor.bcmc.taxii21.resources;

import com.google.gson.annotations.Expose;
import xor.bcmc.taxii2.Identifiable;

import java.time.ZonedDateTime;

/**
 * From Taxii Spec 2.0, section 4.3.1
 * This type represents an object that was not added to the Collection.
 * It contains the id of the object and a message describing why it couldn't be added.
 */
public class StatusDetails implements Identifiable<String> {
    /**
     * The identifier of the object that failed to be created.
     *  For STIX objects the id MUST be the STIX Object id.
     *  For object types that do not have their own identifier, the server MAY use any value as the id.
     */
    @Expose
    private String id;

    /**
     * The version of the object that succeeded, is pending, or failed to be created.
     *
     * For STIX objects the version <b>MUST</b> be the STIX modified timestamp Property.
     *
     * If a STIX object is not versioned (and therefore does not have a modified timestamp),
     * the created timestamp <b>MUST</b> be used.
     */
    @Expose
    private ZonedDateTime version;

    /**
     * (Optional) A message indicating why the object failed to be created.
     */
    @Expose
    private String message;

    public StatusDetails() {
    }

    public StatusDetails(String id, ZonedDateTime version) {
        this.id = id;
        this.version = version;
    }

    public StatusDetails(String id, ZonedDateTime version, String message) {
        this.id = id;
        this.version = version;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StatusDetails withId(String id) {
        this.id = id;
        return this;
    }

    public ZonedDateTime getVersion() {
        return version;
    }

    public void setVersion(ZonedDateTime version) {
        this.version = version;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StatusDetails withVersion(ZonedDateTime version) {
        this.version = version;
        return this;
    }

    public StatusDetails withMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString () {
        StringBuilder json = new StringBuilder();
        json.append("{\"id\":\"").append(id).append("\"");
        if (message != null) {
            json.append(",\"message\":\"").append(message).append("\"");
        }
        return json.append("}").toString();
    }

    @Override
    public boolean equals(Object statusResource) {
        if (statusResource == null)
            return false;
        if (!(statusResource instanceof StatusDetails))
            return false;
        StatusDetails status = (StatusDetails) statusResource;
        return this.getId().equals(status.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
