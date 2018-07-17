package xor.bcmc.taxii2.resources;

import com.google.gson.JsonPrimitive;
import org.junit.Test;
import xor.bcmc.taxii2.JsonHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class StatusResourceTest {

    private static String FAIL_MESSAGE = "Publishing failed because this is just a test.";

    private StatusResource getTestStatus() {
        StatusResource status = new StatusResource(UUID.randomUUID().toString(),
                StatusResource.StatusEnum.PENDING,
                3,
                1, 1, 1);
        return status;
    }

    private StatusResource getFullTestStatus() {
        StatusResource status = getTestStatus();
        status.setRequestTimestamp(ZonedDateTime.now().withZoneSameLocal(ZoneId.of("Z")));
        List<String> successes = new ArrayList<>();
        successes.add("stixID-1234-s");
        status.setSuccesses(successes);
        List<StatusFailure> statusFailures = new ArrayList<>();
        statusFailures.add(new StatusFailure("stixID-1233-f", FAIL_MESSAGE));
        status.setFailures(statusFailures);
        List<String> pendings = new ArrayList<>();
        pendings.add("stixID-1235-p");
        status.setPendings(pendings);
        return status;
    }

    @Test
    public void serializeAndDeserialize() {
        StatusResource status = getFullTestStatus();
        status.withCustomProperty("x_flarecloud_property2", new JsonPrimitive("value2"));

        String json = status.toJson();
        System.out.println(json);
        assertThat(StatusResource.fromJson(json), equalTo(status));
        assertTrue(status.getCustomProperties().get("x_flarecloud_property2").getAsString().equals("value2"));
    }

    @Test
    public void toStringTest() {
        StatusResource status = getFullTestStatus();
        System.out.println(status.toString());
        String statusJson = status.toString();
        assertTrue(statusJson.contains(FAIL_MESSAGE));
        StatusResource status2 = StatusResource.fromJson(statusJson);

        assertEquals(status, status2);
    }

    @Test
    public void toString_WithNullValues_Test() {
        StatusResource status = new StatusResource("emptyStatusResource"); //Make sure it doesn't throw an exception
        System.out.println(status.toString());
    }

    @Test
    public void toJSON_fromJSON_Test(){
        StatusResource original = getFullTestStatus();
        String statusJSON = original.toString();
        StatusResource status = StatusResource.fromJson(statusJSON);
        System.out.println(status.toString());
        assertEquals(original, status);
    }

    @Test
    public void toJSON_fromJSON_WithNulls_Test(){
        StatusResource original = new StatusResource("emptyStatusResource");
        String statusJSON = original.toString();
        StatusResource status = StatusResource.fromJson(statusJSON);
        System.out.println(status.toString());
        assertEquals(original, status);
    }

    @Test
    public void toJSON_fromJSON_WithEmptyLists_Test(){
        StatusResource original = new StatusResource("emptyStatusResource");
        String statusJSON = original.toString();
        assertFalse("JSON representation contains an empty list! \n" + statusJSON, statusJSON.contains("[]")); //No empty lists!
        StatusResource status = StatusResource.fromJson(statusJSON);
        System.out.println(status.toString());
        assertEquals(original, status);
    }

    @Test
    public void customPropertiesNotSerialized() {
        StatusResource statusResource = new StatusResource();
        statusResource.withCustomProperty("key", new JsonPrimitive("value"));
        System.out.println(statusResource);
        assertFalse(statusResource.toJson().contains("custom_properties"));
    }
}
