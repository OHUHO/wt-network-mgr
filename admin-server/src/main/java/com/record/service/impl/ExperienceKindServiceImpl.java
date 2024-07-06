package com.record.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.record.entity.ExperienceKind;
import com.record.mapper.ExperienceKindMapper;
import com.record.mapper.ExperienceMapper;
import com.record.service.ExperienceKindService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ExperienceKindServiceImpl extends ServiceImpl<ExperienceKindMapper, ExperienceKind> implements ExperienceKindService {

    private final ExperienceKindMapper experienceKindMapper;

    @Override
    public ExperienceKind findByKindName(String kindName) {
        return experienceKindMapper.selectOne(
                new LambdaQueryWrapper<ExperienceKind>()
                        .eq(ExperienceKind::getKindName,kindName)
        );
    }
}
