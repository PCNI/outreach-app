package com.innoppl.outreach.service.rest;

/**
 *
 * @author Jerald Mejarla
 */
public class ResponseHeader {

    private final transient int status;
    private final transient String code;
    private final transient String message;
    private final transient long count;

    public ResponseHeader(final int status, final String code, 
            final String message, final long count) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.count = count;
    }

    /**
     *
     * @return internal error code
     */
    public String getCode() {
        return code;
    }

    /**
     *
     * @return user understandable response message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @return response HTTP status
     */
    public int getStatus() {
        return status;
    }

    /**
     * 
     * @return result count
     */
    public long getCount() {
        return count;
    }    
}
