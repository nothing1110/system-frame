package com.zhuangf.frame.generate;

import com.google.common.base.CaseFormat;
import com.zhuangf.frame.config.MybatisCodeConfig;
import freemarker.template.TemplateExceptionHandler;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * java代码生成器
 *
 * @Author: zhuangf
 * @Date: 2018/12/1
 */
public class MybatisCodeGenerator {
    private MybatisCodeConfig config;
    public MybatisCodeGenerator(MybatisCodeConfig codeConfig) {
        this.config = codeConfig;
    }

    /**
     * 通过数据表名称生成代码，Model 名称通过解析数据表名称获得，下划线转大驼峰的形式。
     * 如输入表名称 "t_user_detail" 将生成 TUserDetail、TUserDetailMapper、TUserDetailService ...
     * @param tableNames 数据表名称...
     */
    public void genCode(String... tableNames) {
        for (String tableName : tableNames) {
            genCodeByCustomModelName(tableName, null);
        }
    }

    /**
     * 通过数据表名称，和自定义的 Model 名称生成代码
     * 如输入表名称 "t_user_detail" 和自定义的 Model 名称 "User" 将生成 User、UserMapper、UserService ...
     * @param tableName 数据表名称
     * @param modelName 自定义的 Model 名称
     */
    public void genCodeByCustomModelName(String tableName, String modelName) {
        genModelAndMapper(tableName, modelName);
        genService(tableName, modelName);
        genController(tableName, modelName);
    }


    public void genModelAndMapper(String tableName, String modelName) {
        Context context = new Context(ModelType.FLAT);
        context.setId("Potato");
        context.setTargetRuntime("MyBatis3Simple");
        context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
        context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");

        if (StringUtils.isEmpty(modelName)) modelName = tableNameConvertUpperCamel(tableName);

        //实体类字段注释
        CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
        commentGeneratorConfiguration.setConfigurationType("generator.MyCommentGenerator");
        context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);


        //添加model类序列化插件
        PluginConfiguration serializablePluginConfiguration=new PluginConfiguration();
        serializablePluginConfiguration.setConfigurationType("org.mybatis.generator.plugins.SerializablePlugin");
        context.addPluginConfiguration(serializablePluginConfiguration);


        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setConnectionURL(config.getJdbcUrl());
        jdbcConnectionConfiguration.setUserId(config.getJdbcUsername());
        jdbcConnectionConfiguration.setPassword(config.getJdbcPassword());
        jdbcConnectionConfiguration.setDriverClass(config.getJdbcDiver());
        //针对oracle数据库
        jdbcConnectionConfiguration.addProperty("remarksReporting", "true");
        //针对mysql数据库
        // jdbcConnectionConfiguration.addProperty("useInformationSchema", "true");
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

        PluginConfiguration tkpluginConfiguration = new PluginConfiguration();
        tkpluginConfiguration.setConfigurationType("tk.mybatis.mapper.generator.MapperPlugin");
        tkpluginConfiguration.addProperty("mappers", MybatisCodeConfig.MAPPER_INTERFACE_REFERENCE);
        context.addPluginConfiguration(tkpluginConfiguration);

        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.setTargetProject(config.getProjectJavaPath());
        javaModelGeneratorConfiguration.setTargetPackage(config.getModelPackage());
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetProject(config.getProjectJavaPath());
        sqlMapGeneratorConfiguration.setTargetPackage(config.getMapperPackage());
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setTargetProject(config.getProjectJavaPath());
        javaClientGeneratorConfiguration.setTargetPackage(config.getMapperPackage());
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);
        TableConfiguration tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName(tableName);
//        tableConfiguration.setMapperName(modelName);
        tableConfiguration.setDomainObjectName(modelName);
//        if (Strings.isNullOrEmpty(modelName))tableConfiguration.setDomainObjectName(modelName);
        tableConfiguration.setGeneratedKey(new GeneratedKey("id", "IDENTITY", true, null));
        context.addTableConfiguration(tableConfiguration);

        List<String> warnings;
        MyBatisGenerator generator;
        try {
            Configuration config = new Configuration();
            config.addContext(context);
            config.validate();

            boolean overwrite = true;
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            warnings = new ArrayList<String>();
            generator = new MyBatisGenerator(config, callback, warnings);
            generator.generate(null);
        } catch (Exception e) {
            throw new RuntimeException("生成Model和Mapper失败", e);
        }

        if (generator.getGeneratedJavaFiles().isEmpty() || generator.getGeneratedXmlFiles().isEmpty()) {
            throw new RuntimeException("生成Model和Mapper失败：" + warnings);
        }
        System.out.println(modelName + ".java 生成成功");
        System.out.println(modelName + "Mapper.java 生成成功");
        System.out.println(modelName + "Mapper.xml 生成成功");
    }

    public void genService(String tableName, String modelName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
            data.put("author", config.getAuthor());
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", tableNameConvertLowerCamel(tableName));
            data.put("baseFrmPackage", MybatisCodeConfig.BASE_FRM_PACKAGE);
            data.put("baseProjectPackage", config.getProjectBasePackage());

            File file = new File(config.getServicePath() + modelNameUpperCamel + "Service.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("service.ftl").process(data,
                    new FileWriter(file));
            System.out.println(modelNameUpperCamel + "Service.java 生成成功");

            File file1 = new File(config.getServiceImplPath() + modelNameUpperCamel + "ServiceImpl.java");
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            cfg.getTemplate("service-impl.ftl").process(data,
                    new FileWriter(file1));
            System.out.println(modelNameUpperCamel + "ServiceImpl.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Service失败", e);
        }
    }

    public void genController(String tableName, String modelName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
            data.put("author", config.getAuthor());
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("baseRequestMapping", modelNameConvertMappingPath(modelNameUpperCamel));
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
            data.put("baseFrmPackage", MybatisCodeConfig.BASE_FRM_PACKAGE);
            data.put("baseProjectPackage", config.getProjectBasePackage());
            File file = new File(config.getControllerPath() + modelNameUpperCamel + "Controller.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("controller-restful.ftl").process(data, new FileWriter(file));

            System.out.println(modelNameUpperCamel + "Controller.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Controller失败", e);
        }

    }

    private static freemarker.template.Configuration getConfiguration() throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(MybatisCodeConfig.TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    private static String tableNameConvertLowerCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
    }

    private static String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());

    }

    private static String tableNameConvertMappingPath(String tableName) {
        tableName = tableName.toLowerCase();//兼容使用大写的表名
        return "/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
    }

    private static String modelNameConvertMappingPath(String modelName) {
        String tableName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, modelName);
        return tableNameConvertMappingPath(tableName);
    }

    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }
}
