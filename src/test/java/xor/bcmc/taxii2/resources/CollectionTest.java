package xor.bcmc.taxii2.resources;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.junit.Test;
import xor.bcmc.taxii2.JsonHandler;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class CollectionTest {

    private static Collection collection;

    static {
        collection = new Collection();
        collection.withId(UUID.randomUUID().toString());
        collection.withTitle("collection-1");
        collection.withDescription("Collection description");
        collection.withCanRead(true);
        collection.withCanWrite(false);
        collection.withMediaTypes(Arrays.asList("taxii2.0"));
        collection.withCustomProperty("x_flarecloud_property", new JsonPrimitive("value1"));
        System.out.println(collection.toJson());
    }

    @Test
    public void serializeAndDeserialize() {
        String json = collection.toJson();
        assertEquals(Collection.fromJson(json), collection);
    }

    @Test
    public void serializeAndDeserializeEmptyList() {
        Collection collection = new Collection();
        collection.setId("id1");
        collection.setTitle("title");
        collection.setCanRead(true);
        collection.setCanWrite(true);
        JsonObject json = collection.toJsonElement().getAsJsonObject();
        assertThat(json.get("media_types"), equalTo(null));

        Collection collection_ = Collection.fromJson(json.toString());
        assertThat(collection_.getMediaTypes(), equalTo(null));
    }

    @Test
    public void withoutOptionalFieldsEquals() {
        Collection collection_ = new Collection();
        collection_.setId("id1");
        collection_.setTitle("title");
        collection_.setCanRead(true);
        collection_.setCanWrite(true);
        assertTrue(!collection.equals(collection_));
    }

    @Test
    public void withoutOptionalFieldsValidate() {
        Collection collection_ = new Collection();
        collection_.setId("3a5910b5-b4f5-4f1e-8852-a8eec1664847");
        collection_.setTitle("title");
        collection_.setCanRead(true);
        collection_.setCanWrite(true);
        assertTrue(collection_.validate().keySet().size() == 0);
    }

    @Test
    public void withoutOptionalFieldsserializeAndDeserialize() {
        Collection collection_ = new Collection();
        collection_.setId("id1");
        collection_.setTitle("title");
        collection_.setCanRead(true);
        collection_.setCanWrite(true);
        String json = collection_.toJson();
        assertEquals(Collection.fromJson(json), collection_);
    }

    @Test
    public void checkSerialization() {
        JsonObject collectionJson = collection.toJsonElement().getAsJsonObject();
        assertTrue(collectionJson.get("id").getAsString().equals(collection.getId()));
        assertTrue(collectionJson.get("title").getAsString().equals(collection.getTitle()));
        assertTrue(collectionJson.get("description").getAsString().equals(collection.getDescription()));
        assertTrue(collectionJson.get("can_read").getAsBoolean() == collection.getCanRead());
        assertTrue(collectionJson.get("can_write").getAsBoolean() == collection.getCanWrite());
        assertTrue(collectionJson.get("media_types").getAsJsonArray().get(0).getAsString().equals("taxii2.0"));

        assertTrue(collectionJson.keySet().size() == 6);

        Collection collection_ = Collection.fromJson(collectionJson.toString());
        assertTrue(collection.equals(collection_));
    }

    @Test
    public void customPropertiesNotSerialized() {
        Collection collection = new Collection();
        collection.withCustomProperty("key", new JsonPrimitive("value"));
        System.out.println(collection);
        assertFalse(collection.toJson().contains("custom_properties"));
    }
}