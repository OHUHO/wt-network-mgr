package com.record.controller.demo;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "权限测试1")
public class AdminController {

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public String get() {
        return "GET:: admin controller";
    }


    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    // @Hidden
    public String post() {
        return "POST:: admin controller";
    }


    @PutMapping
    @PreAuthorize("hasAuthority('admin:update')")
    // @Hidden
    public String put() {
        return "PUT:: admin controller";
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:delete')")
    // @Hidden
    public String delete() {
        return "DELETE:: admin controller";
    }
}
