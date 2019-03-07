package xor.bcmc.taxii21.resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import xor.bcmc.taxii2.Identifiable;
import xor.bcmc.taxii2.JsonHandler;
import xor.bcmc.taxii2.resources.TaxiiResource;
import xor.bcmc.taxii2.validation.Errors;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Taxii Spec 2.0 Section 4.3.1:
 * The status resource represents information about a request to add objects to a Collection.
 * It contains information about the status of the request, such as whether or
 * not it's completed (status) and the status of individual objects within the request
 * (i.e. whether they are still pending, completed and failed, or completed and succeeded).
 */
public class StatusResource21 extends TaxiiResource implements Identifiable<String> {

    /* --------------------------------------------------------------------- */
    public static StatusResource21 fromJson(String json) {
        return JsonHandler.getInstance().fromJson(json, StatusResource21.class);
    }

    /* --------------------------------------------------------------------- */


    public enum StatusEnum {
        @SerializedName("pending")
        PENDING,

        @SerializedName("complete")
        COMPLETE,

        @SerializedName("failure")
        FAILURE
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
    private List<StatusDetails> successes;

    @Expose
    private int failureCount;

    //Optional. A list of objects that were not successfully processed.
    @Expose
    private List<StatusDetails> failures;

    @Expose
    private int pendingCount;

    //Optional. A list of objects for objects that have yet to be processed.
    // For STIX objects the STIX ID MUST be used here.
    // For object types that do not have their own identifier, the server MAY use any value as the id.
    @Expose
    private List<StatusDetails> pendings;

    public StatusResource21() {

    }

    public StatusResource21(String id) {
        this.id = id;
    }

    /**
     * Constructor with only the required TAXII 2.0 fields.
     */
    public StatusResource21(String id, StatusEnum status, int totalCount, int successCount, int failureCount, int pendingCount) {
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

    public StatusResource21 withId(String id) {
        this.id = id;
        return this;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public ZonedDateTime getRequestTimestamp() {
        return requestTimestamp;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public List<StatusDetails> getSuccesses() {
        return successes;
    }

    public int getFailureCount() {
        return failureCount;
    }

    public List<StatusDetails> getFailures() {
        return failures;
    }

    public int getPendingCount() {
        return pendingCount;
    }

    public List<StatusDetails> getPendings() {
        return pendings;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public void setRequestTimestamp(ZonedDateTime requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public void setSuccesses(List<StatusDetails> successes) {
        this.successes = successes;
    }

    public void setFailureCount(int failureCount) {
        this.failureCount = failureCount;
    }

    public void setFailures(List<StatusDetails> failures) {
        this.failures = failures;
    }

    public void setPendingCount(int pendingCount) {
        this.pendingCount = pendingCount;
    }

    public void setPendings(List<StatusDetails> pendings) {
        this.pendings = pendings;
    }

    public StatusResource21 withStatus(StatusEnum status) {
        this.status = status;
        return this;
    }

    public StatusResource21 withRequestTimestamp(ZonedDateTime requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
        return this;
    }

    public StatusResource21 withTotalCount(int totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public StatusResource21 withSuccessCount(int successCount) {
        this.successCount = successCount;
        return this;
    }

    public StatusResource21 withSuccesses(List<StatusDetails> successes) {
        this.successes = successes;
        return this;
    }

    public StatusResource21 withFailureCount(int failureCount) {
        this.failureCount = failureCount;
        return this;
    }

    public StatusResource21 withFailures(List<StatusDetails> failures) {
        this.failures = failures;
        return this;
    }

    public StatusResource21 withPendingCount(int pendingCount) {
        this.pendingCount = pendingCount;
        return this;
    }

    public StatusResource21 withPendings(List<StatusDetails> pendings) {
        this.pendings = pendings;
        return this;
    }

    public StatusResource21 withSuccess(StatusDetails success) {
        if (this.successes == null) {
            this.successes = new ArrayList<>();
        }
        this.successes.add(success);
        return this;
    }
    public StatusResource21 withFailure(StatusDetails failure) {
        if (this.failures == null) {
            this.failures = new ArrayList<>();
        }
        this.failures.add(failure);
        return this;
    }
    public StatusResource21 withPending(StatusDetails pending) {
        if (this.pendings == null) {
            this.pendings = new ArrayList<>();
        }
        this.pendings.add(pending);
        return this;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusResource21 that = (StatusResource21) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
