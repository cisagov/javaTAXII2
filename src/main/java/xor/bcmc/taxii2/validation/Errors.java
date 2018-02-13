package xor.bcmc.taxii2.validation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Errors extends HashMap<String, String> {
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

    public Errors reject(String key, String description) {
        this.put(key, description);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");
        for (Map.Entry<String, String> entry : this.entrySet()) {
            stringBuilder.append(String.format("\"{}\": \"{}\", ", entry.getKey(), entry.getValue()));
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
