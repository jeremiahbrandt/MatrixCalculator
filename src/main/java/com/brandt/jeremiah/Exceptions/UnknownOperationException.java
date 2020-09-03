package com.brandt.jeremiah.Exceptions;

public class UnknownOperationException extends Exception {
    private String operationString;

    public UnknownOperationException(String operationString) {
        this.operationString = operationString;
    }

    public String getOperationString() {
        return operationString;
    }
}
