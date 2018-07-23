package xor.bcmc.taxii2.resources;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import org.junit.Test;
import xor.bcmc.taxii2.JsonHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class ManifestResourceTest {
    @Test
    public void serializeAndDeserialize() {
        ManifestEntry entry = new ManifestEntry();
        entry.withId("id1");

        ManifestResource manifestResource = new ManifestResource();
        manifestResource.setObjects(
                Arrays.asList(entry));

        String json = manifestResource.toJson();
        ManifestResource manifestResource_ = JsonHandler.getInstance().getGson().fromJson(json, ManifestResource.class);
        assertTrue(manifestResource.equals(manifestResource_));
    }

    @Test
    public void serializeAndDeserializeEmptyList() {
        ManifestResource manifestResource = new ManifestResource();

        String json = manifestResource.toJson();
        ManifestResource manifestResource_ = JsonHandler.getInstance().getGson().fromJson(json, ManifestResource.class);
        assertTrue(manifestResource.equals(manifestResource_));
    }

    @Test
    public void withoutOptionalFieldsEquals() {
        ManifestResource manifestResource1 = new ManifestResource();
        ManifestResource manifestResource2 = new ManifestResource();

        assertTrue(manifestResource1.equals(manifestResource2));
    }

    @Test
    public void withoutOptionalFieldsValidate() {
        ManifestResource manifestResource = new ManifestResource();

        assertTrue(manifestResource.validate().keySet().size() == 0);
    }

    @Test
    public void checkSerialization() {

        ManifestEntry manifestEntry = new ManifestEntry();
        manifestEntry.setId("indicator--29aba82c-5393-42a8-9edb-6a2cb1df070b");
        manifestEntry.setDateAdded(ZonedDateTime.now());
        manifestEntry.setVersions(Arrays.asList("2016-11-03T12:30:59.000Z"));
        manifestEntry.withMediaType("application/vnd.oasis.stix+json; version=2.0");

        ManifestResource manifestResource = new ManifestResource(Arrays.asList(manifestEntry));

        JsonObject manifestResourceJson = manifestResource.toJsonElement().getAsJsonObject();
        assertTrue(manifestResourceJson.get("objects").getAsJsonArray().size() == 1);

        JsonObject manifestEntryJson = manifestResourceJson.get("objects").getAsJsonArray().get(0).getAsJsonObject();
        assertTrue(manifestEntryJson.get("id").getAsString().equals(manifestEntry.getId()));
        assertTrue(manifestEntryJson.get("date_added").getAsString().equals(manifestEntry.getDateAdded().withZoneSameInstant(ZoneId.of("Z")).toString()));
        assertTrue(manifestEntryJson.get("versions").getAsJsonArray().get(0).getAsString().equals(manifestEntry.getVersions().get(0)));
        assertTrue(manifestEntryJson.get("media_types").getAsJsonArray().get(0).getAsString().equals(manifestEntry.getMediaTypes().get(0)));

        assertTrue(manifestResourceJson.keySet().size() == 1);

        ManifestResource manifestResource_ = JsonHandler.getInstance().fromJson(manifestResourceJson.toString(), ManifestResource.class);
        assertTrue(manifestResource.equals(manifestResource_));
    }

    @Test
    public void customPropertiesNotSerialized() {
        ManifestResource manifestResource = new ManifestResource();
        manifestResource.withCustomProperty("key", new JsonPrimitive("value"));
        System.out.println(manifestResource);
        assertFalse(manifestResource.toJson().contains("custom_properties"));
    }
}