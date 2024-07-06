package com.record.dto;

import com.record.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Builder
public class UserDTO {

    @Schema(name = "email",description = "邮箱")
    private String email;
    @Schema(name = "password",description = "密码")
    private String password;
    @Schema(name = "nickname",description = "昵称")
    private String nickname;
    @Schema(name = "role", description = "角色")
    private Role role;
}
