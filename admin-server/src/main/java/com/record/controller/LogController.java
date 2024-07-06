package com.record.controller;

import com.record.entity.Log;
import com.record.service.LogService;
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
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author OR
 * @since 2023-05-05
 */
@RestController
@RequestMapping("/api/v1/log")
@Tag(name = "日志管理")
@RequiredArgsConstructor
@PreAuthorize("hasRole('CHASER')")
public class LogController {

    private final LogService logService;

    @GetMapping("/find/all")
    @Operation(summary = "查询所有的日志")
    public Result findAll(){
        List<Log> logs = logService.findAll();
        return Result.success("查询成功！",logs);
    }


    @GetMapping("/find")
    @Operation(summary = "通过id查询日志")
    @Parameter(required = true,name = "id",description = "日志id",in = ParameterIn.QUERY)
    public Result findById(Long id){
        if (id == 0) return Result.error("参数不能为空！");
        Log log = logService.getById(id);
        return Result.success("查询成功！",log);
    }

    @GetMapping("/find/keywords")
    @Operation(summary = "通过关键词查询日志")
    @Parameter(required = true,name = "keywords",description = "关键词",in = ParameterIn.QUERY)
    public Result findByKeyWords(String keywords){
        if (StringUtils.isNoneBlank(keywords)){
            List<Log> logs = logService.findByKeyWords(keywords);
            return Result.success("查询成功！",logs);
        }
        return Result.error("关键字不能为空！");
    }

    @PostMapping("/add")
    @Operation(summary = "增加日志")
    public Result addLog(@RequestBody Log log){
        if (log == null) return Result.error("参数不能为空！");
        boolean save = logService.addLog(log);
        if (save) return Result.success("添加成功！");
        return Result.error("添加失败！");
    }

    @PutMapping("update")
    @Operation(summary = "编辑日志")
    public Result updateLog(@RequestBody Log log){
        if (log == null) return Result.error("参数不能为空!");
        boolean update = logService.updateById(log);
        if (update) return Result.success("修改成功！");
        return Result.error("修改失败！");
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除日志")
    @Parameter(required = true,name = "id",description = "日志id",in = ParameterIn.QUERY)
    public Result deleteLog(Long id){
        if (id == 0) return Result.error("参数不能为空！");
        boolean remove = logService.removeById(id);
        if (remove) return Result.success("删除成功！");
        return Result.error("删除失败！");
    }

    @GetMapping("/count")
    @Operation(summary = "查询网络维护次数")
    @PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR') or hasRole('ADMIN')")
    // @Hidden
    public Result findLogCount(){
        long count = logService.count();
        return Result.success("查询成功！",count);
    }

    @GetMapping("/analysis")
    @Operation(summary = "系统维护次数分析")
    @PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR') or hasRole('ADMIN')")
    // @Hidden
    public Result analysis(){
        List<Integer> res = logService.analysis();
        return Result.success("查询成功！",res);
    }


    @GetMapping("/weekly")
    @Operation(summary = "查询周排行")
    @PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR') or hasRole('ADMIN')")
    // @Hidden
    public Result findWeeklyRank(){
        Map<String,Long> map = logService.findWeeklyRank();
        return Result.success("查询成功！",map);
    }

    @GetMapping("/monthly")
    @Operation(summary = "查询月排行")
    @PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR') or hasRole('ADMIN')")
    // @Hidden
    public Result findMonthlyRank(){
        Map<String,Long> map = logService.findMonthlyRank();
        return Result.success("查询成功！",map);
    }

    // TODO 修改权限
    @GetMapping("/find/{userId}")
    @Operation(summary = "通过用户id查询日志")
    @Parameter(required = true,name = "userId",description = "用户id")
    @PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR') or hasRole('ADMIN')")
    public Result findByUserId(@PathVariable Long userId){
        List<Log> logs = logService.findByUserId(userId);
        return Result.success("查询成功！",logs);
    }

}
