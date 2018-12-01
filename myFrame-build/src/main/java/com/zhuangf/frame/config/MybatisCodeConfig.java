package com.zhuangf.frame.config;

/**
 * @Author: zhuangf
 * @Date: 2018/12/1
 */
public class MybatisCodeConfig {
    //项目包路径相关配置
    public static final String BASE_FRM_PACKAGE = "com.zhuangf.frame";//基础框架包
    public static final String MAPPER_INTERFACE_REFERENCE = BASE_FRM_PACKAGE + ".base.dao.mybatis.CommonBaseMapper";//Mapper插件基础接口的完全限定名
    public static final String TEMPLATE_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/template/mybatis";//模板位置

    //项目配置
    private String projectBasePackage;//项目基础包
    private String projectPath;//项目基础地址
    private String modelPackageName;//实体
    private String servicePackageName;//服务
    private String controllerPackageName;//controller
    private String mapperPackageName;//mapper

    //jdbc配置
    private String jdbcUrl;
    private String jdbcUsername;
    private String jdbcPassword;
    private String jdbcDiver;
    //作者
    private String author;

    public String getMapperPackage() {
        return projectBasePackage+"."+mapperPackageName;
    }

    public String getModelPackage() {
        return projectBasePackage+"."+controllerPackageName;
    }

    public String getControllerPath() {
        return getProjectJavaPath()+packageConvertPath(projectBasePackage+"."+modelPackageName);
    }

    public String getServicePath() {
        return getProjectJavaPath()+packageConvertPath(projectBasePackage+"."+servicePackageName);
    }

    public String getServiceImplPath() {
        return getProjectJavaPath()+packageConvertPath(projectBasePackage+"."+servicePackageName+"."+"impl");
    }

    /**
     * 获得项目代码路径
     * @return
     */
    public String getProjectJavaPath() {
        return projectPath+"/src/main/java";
    }

    /**
     * 根据包获得路径
     * @param packageName
     * @return
     */
    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getProjectBasePackage() {
        return projectBasePackage;
    }

    public MybatisCodeConfig setProjectBasePackage(String projectBasePackage) {
        this.projectBasePackage = projectBasePackage;
        return this;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public MybatisCodeConfig setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
        return this;
    }

    public String getJdbcUsername() {
        return jdbcUsername;
    }

    public MybatisCodeConfig setJdbcUsername(String jdbcUsername) {
        this.jdbcUsername = jdbcUsername;
        return this;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public MybatisCodeConfig setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
        return this;
    }

    public String getJdbcDiver() {
        return jdbcDiver;
    }

    public MybatisCodeConfig setJdbcDiver(String jdbcDiver) {
        this.jdbcDiver = jdbcDiver;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public MybatisCodeConfig setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getModelPackageName() {
        return modelPackageName;
    }

    public MybatisCodeConfig setModelPackageName(String modelPackageName) {
        this.modelPackageName = modelPackageName;
        return this;
    }

    public String getServicePackageName() {
        return servicePackageName;
    }

    public MybatisCodeConfig setServicePackageName(String servicePackageName) {
        this.servicePackageName = servicePackageName;
        return this;
    }

    public String getControllerPackageName() {
        return controllerPackageName;
    }

    public MybatisCodeConfig setControllerPackageName(String controllerPackageName) {
        this.controllerPackageName = controllerPackageName;
        return this;
    }

    public String getMapperPackageName() {
        return mapperPackageName;
    }

    public MybatisCodeConfig setMapperPackageName(String mapperPackageName) {
        this.mapperPackageName = mapperPackageName;
        return this;
    }
}
