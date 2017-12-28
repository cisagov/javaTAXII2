package xor.bcmc.taxii2;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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

    private static void assertDate (Timestamp timestamp,
                                    int year, int month, int day,
                                    int hour, int min, int second,
                                    int nano) {
        LocalDateTime time = timestamp.toLocalDateTime();
        assertThat(time.getYear(), equalTo(year));
        assertThat(time.getMonthValue(), equalTo(month));
        assertThat(time.getDayOfMonth(), equalTo(day));
        assertThat(time.getHour(), equalTo(hour));
        assertThat(time.getMinute(), equalTo(min));
        assertThat(time.getSecond(), equalTo(second));
        assertThat(time.getNano(), equalTo(nano));
    }
}
