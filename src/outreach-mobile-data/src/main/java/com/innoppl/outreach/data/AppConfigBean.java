package com.innoppl.outreach.data;

/**
 *
 * @author Jerald Mejarla
 */
public class AppConfigBean {
    private String cdnBaseUrl;
    private String cdnOriginDir;
    private String domainVerifier;

    private String smtpHost;
    private Integer smtpPort = 465;
    private String smtpUserName;
    private String smtpPassword;
    private boolean smtpSSLEnabled = true;
    private String emailFrom;

    public String getCdnBaseUrl() {
        return cdnBaseUrl;
    }

    public void setCdnBaseUrl(String cdnBaseUrl) {
        this.cdnBaseUrl = cdnBaseUrl;
    }

    public String getCdnOriginDir() {
        return cdnOriginDir;
    }

    public void setCdnOriginDir(String cdnOriginDir) {
        this.cdnOriginDir = cdnOriginDir;
    }

    public String getDomainVerifier() {
        return domainVerifier;
    }

    public void setDomainVerifier(String domainVerifier) {
        this.domainVerifier = domainVerifier;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public Integer getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(Integer smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getSmtpUserName() {
        return smtpUserName;
    }

    public void setSmtpUserName(String smtpUserName) {
        this.smtpUserName = smtpUserName;
    }

    public String getSmtpPassword() {
        return smtpPassword;
    }

    public void setSmtpPassword(String smtpPassword) {
        this.smtpPassword = smtpPassword;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public boolean isSmtpSSLEnabled() {
        return smtpSSLEnabled;
    }

    public void setSmtpSSLEnabled(boolean smtpSSLEnabled) {
        this.smtpSSLEnabled = smtpSSLEnabled;
    }
}
