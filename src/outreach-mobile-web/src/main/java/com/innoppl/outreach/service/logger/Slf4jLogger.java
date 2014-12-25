package com.innoppl.outreach.service.logger;

import java.text.MessageFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jerald Mejarla
 */
@Component
public class Slf4jLogger implements AbstractLogger {

    @Override
    public boolean isLogLevel(final LogLevel logLevel, final Class<?> clazz) {

        boolean result;
        switch (logLevel) {
            case DEBUG:
                result = getLogger(clazz).isDebugEnabled();
                break;
            case ERROR:
                result = getLogger(clazz).isErrorEnabled();
                break;
            case INFO:
                result = getLogger(clazz).isInfoEnabled();
                break;
            case TRACE:
                result = getLogger(clazz).isTraceEnabled();
                break;
            case WARN:
                result = getLogger(clazz).isWarnEnabled();
                break;
            default:
                result = false;
        }
        return result;
    }

    @Override
    public void log(final LogLevel logLevel, final Class<?> clazz,
            final Throwable throwable, final String pattern,
            final Object... arguments) {

        switch (logLevel) {
            case DEBUG:
                debug(clazz, throwable, pattern, arguments);
                break;

            case ERROR:
                error(clazz, throwable, pattern, arguments);
                break;

            case INFO:
                info(clazz, throwable, pattern, arguments);
                break;

            case TRACE:
                trace(clazz, throwable, pattern, arguments);
                break;

            case WARN:
                warn(clazz, throwable, pattern, arguments);
                break;
        }
    }

    /**
     *
     * @param clazz
     * @param throwable
     * @param pattern
     * @param arguments
     */
    private void debug(final Class<?> clazz, final Throwable throwable,
            final String pattern, final Object... arguments) {

        if (throwable != null) {
            getLogger(clazz).debug(format(pattern, arguments), throwable);
        } else {
            getLogger(clazz).debug(format(pattern, arguments));
        }
    }

    /**
     *
     * @param clazz
     * @param throwable
     * @param pattern
     * @param arguments
     */
    private void error(final Class<?> clazz, final Throwable throwable,
            final String pattern, final Object... arguments) {

        if (throwable != null) {
            getLogger(clazz).error(format(pattern, arguments), throwable);
        } else {
            getLogger(clazz).error(format(pattern, arguments));
        }
    }

    /**
     *
     * @param clazz
     * @param throwable
     * @param pattern
     * @param arguments
     */
    private void info(final Class<?> clazz, final Throwable throwable,
            final String pattern, final Object... arguments) {

        if (throwable != null) {
            getLogger(clazz).info(format(pattern, arguments), throwable);
        } else {
            getLogger(clazz).info(format(pattern, arguments));
        }
    }

    /**
     *
     * @param clazz
     * @param throwable
     * @param pattern
     * @param arguments
     */
    private void trace(final Class<?> clazz, final Throwable throwable,
            final String pattern, final Object... arguments) {

        if (throwable != null) {
            getLogger(clazz).trace(format(pattern, arguments), throwable);
        } else {
            getLogger(clazz).trace(format(pattern, arguments));
        }
    }

    /**
     *
     * @param clazz
     * @param throwable
     * @param pattern
     * @param arguments
     */
    private void warn(final Class<?> clazz, final Throwable throwable,
            final String pattern, final Object... arguments) {

        if (throwable != null) {
            getLogger(clazz).warn(format(pattern, arguments), throwable);
        } else {
            getLogger(clazz).warn(format(pattern, arguments));
        }
    }

    /**
     *
     * @param pattern
     * @param arguments
     * @return logging format
     */
    private String format(final String pattern, final Object... arguments) {
        return MessageFormat.format(pattern, arguments);
    }

    /**
     *
     * @param clazz
     * @return {@link Logger}
     */
    private Logger getLogger(final Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}
