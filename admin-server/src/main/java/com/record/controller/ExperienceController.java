package com.record.controller;

import com.record.entity.Experience;
import com.record.service.ExperienceService;
import com.record.utils.Result;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author OR
 * @since 2023-05-05
 */
@RestController
@RequestMapping("/api/v1/experience")
@Tag(name = "网络经验")
@RequiredArgsConstructor
@PreAuthorize("hasRole('SUPERVISOR')")
public class ExperienceController {

    private final ExperienceService experienceService;

    @GetMapping("/search/all")
    @Operation(summary = "查询所有网络经验")
    // @PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR')")
    @PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR')")
    public Result findAll(){
        List<Experience> experiences = experienceService.findAll();
        return Result.success("查询成功！",experiences);
    }

    @GetMapping("/search/name")
    @Operation(summary = "按照设备名称查询")
    @Parameter(required = true,name = "deviceName", description = "设备名称",in = ParameterIn.QUERY)
    // @PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR')")
    @PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR')")
    public Result findByDeviceName(String deviceName){
        if (StringUtils.isBlank(deviceName)){
            return Result.error("设备名称不能为空！");
        }
        List<Experience> experiences = experienceService.findByDeviceName(deviceName);
        return Result.success("查询成功！",experiences);
    }


    @GetMapping("/search/kind")
    @Operation(summary = "按照经验种类查询")
    @Parameter(required = true,name = "kindName", description = "经验种类",in = ParameterIn.QUERY)
    // @PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR')")
    @PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR')")
    public Result findByKindName(String kindName){
        if (StringUtils.isBlank(kindName)){
            return Result.error("种类不能为空！");
        }
        List<Experience> experiences = experienceService.findByKindName(kindName);
        return Result.success("查询成功！",experiences);
    }

    @GetMapping("/search/area")
    @Operation(summary = "按照设备区域查询")
    @Parameter(required = true,name = "deviceArea", description = "网络设备所处区域",in = ParameterIn.QUERY)
    // @PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR')")
    @PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR')")
    public Result findByDeviceArea(String deviceArea){
        if (StringUtils.isBlank(deviceArea)){
            return Result.error("区域不能为空！");
        }
        List<Experience> experiences = experienceService.findByDeviceArea(deviceArea);
        return Result.success("查询成功！",experiences);
    }

    @GetMapping("/search/titleorcontent")
    @Operation(summary = "按照故障表现查询")
    @Parameter(required = true,name = "description", description = "故障表现",in = ParameterIn.QUERY)
    // @PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR')")
    @PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR')")
    public Result findByTitleAndContent(String description){
        if (StringUtils.isBlank(description))
            return Result.error("表现不能为空！");
        List<Experience> experiences = experienceService.findByTitleAndContent(description);
        return Result.success("查询成功！",experiences);
    }

    @GetMapping("/search/sign")
    @Operation(summary = "通过测试信号差异查询")
    @Parameter(required = true,name = "testSign",description = "网络测试信号差异",in = ParameterIn.QUERY)
    // @PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR')")
    @PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR')")
    public Result findByTestSign(String testSign){
        if (StringUtils.isBlank(testSign))
            return Result.error("测试信号差异不能为空！");
        List<Experience> experiences = experienceService.findByTestSign(testSign);
        return Result.success("查询成功！",experiences);
    }


    @GetMapping("/search/id")
    @Operation(summary = "通过id查询经验")
    @Parameter(required = true,name = "id",description = "经验id",in = ParameterIn.QUERY)
    @PreAuthorize("hasRole('SUPERVISOR')")
    public Result findById(Long id){
        if (id == 0) return Result.error("id不能为空！");
        Experience experience = experienceService.getById(id);
        return Result.success("查询成功！",experience);
    }

    @PostMapping("/add")
    @Operation(summary = "增加网络经验")
    // @Parameter(required = true,name = "experience",description = "网络经验实体")
    @PreAuthorize("hasRole('SUPERVISOR')")
    public Result addExperience(@RequestBody Experience experience){
        if (experience != null){
            experienceService.addExperience(experience);
            return Result.success("添加成功！");
        }
        return Result.error("参数不能为空！");
    }

    @PutMapping("/update")
    @Operation(summary = "编辑网络经验")
    @PreAuthorize("hasRole('SUPERVISOR')")
    public Result updateExperience(@RequestBody Experience experience){
        if (experience != null){
            experienceService.updateExperience(experience);
            return Result.success("修改成功！");
        }
        return Result.error("参数不能为空！");
    }

    @DeleteMapping("delete")
    @Operation(summary = "删除网络经验")
    @Parameter(required = true,name = "id",description = "经验id",in = ParameterIn.QUERY)
    @PreAuthorize("hasRole('SUPERVISOR')")
    public Result deleteExperience(Long id){
        if (id == 0) return Result.error("参数不能为空！");
        experienceService.removeById(id);
        return Result.success("删除成功！");
    }

    @GetMapping("/count")
    @Operation(summary = "查询系统经验数量")
    @PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR') or hasRole('ADMIN')")
    @Hidden
    public Result findExperienceCount(){
        long count = experienceService.count();
        return Result.success("查询成功！",count);
    }

}
