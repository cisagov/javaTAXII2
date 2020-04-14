package xor.bcmc.taxii2.validation;

import xor.bcmc.taxii2.JsonHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Errors extends HashMap<String, String> {
    private static final Pattern UUID_PATTERN = Pattern.compile("[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89ab][a-f0-9]{3}-[0-9a-f]{12}");

    public Errors rejectIfNullOrEmpty(String key, String value) {
        if (value == null || value.trim().isEmpty()) {
            this.put(key, String.format("'%s' cannot be null or empty", key));
        }
        return this;
    }

    public Errors rejectIfNullOrEmpty(String key, List value) {
        if (value == null || value.isEmpty()) {
            this.put(key, String.format("'%s' cannot be null or empty", key));
        }
        return this;
    }

    public Errors rejectIfNotContains(String key, List listValue, String requiredValue) {
        if (listValue == null || listValue.isEmpty() || requiredValue == null || requiredValue.isEmpty() || !listValue.contains(requiredValue)) {
            this.put(key, String.format("'%s' MUST contain '%s'", key, requiredValue));
        }
        return this;
    }

    //uuid version 4
    public Errors rejectIfNotValidUUID(String key, String value) {
        Matcher matcher = UUID_PATTERN.matcher(value);
        if (!matcher.matches()) {
            this.put(key, key + " is not a valid UUID.");
        }
        return this;
    }

    /**
     * whitelist: letter upper/lower case, digit, and underscore
     */
    public Errors rejectIfNotInWhitelist(String key, String value) {
        Pattern pattern = Pattern.compile("[-a-zA-Z0-9_]{1,100}");
        Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()) {
            this.put(key, String.format("'%s' is not valid", key));
        }
        return this;
    }

    public Errors reject(String key, String description) {
        this.put(key, description);
        return this;
    }

    public Errors rejectIfLessThanOrEqual(String key, Integer maxContentLength, int val)
    {
        if ((maxContentLength != null) &&  (maxContentLength <= val)) {
            this.put(key, String.format("'%s' is less than or equal '%d'", key, val ));
        }

        return this;
    }
    @Override
    public String toString() {
        return JsonHandler.getInstance().toJson(this);
    }


}
