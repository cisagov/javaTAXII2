package gov.dhs.cisa.ctm.taxii2.resources;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import gov.dhs.cisa.ctm.taxii2.JsonHandler;
import gov.dhs.cisa.ctm.taxii2.resources.ManifestEntry;

import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class ManifestEntryTest {
    @Test
    public void serializeAndDeserialize() {
        ManifestEntry manifestEntry = new ManifestEntry();
        manifestEntry.setId("indicator--29aba82c-5393-42a8-9edb-6a2cb1df070b");
        manifestEntry.setDateAdded(ZonedDateTime.now());
        manifestEntry.setVersions(Arrays.asList("2016-11-03T12:30:59.000Z"));
        manifestEntry.withMediaType("application/vnd.oasis.stix+json; version=2.0");

        String json = manifestEntry.toJson();
        assertThat(JsonHandler.gson.fromJson(json, ManifestEntry.class), equalTo(manifestEntry));
    }

    @Test
    public void serializeAndDeserializeEmptyList() {
        ManifestEntry manifestEntry = new ManifestEntry();
        manifestEntry.setId("indicator--29aba82c-5393-42a8-9edb-6a2cb1df070b");

        JsonObject jsonObject = manifestEntry.toJsonElement().getAsJsonObject();
        assertThat(jsonObject.get("versions"), equalTo(null));
        assertThat(jsonObject.get("media_types"), equalTo(null));

        ManifestEntry manifestEntry_ = JsonHandler.gson.fromJson(jsonObject, ManifestEntry.class);
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
    public void checkSerialization() {
        ManifestEntry manifestEntry = new ManifestEntry();
        manifestEntry.setId("indicator--29aba82c-5393-42a8-9edb-6a2cb1df070b");
        manifestEntry.setDateAdded(ZonedDateTime.now());
        manifestEntry.setVersions(Arrays.asList("2016-11-03T12:30:59.000Z"));
        manifestEntry.withMediaType("application/vnd.oasis.stix+json; version=2.0");

        JsonObject manifestEntryJson = manifestEntry.toJsonElement().getAsJsonObject();
        assertEquals(manifestEntryJson.get("id").getAsString(), manifestEntry.getId());
        assertEquals(manifestEntryJson.get("date_added").getAsString(), manifestEntry.getDateAdded().withZoneSameInstant(ZoneId.of("Z")).toString());
        assertEquals(manifestEntryJson.get("versions").getAsJsonArray().get(0).getAsString(), manifestEntry.getVersions().get(0));
        assertEquals(manifestEntryJson.get("media_types").getAsJsonArray().get(0).getAsString(), manifestEntry.getMediaTypes().get(0));

        assertEquals(4, manifestEntryJson.keySet().size());

        ManifestEntry manifestEntry_ = JsonHandler.gson.fromJson(manifestEntryJson.toString(), ManifestEntry.class);
        assertEquals(manifestEntry, manifestEntry_);
    }

    @Test
    public void customPropertiesNotSerialized() {
        ManifestEntry manifestEntry = new ManifestEntry();
        manifestEntry.withCustomProperty("key", new JsonPrimitive("value"));
        System.out.println(manifestEntry);
        assertFalse(manifestEntry.toJson().contains("custom_properties"));
    }
}