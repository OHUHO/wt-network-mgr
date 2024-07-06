package com.record.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author OR
 * @since 2023-05-05
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("w_log")
@Schema(description = "Log对象")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "维护和优化该条记录的用户")
    private Long userId;

    @Schema(description = "网络维护和优化日志中年份")
    private Integer year;

    @Schema(description = "网络维护和优化日志中月份")
    private Integer month;

    @Schema(description = "网络维护和优化日志中日期")
    private Integer day;

    @Schema(description = "网络维护和优化日志中标题")
    private String title;

    @Schema(description = "网络维护和优化日志中内容")
    private String content;

    @Schema(description = "创建时间")
    private String publishDate;

    @Schema(description = "逻辑删除")
    @TableLogic
    private Integer isDelete;
}
