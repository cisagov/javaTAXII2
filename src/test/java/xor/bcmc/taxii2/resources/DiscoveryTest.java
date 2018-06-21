package xor.bcmc.taxii2.resources;

import com.google.gson.JsonPrimitive;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class DiscoveryTest {

    private static Discovery discovery;

    static {
        discovery = new Discovery();
        discovery.withTitle("Discovery Root 1");
        discovery.withDescription("Discovery Description");
        discovery.withContact("Discovery Contact");
        discovery.withDefaultApiRoot("Default Api Root");
        discovery.withApiRoots(Arrays.asList("Api Root 1", "Api Root 2"));
        discovery.withCustomProperty("x_flarecloud_property", new JsonPrimitive("value1"));
        System.out.println(discovery.toJson());
    }

    @Test
    public void serializeAndDeserialize() {
        String json = discovery.toJson();
        assertEquals(Discovery.fromJson(json), discovery);
    }

    @Test
    public void bareMinimumFieldsTest() {
        Discovery emptyDiscovery = new Discovery("Title", null);
        assertTrue(emptyDiscovery.validate().isEmpty());

        String json = emptyDiscovery.toJson();
        assertEquals(Discovery.fromJson(json), emptyDiscovery);
        String toString = emptyDiscovery.toString();
        assertEquals(Discovery.fromJson(toString), emptyDiscovery);
    }

}