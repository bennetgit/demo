package com.wfc.cxf.core.exception;

public class CheckShipmentException extends RuntimeException {

    private static final long serialVersionUID = -6003597707765416714L;

    public CheckShipmentException() {
        super();
    }

    public CheckShipmentException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckShipmentException(String message) {
        super(message);
    }

    public CheckShipmentException(Throwable cause) {
        super(cause);
    }

}
