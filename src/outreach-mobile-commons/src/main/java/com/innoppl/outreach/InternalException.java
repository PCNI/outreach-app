package com.innoppl.outreach;

/**
 *
 * @author Jerald Mejarla
 */
public class InternalException extends Exception {

    private static final long serialVersionUID = 5684149391929954579L;
    private final transient String errorCode;

    /**
     *
     * @param currentCause Exception
     * @param newErrorCode Error Code
     */
    public InternalException(
            final Throwable currentCause,
            final String newErrorCode) {

        super(currentCause);
        this.errorCode = newErrorCode;
    }

    public InternalException(
            final String errorMessage,
            final String newErrorCode) {

        super(errorMessage);
        this.errorCode = newErrorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }
}
