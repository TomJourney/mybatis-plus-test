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

<br>

---

## 【2.1】使用MyBatisPlus的DB静态工具查询单个及多个用户地址

【UseStaticApiRestfulUserController】

```python
@RestController
@RequestMapping("/staticdb/restful/user")
@RequiredArgsConstructor
public class UseStaticApiRestfulUserController {

    private final MyBatisPlusUserService myBatisPlusUserService;

    private final UserConverter userConverter;

   // 查询单个 
    @GetMapping(path = "/queryUserById/{id}", consumes = "application/json")
    public UserVO queryUserById(@PathVariable("id") Long id) {
        return myBatisPlusUserService.queryUserAndAddrById(id);
    }

    // 查询多个 
    @GetMapping(path = "/queryUserByIds", consumes = "application/json")
    public List<UserVO> queryUserByIds(@RequestParam("ids") List<Long> ids) {
        return myBatisPlusUserService.queryUserAndAddrById(ids);
    }
}
```

【MyBatisPlusUserService】

```java
@Service
@RequiredArgsConstructor
public class MyBatisPlusUserService extends ServiceImpl<UserMapper, UserPO> {

    private final UserConverter userConverter;
    private final UserAddrConverter userAddrConverter;    

    // 使用MyBatisPlus的DB工具查询单个用户地址
    public UserVO queryUserAndAddrById(Long id) {
        // 1 查询用户
        UserPO userPO = getById(id);
        // 2 查询地址
        List<UserAddrPO> userAddrPOList = Db.lambdaQuery(UserAddrPO.class).eq(UserAddrPO::getUserId, id).list();
        UserVO userVO = userConverter.toUserVO(userPO);
        // 3 封装地址到用户
        if (!CollectionUtils.isEmpty(userAddrPOList)) {
            userVO.setUserAddrVOList(userAddrConverter.toUserAddrVOList(userAddrPOList));
        }
        return userVO;
    }
    
    // 使用MyBatisPlus的DB工具查询多个用户地址 
    public List<UserVO> queryUserAndAddrById(List<Long> ids) {
        // 1 查询用户列表
        List<UserVO> userVOList = userConverter.toUserVOList(listByIds(ids));

        // 2 查询地址列表
        List<Long> dbUserIdList = userVOList.stream().map(UserVO::getId).collect(Collectors.toList());
        List<UserAddrPO> userAddrPOList = Db.lambdaQuery(UserAddrPO.class).in(UserAddrPO::getUserId, dbUserIdList).list();
        // 转为map，其中key为用户id，value为地址vo列表
        Map<Long, List<UserAddrVO>> userIdToUserAddrVOsMap =
                userAddrConverter.toUserAddrVOList(userAddrPOList).stream().collect(Collectors.groupingBy(UserAddrVO::getUserId));
        // 3 封装地址到用户
        if (!CollectionUtils.isEmpty(userVOList)) {
            userVOList.forEach(userVO->{
                userVO.setUserAddrVOList(userIdToUserAddrVOsMap.getOrDefault(userVO.getId(), Collections.emptyList()));
            });
        }
        return userVOList;
    }
}
```

### 【查询单个用户的访问效果】

地址：get  localhost:8081/staticdb/restful/user/queryUserById/1 

```c++
{
    "id": 1,
    "name": "user1",
    "mobilePhone": "17612342701",
    "addr": "成都天府三街101号",
    "balance": 1.00,
    "userState": "1",
    "userAddrVOList": [
        {
            "id": 1,
            "userId": 1,
            "addrInfo": "成都高新区大学路1号",
            "addrType": "UNVS"
        },
        {
            "id": 3,
            "userId": 1,
            "addrInfo": "成都高新区大学路学府家园",
            "addrType": "HOME"
        }
    ]
}
```



### 【查询多个用户的访问效果】

地址：get localhost:8081/staticdb/restful/user/queryUserByIds?ids=1,2 

```c++
[
    {
        "id": 1,
        "name": "user1",
        "mobilePhone": "17612342701",
        "addr": "成都天府三街101号",
        "balance": 1.00,
        "userState": "1",
        "userAddrVOList": [
            {
                "id": 1,
                "userId": 1,
                "addrInfo": "成都高新区大学路1号",
                "addrType": "UNVS"
            },
            {
                "id": 3,
                "userId": 1,
                "addrInfo": "成都高新区大学路学府家园",
                "addrType": "HOME"
            }
        ]
    },
    {
        "id": 2,
        "name": "user2",
        "mobilePhone": "110",
        "addr": "成都天府四街401号",
        "balance": 2.00,
        "userState": "0",
        "userAddrVOList": [
            {
                "id": 4,
                "userId": 2,
                "addrInfo": "成都高新区大学路学府家园201号",
                "addrType": "HOME"
            },
            {
                "id": 5,
                "userId": 2,
                "addrInfo": "成都高新区大学路学府家园202号",
                "addrType": "HOME"
            }
        ]
    }
]
```

<br>

---

# 【3】逻辑删除

使用文档参见： [https://baomidou.com/guides/logic-delete/](https://baomidou.com/guides/logic-delete/) 

1）业务背景： 逻辑删除不会真正删除数据，而是用一个字段标记数据的删除状态；实现如下：

- 在表中添加一个字段deleted标记数据是否被删除；逻辑删除时，deleted=1，否则等于0；
- 查询时仅查询deleted=0的数据；

2）相关sql：

- 逻辑删除： update table set deleted=1 where deleted=0 and id = #{id}
- 查询： select * from table where deleted=0

<br>

---

## 【3.1】代码实现

1）MyBatisPlus提供了逻辑删除，但需要以下配置。

【application.yml】

```yaml
mybatis-plus:
  global-config:
    db-config:
      logic-delete-field: deleted # 全局逻辑删除字段名
      logic-delete-value: 1 # 逻辑已删除值。可选，默认值为 1
      logic-not-delete-value: 0 # 逻辑未删除值。可选，默认值为 0
```

步骤1：为user_tbl表新增逻辑删除字段 deleted；

```sql
alter table mywarn.user_tbl add column `deleted` varchar(1) default '0' COMMENT '逻辑删除标记（1-已删除，0-未删除）';
```

步骤2：<font color=red>为UserPO新增字段 deleted，否则逻辑删除丕生效（非常重要） </font>

```java
@Data
@TableName("user_tbl")
public class UserPO {
    @TableId("id")
    private Long id;

    @TableField("name")
    private String name;

    private String mobilePhone;

    private String addr;

    private BigDecimal balance;

    private String userState;

    private String deleted;
}
```

步骤3：编写测试用例

```java
@SpringBootTest
public class MyBatisPlusUserServiceTest {

    @Autowired
    private MyBatisPlusUserService userService;

    @Test
    void testLogicDelete() {
        Long id = 103L;
        // 删除
        userService.removeById(id);
        // 查询
        UserPO userPO = userService.getById(id);
        System.out.println(userPO);
    }
}
```

【sql执行日志】

```c++
==>  Preparing: UPDATE user_tbl SET deleted='1' WHERE id=? AND deleted='0'
==> Parameters: 103(Long)
<==    Updates: 1

==>  Preparing: SELECT id,name,mobile_phone,addr,balance,user_state,deleted FROM user_tbl WHERE id=? AND deleted='0'
==> Parameters: 103(Long)
<==      Total: 0
```

<br>

---

# 【4】枚举处理器





<br>

---

# 【5】JSON处理器

















