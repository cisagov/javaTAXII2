package xor.bcmc.taxii2.resources;

import com.google.gson.annotations.Expose;
import xor.bcmc.taxii2.Identifiable;

/**
 * From Taxii Spec 2.0, section 4.3.1
 * This type represents an object that was not added to the Collection.
 * It contains the id of the object and a message describing why it couldn't be added.
 */
public class StatusFailure implements Identifiable<String> {
    /**
     * The identifier of the object that failed to be created.
     *  For STIX objects the id MUST be the STIX Object id.
     *  For object types that do not have their own identifier, the server MAY use any value as the id.
     */
    @Expose
    private String id;

    /**
     * (Optional) A message indicating why the object failed to be created.
     */
    @Expose
    private String message;

    public StatusFailure() {}

    public StatusFailure(String id) {
        this.id = id;
    }

    public StatusFailure(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StatusFailure withId(String id) {
        this.id = id;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StatusFailure withMessage(String message) {
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
        if (!(statusResource instanceof StatusFailure))
            return false;
        StatusFailure status = (StatusFailure) statusResource;
        return this.getId().equals(status.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
