package com.wangxt.oss.core.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;

@Getter
@Setter
public class IOSSConfig {
    private final String accessId;

    private final String accessKey;

    private final URL endpoint;

    private final URL internetEndpoint;

    private final URL cdnEndpoint;

    private final URL intranetEndpoint;

    private final String bucketName;

    public IOSSConfig(String accessId, String accessKey, URL endpoint, URL internetEndpoint, URL cdnEndpoint, URL intranetEndpoint, String bucketName) {
        this.accessId = accessId;
        this.accessKey = accessKey;
        this.endpoint = endpoint;
        this.internetEndpoint = internetEndpoint;
        this.cdnEndpoint = cdnEndpoint;
        this.intranetEndpoint = intranetEndpoint;
        this.bucketName = bucketName;
    }
}
