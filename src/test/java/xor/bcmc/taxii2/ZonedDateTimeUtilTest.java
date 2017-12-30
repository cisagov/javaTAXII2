package xor.bcmc.taxii2;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static xor.bcmc.taxii2.ZonedDateTimeUtil.MAX_PRECISION;

public class ZonedDateTimeUtilTest {

    @Test
    public void test () {
        String timestampStr = "2016-01-01T01:01:01Z";
        ZonedDateTime timestamp = ZonedDateTimeUtil.fromString(timestampStr);

        assertDate(timestamp,
                2016, 1, 1,
                1, 1, 1,
                0);
    }

    @Test
    public void testFractionalSeconds () {
        String timestampStr = "2016-01-01T01:01:01.";
        ZonedDateTime timestamp = null;
        String fractionalSeconds = "";
        for (int i = 0; i < MAX_PRECISION; i++) {
            fractionalSeconds += "1";
            String fractionalSeconds_ = fractionalSeconds;
            for (int n = 0; n < (9 - fractionalSeconds.length()); n++) {
                fractionalSeconds_ += "0";
            }
            timestamp = ZonedDateTimeUtil.fromString(timestampStr + fractionalSeconds + "Z");
            assertDate(timestamp,
                    2016, 1, 1,
                    1, 1, 1,
                    Integer.valueOf(fractionalSeconds_));
        }
    }

    @Test
    public void test_toString1 () {
        String timestampStr = "2016-01-01T01:01:01Z";
        ZonedDateTime timestamp = ZonedDateTimeUtil.fromString(timestampStr);

        String timestampStr_ = ZonedDateTimeUtil.toString(timestamp);
        assertThat(timestampStr_, equalTo("2016-01-01T01:01:01.000000000Z"));
    }

    @Test
    public void test_toString2 () {
        String timestampStr = "2016-01-01T01:01:01.0001Z";
        ZonedDateTime timestamp = ZonedDateTimeUtil.fromString(timestampStr);

        String timestampStr_ = ZonedDateTimeUtil.toString(timestamp);
        assertThat(timestampStr_, equalTo("2016-01-01T01:01:01.000100000Z"));
    }

    @Test
    public void test_toString3 () {
        String timestampStr = "2016-01-01T01:01:01.000000001Z";
        ZonedDateTime timestamp = ZonedDateTimeUtil.fromString(timestampStr);

        String timestampStr_ = ZonedDateTimeUtil.toString(timestamp);
        assertThat(timestampStr_, equalTo("2016-01-01T01:01:01.000000001Z"));
    }

    private static void assertDate (ZonedDateTime timestamp,
                                    int year, int month, int day,
                                    int hour, int min, int second,
                                    int nano) {
        assertThat(timestamp.getYear(), equalTo(year));
        assertThat(timestamp.getMonthValue(), equalTo(month));
        assertThat(timestamp.getDayOfMonth(), equalTo(day));
        assertThat(timestamp.getHour(), equalTo(hour));
        assertThat(timestamp.getMinute(), equalTo(min));
        assertThat(timestamp.getSecond(), equalTo(second));
        assertThat(timestamp.getNano(), equalTo(nano));
    }
}
