package xor.bcmc.taxii2.validation;

public interface Validatable {
    Errors validate();
    boolean isValid();
}
