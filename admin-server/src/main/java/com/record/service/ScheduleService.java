package com.record.service;

import com.record.entity.Schedule;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author OR
 * @since 2023-05-05
 */
public interface ScheduleService extends IService<Schedule> {

    /**
     * 查询所有的日程
     * @return
     */
    List<Schedule> findAllSchedule();


    /**
     * 添加日程
     * @param schedule
     * @return
     */
    boolean addSchedule(Schedule schedule);

    /**
     * 系统维护次数分析
     * @return
     */
    List<Integer> analysis();

    /**
     * 通过用户id查询日程
     * @param userId
     * @return
     */
    List<Schedule> findByUserId(Long userId);
}
