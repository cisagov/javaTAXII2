package gov.dhs.cisa.ctm.taxii21.resources;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import gov.dhs.cisa.ctm.taxii2.JsonHandler;
import gov.dhs.cisa.ctm.taxii21.resources.ManifestEntry21;
import gov.dhs.cisa.ctm.taxii21.resources.ManifestResource21;

import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ManifestResource21Test {
    @Test
    public void serializeAndDeserialize() {
        ManifestEntry21 entry = new ManifestEntry21();
        entry.withId("id1");

        ManifestResource21 manifestResource = new ManifestResource21();
        manifestResource.setObjects(
                Collections.singletonList(entry));

        String json = manifestResource.toJson();
        ManifestResource21 manifestResource_ = JsonHandler.gson.fromJson(json, ManifestResource21.class);
        assertEquals(manifestResource, manifestResource_);
    }

    @Test
    public void serializeAndDeserializeEmptyList() {
        ManifestResource21 manifestResource = new ManifestResource21();

        String json = manifestResource.toJson();
        ManifestResource21 manifestResource_ = JsonHandler.gson.fromJson(json, ManifestResource21.class);
        assertEquals(manifestResource, manifestResource_);
    }

    @Test
    public void withoutOptionalFieldsEquals() {
        ManifestResource21 manifestResource1 = new ManifestResource21();
        ManifestResource21 manifestResource2 = new ManifestResource21();

        assertEquals(manifestResource1, manifestResource2);
    }

    @Test
    public void withoutOptionalFieldsValidate() {
        ManifestResource21 manifestResource = new ManifestResource21();

        assertEquals(0, manifestResource.validate().keySet().size());
    }

    @Test
    public void checkSerialization() {

        ManifestEntry21 manifestEntry = new ManifestEntry21();
        manifestEntry.setId("indicator--29aba82c-5393-42a8-9edb-6a2cb1df070b");
        manifestEntry.setDateAdded(ZonedDateTime.now());
        manifestEntry.setVersion("2016-11-03T12:30:59.000Z");
        manifestEntry.setMediaType("application/vnd.oasis.stix+json; version=2.0");

        ManifestResource21 manifestResource = new ManifestResource21(Collections.singletonList(manifestEntry));

        JsonObject manifestResourceJson = manifestResource.toJsonElement().getAsJsonObject();
        assertEquals(1, manifestResourceJson.get("objects").getAsJsonArray().size());

        JsonObject manifestEntryJson = manifestResourceJson.get("objects").getAsJsonArray().get(0).getAsJsonObject();
        assertEquals(manifestEntryJson.get("id").getAsString(), manifestEntry.getId());
        assertEquals(manifestEntryJson.get("date_added").getAsString(), manifestEntry.getDateAdded().withZoneSameInstant(ZoneId.of("Z")).toString());
        assertEquals(manifestEntryJson.get("version").getAsString(), manifestEntry.getVersion());
        assertEquals(manifestEntryJson.get("media_type").getAsString(), manifestEntry.getMediaType());

        assertEquals(1, manifestResourceJson.keySet().size());

        ManifestResource21 manifestResource_ = JsonHandler.gson.fromJson(manifestResourceJson.toString(), ManifestResource21.class);
        assertEquals(manifestResource, manifestResource_);
    }

    @Test
    public void customPropertiesNotSerialized() {
        ManifestResource21 manifestResource = new ManifestResource21();
        manifestResource.withCustomProperty("key", new JsonPrimitive("value"));
        System.out.println(manifestResource);
        assertFalse(manifestResource.toJson().contains("custom_properties"));
    }
}