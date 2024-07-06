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
@TableName("w_message")
@Schema(name = "Message对象", description = "")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(name = "短信id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(name = "发送短信的用户")
    private Long sendUserId;

    @Schema(name = "接收短信的用户")
    private Long receiveUserId;

    @Schema(name = "短信内容")
    private String content;

    @Schema(name = "发布时间")
    private String publishDate;

    @Schema(name = "是否已读标识（0未读，1已读）")
    private Integer identify;

    @Schema(name = "逻辑删除")
    @TableLogic
    private Integer isDelete;
}
