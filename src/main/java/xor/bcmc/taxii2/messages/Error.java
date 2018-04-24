package xor.bcmc.taxii2.messages;

import xor.bcmc.taxii2.resources.TaxiiResource;
import xor.bcmc.taxii2.validation.Errors;

import java.util.Map;

public class Error extends TaxiiResource{
    private String title;
    private String description;
    private String errorId;
    private String errorCode;
    private String httpStatus;
    private String externalDetails;
    private Map<String, String> details;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getExternalDetails() {
        return externalDetails;
    }

    public void setExternalDetails(String externalDetails) {
        this.externalDetails = externalDetails;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public void setDetails(Map<String, String> details) {
        this.details = details;
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
