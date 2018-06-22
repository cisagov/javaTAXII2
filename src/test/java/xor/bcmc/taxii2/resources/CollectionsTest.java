package xor.bcmc.taxii2.resources;

import com.google.gson.JsonPrimitive;
import org.junit.Test;
import xor.bcmc.taxii2.JsonHandler;

import java.util.Arrays;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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
        assertThat(JsonHandler.getInstance().getGson().fromJson(json, Collections.class), equalTo(collections));
        assertEquals(collections.getCollections().get(0), collection);
    }
}
