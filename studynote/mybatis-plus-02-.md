[TOC]

# 【README】

本文代码参见： [https://github.com/TomJourney/mybatis-plus-test](https://github.com/TomJourney/mybatis-plus-test)

本文集成了springboot与mybatis-plus；

本文主要内容如下：

- MybatisPlus-常见注解；
- MybatisPlus-常见配置；

<br>

---

# 【1】MyBatis-plus常见注解

【注解官方使用文档】[https://baomidou.com/reference/annotation/](https://baomidou.com/reference/annotation/) 

1）MybatisPlus通过扫描实体类，并基于反射获取po类信息作为数据库表信息；默认规则如下：

- 类名驼峰转下划线作为表名； 
- 名为id的字段作为主键；
- 变量名驼峰转下划线作为表的字段名； 

<font color=red>补充：若po类的字段与数据库表字段不满足上述默认规则，则需要使用注解来做自定义（这就是注解的作用，非常重要） </font>； 

2）注解（修饰po）：

- @TableName： 设置表名； 
- @TableId：指定表中的主键字段；
- @TableField：指定表中的普通字段；

```java
@Data
@TableName("user_tbl")
public class UserPO {
    @TableId("idXXX")
    private Long id;

    @TableField("nameXXX")
    private String name;

    private String mobilePhone;

    private String addr;
}
```

<br>

---

# 【2】MyBatis-Plus常见配置

mybatis-plus常见配置官方文档：[https://baomidou.com/reference/](https://baomidou.com/reference/)

## 【2.1】MyBatis-Plus配置示例

```properties
# 更多配置可以参考：  https://baomidou.com/reference/
# 别名扫描包
mybatis-plus.type-aliases-package=com.tom.study.mybatisplustest.infrastructure.dao

# mapper.xml路径
mybatis-plus.mapper-locations=classpath*:com.tom.study.mybatisplustest.infrastructure.dao/**/*.xml

# 配置
# 是否开启下划线和驼峰的映射
mybatis-plus.configuration.map-underscore-to-camel-case=true
# 是否开启二级缓存
mybatis-plus.configuration.cache-enabled=false

# 全局配置
# 更新策略： 只更新非空字段 
mybatis-plus.global-config.db-config.update-strategy=not_null
```

<br>

---

# 【3】MyBatisPlus核心内容

## 【3.1】条件构造器

1）MybatisPlus支持各种复杂的where条件，Wrapper类就是构建条件的包装器，该类定义了多种方法用于抽象sql条件（where子句）；

![image-20250705163316998](./pic/02/0201.png)









