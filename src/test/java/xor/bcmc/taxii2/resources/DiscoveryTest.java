package xor.bcmc.taxii2.resources;

import com.google.gson.JsonPrimitive;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

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
    public void withoutOptionalFieldsEquals() {
        Discovery discovery_ = new Discovery();
        discovery_.setTitle("SomeTitle");
        assertTrue(!discovery.equals(discovery_));
    }

    @Test
    public void withoutOptionalFieldsValidate() {
        Discovery discovery_ = new Discovery();
        discovery_.setTitle("SomeTitle");
        assertThat(discovery_.validate().keySet().size(), equalTo(0));
    }

    @Test
    public void withoutOptionalFieldsSerializeAndDeserialize() {
        Discovery discovery_ = new Discovery();
        discovery_.setTitle("SomeTitle");
        String json = discovery_.toJson();
        assertEquals(Discovery.fromJson(json), discovery_);
    }
}