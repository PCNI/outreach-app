package com.innoppl.outreach.service;

import com.innoppl.outreach.AppContext;
import java.util.Locale;

/**
 *
 * @author Jerald Mejarla
 */
public enum Errors {

    E_UNKNOWN_ERROR(500),
    E_USER_NOT_FOUND(401),
    E_INVALID_PASSWORD(401),
    E_USER_DISABLED(401),
    E_LOV_TYPE_NOT_FOUND(400),
    E_SAVE_CLIENT_FAILED(400),
    E_ID_NULL(400),
    E_ID_NOT_NULL(400),
    E_PERSONAL_ID_NULL(400),
    E_PROJ_ENTRY_ID_NULL(400),
    E_CLIENT_NOT_FOUND(400),
    E_FILE_UPLOAD_FAILED(500),
    E_FILE_DELETE_FAILED(500),
    E_EMAIL_SEND_FAILED(500);

    /**
     *
     * @param httpStatusCode
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