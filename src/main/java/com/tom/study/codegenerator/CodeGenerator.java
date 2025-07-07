package com.tom.study.codegenerator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import com.baomidou.mybatisplus.generator.query.SQLQuery;

import java.nio.file.Paths;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName CodeGenerator.java
 * @Description TODO
 * @createTime 2025年07月07日 21:35:00
 * @ 参考地址： https://baomidou.com/guides/new-code-generator/
 */
public class CodeGenerator {

    private static final String url = "jdbc:mysql://localhost:3306/mywarn?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false&rewriteBatchedStatements=true&remarks=true&useInformationSchema=true";

    public static void main(String[] args) {
        FastAutoGenerator.create(url, "root", "root")
                .globalConfig(builder -> builder
                        .author("tom")
                        .outputDir(Paths.get(System.getProperty("user.dir")) + "/src/main/java")
                        .commentDate("yyyy-MM-dd")

                )
                .dataSourceConfig(builder -> builder
                        .dbQuery(new MySqlQuery())
                        .typeConvert(new MySqlTypeConvert())
                        .keyWordsHandler(new MySqlKeyWordsHandler())
                        .databaseQueryClass(SQLQuery.class)
                )
                .packageConfig(builder -> builder
                        .parent("com.baomidou.mybatisplus")
                        .entity("entity")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .xml("mapper.xml")
                )
                .strategyConfig(builder -> {
                            builder.addInclude("user_tbl") // 设置需要生成的表名
                                    .entityBuilder()
                                    .enableLombok() // 启用 Lombok
                                    .enableTableFieldAnnotation() // 启用字段注解
                                    .controllerBuilder()
                                    .enableRestStyle(); // 启用 REST 风格
                        }
                )
                .execute();
    }
}
