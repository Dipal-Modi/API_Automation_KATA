package com.api.model;

import java.util.ArrayList;

public class BookingInputValidationErrorDTO {


        public int errorCode;
        public String error;
        public String errorMessage;
        public ArrayList<String> fieldErrors;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ArrayList<String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(ArrayList<String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }


}
