[toc]

# 版权声明

- 《数据库系统概念》系列学习笔记来源于 Abraham Silberschatz，Henry F.Korth 和 S.Sudarshan 所著 *Database System Concepts 6th edition* [1]，以及  Ben Forta 所著《SQL必知必会》[2]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及科学研究；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 本文采用 [署名-非商业性使用-禁止演绎 4.0 国际 (CC BY-NC-ND 4.0)](https://creativecommons.org/licenses/by-nc-nd/4.0/deed.zh) 协议发布；



# 1. 数据库基本概念

## 1.1 定义

- DBMS: database management system ;
- DBS：Database System；
- SQL:  Structured Query Language ，结构化查询语言；
  - 发音：为字母 S-Q-L 或 sequel ['sikwəl] ；
  - query，n. & vt. 查询；
- UML：Unified Modeling Language，统一建模语言，unify，vt. 统一；

## 1.2 数据视图

- 数据抽象：
  - 物理层；
  - 逻辑层：数据间的关系，与物理层之间存在物理数据独立性；
  - 视图层；
- 实例：instance，某个时刻数据库中所有信息的集合；
- 模式：schema，数据库的总体设计，描述表的特性，E.g. 表中包含何种数据、数据如何分解、如何存储、各部分信息如何命名、各个表之间的关系；
  - 物理模式：在物理层描述数据库的设计；
  - 逻辑模式：在逻辑层描述数据库的设计，使用该模式构造应用程序；
  - 子模式：subschema，即数据库在视图层的模式；
- 数据模型：
  - 关系模型：relational model，表示数据间的关联；
  - 实体-关系模型：entity-relationship model，E-R；
  - 基于对象的数据模型：object-based data model，即面向对象的数据模型；
  - 半结构化数据模型：semistructured data model；



## 1.3 数据库语言

- 数据库语言的不同部分;
  - DDL：数据定义语言，输出存放在包含元数据的数据字典中，数据字典仅能由 DBS 自身访问和修改；
  - DML：数据操纵语言；
- 一致性约束：
  - 域约束：每个属性均有对应的取值范围约束；
  - 参照完整性约束：某些属性集在不同关系中出现，其值必须相等；
  - 断言；



## 1.4 事务管理

- 事务正确执行的四个基本要素：简写为 ACID；
  - 原子性：Atomicity；
  - 一致性：Consistency；
  - 隔离性：Isolation，若两个事务运行在相同的时间内，执行相同的功能，事务的隔离性将确保每一事务在系统中认为只有该事务在使用系统；
  - 持久性：Durability；



## 1.5 数据库体系结构

- 数据库体系结构:
  - 集中式数据库；
  - 客户机/服务器系统；
  - 并行数据库系统；
  - 分布式数据库系统；
- 度量数据库系统性能的指标：
  - 吞吐量：给定时间内完成的事务数量；
  - 响应时间：完成单个事务所需时间；



# 2. 关系模型介绍

## 2.1 定义

- attribute：属性，用于指代列；
- domain：域，每个属性所有可能取值的集合；
- relation/table：关系，用于指代表；
- row / record: 行 / 记录；
- tuple：元组，数学上指一组值的序列，数据库中用于指代行；
- n-tuple：n 元组，有 n 个值的元组，对应于表中的一行；
- foreign key: 外码，即外键，父数据表的主键；
- primary key: 主码，即主键；
  - 主键值不能为空，不能修改、重用，若某行从表中删除，则它的主键不能赋给以后的新行；
  - 可取多个 column 组合作为主键；
- 参照关系和被参照关系：E.g. 一个关系模式$r_1$的属性中含有另一个关系模式$r_2$的主键；
  - 参照关系：$r_1$为外键依赖的参照关系；
  - 被参照关系：



## 2.2 MySQL 编码问题

- 数据库编码参数的含义：
  - `character_set_client`：数据库将客户端传来的数据以该种编码加以解读；
  - `character_set_results`：数据库将数据以该种编码发送给客户端；
- 使用`SET`语句修改数据库编码参数，仅在当前窗口内有效，重新打开该窗口将失效；
- 在总配置文件`my.ini`中可修改数据库默认编码参数；

```mysql
SHOW VARIABLES LIKE 'char%';	-- 查看数据库编码
SET character_set_client = 'utf8'；
```



## 2.3 备份与恢复

- 备份：`mysqldump -u用户名 -p密码 数据库名>生成的脚本文件路径`；
  - 在命令行窗口中运行，不必进入数据库；
  - 脚本文件中不含`CREATE database`；
- 恢复：
  - `mysql -u用户名 -p密码 数据库名<脚本文件路径`；
    - 在命令行窗口中运行，不必进入数据库；
    - 在执行该语句前，应先创建数据库`CREATE database`；
  - `source 脚本文件路径`：进入数据库后，创建数据库`CREATE database`，然后执行该语句；



# 3. 初级 SQL
## 3.1 定义和语言特点
### 3.1.1 定义和语言特点

- DDL 和 DML;
  - DDL：Data-Defination Language，数据定义语言；
  - DML：Data-Manipulation Language，数据操纵语言；
- 空值会给数据的访问和更新带来不便，应减少使用；
- SQL语句不区分大小写，将关键字大写，列名和表名小写，提高代码可读性；
- 单条 SQL 语句执行时，忽略其中所有空格，多条SQL语句以`;`分隔；

  - SQL语句可写在一行之中，也可写为多行；
  - 将SQL语句分成多行，易于阅读和调试；
- 注释：
  - `-- `：两个连字符，单行注释；
  - `#`：单行注释；
  - `/*...*/ `：多行注释；



### 3.1.2 DDL

#### 3.1.2.1 数据库和表的创建修改

- 查看：
  - `SHOW DATABASES`：查看所有数据库名单；
  - `SHOW TABLES`：查看所有表名；
  - `DESC tableName`：查看表的详细信息，description；
- `USE databaseName`：选择待操作的数据库；
- 创建：
  - 创建数据库：
    - `CREATE DATABASE test`；
    - `CREATE DATABASE IF NOT EXISTS test`；
  - 创建表：
```mysql
-- CREATE TABLE tableName
CREATE TABLE IF NOT EXISTS tableName (
columnName type,
...
);
```

- 删除：
  - 删除数据库：
    - `DROP DATABASE databaseName`；
    - `DROP DATABASE IF EXISTS databaseName`；
  - 删除表：`DROP TABLE tableName`；
- 修改：

```mysql
-- 添加列
ALTER TABLE tableName ADD (
columnName type,
...
);
-- 修改列类型
ALTER TABLE tableName MODIFY columnName newType;
-- 修改列名和列类型
ALTER TABLE tableName CHANGE columnName NewColumnName newType;
-- 删除列
ALTER TABLE tableName DROP columnName;
-- 修改表名
ALTER TABLE tableName RENAME TO newTableName; 
```

- 设置主键：
  - 创建表时，在变量类型后加关键字`PRIMARY KEY`；
  - 添加主键，`ALTER TABLE tableName ADD PRIMARY KEY(columnName)`；
  - 删除主键：`ALTER TABLE tableName DROP PRIMARY KEY`；
- 设置主键自增长：应对整数类型使用自增长；
  - 创建表时，在`PRIMARY KEY`关键字后加`AUTO_INCREMENT`；	
- 非空约束：创建表时，在变量类型后加关键字`NOT NULL`；
- 唯一约束：创建表时，在变量类型后加关键字`UNIQUE`；



#### 3.1.2.2 插入

```mysql
INSERT INTO tableName (
	columnName1, columnNaem2, ...
) VALUES (
	...
);
-- 不指出列名
INSERT INTO tableName VALUES (
	...
);
```



#### 3.1.2.3 更新和删除

```mysql
UPDATE tableName SET
	columnName1 = value1,
    columnName2 = value2,
    ...
WHERE ...;

DELETE FROM tableName
WHERE ...;
```



#### 3.1.2.4 添加外键

```mysql
-- 创建表时添加外键
CONSTRAINT foreignKeyName
FOREIGN KEY (columnName) REFERENCES tableName(primaryKeyName);
-- 修改表时添加外键约束
ALTER TABLE tableName ADD CONSTRAINT foreignKeyName
FOREIGN KEY (columnName) REFERENCES tableName(primaryKeyName);
-- 删除外键
ALTER TABLE tableName DROP FOREIGN KEY foreignKeyName;
```

- 一对一关系：一个表的主键同时也是外键；
- 多对多关系：创建中间表（关联表），即需要三张表，关联表使用两个外键分别引用另两张表的主键；



### 3.1.3 DCL

- DCL：数据控制语言；
- 一个项目创建一个数据库用户权限，一个项目只对应一个数据库；

```mysql
-- 创建用户，使用百分号表示任意IP均可访问数据库
CREATE USER userName@'IPAddress' IDENTIFIED BY 'Password';
CREATE USER userName@'%' IDENTIFIED BY 'Password';
-- 给用户授权，E.g. ALTER, CREATE, DROP, ...
-- databaseName.tableName
GRANT authority1, authority2, ... ON databaseName.* TO userName@IPAddress;
GRANT ALL ON databaseName.* TO userName@IPAddress;
-- 撤销授权
REVOKE authority1, authority2, ... ON databaseName.* FROM userName@IPAddress;
-- 查看权限
SHOW GRANTS FOR userName@IPAddress;
-- 删除用户
DROP USER userName@IPAddress;
```



### 3.1.4 操作符

- 操作符存在功能冗余；

|   操作符    |         含义         |
| :---------: | :------------------: |
|     <>      |  不等于（等同于!=）  |
|     !>      |  不大于（等同于<=）  |
|     !<      |  不小于（等同于>=）  |
|   BETWEEN   |  在指定的两个值之间  |
|     MOD     |         取余         |
| NOT BETWEEN | 不在指定的两个值之间 |
|   IS NULL   |       为NULL值       |

- 示例：

```mysql
WHERE prod_price BETWEEN 5 AND 10;
WHERE ID MOD 2 = 1;
WHERE MOD(ID, 2) = 1;
```

- 通配符：避免将通配符置于搜索模式的开头，否则将增大时间开销；
  - `%`：匹配任意数量的任意字符，不能匹配空值；
  - `_`：匹配某一个字符；



### 3.1.5 函数

- `CONCAT(str1, str2, ...)`：连接多个字符串；
- `ifnull(expression1, expression2)`:
  - 若表达式1不为空，则返回该表达式；
  - 否则返回表达式2；
- `DATEDIFF(date1, date2)`：返回两个日期相差的天数；



## 3.2 数据定义

### 3.2.1 基本类型

- 基本类型：
  - `char(n)`；固定长度字符串，2字节，$n<255$；
  - `decimal`：无精度缺失问题，适用于对数值敏感的金融数据；
  - `float(n)`；
  - `double(p,d)`：双精度浮点数；
  - `numeric(p,d)`：定点数；
  - `int`；
  - `smallint`：小整数类型；
  - `varchar(n)`；可变长度字符串，4字节，需要额外的空间记录长度,$n<65535$；
  - `text(clob)`：clob，character large object；
    - tinytext；
    - text；
    - mediumtext；
    - longtext；
  - `blob`：字节类型；
    - tinyblob；
    - blob；
    - mediumblob；
    - longblob；
  - `date`：日期，yyyy-mm-dd；
  - `time(p)`：时间，hh:mm:ss，可指定小数位长度，默认为0；
  - `timestamp(p)`：时间戳类型，可指定小数位长度，默认为6；
- 类型转换：`CAST t1 AS t2`；
- 从`DATE, TIME`中提取单独的域：`EXTRACT(field FROM d)`，域为年月日时分秒；
- 注意：
  - 两个`char`比较时，将自动追加空格，使得两者便于比较；
  - `char`和`varchar`比较时，无法自动追加空格补齐长度，建议将两者均设置为`varchar`便于比较；
  - 字符串使用单引号括起；



### 3.2.2 基本模式定义

```mysql
... not null;			-- 属性值不得为空；
alter table r add A D;	-- 向关系r中添加属性A，该属性的域为D；
alter table r drop A;	-- 删除关系r中的属性A；
```



## 3.3 SQL 查询的基本结构

### 3.3.1 查询、排序和自然连接

- `all`与`distinct`：
  - `all`：显式指定在结果中保留重复值，可省略；
  - `distinct`：去重；
- `select`子句中可使用运算符：$+、-、\times、\div$；
  - 运算对象：常数、属性；
  - 与空值相加结果为空；
  - 若运算对象不为数值，则视为0参与计算；
- 若多个关系中的属性名相同，则使用关系名作为前缀加以区分；
- 返回前若干行：其中`OFFSET`后的数字表示起始行的编号，但检索时不包括该行，而是返回从下一行开始的检索结果；

```mysql
LIMIT 5;			-- 返回前5行；
-- 其它方案
LIMIT 4 OFFSET 3;	-- MySQL支持简化版的 LIMIT 语句；
LIMIT 3,4;			-- 从第3行开始，但不包括该行，共计返回4行；
```

- `ORDER BY`：指定列名排序、使用相对列位置排序，两种方法可混用；

```mysql
-- 按单列排序
ORDER BY 2;
-- 按多列排序
ORDER BY 2,3;
```

- 指定排序方向：

  - 升序：`ASC`，默认值，可缺省；
  - 降序：`DESC`，在指定列后使用关键词`DESC`；



### 3.3.2 附加基本运算

- 重命名：`as`用于`select`或`from`语句中，可省略;

  - 相关名称/相关变量/元组变量：将关系重命名的标识符；
  - 临时表必须命名；

- 字符串运算：

  - SQL 中使用单引号表示字符串；
  - 若字符串中含有单引号，则使用连续两个单引号表示一个单引号，E.g. `''`；
  - 模糊查询：不使用等号，使用比较运算符`like`；
    - 百分号：匹配任意子串；
    - 下划线：匹配任意一个字符；
  - 定义转义字符：关键字`escape`；

  ```mysql
  like 'ab\%' escape '\';		-- 定义反斜杠为转义字符
  ```



### 3.3.3 空值

- 涉及空值的运算：
  - 算术运算：结果为空；
  - 比较运算：结果为`unknown`；
  - 布尔运算：`AND`、`OR`、`NOT`，`AND`的优先级高于`OR`，对于短路逻辑无法求出结果的值，作为`unknown`；
  - `where`子句不会将`unknown`对应的子句加入到返回结果中；
  - `select`子句、谓词处理空值的差异：
    - `select distinct`：若两个元组各个属性值均相等，即被视为相同元组，即使其中部分值为空；
    - 谓词中`null=null`将返回`unknown`，`null`不是一个数，各个`null`被认为不相同；
- 检测`null`和`unknown`：
  - `is null`；
  - `is not null`；
  - `is unknown`；
  - `is not unknown`；



### 3.3.4 聚集函数和分组聚集

- 聚集函数：聚集函数忽略输入中的空值，若所有输入值均为空，则返回`null`；
  - `min`：适用于数值和非数值类型数据，E.g. 字符串 ；
  - `max`：适用于数值和非数值类型数据；
  - `count`：适用于数值和非数值类型数据；
    - 不允许在`count(*)`中使用`distinct`，即使用`*`统计元组数量时不得去重；
  - `sum`：仅适用于数值；
  - `avg`：仅适用于数值；
- `group by`的使用要求：
  - 可包含任意数目的列，实现嵌套分组；
  - 后接的每一列必须是检索列或有效的表达式，而不能是聚集函数；

    - 若在`SELECT`中使用表达式，则必须在`group by`子句中指定相同的表达式，不能使用别名；
  - 大多数 DBMS 不允许该子句后接长度可变的数据类型，E.g. 文本、备注型字段；
  - 除聚集函数中的属性之外，`SELECT`语句中的其它属性必须均在`GROUP BY`中给出；
  - 该子句将待分组列中值为`null`的所有行视为一组；
- `having`子句：
  - 用于筛选`group by`产生的分组；
  - 任意出现在`having`子句中，且未被聚集的属性，必须出现在`group by`中；
- `where`与`having`的区别：
  - where过滤行：在数据分组前过滤，无法和聚集函数一起使用；
  - `having`：在数据分组后过滤，可以和聚集函数一起使用；



### 3.3.5 嵌套子查询

- 嵌套子查询可用于`from`或`where`子句中；
- 集合成员资格查询：使用`in`、`not in`连接词；
- 集合的比较：
  - `all`：表示集合中所有元素；
  - `some`：等价于`any`，表示某一个元素；
  - 特例：
    - `=all`：等于集合中的任意值，不同于`in`；
    - `<>all`：不等于集合中的任意值，等价于`not in`；
    - `<> some`：部分不相等，不同于`not in`；
    - `=some`：等于集合中的某一个值，等价于`in`；
- 空关系测试：
  - `exists`：不为空时返回`true`；
  - 相关子查询：内层`select-from-where`语句中使用了外层查询的相关名称；
  - 相关子查询的作用域：
    - 类似于编程语言中变量的作用于规则；
    - 子查询的属性可被外查询使用；
- 重复元组存在性测试：
  - `unique`：无重复元组时返回`true`；
  - `not unique`；
  - 注意：若两个元组中某个属性为空，`unique`视为不相等；
- `with`子句：定义临时关系，该关系仅在包含该子句的查询中有效；
- 标量子查询：返回包含单个属性的单个元组；



## 3.4 修改数据库

- 删除：

```mysql
DROP FROM r;			-- 删除关系r及其模式
DELETE FROM r;			-- 仅删除关系r中的所有记录
```

- 插入：

```mysql
INSERT INTO r VALUES (...);	-- 向关系r中插入一个记录
INSERT INTO r (a1, a2, ...) VALUES (...);	-- 插入时指定属性名
```

- 更新：

```mysql
UPDATE r SET a1 = ...;	-- 更改关系r中的属性值
```

- `CASE`语句：

```mysql
CASE
	WHEN pred1 THEN result1
	WHEN pred2 THEN result2
	ELSE result0
END
```



# 4. 中级 SQL

## 4.1 连接表达式

- 连接类型：
  - 内连接：
    - 使用`WHERE`子句；
    - `INNER JOIN ... ON ...`，可简写为`INNER`，不保留未匹配的元组；
    - 自然连接；
  - 外连接：保留未匹配的元组；
    - 左外连接：`NATURAL LEFT OUTER JOIN`；
    - 右外连接：`NATURAL RIGHT OUTER JOIN`；
    - 全外连接：`NATURAL FULL OUTER JOIN`，MySQL 不支持，可使用左外连接和右外连接求并集替代；
- 连接条件：
  - `NATURAL`：连接共有属性**全部**相同的元组，共有属性在结果中仅出现一次；
  - `USING`：指定需要匹配的元组；
  - `ON`：与外连接一起使用时，将保留多个共有属性的结果；

```mysql
FROM r1 JOIN r2 USING A;	-- 连接属性A相等的元组；
FROM student LEFT OUTER JOIN takes ON student.ID = takes.ID; 
```



## 4.2 视图

- 视图：view，数据库系统存储查询表达式，但不存储结果；

```mysql
CREATE VIEW viewname AS
...;						-- 此处为查询表达式
CREATE VIEW viewname(a1, a2, ...) AS
...;						-- 显示指定视图名和属性名
```

- 物化视图：materialized view，数据库系统存储结果；
- 视图维护/物化视图维护：更新物化视图；



## 4.3 事务

- 事务的开始和结束：
  - 事务的开始：一条 SQL 语句被执行时；
  - 事物的结束：
    - 提交事务：`Commit/Commit work`；
    - 回滚事务：`Rollback/Rollback work`；



## 4.4 完整性约束

- 单个关系上的约束：
  - `NOT NULL`：主键默认非空，无需显式声明；
  - `UNIQUE`：`UNIQUE(A1, A2, ...)`，各属性的组合值不完全相同；
  - `CHECK(谓词)`；
- 参照完整性约束：被参照的属性应为主键或使用`UNIQUE`约束；
- 违反参照完整性约束的对策：
  - 若删除时违反参照完整性约束，则删除被参照的元组；
  - 若更新时违反参照完整性约束，则更新被参照的元组；
  - 若违反约束，则将参照域置为空；
  - 若违反约束，则将参照域置为默认值；
  - 延迟检查约束关系：在事务结束时检查，而非在中间步骤检查；

```mysql
...
FOREIGN KEY (dept_name) REFERENCES department
	ON DELETE CASCADE	
	ON UPDATE CASCADE,
	...
	
	ON DELETE SET NULL
	ON DELETE SET DEFAULT
```





# ==Schedule==

- 仅学习前12章，12月31日前完成 chapter 1-12；
- 每天10-11页，30天完成334页；
- 整理新笔记：chapter 1；
- 整理旧笔记：每天整理旧版本笔记1-2章；
- Page 90，chapter 4已完成；

# References

[1] 杨冬靑, 李红燕, 唐世渭. 数据库系统概念[M]. 机械工业出版社, 2013. 
[2] Forta B, 钟鸣, 刘晓霞. SQL 必知必会[J]. 2013.



# 旧版本笔记分割线


# 7 创建计算字段

## 7.1 术语

|      英文      |    中文     |      释义      |
| :------------: | :---------: | :------------: |
|     field      |    字段     |       -        |
|  concatenate   |    拼接     | 将值联结到一起 |
| derived column | 导出列/别名 |       -        |

- 字段：与“列”的意思基本相同，常互换使用；
  - 数据库中的列一般称为“列”；
  - “字段”通常与“计算”一起使用，“计算字段”；



## 7.2 使用细节

- 字段并不实际存在于数据库表中，而是运行时在`SELECT`语句内创建的；

  - 此时`SELECT`语句返回一个计算字段（列）；

- 区分：

  - 只有数据库知道`SELECT`语句中哪些列是实际的表列，哪些列是计算字段；
  - 从客户端（E.g. 应用程序）来看，计算字段的数据与其他列的数据返回方式相同；
  - 很多格式转换和格式化工作，既可以在数据库服务器上完成，又可以在客户端应用程序内完成，但在数据库服务器上执行这些操作更快；

- 部分 DBMS 将计算字段保存为填充为列宽的文本值，有些时候需要去除这些多余的空格：

  - RTRIM()：除去字符串右侧空格；
  - LTRIM()：除去字符串左侧空格；
  - TRIM()：除去字符串两侧空格；
    - trim, vt.&n.修剪；

- MySQL 中使用函数`CONCAT`拼接两列：

  ```mysql
  SELECT CONCAT(vend_name, ' (', vend_country, ')')
  
  
  
  ```



## 7.3 使用别名

- 别名：亦称导出列；

- 使用关键字`AS`：

  ```mysql
  SELECT quantiyt*item_price AS expended_price
  
  
  
  ```

  - SQL 支持的基本算术运算包括：`+`、`-`、`*`、`/`；

- 别名的用途：

  - 为计算字段命名，便于客户端引用；
  - 当现有的表列名包含不合法字符（E.g. 空格）或易混淆时，可予以修正；

- 别名的命名原则：

  - 别名可以是一个单词或一个字符串，若为字符串，则应将字符串括在引号中；
    - 将字符串括在引号中，容易给客户端应用带来麻烦；
    - 推荐使用一个单词命名；

- `SELECT`语句可用于测试、检验函数、计算表达式；

  ```mysql
  SELECT 2*3			-- 计算表达式 2*3；
  SELECT NOW()		-- 返回当前时间和日期；
  
  
  
  ```

  

# 8. 使用函数处理数据

## 8.2 使用 SQL 函数需要注意的问题

- 若干个常用的函数：

  ```mysql
  SUBSTRING();		-- 提取字符串的组成部分；
  CONVERT();			-- 数据类型转换；
  
  CURDATE();			-- 取当前日期；
  YEAR();				-- 从日期中提取年份；
  
  LENGTH();			-- 返回字符串的长度；
  SOUNDEX();			-- 返回字符串的SOUNDEX值；
  
  LEFT();				-- 返回字符串左边的字符；
  RIGHT(); 			-- 返回字符串右边的字符；
  
  LOWER();			-- 将字符串转换为小写；
  UPPER();			-- 将字符串转换为大写；
  
  
  
  ```

  - 关于 `SOUNDEX()` 函数：将文本串转换为其语音表示的字母数字模式；

  - 使用`SOUNDEX()` 函数检索虽拼写错误，但发音相近的文本串；

    ```mysql
    WHERE SOUNDEX(cust_contact) = SOUNDEX('Michael Green');
    
    
    
    ```

- 数值处理函数：

  ```mysql
  PI();				-- 返回圆周率；
  
  ABS();				-- 返回一个数的绝对值；
  SQRT();				-- 返回一个数的平方根；
  EXP();				-- 返回一个数的指数值；
  
  SIN();				-- 返回一个角度的正弦值；
  COS();				-- 返回一个角度的余弦值；
  TAN();				-- 返回一个角度的正切；
  
  
  
  ```



# 9. 汇总数据

## 9.2 5个聚集函数

- 各 DBMS 对聚集函数的支持基本一致；

- SQL 聚集函数：

  - `MAX()`；
  - `MIN()`；
  - `SUM()`；
  - `AVG()`；
  - `COUNT()`；

- `MAX()`：

  - 忽略值为 NULL 的行；
  - 可找出最大的数值或日期值；
  - 许多 DBMS 允许将该函数应用与文本数据，返回该列排序后的最后一行；

- `MIN()`：

  - 忽略值为 NULL 的行；
  - 许多 DBMS 允许将该函数应用与文本数据，返回该列排序后的最前面的一行；

- `SUM()`：

  - 忽略值为 NULL 的行；

- `AVG()`:

  - 忽略值为 NULL 的行；
  - 必须指定列名作为参数；
  - 只能用于单个列，若要获得多个列的均值，则需使用多个`AVG()`；

- `COUNT()`：

  ```mysql
  SELECT COUNT(*) AS num_cust		
  -- 以星号为参数，返回该列所有行的数量；
  SELECT COUNT(cust_email) AS num_cust  
  -- 以列名为参数，返回该列所有非空值的行的数量；
  
  
  
  ```

- `DISTINCT`参数对不同的值进行计算，`ALL`为默认参数，无需指定；

  - `DISTINCT`参数不必用于`MAX()`、`MIN()`中，无意义；

  - 由于该参数必须使用列名，因此不可用于`COUNT(*)`中；

  - 适用于如下情形：

    ```mysql
    SUM(DISTINCT column_name)   -- 返回该列中所有不同值之和
    AVG(DISTINCT column_name)   -- 返回该列中所有不同值的均值
    COUNT(DISTINCT column_name) -- 返回该列中所有不同值的行数
    
    
    
    ```

    

# 10. 分组数据

## 10.3 SELECT 子句顺序

 

|   子句   |        功能        |        是否必须使用        |
| :------: | :----------------: | :------------------------: |
|  SELECT  | 待返回的列或表达式 |             是             |
|   FROM   |   从中检索数据表   |  仅当从表中选择数据时使用  |
|  WHERE   |       过滤行       |             否             |
| GROUP BY |        分组        | 仅当分组实现聚集计算时使用 |
|  HAVING  |      过滤分组      |             否             |
| ORDER BY |        排序        |             否             |
|  LIMIT   |  限制返回结果数量  |             否             |

- `HAVING`：分组后的筛选条件，应用于分组中聚集计算的结果；

# 11. 使用子查询

## 11.1 术语

| 英文  | 中文 | 释义 |
| :---: | :--: | :--: |
| query | 查询 |  -   |
|       |      |      |

- 查询：任何 SQL 语句均有查询功能，但该属于一般指代 `SELECT` 语句；
- 子查询常用于：
  - `WHERE`子句中的`IN`操作符中；
  - 作为计算字段使用子查询；
- 完全限定列名：表名、列名用句点分隔，E.g. `Orders.cust_id`；
  - 当操作涉及不同表，且其中的列名相同时，应使用完全限定列名；



## 11.2 在 WHERE 子句中利用子查询进行过滤

- 嵌套子查询无数量限制，但不宜过多；
- 子查询执行顺序为先内后外；
- 作为子查询的`SELECT`语句只能查询单个列；



# 12. 联结表

## 12.1 术语

- 笛卡尔积：检索出的行数是第一张表的行数乘以第二张表的行数；
  - 叉联结：返回笛卡尔积的联结；



## 12.2 联结

- 联结：

  - 在一条`SELECT`语句中，连接多个表返回一组输出；
  - 联结的表越多，性能下降越严重；

- 等值连接；

- 创建联结：

  - 简单语法：

    ```mysql
    SELECT vend_name, prod_name, prod_price
    FROM Vendors, Products
    -- 联结两个表
    WHERE Vendors.vend_id = Products.vend_id;   
    
    -- 联结多个表，联结条件用AND连接；
    WHERE ... AND ...;							
    
    
    
    ```

  - 标准语法：

    - 明确指定连接的类型，此处为内联结；
    - 内联结的条件由`ON`子句给出；

    ```mysql
    SELECT vend_name, prod_name, prod_price
    FROM Vendors INNER JOIN Products
    ON Vendors.vend_id = Products_vend_id;
    
    
    
    ```

# 13. 创建高级联结

## 13.1 术语

- 联结的种类：
  - 内联结；
  - 外联结；
  - 自联结；
  - 自然联结；
  - 等值联结；

## 13.2 使用表别名

- 表别名与列别名的区别：
  - 表别名仅在查询执行中使用，不返回到客户端；
  - 列别名返回至客户端；
- 使用表别名的优点：允许在一条 SQL 语句中多次使用相同的表；



## 13.3 自联结、自然联结和外联结

- 自联结：将一个表与其自身进行联结；

  - 自联结通常作为外部语句，相较于子查询语句，处理速度更快；
  - 因此自联结常用语替代从相同表中检索数据的子查询语句；
  - 实现方法：为同一个表取两个不同的别名；

  ```mysql
  WHERE Customers AS c1, Customers AS c2
  ```
  
    
  
- 自然联结：不同于标准的联结返回所有数据，自然联结中相同的列仅返回一次，即不返回两张表中相同的列；

  - 实现方法：对其中一个表使用通配符`SELECT *`，对其他表中的列使用明确的列名；

  ```mysql
  SELECT C.*, O.order_num, OI.prod_id
  ```
  
- 外联结：返回所有关联的行，以及部分没有关联的行；

  - 左外联结：`LEFT OUTER JOIN ... ON ...`，返回所有关联的行，以及左侧表中未被关联的行；
  - 右外联结：`RIGHT OUTER JOIN ... ON ...`，返回所有关联的行，以及右侧表中未被关联的行；

- 使用联结的原则;

  - 当使用多个联结时，可先分别测试各个联结，最后进行整体测试，以便排除错误；



# 14. 集合运算

## 14.1 定义

- 集合运算：将多个`SELECT`语句的返回结果执行集合运算后输出；
  - 并运算：`union`；
  - 交运算：`intersect`；
  - 并运算：`except`；

## 14.2 集合运算

- 并运算：
  - `union`：对重复行，只返回一次；
  - `union all`：返回所有重复行；
- 交运算：
  - `intersect`；
  - `intersect all`；
- 并运算：
  - `except`；
  - `except all`：当前者的重复元素数量大于后者时，才会出现在结果中；
- 组合查询的使用原则：
  - 仅在最后一个`SELECT`语句后加分号；
  - 各查询必须包含相同的列、表达式、聚集函数（不必以相同的次序列出）；
  - 各列的数据类型必须兼容，即是 DBMS 可以隐含转换的类型；
  - `ORDER BY`子句仅在最后一条`SELECT`语句中出现一次；



# 15. 插入数据

## 15.1 INSERT 语句

- `INSERT`语句的功能：
  - 插入完整行；
  - 插入行中的部分项；
  - 插入某些查询的结果；



## 15.2 插入完整行

- 对未指定值的项，将其赋值为`NULL`；

- 尽量使用明确指定列名的`INSERT`语句；

  - 即使列的次序发生变化，也能正确填充数据；

  ```sql
  INSERT INTO Customers(cust_id,cust_name)
  VALUES('1000000006', 'Toy Land');
  
  INSERT INTO Customers				-- 不指定列名的填充；
  VALUES('1000000006', 'Toy Land');
  
  
  
  ```



## 15.3 插入行中的部分项

- 使用指定列名的`INSERT`语句，可省略部分项不填充，但须满足以下某个条件：
  - 在表的定义中，允许该列的值为`NULL`；
  - 在表的定义中给出默认值；
  - 即省略部分项时，将使用`NULL`或默认值；



## 15.4 插入某些查询的检索结果

- `INSERT  ... SELECT ...`语句根据列的位置填充，而非根据列名填充；

  - 即`SELECT`中的第一列填充至`INSERT`中的第一列；

  ```sql
  INSERT INTO Customers(cust_name, cust_id)
  SELECT cust_name, cust_id
  FROM CustNew;
  ```



## 15.5 将数据从一张表复制到另一张表

```sql
CREATE TABLE Customers AS
SELECT *
FROM Customers;
```

  