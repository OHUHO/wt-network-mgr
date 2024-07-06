package com.record.controller;

import com.record.dto.UserDTO;
import com.record.entity.User;
import com.record.service.UserService;
import com.record.utils.Result;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author OR
 * @since 2023-05-05
 */
@RestController
@RequestMapping("/api/v1/admin/user")
@Tag(name = "用户管理")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    private final UserService userService;

    @GetMapping("/find/email")
    @Operation(summary = "通过邮箱查找用户")
    @Parameter(required = true,name = "email",description = "邮箱",in = ParameterIn.QUERY)
    @PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR') or hasRole('ADMIN')")
    public Result findByEmail(String email){
        User user = userService.findByEmail(email);
        return Result.success("查询成功！",user);
    }

    @GetMapping("/find/all")
    // @PreAuthorize("hasAuthority('admin:read')")
    @PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR') or hasRole('ADMIN')")
    @Operation(summary = "查找所有的用户")
    public Result findAllUser(){
        List<User> users = userService.findAllUser();
        return Result.success("获取成功！",users);
    }

    @GetMapping("/find")
    @Operation(summary = "通过关键词查找用户")
    @Parameter(required = true, name = "keywords", description = "关键词", in = ParameterIn.QUERY)
    public Result findByKeywords(String keywords){
        if (StringUtils.isBlank(keywords)){
            return Result.error("关键词不能为空！");
        }
        List<User> users = userService.findByKeywords(keywords);
        return Result.success("获取成功！",users);
    }

    @PostMapping("/add")
    @Operation(summary = "添加用户")
    // @Parameter(required = true,name = "userDTO",description = "用户实体")
    public Result addUser(@RequestBody UserDTO userDTO){
        if (userDTO != null){
            userService.addUser(userDTO);
            return Result.success("添加成功！");
        }
        return Result.error("参数不能为空！");
    }

    @PutMapping("/update")
    @Operation(summary = "修改用户")
    @PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR') or hasRole('ADMIN')")
    @Parameters({@Parameter(name = "id",description = "用户id",in = ParameterIn.QUERY,required = true),
            @Parameter(name = "password",description = "用户密码",in = ParameterIn.QUERY),
            @Parameter(name = "role",description = "用户角色，可选值: CHASER, SUPERVISOR, ADMIN",in = ParameterIn.QUERY)})
    public Result updateUser(Long id,String password,String role){
        if (StringUtils.isNoneBlank(password) || StringUtils.isNoneBlank(role)){
            userService.updateUser(id,password,role);
            return Result.success("更新成功！");
        }
        return Result.error("参数不能为空！");
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除用户")
    @Parameter(required = true, name = "id",description = "用户id")
    public Result deleteUser(@PathVariable("id") Long id){
        if (id != null){
            userService.deleteUser(id);
            return Result.success("删除成功！");
        }
        return Result.error("参数不能为空！");
    }

    @GetMapping("/count")
    @Operation(summary = "查询系统总人数")
    @PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR') or hasRole('ADMIN')")
    // @Hidden
    public Result findUserCount(){
        long count = userService.count();
        return Result.success("查询成功！",count);
    }

    @GetMapping("/role")
    @Operation(summary = "查询系统每个角色的数量")
    @PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR') or hasRole('ADMIN')")
    // @Hidden
    public Result findRoleCount(){
        Map<String,Long> map = userService.role();
        return Result.success("查询成功！",map);
    }

    @PutMapping("/update/{id}/{nickname}")
    @Operation(summary = "修改用户昵称")
    @Parameters({@Parameter(required = true, name = "id", description = "用户ID"),
            @Parameter(required = true, name = "nickname", description = "用户昵称")
    })
    @PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR') or hasRole('ADMIN')")
    public Result updateNickname(@PathVariable Long id, @PathVariable String nickname){
        userService.updateNickname(id,nickname);
        return Result.success("更新成功！");
    }

    @PutMapping("/update/{id}/{oldPassword}/{password}")
    @Operation(summary = "修改用户密码")
    @Parameters({@Parameter(required = true, name = "id", description = "用户ID"),
            @Parameter(required = true, name = "oldPassword", description = "旧密码"),
            @Parameter(required = true, name = "password", description = "新密码")
    })
    @PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR') or hasRole('ADMIN')")
    public Result updateNickname(@PathVariable Long id, @PathVariable String oldPassword, @PathVariable String password){
        return userService.updatePassword(id,oldPassword,password);
    }



}

// TODO 前端添加搜索框，按照不同的条件搜索
// TODO 前端将单选框替换成用户id

