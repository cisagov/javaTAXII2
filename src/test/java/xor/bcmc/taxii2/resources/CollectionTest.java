package xor.bcmc.taxii2.resources;

import org.junit.Test;
import xor.bcmc.taxii2.JsonHandler;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

class CollectionTest {

    private static Collection collection;

    static {
        collection = new Collection();
        collection.withId(UUID.randomUUID().toString());
        collection.withTitle("collection-1");
        collection.withDescription("Collection description");
        collection.withCanRead(true);
        collection.withCanWrite(false);
        collection.withMediaTypes(Arrays.asList("taxii2.0"));
        System.out.println(collection.toJson());
    }

    @Test
    void serializeAndDeserialize() {
        String json = collection.toJson();
        assertEquals(Collection.fromJson(json), collection);
    }
}