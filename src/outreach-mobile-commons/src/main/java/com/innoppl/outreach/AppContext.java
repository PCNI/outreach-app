package com.innoppl.outreach;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *
 * @author Jerald Mejarla
 */
public class AppContext implements ApplicationContextAware {

    private static ApplicationContext ctxt;

    @Override
    public void setApplicationContext(final ApplicationContext globalAppContext)
            throws BeansException {
        AppContext.ctxt = globalAppContext;

    }

    /**
     *      
     *
     * @return {@link ApplicationContext}      
     */
    public static ApplicationContext getApplicationContext() {
        return ctxt;

    }
}