package com.innoppl.outreach.service.rest;

import com.innoppl.outreach.AppContext;
import com.innoppl.outreach.data.beans.ValueAndCount;
import com.innoppl.outreach.service.Errors;
import com.innoppl.outreach.service.Messages;
import com.innoppl.outreach.service.ServiceException;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import org.apache.commons.lang.LocaleUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @param <T> Entity Object
 * @author Jerald Mejarla
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseWrapper<T extends Serializable> {

    private final static Logger LOG
            = LoggerFactory.getLogger(ResponseWrapper.class);
    private final transient ResponseHeader header;
    private final transient List<T> body;

    /**
     *
     * @param localeString
     * @param exception
     */
    public ResponseWrapper(final String localeString,
            final ServiceException exception) {
        LOG.error(ExceptionHelper.getStackTrace(exception));
        final Locale locale = LocaleUtils.toLocale(localeString);
        final int status = ExceptionHelper.getStatus(exception);
        final String code = ExceptionHelper.getErrorCode(exception);
        final String message = ExceptionHelper.getMessage(locale, exception);
        this.header = new ResponseHeader(status, code, message, 0);
        this.body = new ArrayList<>(0);
    }

    /**
     *
     * @param exception
     * @param localeString
     * @param errors
     */
    public ResponseWrapper(final Exception exception, final String localeString,
            final Errors errors) {
        LOG.error(ExceptionHelper.getStackTrace(exception));
        final Locale locale = LocaleUtils.toLocale(localeString);
        final int status = errors.getHttpStatusCode();
        final String code = errors.name();
        final String message = errors.getDescription(locale);
        this.header = new ResponseHeader(status, code, message, 0);
        this.body = new ArrayList<>(0);
    }

    /**
     *
     * @param message
     * @param localeString
     * @param data
     */
    public ResponseWrapper(final Messages message, final String localeString,
            final T data) {
        if (data != null) {
            try {
                LOG.debug(
                        new ObjectMapper()
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(data));
            } catch (IOException ex) {
                LOG.debug(data.toString());
            }
        }
        final Locale locale = LocaleUtils.toLocale(localeString);
        this.body = new ArrayList<>(1);
        this.body.add(data);
        final String success
                = AppContext.getApplicationContext().getMessage(message.name(),
                        new Object[]{}, locale);
        this.header = new ResponseHeader(200, "0", success, 1);
    }

    /**
     *
     * @param localeString
     * @param data
     */
    public ResponseWrapper(final String localeString,
            final T data) {
        if (data != null) {
            try {
                LOG.debug(
                        new ObjectMapper()
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(data));
            } catch (IOException ex) {
                LOG.debug(data.toString());
            }
        }
        final Locale locale = LocaleUtils.toLocale(localeString);
        this.body = new ArrayList<>(1);
        this.body.add(data);
        final String success
                = AppContext.getApplicationContext().getMessage("SUCCESS_STR",
                        new Object[]{}, locale);
        this.header = new ResponseHeader(200, "0", success, 1);
    }

    /**
     *
     * @param localeString
     * @param data
     */
    public ResponseWrapper(final String localeString, final List<T> data) {
        if (data != null) {
            try {
                LOG.debug(
                        new ObjectMapper()
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(data));
            } catch (IOException ex) {
                LOG.debug(data.toString());
            }
        }
        final Locale locale = LocaleUtils.toLocale(localeString);
        this.body = data;
        final String success
                = AppContext.getApplicationContext().getMessage("SUCCESS_STR",
                        new Object[]{}, locale);
        final int size = data != null ? data.size() : 0;
        this.header = new ResponseHeader(200, "0", success, size);
    }

    /**
     *
     * @param localeString
     * @param data
     */
    public ResponseWrapper(final String localeString,
            final ValueAndCount<T> data) {
        if (data != null) {
            try {
                LOG.debug(
                        new ObjectMapper()
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(data.getData())
                        + ", Count: " + data.getCount());
            } catch (IOException ex) {
                LOG.debug(data.getData().toString());
            }
            this.body = data.getData();
        } else {
            this.body = null;
        }
        final Locale locale = LocaleUtils.toLocale(localeString);
        final String success
                = AppContext.getApplicationContext().getMessage("SUCCESS_STR",
                        new Object[]{}, locale);
        this.header = new ResponseHeader(200, "0", success, (data != null) ? data.getCount() : 0);
    }

    /**
     *
     * @return {@link List} T
     */
    public List<T> getBody() {
        if (body != null) {
            return Collections.unmodifiableList(this.body);
        } else {
            return Collections.emptyList();
        }
    }

    /**
     *
     * @return {@link ResponseHeader}
     */
    public ResponseHeader getHeader() {
        return this.header;
    }
}
