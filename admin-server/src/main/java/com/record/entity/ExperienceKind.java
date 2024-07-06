package com.record.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("w_experience_kind")
@Schema(name = "ExperienceKind对象", description = "")
public class ExperienceKind implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(name = "经验种类id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(name = "经验名称")
    private String kindName;

    @Schema(name = "创建时间")
    private String publishDate;
}
