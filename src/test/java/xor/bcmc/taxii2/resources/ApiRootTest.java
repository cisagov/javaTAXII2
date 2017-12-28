package xor.bcmc.taxii2.resources;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

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

    @Test
    void equivalience() {
        String json = "{\"title\":\"Api root 1 title\",\"description\":\"Api root 1 description\",\"versions\":[\"taxii-2.0\",\"taxii-2.1\"],\"max_content_length\":50}";
        ApiRoot apiRoot1 = ApiRoot.fromJson(json);
        ApiRoot apiRoot2 = ApiRoot.fromJson(json);
        assertEquals(apiRoot1, apiRoot2);
    }
}