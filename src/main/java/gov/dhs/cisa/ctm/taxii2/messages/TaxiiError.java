package gov.dhs.cisa.ctm.taxii2.messages;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;

import gov.dhs.cisa.ctm.taxii2.resources.TaxiiResource;
import gov.dhs.cisa.ctm.taxii2.validation.Errors;

import java.util.Map;

public class TaxiiError extends TaxiiResource{

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
    private JsonObject details;

    public TaxiiError() {}

    public String getTitle() {
        return title;
    }

    public TaxiiError withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TaxiiError withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getErrorId() {
        return errorId;
    }

    public TaxiiError withErrorId(String errorId) {
        this.errorId = errorId;
        return this;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public TaxiiError withErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public TaxiiError withHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public String getExternalDetails() {
        return externalDetails;
    }

    public TaxiiError withExternalDetails(String externalDetails) {
        this.externalDetails = externalDetails;
        return this;
    }

    public JsonObject getDetails() {
        return details;
    }

    public TaxiiError withDetails(JsonObject details) {
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
