package com.wangxt.oss.core.pojo;

/**
 * Created by yunheng on 2017/6/7.
 */
public class OSSPostUploadPmsBean {
    private String accessid;
    private String policy;
    private String signature;
    private String fileKey;
    private String host;
    private String md5;

    public String getAccessid() {
        return accessid;
    }

    public void setAccessid(String accessid) {
        this.accessid = accessid;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
