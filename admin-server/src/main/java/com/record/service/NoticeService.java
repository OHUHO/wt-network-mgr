package com.record.service;

import com.record.entity.Notice;
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
public interface NoticeService extends IService<Notice> {

    /**
     * 查找所有的系统公告
     * @return
     */
    List<Notice> findAll();

    /**
     * 通过 id 查找公告
     * @param id
     * @return
     */
    Notice findById(Long id);

    /**
     * 添加系统公告
     * @param notice
     */
    void addNotice(Notice notice);

    /**
     * 修改系统公告
     * @param id
     * @param content
     * @param userId
     */
    void updateNotice(Long id, String content, Long userId);

    /**
     * 删除系统公告
     * @param id
     */
    void deleteNotice(Long id);

    /**
     * 通过关键词查询
     * @param keywords
     * @return
     */
    List<Notice> findByKeywords(String keywords);
}
