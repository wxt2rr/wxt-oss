package com.wangxt.oss.core.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 上传文件结果对象
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PutObjectResult {
    private String eTag;
}
