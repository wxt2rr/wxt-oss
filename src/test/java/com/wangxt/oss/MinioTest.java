package com.wangxt.oss;

import com.wangxt.oss.core.config.IOSSConfig;
import com.wangxt.oss.core.factory.OssFactory;
import com.wangxt.oss.core.pojo.OSSObject;
import com.wangxt.oss.core.pojo.PutObjectResult;
import com.wangxt.oss.core.service.IOSS;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.net.URL;

public class MinioTest {

    private IOSS minioOss;

    @Before
    public void init() throws Exception{
        minioOss = OssFactory.MinioOss(IOSSConfig.builder().
                accessId("").
                accessKey("").
                endpoint(new URL("https://decs.pcl.ac.cn:3861")).
                bucketName("study").
                build());
    }

    @Test
    public void putFile() throws Exception{
        String webUrl = "https://img0.baidu.com/it/u=2216119779,653055386&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500";
        try (InputStream is = new URL(webUrl).openStream()) {
            PutObjectResult putObjectResult = minioOss.putFile("test.jpg", is);
            Assert.assertNotNull(putObjectResult);
            Assert.assertNotNull(putObjectResult.getETag());
        }
    }

    @Test
    public void getFile() throws Exception{
        OSSObject file = minioOss.getFile("test.jpg");
        Assert.assertNotNull(file);
        Assert.assertNotNull(file.getInputStream());
    }
}
