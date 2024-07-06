package com.record.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.record.entity.Log;
import com.record.entity.Schedule;
import com.record.mapper.ScheduleMapper;
import com.record.service.ScheduleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.record.utils.RecentMonth;
import com.record.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author OR
 * @since 2023-05-05
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule> implements ScheduleService {

    private final ScheduleMapper scheduleMapper;


    @Override
    public List<Schedule> findAllSchedule() {
        return scheduleMapper.selectList(null);
    }

    @Override
    public boolean addSchedule(Schedule schedule) {
        schedule.setPublishDate(TimeUtils.generateCurrentTime());
        return scheduleMapper.insert(schedule) != 0;
    }

    @Override
    public List<Integer> analysis() {
        String now = TimeUtils.generateCurrentTime();
        String lastYear = TimeUtils.recentYear();

        List<Schedule> schedules = scheduleMapper.selectList(
                new LambdaQueryWrapper<Schedule>()
                        .between(Schedule::getPublishDate, lastYear, now)
        );

        Map<String, Integer> map = new HashMap<>();
        schedules.stream()
                .map(log -> log.getPublishDate().substring(0,7))
                .collect(Collectors.groupingBy(String::valueOf))
                .forEach((k,v) -> {
                    String key = k.substring(5);
                    map.put(key,v.size());
                });

        List<Integer> list = Arrays.asList(new Integer[12]);
        List<Integer> month = RecentMonth.getRecentMonth(12);
        month.forEach(x -> {
            int index = month.indexOf(x);
            list.set(index,0);
            map.forEach((k,v) -> {
                int key = Integer.parseInt(k);
                if (x == key) list.set(index,v);
            });
        });

        /* List<Integer> list = RecentMonth.getRecentMonth(12);
        list.forEach(x -> {
            int index = list.indexOf(x);
            list.set(index,0);
            map.forEach((k,v) -> {
                int key = Integer.parseInt(k);
                if (x == key) list.set(index,v);
            });
        }); */
        return list;
    }

    @Override
    public List<Schedule> findByUserId(Long userId) {
        return scheduleMapper.selectList(
                new LambdaQueryWrapper<Schedule>()
                        .eq(Schedule::getUserId,userId)
        );
    }


}
