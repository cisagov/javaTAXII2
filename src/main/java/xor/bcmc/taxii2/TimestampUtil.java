package xor.bcmc.taxii2;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

public class TimestampUtil {
    public static final int MAX_PRECISION = 6;
    private static final String STANDARD_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    private static final DateTimeFormatter STANDARD_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern(STANDARD_FORMAT)
            .toFormatter()
            .withZone(ZoneId.of("UTC"));

    private static DateTimeFormatter[] FRACTIONAL_FORMATTERS = new DateTimeFormatter[MAX_PRECISION];

    static {
        StringBuilder prefix = new StringBuilder("yyyy-MM-dd'T'HH:mm:ss.");
        for (int i=0; i < MAX_PRECISION; i++) {
            prefix.append("S");
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendPattern(prefix + "'Z'")
                    .toFormatter()
                    .withZone(ZoneId.of("UTC"));
            FRACTIONAL_FORMATTERS[i] = formatter;
        }

    }

    public static Timestamp fromString(String timestampStr) {
        ZonedDateTime date = parseStandard(timestampStr);
        if (date != null) {
            return Timestamp.valueOf(date.toLocalDateTime());
        }

        date = parseFractionalSeconds(timestampStr);
        if (date != null) {
            return Timestamp.valueOf(date.toLocalDateTime());
        }

        return null;
    }

    public static String toString (Timestamp timestamp) {
        String[] timestampParts = timestamp.toString().split(" ");
        String YMD_part = timestampParts[0];
        String[] hmsn_parts = timestampParts[1].split("\\.");
        String HMS_part = hmsn_parts[0];
        String N_part = String.format("%0$-9s", hmsn_parts[1]).replace(" ", "0");

        StringBuilder sb = new StringBuilder()
                .append(YMD_part).append("T").append(HMS_part).append(".").append(N_part).append("Z");
        return sb.toString();
    }

    private static ZonedDateTime parseStandard (String timestampStr) {
        try {
            return ZonedDateTime.from(STANDARD_FORMATTER.parse(timestampStr));
        } catch (Exception e) {
            return null;
        }
    }

    private static ZonedDateTime parseFractionalSeconds (String timestampStr) {
        for (int i=0; i < MAX_PRECISION; i++) {
            DateTimeFormatter formatter = FRACTIONAL_FORMATTERS[i];
            try {
                return ZonedDateTime.from(formatter.parse(timestampStr));
            } catch (Exception e) {
                continue;
            }
        }
        return null;
    }
}
