package com.record.controller;

import com.record.entity.Contact;
import com.record.service.ContactService;
import com.record.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
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
@RequestMapping("/api/v1/contact")
@Tag(name = "通讯录管理")
@RequiredArgsConstructor
@PreAuthorize("hasRole('CHASER') or hasRole('SUPERVISOR')")
public class ContactController {

    private final ContactService contactService;


    @GetMapping("/find/all")
    @Operation(summary = "查询所有联络人")
    public Result findAllContact(){
        List<Contact> contacts = contactService.findAll();
        return Result.success("查询成功!",contacts);
    }


    @GetMapping("/find")
    @Operation(summary = "通过id查询联系人")
    @Parameter(required = true,name = "id",description = "联络人id",in = ParameterIn.QUERY)
    public Result findById(Long id){
        if (id == 0) return Result.error("参数不能为空！");
        Contact contact = contactService.getById(id);
        return Result.success("查询成功！",contact);
    }

    @GetMapping("/find/keywords")
    @Operation(summary = "通过id查询联系人")
    @Parameter(required = true,name = "keywords",description = "关键词",in = ParameterIn.QUERY)
    public Result findByKeywords(String keywords){
        if (StringUtils.isNoneBlank(keywords)){
            List<Contact> contacts = contactService.findByKeywords(keywords);
            return Result.success("查询成功！",contacts);
        }
        return Result.error("关键词不能为空！");
    }

    @PostMapping("/add")
    @Operation(summary = "增加联络人")
    public Result addContact(@RequestBody Contact contact){
        if (contact == null) return Result.error("参数不能为空！");
        boolean save = contactService.addContact(contact);
        if (save) return Result.success("添加成功！");
        return Result.error("添加失败！");
    }

    @PutMapping("/update")
    @Operation(summary = "修改联络人")
    public Result updateContact(@RequestBody Contact contact){
        if (contact  == null) return Result.error("参数不能为空你！");
        boolean update = contactService.updateById(contact);
        if (update) return Result.success("修改成功！");
        return Result.error("添加失败！");
    }


    @DeleteMapping("/delete")
    @Operation(summary = "删除联络人")
    @Parameter(required = true,name = "id",description = "联络人id",in = ParameterIn.QUERY)
    public Result deleteById(Long id){
        if (id == 0) return Result.error("参数不能为空！");
        boolean remove = contactService.removeById(id);
        if (remove) return Result.success("删除成功 ！");
        return Result.error("删除失败！");
    }

}
