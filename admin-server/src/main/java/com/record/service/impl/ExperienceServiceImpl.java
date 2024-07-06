package com.record.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.record.entity.Experience;
import com.record.entity.ExperienceKind;
import com.record.mapper.ExperienceMapper;
import com.record.service.ExperienceKindService;
import com.record.service.ExperienceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class ExperienceServiceImpl extends ServiceImpl<ExperienceMapper, Experience> implements ExperienceService {

    private final ExperienceMapper experienceMapper;

    private final ExperienceKindService experienceKindService;

    @Override
    public List<Experience> findAll() {
        return experienceMapper.selectList(null);
    }

    @Override
    public List<Experience> findByDeviceName(String deviceName) {
        return experienceMapper.selectList(
                new LambdaQueryWrapper<Experience>()
                        .eq(Experience::getDeviceName,deviceName)
        );
    }

    @Override
    public List<Experience> findByKindName(String kindName) {
        ExperienceKind experienceKind = experienceKindService.findByKindName(kindName);
        if (experienceKind == null){
            return null;
        }
        Integer experienceKindId = experienceKind.getId();
        return experienceMapper.selectList(
                new LambdaQueryWrapper<Experience>()
                        .eq(Experience::getExperienceKindId,experienceKindId)
        );
    }

    @Override
    public List<Experience> findByDeviceArea(String deviceArea) {
        return experienceMapper.selectList(
                new LambdaQueryWrapper<Experience>()
                        .eq(Experience::getDeviceArea,deviceArea)
        );
    }

    @Override
    public List<Experience> findByTitleAndContent(String description) {
        return experienceMapper.selectList(
                new LambdaQueryWrapper<Experience>()
                        .eq(Experience::getTitle,description)
                        .or()
                        .eq(Experience::getContent,description)
        );
    }

    @Override
    public List<Experience> findByTestSign(String testSign) {
        return experienceMapper.selectList(
                new LambdaQueryWrapper<Experience>()
                        .eq(Experience::getTestSign,testSign)
        );
    }

    @Override
    public void addExperience(Experience experience) {
        experienceMapper.insert(experience);
    }

    @Override
    public void updateExperience(Experience experience) {
        experienceMapper.updateById(experience);
    }
}
