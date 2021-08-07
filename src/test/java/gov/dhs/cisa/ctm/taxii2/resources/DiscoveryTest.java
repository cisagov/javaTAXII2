package gov.dhs.cisa.ctm.taxii2.resources;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import gov.dhs.cisa.ctm.taxii2.resources.Discovery;

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
    public void serializeAndDeserializeEmptyList() {
        Discovery discovery_ = new Discovery();
        discovery_.setTitle("SomeTitle");
        JsonObject jsonObject = discovery_.toJsonElement().getAsJsonObject();
        assertThat(jsonObject.get("api_roots"), equalTo(null));

        Discovery discovery__ = Discovery.fromJson(jsonObject.toString());
        assertThat(discovery__.getApiRoots(), equalTo(null));
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

    @Test
    public void checkSerialization() {
        JsonObject discoveryJson = discovery.toJsonElement().getAsJsonObject();
        assertTrue(discoveryJson.get("title").getAsString().equals(discovery.getTitle()));
        assertTrue(discoveryJson.get("description").getAsString().equals(discovery.getDescription()));
        assertTrue(discoveryJson.get("contact").getAsString().equals(discovery.getContact()));
        assertTrue(discoveryJson.get("default").getAsString().equals(discovery.getDefaultApiRoot()));
        assertTrue(discoveryJson.get("api_roots").getAsJsonArray().get(0).getAsString().equals("Api Root 1"));
        assertTrue(discoveryJson.get("api_roots").getAsJsonArray().get(1).getAsString().equals("Api Root 2"));

        assertTrue(discoveryJson.keySet().size() == 5);

        Discovery discovery_ = Discovery.fromJson(discoveryJson.toString());
        assertTrue(discovery.equals(discovery_));
    }

    @Test
    public void customPropertiesNotSerialized() {
        Discovery discovery = new Discovery();
        discovery.withCustomProperty("key", new JsonPrimitive("value"));
        System.out.println(discovery);
        assertFalse(discovery.toJson().contains("custom_properties"));
    }
}