# mybatis-mate

#### 介绍
mybatis-plus 企业（数据优雅处理）模块

- Spring Boot 引入自动依赖注解包（该包自动会引入 annotation 注解包）

```
<dependency>
  <groupId>com.baomidou</groupId>
  <artifactId>mybatis-mate-starter</artifactId>
  <version>1.0.16</version>
</dependency>
```

- 注解（实体分包使用）

```
<dependency>
  <groupId>com.baomidou</groupId>
  <artifactId>mybatis-mate-annotation</artifactId>
  <version>1.0.16</version>
</dependency>
```

- 咨询作者

![微信 wx153666](https://images.gitee.com/uploads/images/2021/0903/235825_2d017339_12260.jpeg)

- mybatis-mate-data-audit ：数据审计（对账）示例

- mybatis-mate-data-datascope ：数据范围（数据权限）示例

- mybatis-mate-ddl-mysql ：表结构自动维护 Mysql 示例

- mybatis-mate-ddl-postgres ：表结构自动维护 PostgreSQL 示例

- mybatis-mate-dict ：字段数据字典自动映射示例

`该功能可以绑定任意类型的数据源，例如：根据 orderId 绑定 orderNo`

- mybatis-mate-encrypt ：字段加密解密示例

- mybatis-mate-encrypt-mysql-aes ：数据库 mysql 字段 aes 双向加密解密示例

- mybatis-mate-encrypt-sm2-sm3-sm4 ：国密 sm2 sm3 sm4 加密算法示例

- mybatis-mate-sensitive-jackson ：字段脱敏 jackson 实现示例

- mybatis-mate-sensitive-words ：请求参数 敏感词 过滤 AC 算法 示例

- mybatis-mate-sharding ：数据库分库分表、动态数据源、读写分离、数据库健康检查自动切换示例

- mybatis-mate-sharding-dynamic ：动态数据源加载卸载示例

- mybatis-mate-sharding-jta-atomikos ：动态多数据源事务 jta atomikos 示例

