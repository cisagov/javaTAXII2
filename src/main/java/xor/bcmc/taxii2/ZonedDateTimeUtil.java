package xor.bcmc.taxii2;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class ZonedDateTimeUtil {
    public static final int MAX_PRECISION = 9;
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

    /**
     *
     * TODO - optimize - we shouldn't have to loop in parseFractionalSeconds()
     *
     * @param timestampStr
     * @return
     */
    public static ZonedDateTime fromString(String timestampStr) {
        ZonedDateTime date = parseStandard(timestampStr);
        if (date != null) {
            return date;
        }

        date = parseFractionalSeconds(timestampStr);
        if (date != null) {
            return date;
        }

        return null;
    }

    public static String toString (ZonedDateTime dateTime) {
        String timestampStr = dateTime.toString();
        String YMD_T_HMSN = timestampStr.split("Z")[0];
        String[] parts = YMD_T_HMSN.split("\\.");
        String YMD_T_HMS = parts[0];

        StringBuilder sb = new StringBuilder()
                .append(YMD_T_HMS)
                .append(".");

        if (parts.length > 1) {
            sb.append(String.format("%0$-9s", parts[1]).replace(" ", "0"));
        } else {
            sb.append(String.format("%0$-9s", "").replace(" ", "0"));
        }

        sb.append("Z");
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
        for (int i = MAX_PRECISION-1; i >= 0; i--) {
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
