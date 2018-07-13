package xor.bcmc.taxii2;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.Expose;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class JsonHandlerTest {

    @Test
    public void testZonedDateTime1 () {
        String timestampStr = "2016-01-01T01:01:01Z";
        ZonedDateTime timestamp = ZonedDateTime.parse(timestampStr);

        ZonedDateTimeJson timestampJson = new ZonedDateTimeJson(timestamp);
        JsonHandler jsonHandler = JsonHandler.getInstance();

        JsonObject timestampJsonObject = new JsonParser().parse(jsonHandler.toJson(timestampJson)).getAsJsonObject();

        assertThat(timestampJsonObject.get("time").getAsString(), equalTo("2016-01-01T01:01:01Z"));
    }

    @Test
    public void testZonedDateTime2 () {
        String timestampStr = "2016-01-01T01:01:01.000001Z";
        ZonedDateTime timestamp = ZonedDateTime.parse(timestampStr);

        ZonedDateTimeJson timestampJson = new ZonedDateTimeJson(timestamp);

        JsonObject timestampJsonObject = new JsonParser().parse(JsonHandler.getInstance().toJson(timestampJson)).getAsJsonObject();

        assertThat(timestampJsonObject.get("time").getAsString(), equalTo("2016-01-01T01:01:01.000001Z"));
    }

    class ZonedDateTimeJson {
        private ZonedDateTime time;

        public ZonedDateTimeJson() {}

        public ZonedDateTimeJson(ZonedDateTime time) {
            this.time = time;
        }

        public ZonedDateTime getTime() {
            return time;
        }

        public void setTime(ZonedDateTime time) {
            this.time = time;
        }
    }

    @Test
    public void testDate1 () {
        // Note: Date has milliseconds precision - it will truncate
        String timestampStr = "2016-01-01T01:01:01.000001Z";
        ZonedDateTime timestamp = ZonedDateTime.parse(timestampStr);
        Date date = Date.from(timestamp.toInstant());

        DateJson dateJson = new DateJson(date);

        JsonObject dateJsonJson = new JsonParser().parse(JsonHandler.getInstance().toJson(dateJson)).getAsJsonObject();

        assertThat(dateJsonJson.get("time").getAsString(), equalTo("2016-01-01T01:01:01Z"));
    }

    class DateJson {
        private Date time;

        public DateJson() {}

        public DateJson(Date time) {
            this.time = time;
        }

        public Date getTime() {
            return time;
        }

        public void setTime(Date time) {
            this.time = time;
        }
    }
}
