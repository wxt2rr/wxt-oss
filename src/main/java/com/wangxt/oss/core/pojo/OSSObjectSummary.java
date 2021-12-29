package com.wangxt.oss.core.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * @desc 根据前缀获取文件信息
 * @author wangxt
 * @date 2021/10/20 14:08
 */
public class OSSObjectSummary {
    /**
     * fileKey
     */
    private String fileKey;

    /**
     * 文件md5
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
}
