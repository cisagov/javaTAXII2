package xor.bcmc.taxii21.resources;
import static org.junit.Assert.*;
import org.junit.Test;
import xor.bcmc.taxii2.validation.Errors;

public class ApiRoot21Test
{
    @Test
    public void testMaxContentLengthValidation() {
        ApiRoot21 apiRoot21 = new ApiRoot21()
        .withId("api-root-1")
        .withTitle("Api root 1 title")
        .withDescription("Api root 1 description")
        .withMaxContentLength(0);

        Errors errors = apiRoot21.validate();

        assertEquals(errors.size(), 2);
    }

    @Test
    public void testMaxContentLengthValidationNegative() {
        ApiRoot21 apiRoot21 = new ApiRoot21()
                .withId("api-root-1")
                .withTitle("Api root 1 title")
                .withDescription("Api root 1 description")
                .withMaxContentLength(-1);

        Errors errors = apiRoot21.validate();

        assertEquals(errors.size(), 2);
    }
}
