package gov.dhs.cisa.ctm.taxii2.resources;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import gov.dhs.cisa.ctm.taxii2.JsonHandler;
import gov.dhs.cisa.ctm.taxii2.resources.Collection;
import gov.dhs.cisa.ctm.taxii2.resources.Collections;

import org.junit.Test;

import java.util.Arrays;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class CollectionsTest {
    private static Collection collection;

    static {
        collection = new Collection();
        collection.withId(UUID.randomUUID().toString());
        collection.withTitle("collection-1");
        collection.withDescription("Collection description");
        collection.withCanRead(true);
        collection.withCanWrite(false);
        collection.withMediaTypes(Arrays.asList("taxii2.0"));
        collection.withCustomProperty("x_flarecloud_property1", new JsonPrimitive("value1"));
//        System.out.println(collection.toJson());
    }

    @Test
    public void serializeAndDeserialize() {
        Collections collections = new Collections(java.util.Collections.singletonList(collection));
        collections.withCustomProperty("x_flarecloud_property2", new JsonPrimitive("value2"));

        String json = collections.toJson();
        System.out.println(json);
        assertThat(JsonHandler.gson.fromJson(json, Collections.class), equalTo(collections));
        assertEquals(collections.getCollections().get(0), collection);
    }

    @Test
    public void serializeAndDeserializeEmptyList() {
        Collections collections = new Collections();

        JsonObject json = collections.toJsonElement().getAsJsonObject();
        assertThat(json.get("collections"), equalTo(null));

        Collections collections_ = JsonHandler.gson.fromJson(json, Collections.class);
        assertThat(collections_.getCollections(), equalTo(null));
    }

    @Test
    public void withoutOptionalFieldsEquals() {
        Collections collections1 = new Collections();
        Collections collections2 = new Collections(Arrays.asList(new Collection()));

        assertTrue(!collections1.equals(collections2));
    }

    @Test
    public void withoutOptionalFieldsValidate() {
        Collections collections1 = new Collections();

        assertTrue(collections1.validate().keySet().size() == 0);
    }

    @Test
    public void checkSerialization() {
        Collections collections = new Collections(Arrays.asList(collection));
        System.out.println(collections);

        JsonObject collectionsJson = collections.toJsonElement().getAsJsonObject();
        assertTrue(collectionsJson.get("collections") != null);

        JsonObject collectionJson = collectionsJson.get("collections").getAsJsonArray().get(0).getAsJsonObject();
        assertTrue(collectionJson.get("id").getAsString().equals(collection.getId()));
        assertTrue(collectionJson.get("title").getAsString().equals(collection.getTitle()));
        assertTrue(collectionJson.get("description").getAsString().equals(collection.getDescription()));
        assertTrue(collectionJson.get("can_read").getAsBoolean() == collection.getCanRead());
        assertTrue(collectionJson.get("can_write").getAsBoolean() == collection.getCanWrite());
        assertTrue(collectionJson.get("media_types").getAsJsonArray().get(0).getAsString().equals("taxii2.0"));

        assertTrue(collectionsJson.keySet().size() == 1);

        Collections collections_ = JsonHandler.gson.fromJson(collectionsJson.toString(), Collections.class);
        assertTrue(collections.equals(collections_));
    }

    @Test
    public void customPropertiesNotSerialized() {
        Collections collections = new Collections();
        collections.withCustomProperty("key", new JsonPrimitive("value"));
        System.out.println(collections);
        assertFalse(collections.toJson().contains("custom_properties"));
    }
}
