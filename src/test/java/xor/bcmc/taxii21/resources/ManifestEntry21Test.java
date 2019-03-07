package xor.bcmc.taxii21.resources;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.junit.Test;
import xor.bcmc.taxii2.Constants;
import xor.bcmc.taxii2.JsonHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class ManifestEntry21Test {
    @Test
    public void serializeAndDeserialize() {
        ManifestEntry21 manifestEntry = new ManifestEntry21();
        manifestEntry.setId("indicator--29aba82c-5393-42a8-9edb-6a2cb1df070b");
        manifestEntry.setDateAdded(ZonedDateTime.now());
        manifestEntry.setVersion("2016-11-03T12:30:59.000Z");
        manifestEntry.setMediaType(Constants.MediaTypes.STIX);

        String json = manifestEntry.toJson();
        assertThat(JsonHandler.getInstance().getGson().fromJson(json, ManifestEntry21.class), equalTo(manifestEntry));
    }

    @Test
    public void serializeAndDeserializeEmptyList() {
        ManifestEntry21 manifestEntry = new ManifestEntry21();
        manifestEntry.setId("indicator--29aba82c-5393-42a8-9edb-6a2cb1df070b");

        JsonObject jsonObject = manifestEntry.toJsonElement().getAsJsonObject();
        assertThat(jsonObject.get("versions"), equalTo(null));
        assertThat(jsonObject.get("media_types"), equalTo(null));

        ManifestEntry21 manifestEntry_ = JsonHandler.getInstance().getGson().fromJson(jsonObject, ManifestEntry21.class);
        assertThat(manifestEntry_.getVersion(), equalTo(null));
        assertThat(manifestEntry_.getVersion(), equalTo(null));
    }

    @Test
    public void withoutOptionalFieldsEquals() {
        ManifestEntry21 manifestEntry1 = new ManifestEntry21();
        manifestEntry1.setId("indicator--29aba82c-5393-42a8-9edb-6a2cb1df070b");

        ManifestEntry21 manifestEntry2 = new ManifestEntry21();
        manifestEntry2.setId("indicator--x");

        assertTrue(!manifestEntry1.equals(manifestEntry2));
    }

    @Test
    public void withoutOptionalFieldsValidate() {
        ManifestEntry21 manifestEntry = new ManifestEntry21();
        manifestEntry.setId("indicator--29aba82c-5393-42a8-9edb-6a2cb1df070b");

        assertEquals(0, manifestEntry.validate().keySet().size());
    }

    @Test
    public void checkSerialization() {
        ManifestEntry21 manifestEntry = new ManifestEntry21();
        manifestEntry.setId("indicator--29aba82c-5393-42a8-9edb-6a2cb1df070b");
        manifestEntry.setDateAdded(ZonedDateTime.now());
        manifestEntry.setVersion("2016-11-03T12:30:59.000Z");
        manifestEntry.setMediaType(Constants.MediaTypes.STIX_21);

        JsonObject manifestEntryJson = manifestEntry.toJsonElement().getAsJsonObject();
        assertEquals(manifestEntryJson.get("id").getAsString(), manifestEntry.getId());
        assertEquals(manifestEntryJson.get("date_added").getAsString(), manifestEntry.getDateAdded().withZoneSameInstant(ZoneId.of("Z")).toString());
        assertEquals(manifestEntryJson.get("version").getAsString(), manifestEntry.getVersion());
        assertEquals(manifestEntryJson.get("media_type").getAsString(), manifestEntry.getMediaType());

        assertEquals(4, manifestEntryJson.keySet().size());

        ManifestEntry21 manifestEntry_ = JsonHandler.getInstance().fromJson(manifestEntryJson.toString(), ManifestEntry21.class);
        assertEquals(manifestEntry, manifestEntry_);
    }

    @Test
    public void customPropertiesNotSerialized() {
        ManifestEntry21 manifestEntry = new ManifestEntry21();
        manifestEntry.withCustomProperty("key", new JsonPrimitive("value"));
        System.out.println(manifestEntry);
        assertFalse(manifestEntry.toJson().contains("custom_properties"));
    }
}
