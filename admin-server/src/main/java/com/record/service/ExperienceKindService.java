package com.record.service;

import com.record.entity.ExperienceKind;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author OR
 * @since 2023-05-05
 */
public interface ExperienceKindService extends IService<ExperienceKind> {

    /**
     * 通过经验种类名称查询
     * @param kindName
     * @return
     */
    ExperienceKind findByKindName(String kindName);
}
