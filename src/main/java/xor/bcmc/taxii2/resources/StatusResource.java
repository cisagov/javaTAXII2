package xor.bcmc.taxii2.resources;

import com.google.gson.*;
import xor.bcmc.taxii2.Identifiable;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Taxii Spec 2.0 Section 4.3.1:
 * The status resource represents information about a request to add objects to a Collection.
 * It contains information about the status of the request, such as whether or
 * not it's completed (status) and the status of individual objects within the request
 * (i.e. whether they are still pending, completed and failed, or completed and succeeded).
 */
public class StatusResource implements Identifiable<String> {

    /* --------------------------------------------------------------------- */
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(ZonedDateTime.class, new JsonDeserializer<ZonedDateTime>() {
                @Override
                public ZonedDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                    return DateTimeFormatter.ISO_DATE_TIME.parse(json.getAsString(), ZonedDateTime::from);
                }
            })
            .registerTypeAdapter(ZonedDateTime.class, new JsonSerializer<ZonedDateTime>() {
                @Override
                public JsonElement serialize(ZonedDateTime src, Type typeOfSrc, JsonSerializationContext context)
                {
                    return new JsonPrimitive(DateTimeFormatter.ISO_DATE_TIME.format(src));
                }
            })
            .enableComplexMapKeySerialization()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat("YYYY-MM-DD'T'HH:mm:ss[.s+]Z")
            .setPrettyPrinting()
            .create();

    public static StatusResource fromJson(String json) {
        return GSON.fromJson(json, StatusResource.class);
    }
    /* --------------------------------------------------------------------- */


    public enum STATUS { PENDING, COMPLETED }

    private String id;

//    @Field("status")
    private STATUS status = STATUS.PENDING;
    //Optional. The datetime of the request that this status resource is monitoring.
    private ZonedDateTime requestTimestamp;
    //The total number of objects that were in the request. For a STIX bundle this would be the number of objects in the bundle.
    private int totalCount;
    private int successCount;
    //Optional. A list of object IDs that were successfully processed.
    // For STIX objects the STIX ID MUST be used here.
    // For object types that do not have their own identifier, the server MAY use any value as the id.
    private List<String> successes;
    private int failureCount;
    //Optional. A list of objects that were not successfully processed.
    private List<StatusFailure> failures;
    private int pendingCount;
    //Optional. A list of objects for objects that have yet to be processed.
    // For STIX objects the STIX ID MUST be used here.
    // For object types that do not have their own identifier, the server MAY use any value as the id.
    private List<String> pendings;

    public StatusResource() {

    }

    public StatusResource(String id) {
        this.id = id;
    }

    /**
     * Constructor with only the required TAXII 2.0 fields.
     */
    public StatusResource(String id, STATUS status, int totalCount, int successCount, int failureCount, int pendingCount) {
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

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public StatusResource withStatus(STATUS status) {
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
        if (status != that.status) return false;
        if (requestTimestamp != null ? !requestTimestamp.equals(that.requestTimestamp) : that.requestTimestamp != null)
            return false;
        if ((successes != null && !successes.isEmpty()) ? !successes.equals(that.successes) : (that.successes != null && !that.successes.isEmpty())) return false;
        if ((failures != null && !failures.isEmpty()) ? !failures.equals(that.failures) : (that.failures != null && !that.failures.isEmpty())) return false;
        return (pendings != null && !pendings.isEmpty()) ? pendings.equals(that.pendings) : (that.pendings == null || that.pendings.isEmpty());
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + (requestTimestamp != null ? requestTimestamp.hashCode() : 0);
        result = 31 * result + totalCount;
        result = 31 * result + successCount;
        result = 31 * result + ((successes != null && !successes.isEmpty())? successes.hashCode() : 0);
        result = 31 * result + failureCount;
        result = 31 * result + ((failures != null && !failures.isEmpty())? failures.hashCode() : 0);
        result = 31 * result + pendingCount;
        result = 31 * result + ((pendings != null && !pendings.isEmpty())? pendings.hashCode() : 0);
        return result;
    }

    @Override
    public String toString () {
        return GSON.toJson(this);
    }
}
