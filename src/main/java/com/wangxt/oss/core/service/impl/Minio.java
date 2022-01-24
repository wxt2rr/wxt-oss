package com.wangxt.oss.core.service.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.wangxt.oss.core.config.IOSSConfig;
import com.wangxt.oss.core.pojo.CopyObjectResult;
import com.wangxt.oss.core.pojo.OSSObject;
import com.wangxt.oss.core.pojo.ObjectMetadata;
import com.wangxt.oss.core.pojo.PutObjectResult;
import com.wangxt.oss.core.service.IOSS;
import io.minio.*;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * minio 实现类
 */
public class Minio implements IOSS {

    private MinioClient minioClient;

    private final IOSSConfig ossConfig;

    /**
     * 获取配置参数类
     */
    @Override
    public IOSSConfig getOssConfig() {
        return this.ossConfig;
    }

    /**
     * @param ossConfig oss配置
     * @return Minio对象
     * @author wangxt
     * @description 构造方法
     * @date 2022/1/24 0024 14:07
     **/
    public Minio(IOSSConfig ossConfig){
        this.ossConfig = ossConfig;
        try {
            minioClient = MinioClient.builder().endpoint(ossConfig.getEndpoint()).
                    credentials(ossConfig.getAccessId(), ossConfig.getAccessKey()).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件方法
     *
     * @param finalKey oss文件路径
     * @param is       文件流
     * @return PutObjectResult
     */
    @Override
    public PutObjectResult putFile(String finalKey, InputStream is) {
        if (StringUtils.isBlank(finalKey) || Objects.isNull(is)) {
            return null;
        }

        try {
            PutObjectArgs args = PutObjectArgs.builder().stream(is, is.available(), -1).
                    bucket(ossConfig.getBucketName()).object(finalKey).build();
            ObjectWriteResponse objectWriteResponse = minioClient.putObject(args);
            return new PutObjectResult(objectWriteResponse.etag());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Exception ignore) {
            }
        }
        return null;
    }

    /**
     * 上传文件方法
     *
     * @param finalKey    oss文件路径
     * @param is          文件流
     * @param contentType 文件类型  eg:image/png
     * @return PutObjectResult
     */
    @Override
    public PutObjectResult putFile(String finalKey, InputStream is, String contentType) {
        if (StringUtils.isBlank(finalKey) || Objects.isNull(is)) {
            return null;
        }

        try {
            PutObjectArgs.Builder builder = PutObjectArgs.builder().stream(is, is.available(), -1).
                    bucket(ossConfig.getBucketName()).object(finalKey);
            if (StringUtils.isNotBlank(contentType)) {
                builder.contentType(contentType);
            }
            ObjectWriteResponse objectWriteResponse = minioClient.putObject(builder.build());
            return new PutObjectResult(objectWriteResponse.etag());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Exception ignore) {
            }
        }
        return null;
    }

    /**
     * 上传文件方法
     *
     * @param finalKey oss文件路径
     * @param bytes     文件字节数组
     * @return PutObjectResult
     */
    @Override
    public PutObjectResult putFile(String finalKey, byte[] bytes) {
        return putFile(finalKey, new ByteArrayInputStream(bytes));
    }

    /**
     * 上传文件方法
     *
     * @param finalKey    oss文件路径
     * @param bytes        文件字节数组
     * @param contentType 文件类型  eg:image/png
     * @return PutObjectResult
     */
    @Override
    public PutObjectResult putFile(String finalKey, byte[] bytes, String contentType) {
        return putFile(finalKey, new ByteArrayInputStream(bytes), contentType);
    }

