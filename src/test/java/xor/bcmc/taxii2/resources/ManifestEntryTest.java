package xor.bcmc.taxii2.resources;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.junit.Test;
import xor.bcmc.taxii2.JsonHandler;

import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ManifestEntryTest {
    @Test
    public void serializeAndDeserialize() {
        ManifestEntry manifestEntry = new ManifestEntry();
        manifestEntry.setId("indicator--29aba82c-5393-42a8-9edb-6a2cb1df070b");
        manifestEntry.setDateAdded(ZonedDateTime.now());
        manifestEntry.setVersions(Arrays.asList("2016-11-03T12:30:59.000Z"));
        manifestEntry.withMediaType("application/vnd.oasis.stix+json; version=2.0");

        String json = manifestEntry.toJson();
        assertThat(JsonHandler.getInstance().getGson().fromJson(json, ManifestEntry.class), equalTo(manifestEntry));
    }

    @Test
    public void serializeAndDeserializeEmptyList() {
        ManifestEntry manifestEntry = new ManifestEntry();
        manifestEntry.setId("indicator--29aba82c-5393-42a8-9edb-6a2cb1df070b");

        JsonObject jsonObject = manifestEntry.toJsonElement().getAsJsonObject();
        assertThat(jsonObject.get("versions"), equalTo(null));
        assertThat(jsonObject.get("media_types"), equalTo(null));

        ManifestEntry manifestEntry_ = JsonHandler.getInstance().getGson().fromJson(jsonObject, ManifestEntry.class);
        assertThat(manifestEntry_.getVersions(), equalTo(null));
        assertThat(manifestEntry_.getVersions(), equalTo(null));
    }

    @Test
    public void withoutOptionalFieldsEquals() {
        ManifestEntry manifestEntry1 = new ManifestEntry();
        manifestEntry1.setId("indicator--29aba82c-5393-42a8-9edb-6a2cb1df070b");

        ManifestEntry manifestEntry2 = new ManifestEntry();
        manifestEntry2.setId("indicator--x");

        assertTrue(!manifestEntry1.equals(manifestEntry2));
    }

    @Test
    public void withoutOptionalFieldsValidate() {
        ManifestEntry manifestEntry = new ManifestEntry();
        manifestEntry.setId("indicator--29aba82c-5393-42a8-9edb-6a2cb1df070b");

        assertTrue(manifestEntry.validate().keySet().size() == 0);
    }

    @Test
    public void customPropertiesNotSerialized() {
        ManifestEntry manifestEntry = new ManifestEntry();
        manifestEntry.withCustomProperty("key", new JsonPrimitive("value"));
        System.out.println(manifestEntry);
        assertFalse(manifestEntry.toJson().contains("custom_properties"));
    }
}