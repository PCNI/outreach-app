package com.innoppl.outreach.service;

/**
 *
 * @author Jerald Mejarla
 */
public class ServiceException extends Exception {

    private static final long serialVersionUID = 5684149391929954579L;
    private final transient Errors errorCode;

    /**
     *
     * @param currentCause Exception
     * @param newErrorCode Error Code
     */
    public ServiceException(
            final Throwable currentCause,
            final Errors newErrorCode) {

        super(currentCause);
        this.errorCode = newErrorCode;
    }

    /**
     *
     * @param newErrorCode
     */
    public ServiceException(final Errors newErrorCode) {

        super();
        this.errorCode = newErrorCode;
    }

    /**
     *
     * @return Internal Error Code
     */
    public Errors getErrorCode() {
        return this.errorCode;
    }
}
