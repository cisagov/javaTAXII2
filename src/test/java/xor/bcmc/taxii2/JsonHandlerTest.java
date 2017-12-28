package xor.bcmc.taxii2;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.Expose;
import org.junit.Test;

import java.sql.Timestamp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class JsonHandlerTest {

    @Test
    public void test1 () {
        String timestampStr = "2016-01-01T01:01:01Z";
        Timestamp timestamp = TimestampUtil.fromString(timestampStr);

        TimestampJson timestampJson = new TimestampJson(timestamp);
        JsonHandler jsonHandler = JsonHandler.getInstance();

        JsonObject timestampJsonObject = new JsonParser().parse(jsonHandler.toJson(timestampJson)).getAsJsonObject();

        assertThat(timestampJsonObject.get("time").getAsString(), equalTo("2016-01-01T01:01:01.000000Z"));
    }

    @Test
    public void test2 () {
        String timestampStr = "2016-01-01T01:01:01.000001Z";
        Timestamp timestamp = TimestampUtil.fromString(timestampStr);

        TimestampJson timestampJson = new TimestampJson(timestamp);
        JsonHandler jsonHandler = JsonHandler.getInstance();

        JsonObject timestampJsonObject = new JsonParser().parse(jsonHandler.toJson(timestampJson)).getAsJsonObject();

        assertThat(timestampJsonObject.get("time").getAsString(), equalTo("2016-01-01T01:01:01.000001Z"));
    }

    class TimestampJson {
        @Expose
        private Timestamp time;

        public TimestampJson(Timestamp time) {
            this.time = time;
        }

        public Timestamp getTime() {
            return time;
        }

        public void setTime(Timestamp time) {
            this.time = time;
        }
    }
}
