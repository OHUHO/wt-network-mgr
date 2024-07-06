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
@TableName("w_experience")
@Schema(description = "Experience")
public class Experience implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "经验id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "经验种类")
    private Integer experienceKindId;

    @Schema(description = "网络设备名称")
    private String deviceName;

    @Schema(description = "网络设备所处区域")
    private String deviceArea;

    @Schema(description = "网络测试信号差异")
    private String testSign;

    @Schema(description = "技术经验标题")
    private String title;

    @Schema(description = "技术经验内容")
    private String content;

    @Schema(description = "创建时间")
    private String publishDate;

    @Schema(description = "逻辑删除")
    @TableLogic
    private Integer isDelete;
}
