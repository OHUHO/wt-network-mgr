package com.record.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("w_token")
@Schema(name = "Token对象", description = "")
public class Token implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(name = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(name = "token")
    private String token;

    @Schema(name = "token type")
    private TokenType tokenType = TokenType.BEARER;

    @Schema(name = "revoked")
    public Boolean revoked;

    @Schema(name = "expired")
    public Boolean expired;

    @Schema(name = "user id")
    private Long userId;
}
