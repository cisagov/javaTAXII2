package xor.bcmc.taxii2.resources;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ApiRootTest {

    private static ApiRoot apiRoot;

    static {
        apiRoot = new ApiRoot();
        apiRoot.withTitle("Api Root 1");
        apiRoot.withDescription("Api Description");
        apiRoot.withMaxContentLength(10);
        apiRoot.withVersions(Arrays.asList("Version 1", "Version 2"));
    }

    @Test
    void serializeAndDeserialize() {
        String json = apiRoot.toJson();
        System.out.println(json);
        assertEquals(ApiRoot.fromJson(json), apiRoot);

        String toString = apiRoot.toString();
        System.out.println(toString);
        assertEquals(ApiRoot.fromJson(toString), apiRoot);
    }
}