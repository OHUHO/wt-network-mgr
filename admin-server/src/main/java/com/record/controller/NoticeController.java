package com.record.controller;

import com.record.entity.Notice;
import com.record.service.NoticeService;
import com.record.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/api/v1/admin/notice")
@Tag(name = "系统公告管理")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@Slf4j
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/find/all")
    @Operation(summary = "查找所有的系统公告")
    public Result findAll(){
        List<Notice> notices = noticeService.findAll();
        return Result.success("获取成功！",notices);
    }


    @GetMapping("find")
    @Operation(summary = "通过id查询公告")
    @Parameter(name = "id",description = "公告id",in = ParameterIn.QUERY,required = true)
    public Result find(Long id){
        Notice notice = noticeService.findById(id);
        return Result.success("获取成功！",notice);
    }

    @GetMapping("find/keywords")
    @Operation(summary = "通过关键词查询公告")
    @Parameter(name = "keywords",description = "关键词",in = ParameterIn.QUERY,required = true)
    public Result find(String keywords){
        if (StringUtils.isNoneBlank(keywords)){
            List<Notice> notices = noticeService.findByKeywords(keywords);
            return Result.success("获取成功！",notices);
        }
        return Result.error("关键词不能为空！");
    }



    @PostMapping("/add")
    @Operation(summary = "添加系统公告")
    // @Parameter(required = true,name = "notice",description = "系统公告实体")
    public Result addNotice(@RequestBody Notice notice){
        if (notice != null){
            noticeService.addNotice(notice);
            return Result.success("添加成功！");
        }
        return Result.error("参数不能为空！");
    }

    @PutMapping("/update")
    @Operation(summary = "修改系统公告")
    @Parameters({@Parameter(required = true,name = "id",description = "系统公告id",in = ParameterIn.QUERY),
            @Parameter(name = "content", description = "内容",in = ParameterIn.QUERY),
            @Parameter(name = "userId", description = "当前用户id",in = ParameterIn.QUERY)})
    public Result updateNotice(Long id,String content, Long userId){

        if (id != 0 && StringUtils.isNoneBlank(content) && userId != 0){
            noticeService.updateNotice(id,content,userId);
            return Result.success("修改成功！");
        }
        return Result.error("参数不能为空！");
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除公告")
    @Parameter(required = true,name = "id", description = "系统公告id")
    public Result deleteNotice(@PathVariable Long id){
        if (id != 0){
            noticeService.deleteNotice(id);
            return Result.success("删除成功！");
        }
        return Result.error("参数不能为空！");
    }

}
