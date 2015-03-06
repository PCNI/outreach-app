package com.innoppl.outreach.service.business.impl;

import com.innoppl.outreach.data.AppConfigBean;
import com.innoppl.outreach.service.Errors;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.EmailService;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jerald Mejarla
 */
@Service("EmailService")
public class EmailServiceImpl implements EmailService {

    @Autowired
    private AppConfigBean appConfigBean;

    @Override
    public void sendPasswordResetEmail(String email, String password)
            throws ServiceException {
        final String message = "Your new password is : " + password;
        sendEmail(email, "Outreach Mobile Password Reset", message);
    }

    /**
     * @param mailTo
     * @param subject
     * @param emailBody
     * @throws ServiceException
     */
    private void sendEmail(final String mailTo, final String subject,
            final String emailBody) throws ServiceException {
        try {
            final HtmlEmail email = new HtmlEmail();
            email.setHostName(appConfigBean.getSmtpHost());
            email.setSmtpPort(appConfigBean.getSmtpPort());
            email.setAuthenticator(new DefaultAuthenticator(
                    appConfigBean.getSmtpUserName(),
                    appConfigBean.getSmtpPassword()));
            if (appConfigBean.isSmtpSSLEnabled()) {
                email.setSSLOnConnect(true);
            }
            email.setSmtpPort(appConfigBean.getSmtpPort());
            email.addTo(mailTo);
            email.setFrom(appConfigBean.getEmailFrom());
            email.setSubject(subject);
            email.setHtmlMsg(emailBody);
            email.setTextMsg("Your email client does not support "
                    + "HTML messages. Please enable");
            email.send();
        } catch (Exception ex) {
            throw new ServiceException(ex, Errors.E_EMAIL_SEND_FAILED);
        }
    }
}
