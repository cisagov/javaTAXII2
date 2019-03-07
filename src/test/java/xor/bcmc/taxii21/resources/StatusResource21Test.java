package xor.bcmc.taxii21.resources;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.junit.Assert;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class StatusResource21Test {

    private static String SUCCESS_MESSAGE = "Test message was successfully accepted!";
    private static String PENDING_MESSAGE = "Test message is currently pending.";
    private static String FAIL_MESSAGE = "Publishing failed because this is just a test.";

    private StatusResource21 getTestStatus() {
        StatusResource21 status = new StatusResource21(UUID.randomUUID().toString(),
                StatusResource21.StatusEnum.PENDING,
                3,
                1, 1, 1);
        return status;
    }

    private static List<StatusDetails> statusSuccesses = new ArrayList<>();
    private static List<StatusDetails> statusFailures = new ArrayList<>();
    private static List<StatusDetails> statusPendings = new ArrayList<>();

    static {
        statusSuccesses.add(new StatusDetails(UUID.randomUUID().toString(), ZonedDateTime.now(), SUCCESS_MESSAGE));
        statusFailures.add(new StatusDetails(UUID.randomUUID().toString(), ZonedDateTime.now().minusSeconds(42), FAIL_MESSAGE));
        statusPendings.add(new StatusDetails(UUID.randomUUID().toString(), ZonedDateTime.now().minusSeconds(84), PENDING_MESSAGE));
    }

    private static void setStatuses(StatusResource21 status) {
        status.setSuccesses(statusSuccesses);
        status.setFailures(statusFailures);
        status.setPendings(statusPendings);
    }

    private StatusResource21 getFullTestStatus() {
        StatusResource21 status = getTestStatus();
        status.setRequestTimestamp(ZonedDateTime.now());

        setStatuses(status);

        return status;
    }

    @Test
    public void serializeAndDeserialize() {
        StatusResource21 status = getFullTestStatus();
        status.withCustomProperty("x_flarecloud_property2", new JsonPrimitive("value2"));

        String json = status.toJson();
        System.out.println(json);
        assertThat(StatusResource21.fromJson(json), equalTo(status));
        assertTrue(status.getCustomProperties().get("x_flarecloud_property2").getAsString().equals("value2"));
    }

    @Test
    public void toStringTest() {
        StatusResource21 status = getFullTestStatus();
        System.out.println(status.toString());
        String statusJson = status.toString();
        assertTrue(statusJson.contains(FAIL_MESSAGE));
        assertTrue(statusJson.contains(SUCCESS_MESSAGE));
        assertTrue(statusJson.contains(PENDING_MESSAGE));
        StatusResource21 status2 = StatusResource21.fromJson(statusJson);

        assertEquals(status, status2);
    }

    @Test
    public void toString_WithNullValues_Test() {
        StatusResource21 status = new StatusResource21("emptyStatusResource21"); //Make sure it doesn't throw an exception
        System.out.println(status.toString());
    }

    @Test
    public void toJSON_fromJSON_Test(){
        StatusResource21 original = getFullTestStatus();
        String statusJSON = original.toString();
        StatusResource21 status = StatusResource21.fromJson(statusJSON);
        System.out.println(status.toString());
        assertEquals(original, status);
    }

    @Test
    public void toJSON_fromJSON_WithNulls_Test(){
        StatusResource21 original = new StatusResource21("emptyStatusResource21");
        String statusJSON = original.toString();
        StatusResource21 status = StatusResource21.fromJson(statusJSON);
        System.out.println(status.toString());
        assertEquals(original, status);
    }

    @Test
    public void toJSON_fromJSON_WithEmptyLists_Test(){
        StatusResource21 original = new StatusResource21("emptyStatusResource21");
        String statusJSON = original.toString();
        assertFalse("JSON representation contains an empty list! \n" + statusJSON, statusJSON.contains("[]")); //No empty lists!
        StatusResource21 status = StatusResource21.fromJson(statusJSON);
        System.out.println(status.toString());
        assertEquals(original, status);
    }

    @Test
    public void checkSerialization() {
        StatusResource21 status = getTestStatus();
        status.setRequestTimestamp(ZonedDateTime.now().withZoneSameLocal(ZoneId.of("Z")));

        setStatuses(status);

        JsonObject statusJson = status.toJsonElement().getAsJsonObject();
        Assert.assertTrue(statusJson.get("id").getAsString().equals(status.getId()));
        Assert.assertTrue(statusJson.get("status").getAsString().toUpperCase().equals(status.getStatus().name()));
        Assert.assertTrue(statusJson.get("status").getAsString().equals(status.getStatus().name().toLowerCase()));
        Assert.assertTrue(statusJson.get("request_timestamp").getAsString().equals(status.getRequestTimestamp().withZoneSameInstant(ZoneId.of("Z")).toString()));
        Assert.assertTrue(statusJson.get("total_count").getAsInt() == status.getTotalCount());

        // Check successes
        Assert.assertTrue(statusJson.get("success_count").getAsInt() == status.getSuccessCount());
        JsonObject statusSuccessJson = statusJson.get("successes").getAsJsonArray().get(0).getAsJsonObject();
        Assert.assertTrue(statusSuccessJson.get("id").getAsString()
                .equals(status.getSuccesses().get(0).getId()));
        Assert.assertTrue(statusSuccessJson.get("message").getAsString()
                .equals(status.getSuccesses().get(0).getMessage()));

        // Check failures
        Assert.assertTrue(statusJson.get("failure_count").getAsInt() == status.getFailureCount());
        JsonObject statusFailureJson = statusJson.get("failures").getAsJsonArray().get(0).getAsJsonObject();
        Assert.assertTrue(statusFailureJson.get("id").getAsString()
                .equals(status.getFailures().get(0).getId()));
        Assert.assertTrue(statusFailureJson.get("message").getAsString()
                .equals(status.getFailures().get(0).getMessage()));

        // Check pendings
        Assert.assertTrue(statusJson.get("pending_count").getAsInt() == status.getPendingCount());
        JsonObject statusPendingsJson = statusJson.get("pendings").getAsJsonArray().get(0).getAsJsonObject();
        Assert.assertTrue(statusPendingsJson.get("id").getAsString()
                .equals(status.getPendings().get(0).getId()));
        Assert.assertTrue(statusPendingsJson.get("message").getAsString()
                .equals(status.getPendings().get(0).getMessage()));


        Assert.assertTrue(statusJson.keySet().size() == 10);

        StatusResource21 status_ = StatusResource21.fromJson(statusJson.toString());
        Assert.assertTrue(status.equals(status_));
    }

    @Test
    public void customPropertiesNotSerialized() {
        StatusResource21 statusResource = new StatusResource21();
        statusResource.withCustomProperty("key", new JsonPrimitive("value"));
        System.out.println(statusResource);
        assertFalse(statusResource.toJson().contains("custom_properties"));
    }

    @Test
    public void checkStatusDetailsEquals() {
        ZonedDateTime version = ZonedDateTime.now(ZoneId.of("Z"));

        StatusDetails details1 = new StatusDetails("attack-pattern--0c7b5b88-8ff7-4a4d-aa9d-feb398cd0061", version);
        StatusDetails details2 = new StatusDetails("attack-pattern--0c7b5b88-8ff7-4a4d-aa9d-feb398cd0061", version);
        assertTrue(details1.equals(details2));

        details1 = new StatusDetails("attack-pattern--0c7b5b88-8ff7-4a4d-aa9d-feb398cd0061", version);
        details2 = new StatusDetails("attack-pattern--0c7b5b88-8ff7-4a4d-aa9d-feb398cd0061", version.plusDays(1L));
        assertFalse(details1.equals(details2));

        details1 = new StatusDetails("attack-pattern--1c7b5b88-8ff7-4a4d-aa9d-feb398cd0061", version);
        details2 = new StatusDetails("attack-pattern--0c7b5b88-8ff7-4a4d-aa9d-feb398cd0061", version);
        assertFalse(details1.equals(details2));
    }
}
