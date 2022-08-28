package com.wangxt.oss.core.service.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.wangxt.oss.core.config.IOSSConfig;
import com.wangxt.oss.core.pojo.*;
import com.wangxt.oss.core.service.IOSS;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class S3Client implements IOSS {

    private AmazonS3 amazonS3;

    private IOSSConfig config;

    public S3Client(IOSSConfig config) {
        AWSCredentials awsCredentials = new BasicAWSCredentials(config.getAccessKey(), config.getAccessKey());
        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
        this.amazonS3 = AmazonS3Client.builder().
                withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(config.getEndpoint().getHost(), "")).
                withCredentials(awsCredentialsProvider).
                disableChunkedEncoding().
                withPathStyleAccessEnabled(Boolean.TRUE).
                build();
        this.config = config;
    }

    @Override
    public IOSSConfig getOssConfig() {
        return this.config;
    }

    @Override
    public PutObjectResult putFile(String finalKey, InputStream is) {
        return putFile(finalKey, is, new ObjectMetadata());
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
        com.amazonaws.services.s3.model.ObjectMetadata objectMetadata = new com.amazonaws.services.s3.model.ObjectMetadata();
        objectMetadata.setContentLength(meta.getSize());
        objectMetadata.setContentType(meta.getContentType());

        com.amazonaws.services.s3.model.PutObjectResult putObjectResult = amazonS3.putObject(config.getBucketName(), finalKey, is, objectMetadata);
        return new PutObjectResult(putObjectResult.getETag());
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
