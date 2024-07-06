package com.record.service;

import com.record.entity.Token;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Optional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author OR
 * @since 2023-05-05
 */
public interface TokenService extends IService<Token> {

    /**
     * 通过用户 id 查找有效的 token
     * @param userId
     * @return
     */
    Optional<Token> findValidTokenByUserId(Long userId);


    Optional<Token> findByToken(String token);

}
