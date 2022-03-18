package com.wangxt.oss.core.service.impl;

import com.wangxt.oss.core.config.IOSSConfig;
import com.wangxt.oss.core.pojo.*;
import com.wangxt.oss.core.service.IOSS;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author wangxt
 * @description 腾讯云的COS
 * @date 2022/3/2 10:01
 **/
public class TxCos implements IOSS {

    @Override
    public IOSSConfig getOssConfig() {
        return null;
    }

    @Override
    public PutObjectResult putFile(String finalKey, InputStream is) {
        return null;
    }

    @Override
    public PutObjectResult putFile(String finalKey, InputStream is, String contentType) {
        return null;
    }

    @Override
    public PutObjectResult putFile(String finalKey, byte[] byts) {
        return null;
    }

    @Override
    public PutObjectResult putFile(String finalKey, byte[] byts, String contentType) {
        return null;
    }

    @Override
    public PutObjectResult putFile(String finalKey, InputStream is, ObjectMetadata meta) {
        return null;
    }

    @Override
    public PutObjectResult putFile(String finalKey, byte[] byts, ObjectMetadata meta) {
        return null;
    }

    @Override
    public OSSObject getFile(String finalKey) {
        return null;
    }

    @Override
    public OSSObject getFile(String finalKey, long rangeFrom, long rangeTo) {
        return null;
    }

    @Override
    public OSSObject getFile(String finalKey, String style) {
        return null;
    }

    @Override
    public ObjectMetadata getObjectMetadata(String finalKey) {
        return null;
    }

    @Override
    public CopyObjectResult copyFile(String fromKey, String toKey) {
        return null;
    }

    @Override
    public CopyObjectResult copyFileNoCatch(String fromKey, String toKey) throws Exception {
        return null;
    }

    @Override
    public CopyObjectResult copyFile2Bucket(String fromKey, IOSS targetBucket, String toKey) {
        return null;
    }

    @Override
    public CopyObjectResult copyFile(String fromKey, String toKey, ObjectMetadata targetFileObjectMetadata) {
        return null;
    }

    @Override
    public CopyObjectResult copyFile2Bucket(String fromKey, IOSS targetBucket, String toKey, ObjectMetadata targetFileObjectMetadata) {
        return null;
    }

    @Override
    public boolean delFile(String finalKey) {
        return false;
    }

    @Override
    public String getDownloadExpUrl(String finalKey, int expiration) {
        return null;
    }

    @Override
    public String getDownloadExpUrl(String finalKey, Date expiration) {
        return null;
    }

    @Override
    public String getPathUrl(String finalKey) {
        return null;
    }

    @Override
    public List<OSSObjectSummary> listObjects(String bTypeName, String prefix) {
        return null;
    }
}
