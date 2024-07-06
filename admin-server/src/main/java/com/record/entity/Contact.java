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
@TableName("w_contact")
@Schema(description = "Contact对象")
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    @Schema(description = "联络人姓名")
    private String name;

    @Schema(description = "联络人性别（0未知，1男，2女）")
    private Integer sex;

    @Schema(description = "联络人电话号码")
    private String phoneNumber;

    @Schema(description = "联络人email")
    private String email;

    @Schema(description = "联络人QQ号码")
    private String qq;

    @Schema(description = "联络人微信号")
    private String wechat;

    @Schema(description = "联络人工作单位")
    private String employer;

    @Schema(description = "联络人住址")
    private String address;

    @Schema(description = "联络人邮编")
    private String postcode;

    @Schema(description = "创建时间")
    private String publishDate;

    @Schema(description = "逻辑删除")
    @TableLogic
    private Integer isDelete;
}
