package com.wangxt.oss.core.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author wangxt
 * @description oss配置类
 * @date 2022/1/24 14:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
public class IOSSConfig {
    /**
     * accessId
     */
    private final String accessId;

    /**
     * accessKey
     */
    private final String accessKey;

    /**
     * 默认 endpoint
     */
    private final URL endpoint;

    /**
     * 公网 endpoint
     */
    private final URL internetEndpoint;

    /**
     * cnd endpoint
     */
    private final URL cdnEndpoint;

    /**
     * 内网 endpoint
     */
    private final URL intranetEndpoint;

    /**
     * 桶名称
     */
    private final String bucketName;


    public IOSSConfig(String accessId, String accessKey, URL endpoint, String bucketName) throws MalformedURLException {
        this(accessId, accessKey, endpoint, bucketName, (String) null, null, null);
    }

    public IOSSConfig(String accessId, String accessKey, String endpoint, String bucketName) throws MalformedURLException {
        this(accessId, accessKey, new URL(endpoint), bucketName);
    }

    public IOSSConfig(String accessId, String accessKey, URL endpoint
            , String bucketName, String internetEndpoint, String cdnEndpoint, String intranetEndpoint) throws MalformedURLException {
        this(accessId, accessKey, endpoint, bucketName
                , internetEndpoint == null ? null : new URL(internetEndpoint)
                , internetEndpoint == null ? null : new URL(cdnEndpoint)
                , internetEndpoint == null ? null : new URL(intranetEndpoint)
        );

    }

    public IOSSConfig(String accessId, String accessKey, URL endpoint
            , String bucketName, URL internetEndpoint, URL cdnEndpoint, URL intranetEndpoint) {

        this.accessId = accessId;
        this.accessKey = accessKey;
        this.endpoint = endpoint;
        this.bucketName = bucketName;
        this.internetEndpoint = internetEndpoint;
        this.cdnEndpoint = cdnEndpoint;
        this.intranetEndpoint = intranetEndpoint;

    }

    public enum OSSUrlDomainType {
        CDN(),
        OSS(),
        OSS_INTERNAL();

        OSSUrlDomainType() {

        }

        /**
         * @param url 源url地址
         * @return 替换 endpoint之后的URL对象
         * @author wangxt
         * @description
         * @date 2022/1/24 0024 14:03
         **/
        public URL replaceOSSUrlEndpoint(URL url, IOSSConfig ossConfig) {
            URL targetDomain = getEndpoint(ossConfig);

            if (targetDomain != null) {
                try {
                    url = new URL(targetDomain.getProtocol(), targetDomain.getHost(), targetDomain.getPort(), url.getFile());
                } catch (MalformedURLException e) {
                    throw new RuntimeException("replaceOSSUrlDomain error ", e);
                }
            }
            return url;
        }

        public URL getEndpoint(IOSSConfig ossConfig) {
            URL targetDomain;
            if (OSSUrlDomainType.CDN.equals(this)) {
                targetDomain = ossConfig.getCdnEndpoint();
            } else if (OSSUrlDomainType.OSS.equals(this)) {
                targetDomain = ossConfig.getInternetEndpoint();
            } else if (OSSUrlDomainType.OSS_INTERNAL.equals(this)) {
                targetDomain = ossConfig.getIntranetEndpoint();
            } else {
                targetDomain = ossConfig.getEndpoint();
            }

            return targetDomain;
        }
    }
}
