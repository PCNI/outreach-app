package com.innoppl.outreach.logger;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author Jerald Mejarla
 */
public final class LoggerUtils {

    /**
     *
     * @param exception
     * @return
     */
    public static String getStackTrace(final Throwable exception) {
        final StringWriter sWriter = new StringWriter();
        final PrintWriter writer = new PrintWriter(sWriter);
        exception.printStackTrace(writer);
        return sWriter.toString();
    }
}
