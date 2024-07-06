package com.record.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.record.dto.UserDTO;
import com.record.entity.Role;
import com.record.entity.User;
import com.record.mapper.UserMapper;
import com.record.security.config.ApplicationConfig;
import com.record.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.record.utils.Result;
import com.record.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author OR
 * @since 2023-05-05
 */
@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;
    // private final PasswordEncoder passwordEncoder;
    // private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public User findByEmail(String email) {
        return userMapper.selectOne(
                new LambdaQueryWrapper<User>()
                        .eq(User::getEmail, email)
        );
    }

    @Override
    public List<User> findAllUser() {
        return userMapper.selectList(null);
    }

    @Override
    public List<User> findByKeywords(String keywords) {

        return userMapper.selectList(
                new LambdaQueryWrapper<User>()
                        .like(User::getEmail,keywords)
                        .or()
                        .like(User::getNickname,keywords)
                        .or()
                        .like(User::getRole,keywords)
                        .or()
                        .like(User::getCreateTime,keywords)
        );
    }

    @Override
    public void addUser(UserDTO user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        var newUser = User.builder()
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .nickname(user.getNickname())
                .role(user.getRole())
                .createTime(TimeUtils.generateCurrentTime())
                .build();
        userMapper.insert(newUser);
    }

    @Override
    public void updateUser(Long id,String password,String role) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        var user = User.builder()
                .id(id)
                .password(passwordEncoder.encode(password))
                .role(Role.valueOf(role))
                .build();
        userMapper.updateById(user);
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public Map<String, Long> role() {
        List<User> users = userMapper.selectList(null);

        Map<String, Long> map = new HashMap<>();
        users.stream()
                .map(user -> String.valueOf(user.getRole()))
                .collect(Collectors.groupingBy(String::valueOf))
                .forEach((k,v) -> {
                    map.put(k, (long) v.size());
                });
        return map;
    }

    @Override
    public void updateNickname(Long id, String nickname) {
        User user = userMapper.selectById(id);
        user.setNickname(nickname);
        userMapper.updateById(user);
    }

    @Override
    public Result updatePassword(Long id,String oldPassword, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = userMapper.selectById(id);
        if (user != null){
            // 判断旧密码是否匹配
            if (passwordEncoder.matches(oldPassword,user.getPassword())){
                user.setPassword(passwordEncoder.encode(password));
                userMapper.updateById(user);
                return Result.success("修改成功！");
            }
            return Result.error("旧密码不正确！");
        }
        return Result.error("用户不存在！");
    }
}
