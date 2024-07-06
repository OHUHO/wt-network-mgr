package com.record.service.impl;

import cn.hutool.core.lang.hash.Hash;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.record.entity.Log;
import com.record.entity.User;
import com.record.mapper.LogMapper;
import com.record.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.record.service.UserService;
import com.record.utils.RecentMonth;
import com.record.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author OR
 * @since 2023-05-05
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

    private final LogMapper logMapper;

    private final UserService userService;


    @Override
    public List<Log> findAll() {
        return logMapper.selectList(null);
    }

    @Override
    public boolean addLog(Log log) {
        String time = TimeUtils.generateCurrentTime();
        log.setPublishDate(time);
        return logMapper.insert(log) != 0;
    }

    @Override
    public List<Integer> analysis() {
        String now = TimeUtils.generateCurrentTime();
        String lastYear = TimeUtils.recentYear();

        List<Log> logs = logMapper.selectList(
                new LambdaQueryWrapper<Log>()
                        .between(Log::getPublishDate, lastYear, now)
        );

        Map<String, Integer> map = new HashMap<>();
        logs.stream()
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
        return list;
    }

    @Override
    public Map<String, Long> findWeeklyRank() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_WEEK, 0);
        calendar.set(Calendar.DAY_OF_WEEK, 1);
        String begin = sdf.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        calendar.set(Calendar.DAY_OF_WEEK, 0);
        String end = sdf.format(calendar.getTime());
        // log.info("开始：{}，结束：{}",begin,end);

        return getRank(begin,end);
    }

    @Override
    public Map<String, Long> findMonthlyRank() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String begin = sdf.format(calendar.getTime());
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        String end = sdf.format(calendar.getTime());

        return getRank(begin,end);
    }

    @Override
    public List<Log> findByUserId(Long userId) {
        return logMapper.selectList(
                new LambdaUpdateWrapper<Log>()
                        .eq(Log::getUserId,userId)
        );
    }

    @Override
    public List<Log> findByKeyWords(String keywords) {
        return logMapper.selectList(
                new LambdaQueryWrapper<Log>()
                        .like(Log::getTitle,keywords)
                        .or()
                        .like(Log::getContent,keywords)
                        .or()
                        .like(Log::getDay,keywords)
                        .or()
                        .like(Log::getMonth,keywords)
                        .or()
                        .like(Log::getDay,keywords)
                        .or()
                        .like(Log::getPublishDate,keywords)
        );
    }


    /**
     * 返回指定时间段中维护次数最多的信息
     * @param begin
     * @param end
     * @return
     */
    private Map<String,Long> getRank(String begin,String end){
        List<Log> logs = logMapper.selectList(
                new LambdaQueryWrapper<Log>()
                        .between(Log::getPublishDate, begin, end)
        );
        if (logs.isEmpty()){
            Map<String,Long> nullMap = new HashMap<>();
            nullMap.put("暂无数据",0L);
            return nullMap;
        }

        Map<String, Long> map = new HashMap<>();
        logs.stream()
                .map(Log::getUserId)
                .collect(Collectors.groupingBy(Long::valueOf))
                .forEach((k,v) -> {
                    // 通过 userId 查询用户昵称
                    User user = userService.getById(k);
                    map.put(user.getNickname(), (long) v.size());
                });
        // 获取最大值对应的 key
        String maxKey = null;
        for (String key: map.keySet()) {
            if (maxKey == null || map.get(key) > map.get(maxKey)){
                maxKey = key;
            }
        }
        Long maxValue = map.get(maxKey);

        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put(maxKey,maxValue);
        return resultMap;
    }


}