    /**
     * 上传文件方法
     *
     * @param finalKey oss文件路径
     * @param is       文件流
     * @param meta     存储对象元数据
     * @return PutObjectResult
     */
    @Override
    public PutObjectResult putFile(String finalKey, InputStream is, ObjectMetadata meta) {
        if (StringUtils.isBlank(finalKey) || Objects.isNull(is)) {
            return null;
        }

        try {
            ListMultimap<String, String> multimapHeaders = ArrayListMultimap.create();
            if (meta.getHeaders() != null) {
                for (Map.Entry<String, List<String>> header : meta.getHeaders().entrySet()) {
                    multimapHeaders.putAll(header.getKey(), header.getValue());
                }
            }

            PutObjectArgs args = PutObjectArgs.builder().contentType(meta.getContentType()).stream(is, is.available(), -1).
                    bucket(ossConfig.getBucketName()).object(finalKey).headers(multimapHeaders).userMetadata(meta.getUserMetadata()).build();
            ObjectWriteResponse objectWriteResponse = minioClient.putObject(args);
            return new PutObjectResult(objectWriteResponse.etag());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException ignore) {
            }
        }
        return null;
    }

    /**
     * 上传文件方法
     *
     * @param finalKey oss文件路径
     * @param bytes     文件字节数组
     * @param meta     存储对象元数据
     * @return PutObjectResult
     */
    @Override
    public PutObjectResult putFile(String finalKey, byte[] bytes, ObjectMetadata meta) {
        return putFile(finalKey, new ByteArrayInputStream(bytes), meta);
    }

    /**
     * 获取文件
     * @param finalKey oss文件路径
     * @return 文件对象
     */
    @Override
    public OSSObject getFile(String finalKey) {
        if (StringUtils.isBlank(finalKey)) {
            return null;
        }

        try {
            GetObjectArgs args = GetObjectArgs.builder().bucket(ossConfig.getBucketName()).object(finalKey).build();
            GetObjectResponse object = minioClient.getObject(args);
            return new OSSObject(object);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取文件
     * @param finalKey oss文件路径
     * @param rangeFrom 文件流起始位置
     * @param rangeTo 文件流终止位置
     * @return 文件对象
     */
    @Override
    public OSSObject getFile(String finalKey, long rangeFrom, long rangeTo) {
        if (StringUtils.isBlank(finalKey)) {
            return null;
        }

        try {
            GetObjectArgs args = GetObjectArgs.builder().bucket(ossConfig.getBucketName()).object(finalKey).offset(rangeFrom).length(rangeTo).build();
            GetObjectResponse object = minioClient.getObject(args);
            return new OSSObject(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取文件
     * @param finalKey oss文件路径
     * @param style 文件格式
     * @return 文件对象
     */
    @Override
    public OSSObject getFile(String finalKey, String style) {
        return getFile(finalKey);
    }

    /**
     * 获取文件元数据
     * @param finalKey oss文件路径
     * @return 文件对象元数据
     */
    @Override
    public ObjectMetadata getObjectMetadata(String finalKey) {
        if (StringUtils.isBlank(finalKey)) {
            return null;
        }

        StatObjectArgs args = StatObjectArgs.builder().bucket(ossConfig.getBucketName()).object(finalKey).build();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        try {
            StatObjectResponse statObjectResponse = minioClient.statObject(args);
            objectMetadata.setETag(statObjectResponse.etag());
            objectMetadata.setSize(statObjectResponse.size());
            objectMetadata.setLastModified(statObjectResponse.lastModified().toLocalDateTime());
            objectMetadata.setContentType(statObjectResponse.contentType());
            objectMetadata.setUserMetadata(statObjectResponse.userMetadata());
            objectMetadata.setHeaders(statObjectResponse.headers().toMultimap());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return objectMetadata;
    }

    /**
     * 复制文件
     * @param fromKey 源文件路径
     * @param toKey 目标文件路径
     * @return 复制操作结果
     */
    @Override
    public CopyObjectResult copyFile(String fromKey, String toKey) {
        return copyFile2Bucket(fromKey, this, toKey);
    }

    /**
     * 复制文件
     * @param fromKey 源文件路径
     * @param toKey 目标文件路径
     * @return 复制操作结果
     */
    @Override
    public CopyObjectResult copyFileNoCatch(String fromKey, String toKey) throws Exception {
        if (StringUtils.isBlank(fromKey) || StringUtils.isBlank(toKey)) {
            return null;
        }

        CopySource source = CopySource.builder().bucket(ossConfig.getBucketName()).object(fromKey).build();
        CopyObjectArgs args = CopyObjectArgs.builder().bucket(ossConfig.getBucketName()).object(toKey).source(source).build();

        ObjectWriteResponse objectWriteResponse = minioClient.copyObject(args);
        String etag = objectWriteResponse.etag();
        return new CopyObjectResult(etag);
    }

    /**
     * 复制文件到其它桶
     * @param fromKey 源文件路径
     * @param targetOss 目标桶名称
     * @param toKey 目标文件路径
     * @return 复制操作结果
     */
    @Override
    public CopyObjectResult copyFile2Bucket(String fromKey, IOSS targetOss, String toKey) {
        if (StringUtils.isBlank(fromKey) || StringUtils.isBlank(toKey)) {
            return null;
        }

        CopySource source = CopySource.builder().bucket(ossConfig.getBucketName()).object(fromKey).build();
        CopyObjectArgs args = CopyObjectArgs.builder().bucket(targetOss.getOssConfig().getBucketName()).object(toKey).source(source).build();

        try {
            ObjectWriteResponse objectWriteResponse = minioClient.copyObject(args);
            String etag = objectWriteResponse.etag();
            return new CopyObjectResult(etag);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 复制文件并指定目标文件元数据信息
     * @param fromKey 源文件路径
     * @param toKey 目标文件路径
     * @param targetFileObjectMetadata 目标文件元数据信息
     * @return 复制操作结果
     */
    @Override
    public CopyObjectResult copyFile(String fromKey, String toKey, ObjectMetadata targetFileObjectMetadata) {
        return copyFile2Bucket(fromKey, this, toKey, targetFileObjectMetadata);
    }

    /**
     * 复制文件到其它桶，并指定目标文件元数据信息
     * @param fromKey 源文件路径
     * @param targetBucket 目标桶名称
     * @param toKey 目标文件路径
     * @param targetFileObjectMetadata 目标文件元数据信息
     * @return 复制操作结果
     */
    @Override
    public CopyObjectResult copyFile2Bucket(String fromKey, IOSS targetBucket, String toKey, ObjectMetadata targetFileObjectMetadata) {
        if (StringUtils.isBlank(fromKey) || StringUtils.isBlank(toKey)) {
            return null;
        }

        try (InputStream is = getFile(fromKey).getInputStream()) {
            PutObjectResult putObjectResult = targetBucket.putFile(toKey, is, targetFileObjectMetadata);
            return new CopyObjectResult(putObjectResult.getETag());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除文件对象
     * @param finalKey oss路径
     * @return true 删除成功
     */
    @Override
    public boolean delFile(String finalKey) {
        if (StringUtils.isBlank(finalKey)) {
            return false;
        }

        RemoveObjectArgs args = RemoveObjectArgs.builder().bucket(ossConfig.getBucketName()).object(finalKey).build();
        try {
            minioClient.removeObject(args);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 获取文件下载地址
     * @param finalKey 文件oss路径
     * @param expiration 过期时间(单位：分钟)
     * @return 下载地址
     */
    @Override
    public String getDownloadExpUrl(String finalKey, int expiration) {
        return null;
    }

    /**
     * 获取文件下载地址
     * @param finalKey 文件oss路径
     * @param expiration 到期时间
     * @return 下载地址
     */
    @Override
    public String getDownloadExpUrl(String finalKey, Date expiration) {
        return null;
    }
}
