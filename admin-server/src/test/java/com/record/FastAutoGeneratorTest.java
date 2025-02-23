package com.record;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class FastAutoGeneratorTest {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/w_admin?useUnicode=true&useSSL=true&characterEncoding=utf8", "root", "jc951753")
                .globalConfig(builder -> {
                    builder.author("OR") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir("E://mybatis_plus//w_admin")  // 指定输出目录
                            .commentDate("yyyy-MM-dd"); // 注释日期

                })
                .packageConfig(builder -> {
                    builder.parent("com") // 设置父包名
                            .moduleName("record") // 设置父包模块名
                            .entity("entity")
                            .mapper("mapper")
                            .xml("mapper.xml")
                            .controller("controller")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "E://mybatis_plus//w_admin"));// 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder //#######################################################
                            // .addInclude("w_user")     // 设置需要生成的表名
                            // .addInclude("w_token")
                            // .addInclude("w_contact")
                            // .addInclude("w_experience")
                            // .addInclude("w_experience_kind")
                            // .addInclude("w_log")
                            // .addInclude("w_message")
                            // .addInclude("w_notice")
                            .addInclude("w_schedule")
                            .addInclude("t_user")
                            .addInclude("t_token")
                            //#######################################################

                            .addTablePrefix("w_","t_", "w_a_") // 设置过滤表前缀

                            .entityBuilder()
                            .enableLombok() // 开启Lombok注解
                            .enableChainModel() // 开启链式模式
                            .logicDeleteColumnName("is_delete") // 逻辑删除字段名
                            .enableFileOverride() //允许文件覆盖

                            .controllerBuilder()
                            .enableRestStyle() // 开启 @RestController控制器
                            .enableFileOverride() //允许文件覆盖

                            .serviceBuilder()
                            .formatServiceFileName("%sService") //去除Service前面的I
                            .formatServiceImplFileName("%sServiceImpl")
                            .enableFileOverride() //允许文件覆盖

                            .mapperBuilder()
                            .superClass(BaseMapper.class)
                            .enableMapperAnnotation() // 开启@Mapper注解
                            .enableBaseResultMap() // 启用BaseResultMap生成
                            .enableBaseColumnList() // 生成基本的SQL片段
                            .enableBaseResultMap()  // 生成基本的resultMap
                            .enableFileOverride(); //允许文件覆盖
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker 引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}