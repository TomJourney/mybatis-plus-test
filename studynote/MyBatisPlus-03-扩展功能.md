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

1）引入DB静态工具的原因：IService仅用于spring单例bean，若是工具类，则无法使用IService或ServiceImpl的接口；所以引入DB静态工具，使得工具类也可以使用IService或ServiceImpl提供的快捷api（增删改查）； 























