package xor.bcmc.taxii2;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class TimestampUtil {
    public static final int MAX_PRECISION = 6;
    private static final String STANDARD_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final String STRING_GENERATION_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'";

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
        LocalDateTime date = parseStandard(timestampStr);
        if (date != null) {
            return Timestamp.valueOf(date);
        }

        date = parseFractionalSeconds(timestampStr);
        if (date != null) {
            return Timestamp.valueOf(date);
        }

        return null;
    }

    public static String toString (Timestamp timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat(STRING_GENERATION_FORMAT);
        return sdf.format(timestamp);
    }

    private static LocalDateTime parseStandard (String timestampStr) {
        try {
            return LocalDateTime.from(STANDARD_FORMATTER.parse(timestampStr));
        } catch (Exception e) {
            return null;
        }
    }

    private static LocalDateTime parseFractionalSeconds (String timestampStr) {
        for (int i=0; i < MAX_PRECISION; i++) {
            DateTimeFormatter formatter = FRACTIONAL_FORMATTERS[i];
            try {
                return LocalDateTime.from(formatter.parse(timestampStr));
            } catch (Exception e) {
                continue;
            }
        }
        return null;
    }
}
