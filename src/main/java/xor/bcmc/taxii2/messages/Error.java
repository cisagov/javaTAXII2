package xor.bcmc.taxii2.messages;

import com.google.gson.annotations.Expose;
import xor.bcmc.taxii2.resources.TaxiiResource;
import xor.bcmc.taxii2.validation.Errors;

import java.util.Map;

public class Error extends TaxiiResource{

    @Expose
    private String title;

    @Expose
    private String description;

    @Expose
    private String errorId;

    @Expose
    private String errorCode;

    @Expose
    private String httpStatus;

    @Expose
    private String externalDetails;

    @Expose
    private Map<String, String> details;

    public Error () {}

    public String getTitle() {
        return title;
    }

    public Error withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Error withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getErrorId() {
        return errorId;
    }

    public Error withErrorId(String errorId) {
        this.errorId = errorId;
        return this;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Error withErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public Error withHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public String getExternalDetails() {
        return externalDetails;
    }

    public Error withExternalDetails(String externalDetails) {
        this.externalDetails = externalDetails;
        return this;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public Error withDetails(Map<String, String> details) {
        this.details = details;
        return this;
    }


    @Override
    public Errors validate() {
        return new Errors();
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
