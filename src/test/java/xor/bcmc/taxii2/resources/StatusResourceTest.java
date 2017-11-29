package xor.bcmc.taxii2.resources;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatusResourceTest {

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
        statusFailures.add(new StatusFailure("stixID-1233", "Publishing failed because this is just a test."));
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
        StatusResource status2 = StatusResource.fromJson(status.toString());

        assertEquals(status, status2);
    }
}
