package xor.bcmc.taxii2.messages;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertTrue;

public class TaxiiErrorTest {

    @Test
    public void serializeAndDeserialize() {
        String title = UUID.randomUUID().toString();
        String description = UUID.randomUUID().toString();
        String errorId = UUID.randomUUID().toString();
        String errorCode = UUID.randomUUID().toString();
        String httpStatus = UUID.randomUUID().toString();
        String externalDetails = UUID.randomUUID().toString();

        JsonObject detailsJson = new JsonObject();
        String key = UUID.randomUUID().toString();
        String value = UUID.randomUUID().toString();
        detailsJson.addProperty(key, value);

        TaxiiError taxiiError = new TaxiiError();
        taxiiError.withTitle(title)
                .withDescription(description)
                .withErrorId(errorId)
                .withErrorCode(errorCode)
                .withHttpStatus(httpStatus)
                .withExternalDetails(externalDetails)
                .withDetails(detailsJson);

        String json = taxiiError.toJson();
        System.out.println(json);

        assertTrue(taxiiError.getTitle().equals(title));
        assertTrue(taxiiError.getDescription().equals(description));
        assertTrue(taxiiError.getErrorId().equals(errorId));
        assertTrue(taxiiError.getErrorCode().equals(errorCode));
        assertTrue(taxiiError.getHttpStatus().equals(httpStatus));
        assertTrue(taxiiError.getExternalDetails().equals(externalDetails));
        assertTrue(taxiiError.getDetails().get(key).getAsString().equals(value));

        JsonObject resultJson = new JsonParser().parse(taxiiError.toString()).getAsJsonObject();
        assertTrue(resultJson.get("title").getAsString().equals(title));
        assertTrue(resultJson.get("description").getAsString().equals(description));
        assertTrue(resultJson.get("error_id").getAsString().equals(errorId));
        assertTrue(resultJson.get("error_code").getAsString().equals(errorCode));
        assertTrue(resultJson.get("http_status").getAsString().equals(httpStatus));
        assertTrue(resultJson.get("external_details").getAsString().equals(externalDetails));
        assertTrue(resultJson.get("details").getAsJsonObject().get(key).getAsString().equals(value));
    }
}