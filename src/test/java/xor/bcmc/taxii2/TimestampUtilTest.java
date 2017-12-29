package xor.bcmc.taxii2;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static xor.bcmc.taxii2.TimestampUtil.MAX_PRECISION;

public class TimestampUtilTest {

    @Test
    public void test () {
        String timestampStr = "2016-01-01T01:01:01Z";
        Timestamp timestamp = TimestampUtil.fromString(timestampStr);

        assertDate(timestamp,
                2016, 1, 1,
                1, 1, 1,
                0);
    }

    @Test
    public void testFractionalSeconds () {
        String timestampStr = "2016-01-01T01:01:01.";
        Timestamp timestamp = null;
        String fractionalSeconds = "";
        for (int i = 0; i < MAX_PRECISION; i++) {
            fractionalSeconds += "1";
            String fractionalSeconds_ = fractionalSeconds;
            for (int n = 0; n < (9 - fractionalSeconds.length()); n++) {
                fractionalSeconds_ += "0";
            }
            timestamp = TimestampUtil.fromString(timestampStr + fractionalSeconds + "Z");
            assertDate(timestamp,
                    2016, 1, 1,
                    1, 1, 1,
                    Integer.valueOf(fractionalSeconds_));
        }
    }

    @Test
    public void test_toString1 () {
        String timestampStr = "2016-01-01T01:01:01Z";
        Timestamp timestamp = TimestampUtil.fromString(timestampStr);

        String timestampStr_ = TimestampUtil.toString(timestamp);
        assertThat(timestampStr_, equalTo("2016-01-01T01:01:01.000000000Z"));
    }

    @Test
    public void test_toString2 () {
        String timestampStr = "2016-01-01T01:01:01.0001Z";
        Timestamp timestamp = TimestampUtil.fromString(timestampStr);

        String timestampStr_ = TimestampUtil.toString(timestamp);
        assertThat(timestampStr_, equalTo("2016-01-01T01:01:01.000100000Z"));
    }

    @Test
    public void test_now () {
        Timestamp before = Timestamp.from(Instant.now());
        Timestamp timestamp = TimestampUtil.now();
        Timestamp after = Timestamp.from(Instant.now());

        assert timestamp.after(before) || timestamp.equals(before);
        assert timestamp.before(after) || timestamp.equals(after);
    }


    private static void assertDate (Timestamp timestamp,
                                    int year, int month, int day,
                                    int hour, int min, int second,
                                    int nano) {
        LocalDateTime time = timestamp.toLocalDateTime();
        ZonedDateTime time_zoned = time.atZone(ZoneId.of("UTC"));

        assertThat(time_zoned.getYear(), equalTo(year));
        assertThat(time_zoned.getMonthValue(), equalTo(month));
        assertThat(time_zoned.getDayOfMonth(), equalTo(day));
        assertThat(time_zoned.getHour(), equalTo(hour));
        assertThat(time_zoned.getMinute(), equalTo(min));
        assertThat(time_zoned.getSecond(), equalTo(second));
        assertThat(time_zoned.getNano(), equalTo(nano));
    }
}
