[TOC]

# 【README】

本文代码参见： [https://github.com/TomJourney/mybatis-plus-test](https://github.com/TomJourney/mybatis-plus-test)

本文介绍MyBatisPlus扩展功能，如下；

- 代码生成；
- 静态工具；
- 逻辑删除；
- 枚举处理器；
- json处理器；

<br>

---

# 【1】基于MyBatisPlus的代码生成器

方法1：使用代码生成代码（不推荐，仅参考），官方文档：[https://baomidou.com/guides/new-code-generator/](https://baomidou.com/guides/new-code-generator/)

<br>

---

# 【2】MyBatisPlus-DB静态工具

1）引入DB静态工具的原因：

- 原因1：IService仅用于spring单例bean，若是工具类，则无法使用IService或ServiceImpl的接口；
- 原因2：若存在一个请求需要查询多张表，则可能存在IService实现类的springbean相互引用的问题；

所以引入DB静态工具，使得工具类也可以使用MyBatisPlus提供的增删改查api； 

2）业务需求：

- 需求1：改造根据id查询用户的接口，查询用户的同时，也查询用户对应地址；
- 需求2：改造根据id批量查询用户的接口，查询用户的同时，查询批量用户对应的地址；
- 需求3：新实现根据id查询用户大学地址功能，若用户状态为不可用（userState=0），则抛出异常；

<br>

---

## 【2.1】使用MyBatisPlus的DB静态工具查询用户地址





















