package com.innoppl.outreach.service.rest;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;

import com.innoppl.outreach.service.ServiceException;

/**
 *
 * @author Jerald Mejarla
 */
public final class ExceptionHelper {

    private ExceptionHelper() {
    }

    /**
     *
     * @param exception
     * @return
     */
    public static String getErrorCode(final ServiceException exception) {
        return exception.getErrorCode().name();
    }

    /**
     *
     * @param exception
     * @return
     */
    public static int getStatus(final ServiceException exception) {
        return exception.getErrorCode().getHttpStatusCode();
    }

    /**
     *
     * @param exception
     * @return
     */
    public static String getMessage(final Locale locale, 
    		final ServiceException exception) {
    	
        return exception.getErrorCode().getDescription(locale);
    }

    /**
     * 
     * @param aThrowable
     * @return {@link String} Exception Stack Trace
     */
    public static String getStackTrace(final Throwable aThrowable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }
}
