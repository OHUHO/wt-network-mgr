package com.record.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author OR
 * @since 2023-05-05
 */
@Data
@Builder
@Accessors(chain = true)
@TableName("w_notice")
@Schema(name = "Notice", description = "")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "系统公告管理id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "系统公告内容")
    private String content;

    @Schema(description = "发布者")
    private Long userId;

    @Schema(description = "创建时间")
    private String publishDate;

    @Schema(description = "逻辑删除")
    @TableLogic
    private Integer isDelete;
}
