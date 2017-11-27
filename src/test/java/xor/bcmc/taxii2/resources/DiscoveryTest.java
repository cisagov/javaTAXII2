package xor.bcmc.taxii2.resources;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DiscoveryTest {

    private static Discovery discovery;

    static {
        discovery = new Discovery();
        discovery.withTitle("Discovery Root 1");
        discovery.withDescription("Discovery Description");
        discovery.withContact("Discovery Contact");
        discovery.withDefaultApiRoot("Default Api Root");
        discovery.withApiRoots(Arrays.asList("Api Root 1", "Api Root 2"));
        System.out.println(discovery.toJson());
    }

    @Test
    void serializeAndDeserialize() {
        String json = discovery.toJson();
        assertEquals(new Discovery().fromJson(json), discovery);
    }

}