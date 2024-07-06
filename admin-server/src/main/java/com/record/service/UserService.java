package com.record.service;

import com.record.dto.UserDTO;
import com.record.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.record.utils.Result;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author OR
 * @since 2023-05-05
 */
public interface UserService extends IService<User> {


    User findByEmail(String email);


    /**
     * 获取所有用户
     * @return
     */
    List<User> findAllUser();

    /**
     * 通过关键词查找用户
     * @param keywords
     * @return
     */
    List<User> findByKeywords(String keywords);


    /**
     * 添加用户
     * @param user
     */
    void addUser(UserDTO user);

    /**
     * 修改用户
     * @param id
     * @param password
     * @param role
     */
    void updateUser(Long id,String password,String role);

    /**
     * 删除用户
     * @param id
     */
    void deleteUser(Long id);

    /**
     * 查询系统中每个角色的数量
     * @return
     */
    Map<String, Long> role();

    /**
     * 更新昵称
     * @param id
     * @param nickname
     */
    void updateNickname(Long id, String nickname);


    /**
     * 更新密码
     * @param id
     * @param oldPassword
     * @param password
     * @return
     */
    Result updatePassword(Long id,String oldPassword, String password);
}
