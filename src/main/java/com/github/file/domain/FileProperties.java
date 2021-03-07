package com.github.file.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("octopus.file")
public class FileProperties {
    private String cloudType;
    private String endpoint;
    private String region;
    private String accessKeyId;
    private String accessKeySecret;
    private String staticDomain;
    private String applicationName;

    public String getCloudType() {
        return cloudType;
    }

    public void setCloudType(String cloudType) {
        this.cloudType = cloudType;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getStaticDomain() {
        return staticDomain;
    }

    public void setStaticDomain(String staticDomain) {
        this.staticDomain = staticDomain;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
}