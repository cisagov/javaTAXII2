package gov.dhs.cisa.ctm.taxii2.resources;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import gov.dhs.cisa.ctm.taxii2.JsonHandler;
import gov.dhs.cisa.ctm.taxii2.resources.ManifestEntry;
import gov.dhs.cisa.ctm.taxii2.resources.ManifestResource;

import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;

import static org.junit.Assert.*;

public class ManifestResourceTest {
    @Test
    public void serializeAndDeserialize() {
        ManifestEntry entry = new ManifestEntry();
        entry.withId("id1");

        ManifestResource manifestResource = new ManifestResource();
        manifestResource.setObjects(
                Collections.singletonList(entry));

        String json = manifestResource.toJson();
        ManifestResource manifestResource_ = JsonHandler.gson.fromJson(json, ManifestResource.class);
        assertEquals(manifestResource, manifestResource_);
    }

    @Test
    public void serializeAndDeserializeEmptyList() {
        ManifestResource manifestResource = new ManifestResource();

        String json = manifestResource.toJson();
        ManifestResource manifestResource_ = JsonHandler.gson.fromJson(json, ManifestResource.class);
        assertEquals(manifestResource, manifestResource_);
    }

    @Test
    public void withoutOptionalFieldsEquals() {
        ManifestResource manifestResource1 = new ManifestResource();
        ManifestResource manifestResource2 = new ManifestResource();

        assertEquals(manifestResource1, manifestResource2);
    }

    @Test
    public void withoutOptionalFieldsValidate() {
        ManifestResource manifestResource = new ManifestResource();

        assertEquals(0, manifestResource.validate().keySet().size());
    }

    @Test
    public void checkSerialization() {

        ManifestEntry manifestEntry = new ManifestEntry();
        manifestEntry.setId("indicator--29aba82c-5393-42a8-9edb-6a2cb1df070b");
        manifestEntry.setDateAdded(ZonedDateTime.now());
        manifestEntry.setVersions(Collections.singletonList("2016-11-03T12:30:59.000Z"));
        manifestEntry.withMediaType("application/vnd.oasis.stix+json; version=2.0");

        ManifestResource manifestResource = new ManifestResource(Collections.singletonList(manifestEntry));

        JsonObject manifestResourceJson = manifestResource.toJsonElement().getAsJsonObject();
        assertEquals(1, manifestResourceJson.get("objects").getAsJsonArray().size());

        JsonObject manifestEntryJson = manifestResourceJson.get("objects").getAsJsonArray().get(0).getAsJsonObject();
        assertEquals(manifestEntryJson.get("id").getAsString(), manifestEntry.getId());
        assertEquals(manifestEntryJson.get("date_added").getAsString(), manifestEntry.getDateAdded().withZoneSameInstant(ZoneId.of("Z")).toString());
        assertEquals(manifestEntryJson.get("versions").getAsJsonArray().get(0).getAsString(), manifestEntry.getVersions().get(0));
        assertEquals(manifestEntryJson.get("media_types").getAsJsonArray().get(0).getAsString(), manifestEntry.getMediaTypes().get(0));

        assertEquals(1, manifestResourceJson.keySet().size());

        ManifestResource manifestResource_ = JsonHandler.gson.fromJson(manifestResourceJson.toString(), ManifestResource.class);
        assertEquals(manifestResource, manifestResource_);
    }

    @Test
    public void customPropertiesNotSerialized() {
        ManifestResource manifestResource = new ManifestResource();
        manifestResource.withCustomProperty("key", new JsonPrimitive("value"));
        System.out.println(manifestResource);
        assertFalse(manifestResource.toJson().contains("custom_properties"));
    }
}