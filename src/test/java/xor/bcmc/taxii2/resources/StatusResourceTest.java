package xor.bcmc.taxii2.resources;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class StatusResourceTest {

    private static String FAIL_MESSAGE = "Publishing failed because this is just a test.";

    private StatusResource getTestStatus() {
        StatusResource status = new StatusResource(UUID.randomUUID().toString(),
                StatusResource.STATUS.PENDING,
                3,
                1, 1, 1);
        return status;
    }

    private StatusResource getFullTestStatus() {
        StatusResource status = getTestStatus();
        status.setRequestTimestamp(ZonedDateTime.now());
        List<String> successes = new ArrayList<>();
        successes.add("stixID-1234");
        status.setSuccesses(successes);
        List<StatusFailure> statusFailures = new ArrayList<>();
        statusFailures.add(new StatusFailure("stixID-1233", FAIL_MESSAGE));
        status.setFailures(statusFailures);
        List<String> pendings = new ArrayList<>();
        pendings.add("stixID-1235");
        status.setPendings(pendings);
        return status;
    }

    @Test
    public void toStringTest() {
        StatusResource status = getFullTestStatus();
//        System.out.println(status.toString());
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
//        System.out.println(status.toString());
        assertEquals(original, status);
    }

    @Test
    public void toJSON_fromJSON_WithNulls_Test(){
        StatusResource original = new StatusResource("emptyStatusResource");
        String statusJSON = original.toString();
        StatusResource status = StatusResource.fromJson(statusJSON);
//        System.out.println(status.toString());
        assertEquals(original, status);
    }
}
