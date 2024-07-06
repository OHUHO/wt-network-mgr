package com.record.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("w_schedule")
@Schema(description = "Schedule对象")
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    @Schema(description = "网络维护和优化日程中年份")
    private String year;

    @Schema(description = "网络维护和优化日程中月份")
    private String month;

    @Schema(description = "网络维护和优化日程中日期")
    private String day;

    @Schema(description = "网络维护和优化日程中标题")
    private String title;

    @Schema(description = "网络维护和优化日程中内容")
    private String content;

    @Schema(description = "创建时间")
    private String publishDate;

    @Schema(description = "逻辑删除")
    @TableLogic
    private Integer isDelete;
}
