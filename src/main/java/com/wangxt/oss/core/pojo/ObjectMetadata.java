package com.wangxt.oss.core.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
/**
 * @desc 文件存储元数据对象
 * @author wangxt
 * @date 2021/10/20 8:15
 */
public class ObjectMetadata {
    /**
     * eTag 文件MD5值
     */
    private String eTag;
    /**
     * 文件大小
     */
    private long size;
    /**
     * 文件最后修改时间
     */
    private LocalDateTime lastModified;
    /**
     * 文件类型
     */
    private String contentType;
    /**
     * 用户自定义元数据
     */
    private Map<String, String> userMetadata;

    /**
     * 用户自定义响应头
     */
    private Map<String, List<String>> headers;

    public String getETag() {
        return eTag;
    }

    public void setETag(String eTag) {
        this.eTag = eTag;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Map<String, String> getUserMetadata() {
        return userMetadata;
    }

    public void setUserMetadata(Map<String, String> userMetadata) {
        this.userMetadata = userMetadata;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }
}
