package com.record.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Collection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
@TableName("w_user")
@Schema(name = "User对象", description = "")
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @Schema(name = "用户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(name = "用户登录名(邮箱)")
    private String email;

    @Schema(name = "登录密码")
    private String password;

    @Schema(name = "用户昵称")
    private String nickname;

    @Schema(name = "用户角色（CHASER 一线人员，SUPERVISOR 部门主管，ADMIN 系统管理员）")
    private Role role = Role.USER;

    @Schema(name = "创建时间")
    private String createTime;

    @Schema(name = "逻辑删除（1，表示删除）")
    @TableLogic
    private Integer isDelete;

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
