package com.record.controller;

import com.record.entity.Log;
import com.record.entity.Schedule;
import com.record.service.ScheduleService;
import com.record.utils.Result;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/v1/schedule")
@Tag(name = "日程管理")
@RequiredArgsConstructor
@PreAuthorize("hasRole('SUPERVISOR')")
public class ScheduleController {

    private final ScheduleService scheduleService;


    @GetMapping("/find/all")
    @Operation(summary = "查询所有日程")
    public Result findAllSchedule(){
        List<Schedule> schedules = scheduleService.findAllSchedule();
        return Result.success("查询成功！",schedules);
    }

    @GetMapping("/find")
    @Operation(summary = "通过id查询日程")
    @Parameter(required = true,name = "id",description = "日程id",in = ParameterIn.QUERY)
    public Result findById(Long id){
        if (id == 0) return Result.error("参数不能为空！");
        Schedule schedule = scheduleService.getById(id);
        return Result.success("查询成功！",schedule);
    }


    @PostMapping("/add")
    @Operation(summary = "添加日程")
    public Result addSchedule(@RequestBody Schedule schedule){
        if (schedule == null) return Result.error("参数不能为空！");
        boolean save = scheduleService.addSchedule(schedule);
        if (save)  return Result.success("保存成功！");
        return Result.error("保存失败！");
    }


    @PutMapping("/update")
    @Operation(summary = "编辑日程")
    public Result updateSchedule(@RequestBody Schedule schedule){
        if (schedule == null) return Result.error("参数不能为空！");
        boolean update = scheduleService.updateById(schedule);
        if (update) return Result.success("保存成功！");
        return Result.error("保存失败！");
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除日程")
    @Parameter(required = true,name = "id",description = "日程id",in = ParameterIn.QUERY)
    public Result deleteSchedule(Long id){
        if (id == 0) return Result.error("参数不能为空!");
        boolean remove = scheduleService.removeById(id);
        if (remove) return Result.success("删除成功！");
        return Result.error("删除失败！");
    }

    @GetMapping("/analysis")
    @Operation(summary = "系统维护次数分析")
    @PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR') or hasRole('ADMIN')")
    // @Hidden
    public Result analysis(){
        List<Integer> res = scheduleService.analysis();
        return Result.success("查询成功！",res);
    }

    @GetMapping("/count")
    @Operation(summary = "获取网络日程数量")
    @PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR') or hasRole('ADMIN')")
    // @Hidden
    public Result findScheduleCount(){
        long count = scheduleService.count();
        return Result.success("获取成功！",count);
    }

    // TODO 修改权限
    @GetMapping("/find/{userId}")
    @Operation(summary = "通过用户id查询日程")
    @Parameter(required = true,name = "userId",description = "用户id")
    @PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR') or hasRole('ADMIN')")
    public Result findByUserId(@PathVariable Long userId){
        List<Schedule> schedules = scheduleService.findByUserId(userId);
        return Result.success("查询成功！",schedules);
    }
}
