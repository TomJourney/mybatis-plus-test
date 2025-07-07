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

## 【2.3】使用MyBatisPlus的DB静态工具判断用户状态



















