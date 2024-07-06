package com.record.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.record.entity.Notice;
import com.record.mapper.NoticeMapper;
import com.record.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.record.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    private final NoticeMapper noticeMapper;


    @Override
    public List<Notice> findAll() {
        return noticeMapper.selectList(null);
    }

    @Override
    public Notice findById(Long id) {
        return noticeMapper.selectById(id);
    }

    @Override
    public void addNotice(Notice notice) {
        String time = TimeUtils.generateCurrentTime();
        notice.setPublishDate(time);
        noticeMapper.insert(notice);
    }

    @Override
    public void updateNotice(Long id, String content, Long userId) {
        var notice = Notice.builder()
                .id(id)
                .content(content)
                .userId(userId)
                .build();
        noticeMapper.updateById(notice);
    }

    @Override
    public void deleteNotice(Long id) {
        noticeMapper.deleteById(id);
    }

    @Override
    public List<Notice> findByKeywords(String keywords) {

        return noticeMapper.selectList(
                new LambdaQueryWrapper<Notice>()
                        .like(Notice::getUserId,keywords)
                        .or()
                        .like(Notice::getUserId,keywords)
                        .or()
                        .like(Notice::getPublishDate,keywords)
                        .or()
                        .like(Notice::getContent,keywords)
        );
    }
}
