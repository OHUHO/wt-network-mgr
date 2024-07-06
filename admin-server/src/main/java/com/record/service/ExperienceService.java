package com.record.service;

import com.record.entity.Experience;
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
public interface ExperienceService extends IService<Experience> {


    /**
     * 查询所有网络经验
     * @return
     */
    List<Experience> findAll();


    /**
     * 通过网络设备名称查询
     * @param deviceName
     * @return
     */
    List<Experience> findByDeviceName(String deviceName);


    /**
     * 通过经验种类查询
     * @param kindName
     * @return
     */
    List<Experience> findByKindName(String kindName);


    /**
     * 通过网络设备所处区域查询
     * @param deviceArea
     * @return
     */
    List<Experience> findByDeviceArea(String deviceArea);


    /**
     * 通过网络设备故障表现查询
     * @param description
     * @return
     */
    List<Experience> findByTitleAndContent(String description);


    /**
     * 通过网络测试信号差异查询
     * @param testSign
     * @return
     */
    List<Experience> findByTestSign(String testSign);


    /**
     * 增加用户经验
     * @param experience
     */
    void addExperience(Experience experience);


    /**
     * 编辑网络经验
     * @param experience
     */
    void updateExperience(Experience experience);
}
