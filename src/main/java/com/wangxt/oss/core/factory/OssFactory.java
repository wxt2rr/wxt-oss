package com.wangxt.oss.core.factory;

import com.wangxt.oss.core.config.IOSSConfig;
import com.wangxt.oss.core.service.IOSS;
import com.wangxt.oss.core.service.impl.AliOss;
import com.wangxt.oss.core.service.impl.Minio;
import com.wangxt.oss.core.service.impl.S3Client;

/**
 * 工厂方法，屏蔽实体对象，不对外暴露
 */
public class OssFactory {

    /**
     * 获取Minio对象
     *
     * @param config oss配置对象
     * @return oss对象
     */
    public static IOSS MinioOss(IOSSConfig config) {
        return new Minio(config);
    }

    /**
     * 获取AliOss对象
     *
     * @param config oss配置对象
     * @return oss对象
     */
    public static IOSS AliOss(IOSSConfig config) {
        return new AliOss(config);
    }

    /**
     * 获取S3 通用对象存储对象
     * @param config oss 配置对象
     * @return oss对象
     */
    public static IOSS S3Oss(IOSSConfig config) {
        return new S3Client(config);
    }
}
