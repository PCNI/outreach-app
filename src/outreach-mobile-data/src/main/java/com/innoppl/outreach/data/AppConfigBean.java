package com.innoppl.outreach.data;

/**
 *
 * @author Jerald Mejarla
 */
public class AppConfigBean {
    private String cdnBaseUrl;
    private String cdnOriginDir;

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
}
