package com.wangxt.oss.core.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 * @desc web upload auth param
 * @author wangxt
 * @date 2021/10/26 8:33
 * @param
 * @return
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OSSPostUploadPmsBean {
    private String host;
    private String fileKey;
    private String url;
    Map<String,String> paramMap;

    @Override
    public String toString() {
        return "OSSPostUploadPmsBean{" +
                "host='" + host + '\'' +
                ", fileKey='" + fileKey + '\'' +
                ", url='" + url + '\'' +
                ", paramMap=" + paramMap +
                '}';
    }
}
