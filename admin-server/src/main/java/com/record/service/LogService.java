package com.record.service;

import com.record.entity.Log;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author OR
 * @since 2023-05-05
 */
public interface LogService extends IService<Log> {

    /**
     * 查询所有的日志
     * @return
     */
    List<Log> findAll();


    /**
     * 添加日志
     * @param log
     * @return
     */
    boolean addLog(Log log);

    /**
     * 系统维护次数分析
     * @return
     */
    List<Integer> analysis();

    /**
     * 查询周排行
     * @return
     */
    Map<String, Long> findWeeklyRank();

    /**
     * 查询月排行
     * @return
     */
    Map<String, Long> findMonthlyRank();

    /**
     * 通过用户id查询日志
     * @param userId
     * @return
     */
    List<Log> findByUserId(Long userId);

    /**
     * 通过关键词查询
     * @param keywords
     * @return
     */
    List<Log> findByKeyWords(String keywords);
}
