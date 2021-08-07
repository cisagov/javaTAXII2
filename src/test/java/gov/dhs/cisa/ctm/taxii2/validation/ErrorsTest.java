package gov.dhs.cisa.ctm.taxii2.validation;

import org.junit.Test;

import gov.dhs.cisa.ctm.taxii2.validation.Errors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ErrorsTest {

    @Test
    public void uuidNONv4Test() {
        Errors validationErrors = new Errors().rejectIfNotValidUUID("id", "col1");
        assertTrue(validationErrors.containsKey("id"));
    }

    @Test
    public void uuidV1Test() {
        //Should fail because this is a v1 UUID, not v4
        Errors validationErrors = new Errors().rejectIfNotValidUUID("id", "ab825692-532c-11ea-8d77-2e728ce88125");
        assertTrue(validationErrors.containsKey("id"));
    }

    @Test
    public void uuidV4Test() {
        Errors validationErrors = new Errors().rejectIfNotValidUUID("id", "7ca39380-5179-4e40-b23a-fd7e821c0d07");
        assertFalse(validationErrors.containsKey("id"));

        validationErrors = new Errors().rejectIfNotValidUUID("id", "28d440a5-b1c9-40b9-b39b-a3abac4b637a");
        assertFalse(validationErrors.containsKey("id"));

        validationErrors = new Errors().rejectIfNotValidUUID("id", "98280bdf-bf87-443d-8299-7e7f45c242b2");
        assertFalse(validationErrors.containsKey("id"));

        validationErrors = new Errors().rejectIfNotValidUUID("id", "a6dadd0c-142d-43f0-8840-fb610e42feb8");
        assertFalse(validationErrors.containsKey("id"));

        validationErrors = new Errors().rejectIfNotValidUUID("id", "e1ffaee4-e553-4de1-b5f5-bb61e07da04b");
        assertFalse(validationErrors.containsKey("id"));
    }

}
