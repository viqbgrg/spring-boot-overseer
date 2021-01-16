package com.github.viqbgrg.springbootoverseer.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <p>
 * mysql 代码生成器演示例子
 * </p>
 *
 * @author jobob
 * @since 2018-09-12
 */
public class MysqlGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入").append(tip).append("：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * RUN THIS
     */
    public static void main(String[] args) {



        String projectPath = System.getProperty("user.dir");

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig.
                Builder("jdbc:h2:mem:test;MODE=MySQL;DATABASE_TO_LOWER=TRUE;INIT=RUNSCRIPT FROM 'src/main/resources/schema.sql'","","")
                .driver("org.h2.Driver").dbType(DbType.H2).build();

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator(dsc);
        // 全局配置
        GlobalConfig gc = new GlobalConfig.Builder().outputDir(projectPath + "/src/main/java")
                .author("bing").openDir(false).build();
        mpg.global(gc);
        // 包配置
        PackageConfig pc = new PackageConfig.Builder().parent("com.github.viqbgrg.springbootoverseer").build();
        mpg.packageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig();
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public File outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return new File(projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML);
            }
        });
        cfg.addFileOutConfig(focList);
        mpg.injection(cfg);
        mpg.template(new TemplateConfig.Builder().all().mapper("mapper.java").build().disable(TemplateType.XML));
        // 策略配置
        StrategyConfig strategy = new StrategyConfig.Builder().addInclude(scanner("表名")).addTablePrefix(pc.getModuleName() + "_")
                .entityBuilder().naming(NamingStrategy.underline_to_camel).columnNaming(NamingStrategy.underline_to_camel)
                .lombok(true).controllerBuilder().restStyle(true).hyphenStyle(true).serviceBuilder().superServiceClass("com.github.viqbgrg.springbootoverseer.service.BaseService").build();

        mpg.strategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.engine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}