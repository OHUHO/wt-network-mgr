package com.record.controller.demo;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "测试（无需认证）")
@RestController
@RequestMapping("/api/v1/test")
public class HelloController {


    @Operation(summary = "打招呼")
    @GetMapping("/hello")
    public String hello(){
        return "吴彦组网络管理系统欢迎你！";
    }

}
