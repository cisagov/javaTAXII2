package xor.bcmc.taxii2.resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import xor.bcmc.taxii2.Identifiable;
import xor.bcmc.taxii2.JsonHandler;
import xor.bcmc.taxii2.validation.Errors;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Taxii Spec 2.0 Section 4.3.1:
 * The status resource represents information about a request to add objects to a Collection.
 * It contains information about the status of the request, such as whether or
 * not it's completed (status) and the status of individual objects within the request
 * (i.e. whether they are still pending, completed and failed, or completed and succeeded).
 */
public class StatusResource extends TaxiiResource implements Identifiable<String> {

    /* --------------------------------------------------------------------- */
    public static StatusResource fromJson(String json) {
        return JsonHandler.getInstance().fromJson(json, StatusResource.class);
    }
    /* --------------------------------------------------------------------- */


    public enum StatusEnum {
        @SerializedName("pending")
        PENDING,

        @SerializedName("complete")
        COMPLETE
    }

    @Expose
    private String id;

//    @Field("status")
    @Expose
    private StatusEnum status = StatusEnum.PENDING;

    //Optional. The datetime of the request that this status resource is monitoring.
    @Expose
    private ZonedDateTime requestTimestamp;

    //The total number of objects that were in the request. For a STIX bundle this would be the number of objects in the bundle.
    @Expose
    private int totalCount;

    @Expose
    private int successCount;

    //Optional. A list of object IDs that were successfully processed.
    // For STIX objects the STIX ID MUST be used here.
    // For object types that do not have their own identifier, the server MAY use any value as the id.
    @Expose
    private List<String> successes;

    @Expose
    private int failureCount;

    //Optional. A list of objects that were not successfully processed.
    @Expose
    private List<StatusFailure> failures;

    @Expose
    private int pendingCount;

    //Optional. A list of objects for objects that have yet to be processed.
    // For STIX objects the STIX ID MUST be used here.
    // For object types that do not have their own identifier, the server MAY use any value as the id.
    @Expose
    private List<String> pendings;

    public StatusResource() {

    }

    public StatusResource(String id) {
        this.id = id;
    }

    /**
     * Constructor with only the required TAXII 2.0 fields.
     */
    public StatusResource(String id, StatusEnum status, int totalCount, int successCount, int failureCount, int pendingCount) {
        this.id = id;
        this.status = status;
        this.totalCount = totalCount;
        this.successCount = successCount;
        this.failureCount = failureCount;
        this.pendingCount = pendingCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StatusResource withId(String id) {
        this.id = id;
        return this;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public StatusResource withStatus(StatusEnum status) {
        this.status = status;
        return this;
    }

    public ZonedDateTime getRequestTimestamp() {
        return requestTimestamp;
    }

    public void setRequestTimestamp(ZonedDateTime requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }

    public StatusResource withRequestTimestamp(ZonedDateTime requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
        return this;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public StatusResource withTotalCount(int totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public StatusResource withSuccessCount(int successCount) {
        this.successCount = successCount;
        return this;
    }

    public List<String> getSuccesses() {
        return successes;
    }

    public void setSuccesses(List<String> successes) {
        this.successes = successes;
    }

    public StatusResource withSuccesses(List<String> successes) {
        this.successes = successes;
        return this;
    }

    public StatusResource withSuccessId(String messageId) {
        if (this.successes == null) {
            this.successes = new ArrayList<>(1);
        }
        this.successes.add(messageId);
        return this;
    }

    public int getFailureCount() {
        return failureCount;
    }

    public void setFailureCount(int failureCount) {
        this.failureCount = failureCount;
    }

    public StatusResource withFailureCount(int failureCount) {
        this.failureCount = failureCount;
        return this;
    }

    public List<StatusFailure> getFailures() {
        return failures;
    }

    public void setFailures(List<StatusFailure> failures) {
        this.failures = failures;
    }

    public StatusResource withFailures(List<StatusFailure> failures) {
        this.failures = failures;
        return this;
    }

    public StatusResource withFailure(StatusFailure failure) {
        if (this.failures == null) {
            this.failures = new ArrayList<>(1);
        }
        this.failures.add(failure);
        return this;
    }

    public int getPendingCount() {
        return pendingCount;
    }

    public void setPendingCount(int pendingCount) {
        this.pendingCount = pendingCount;
    }

    public StatusResource withPendingCount(int pendingCount) {
        this.pendingCount = pendingCount;
        return this;
    }

    public StatusResource withPendingId(String messageID) {
        if (this.pendings == null) {
            this.pendings = new ArrayList<>(1);
        }
        this.pendings.add(messageID);
        return this;
    }

    public List<String> getPendings() {
        return pendings;
    }

    public void setPendings(List<String> pendings) {
        this.pendings = pendings;
    }

    public StatusResource withPendings(List<String> pendings) {
        this.pendings = pendings;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatusResource that = (StatusResource) o;

        if (totalCount != that.totalCount) return false;
        if (successCount != that.successCount) return false;
        if (failureCount != that.failureCount) return false;
        if (pendingCount != that.pendingCount) return false;
        if (!id.equals(that.id)) return false;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + totalCount;
        result = 31 * result + successCount;
        result = 31 * result + failureCount;
        result = 31 * result + pendingCount;
        return result;
    }

    @Override
    public Errors validate() {
        Errors errors = new Errors();
        errors.rejectIfNullOrEmpty("id", this.id);
        errors.rejectIfNullOrEmpty("status", this.status.toString());
        return errors;
    }

    @Override
    public boolean isValid() {
        return validate().isEmpty();
    }
}
