package gov.dhs.cisa.ctm.taxii2.validation;

public interface Validatable {
    Errors validate();
    boolean isValid();
}
