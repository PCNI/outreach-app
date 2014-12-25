package com.innoppl.outreach.service;

import com.innoppl.outreach.AppContext;
import java.util.Locale;

/**
 *
 * @author Jerald Mejarla
 */
public enum Errors {

    E_UNKNOWN_ERROR(500),
    E_USER_NOT_FOUND(402),
    E_INVALID_PASSWORD(402),
    E_USER_DISABLED(402),
    E_LOV_TYPE_NOT_FOUND(500);

    /**
     *
     * @param httpStatusCode
     * @param resourceName
     */
    private Errors(final int httpStatusCode) {
        this.code = httpStatusCode;
    }

    /**
     *
     * @param locale {@link Locale}
     * @return user understandable description of the error
     */
    public String getDescription(final Locale locale) {
        return AppContext.getApplicationContext().getMessage(this.name(),
                new Object[]{}, locale);
    }

    /**
     *
     * @return HTTP Status Code
     */
    public int getHttpStatusCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + this.name();
    }
    private final int code;
}