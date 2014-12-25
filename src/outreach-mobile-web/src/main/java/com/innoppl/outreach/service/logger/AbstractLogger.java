package com.innoppl.outreach.service.logger;

/**
 * 
 * @author Jerald Mejarla
 */
public interface AbstractLogger {

    /**
     * 
     * @param logLevel
     * @param clazz
     * @return {@link LogLevel}
     */
    boolean isLogLevel(LogLevel logLevel, Class<?> clazz);

    /**
     * 
     * @param logLevel
     * @param clazz
     * @param throwable
     * @param pattern
     * @param arguments
     */
    void log(LogLevel logLevel, Class<?> clazz, Throwable throwable,
    		String pattern, Object... arguments);
}
