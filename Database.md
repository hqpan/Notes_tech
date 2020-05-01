[toc]

# 版权声明

- 《数据库系统概念》系列学习笔记来源于 Abraham Silberschatz，Henry F.Korth 和 S.Sudarshan 所著 *Database System Concepts 6th edition* [1]，以及  Ben Forta 所著《SQL必知必会》[2]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及科学研究；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 本文采用 [署名-非商业性使用-禁止演绎 4.0 国际 (CC BY-NC-ND 4.0)](https://creativecommons.org/licenses/by-nc-nd/4.0/deed.zh) 协议发布；

# 1. 数据库基本概念

## 1.1 定义

- DBS：Database System；
- DBMS: database management system ;
- SQL:  Structured Query Language ，结构化查询语言；
  - 发音：为字母 S-Q-L 或 sequel ['sikwəl] ；
  - query，n. & vt. 查询；
- UML：Unified Modeling Language，统一建模语言，unify，vt. 统一；
- 数据字典：存放 DDL 的输出，其中包含元数据；
- 数据库系统性能的度量指标：
  - 吞吐量：给定时间内完成任务的数量；
  - 响应时间：完成单个任务所需时间；

## 1.2 数据视图

- 数据抽象：
  - 物理层；
  - 逻辑层：数据间的关系，与物理层之间存在物理数据独立性；
    - 物理数据独立性：应用程序不依赖于物理模式；
  - 视图层；
- 实例：某个时刻数据库中所有信息的集合；
- 模式：schema，数据库的总体设计，描述表的特性，E.g. 表中包含何种数据、数据如何分解、如何存储、各部分信息如何命名、各个表之间的关系；
  - 物理模式：在物理层描述数据库的设计；
  - 逻辑模式：在逻辑层描述数据库的设计，使用该模式构造应用程序；
  - 子模式：subschema，即数据库在视图层的模式；
- 数据模型：
  - 关系模型：表示数据间的关联；
  - 实体-关系模型：entity-relationship model，E-R；
  - 基于对象的数据模型：即面向对象的数据模型；
  - 半结构化数据模型：允许相同的数据项具有不同的属性集，E.g. XML 可用于表示半结构化数据；

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

# 2. 关系模型相关定义

- 模式和实例：
  - 模式：逻辑设计；
  - 实例：特定时刻下的内容；
- 关系：指代表；
  - 关系实例：包含一组特定的行；
  - 关系模式：属性、属性类型、各属性对应的域、关系上的约束；
  - 关系模型：用表的集合表示数据间的联系；
- 域：每个属性所有可能取值的集合；
- 属性：指代列；
- 导出列：给列取别名，亦称导出列；
- field：字段/计算字段，计算后得到的列，便于区分表中原有的列；
- row / record: 行 / 记录；
- n 元组：有 n 个值的元组，对应于表中的一行；
- 超码：一个或多个属性的集合，可用于唯一标识一个元组；
  - 候选码：最小的超码；
  - 主码：即主键，被设计者选中，用于区分不同元组的候选码；
    - 主键值不能为空，不能修改、重用，若某行从表中删除，则它的主键不能赋给以后的新行；
    - 可取多个 column 组合作为主键；
- 外码：即外键，E.g. 关系$r_2$的主键是关系$r_1$中的属性；
  - $r_1$：外码依赖的参照关系；
  - $r_2$：外码的被参照关系；

# 3. 初级 SQL
## 3.1 定义和语言特点
### 3.1.1 定义和语言特点

- DDL 和 DML：
  - DDL：Data-Defination Language，数据定义语言；
  - DML：Data-Manipulation Language，数据操纵语言；
- SQL语句书写要求：
  - 不区分大小写；
  - 建议将关键字大写，列名和表名小写，提高代码可读性；
  - 语句执行时，忽略其中所有空格，多条语句用分号分隔；
  - 语句可写为一行，也可写为多行，分成多行，便于阅读和调试；
  - 注释：
    - 单行注释：`-- `、`#`；
    - 多行注释：`/*...*/ `；



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
| NOT BETWEEN | 不在指定的两个值之间 |
|     MOD     |         取余         |
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

- `CONCAT(str1, str2, ...)`：连接多个字符串，concatenate, vt. 把...联系起来；
- 去除字符串中的空格：trim, vt. & n.修剪；
  - `TRIM()`：除去字符串两侧空格；
  - `LTRIM()`：除去字符串左侧空格；
  - `RTRIM()`：除去字符串右侧空格；
- `ifnull(expression1, expression2)`:
  - 若表达式1不为空，则返回该表达式；
  - 否则返回表达式2；

```mysql
SUBSTRING();		-- 提取字符串的组成部分；
CONVERT();			-- 数据类型转换；
  
CURDATE();			-- 取当前日期；
YEAR();				-- 从日期中提取年份；
DATEDIFF(date1, date2);		-- 返回两个日期相差的天数
  
LENGTH();			-- 返回字符串的长度；
SOUNDEX();			-- 返回字符串的SOUNDEX值；
  
LEFT();				-- 返回字符串左边的字符；
RIGHT(); 			-- 返回字符串右边的字符；
  
LOWER();			-- 将字符串转换为小写；
UPPER();			-- 将字符串转换为大写；
```

-  `SOUNDEX()` 函数：

   - 将文本串转换为其语音表示的字母数字模式；

   - 用于检索虽拼写错误，但发音相近的文本串；

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
  - 返回一个关系中的所有属性：`SELECT R1.*;`；
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
  
  - `MAX()`；
  - `MIN()`；
  - `SUM()`；
  - `AVG()`；
  - `COUNT()`；
  
- `MAX()`：

  - 可找出最大的数值或日期值；
  - 可用于文本数据，返回该列排序后的最后一行；

- `MIN()`：可用于文本数据，返回该列排序后的最后一行；

- `AVG()`:

  - 必须指定列名作为参数；
  - 只能用于单个列，若要获得多个列的均值，则需使用多个`AVG()`；

- `COUNT()`：不允许在`count(*)`中使用`distinct`，即使用`*`统计元组数量时不得去重；

  ```mysql
  SELECT COUNT(*) AS num_cust		
  -- 以星号为参数，返回该列所有行的数量；
  SELECT COUNT(cust_email) AS num_cust  
  -- 以列名为参数，返回该列所有非空值的行的数量；
  ```

- `DISTINCT`：

  - 可用于聚集函数中；
  - 由于该参数必须使用列名，因此不可用于`COUNT(*)`中；

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
  - `where`：在数据分组前过滤，无法和聚集函数一起使用；
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
UPDATE r
SET a1 = ...;	-- 更改关系r中的属性值
```

- `CASE`语句：选择；

```mysql
CASE
	WHEN pred1 THEN result1
	WHEN pred2 THEN result2
	ELSE result0
END;
```



# 4. 中级 SQL

## 4.1 连接

- 连接类型：
  - 自联结：
    - 将一个表与其自身进行联结；
    - 实现方法：为同一个表取两个不同的别名，然后投影所有列；
  - 自然连接：两个关系中对应的属性名和属性值均相同，返回的结果中不包含重复属性；
  - 等值连接：两个关系中指定的属性值相等，属性名可以不同，返回的结果中包含重复属性；
  - 内连接：
    - 使用`WHERE`子句；
    - `INNER JOIN`，可简写为`INNER`，不保留未匹配的元组；
  - 外连接：保留未匹配的元组；
    - 左外连接：`LEFT OUTER JOIN`；
    - 右外连接：`RIGHT OUTER JOIN`；
    - 全外连接：`FULL OUTER JOIN`，MySQL 不支持，可使用左外连接和右外连接求并集替代；
- 连接条件：
  - `NATURAL`：连接共有属性**全部**相同的元组，共有属性在结果中仅出现一次；
  - `USING`：指定需要匹配的属性名，仅当两个关系中对应的属性名相同时才能使用，相较于`ON`语法更简洁；
  - `ON`：指定连接条件；

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



# 5. JDBC

## 5.1 定义

- ODBC：Open Database Connectivity，开放数据库互联；
- JDBC：Java Database Connectivity，Java 数据库连接；
  - JDBC 相关接口定义位于`java.sql.*`；

## 5.2 从通用程序语言中访问数据库

- 从通用程序语言中访问数据库的方法：
  - 动态 SQL：
    - 运行时被解释；
    - 可在运行时构建、提交 SQL 语言；
    - 借助函数（面向过程语言）、方法（面向对象语言）与数据库交互，E.g. JDBC；
  - 嵌入式 SQL：
    - SQL 语句在编译时全部确定；
    - 经预处理器处理，宿主语言编译器编译；
- `Class.forName()`：加载 JDBC 驱动程序，此后方能访问数据库；
- `DriverManager`类的`getConnection`方法：打开一个数据库连接；
- `Statement`：对该种类型的对象调用特定方法，将 SQL 语句作为参数传递给该对象；
- 可将对数据库的查询结果存储在集合变量中，每次取出1个元组进行处理；
- 预备语句：先给出问号，后给出实际值；
  - 编译一次，修改参数执行多次；
  - 防止 SQL 注入：`setString`、`setInt`等方法将自动检查语法错误，必要时插入转义字符；



# 6. 函数和过程

- 函数：
  - 允许函数同名，只需保持函数的参数个数或类型不完全相同即可；
- 过程：
  - 允许过程同名，只需保持参数个数不同即可；



# 7. 形式化关系查询语言

- 关系代数基本运算：
  - 选择；
  - 投影：筛选出部分列，以集合形式（去重）返回；
  - 并运算；
  - 差运算；
  - 笛卡尔积；



# 8. 数据库设计和E-R模型

## 8.1 定义

- degree：度，参与联系集的实体集数量；
- 属性：
  - 复合属性：可划分为若干简单属性，E.g. address 可划分为 street、city、state 等信息；
  - 多值属性：E.g. 属性 phone_number 可能有多个值；
  - 派生属性：无需存储，可由其它属性计算得到，E.g. date_of_bath 和 age；
- 约束：
  - 映射基数：一个实体通过一个联系集能关联的实体数量，E.g. 一对一，一对多……；
  - 参与约束：
    - total：实体集中的**所有**实体均参与到某个联系集中；
    - partial：实体集中的**部分**实体均参与到某个联系集中；



## 8.2 E-R 图

- 强实体集和弱实体集：
  - 强实体集：有主码；
  - 弱实体集：无主码，与标知实体集/属主实体集关联后才有意义；
- 标知性联系：弱实体集与标知实体集之间的联系；

# ==待整理内容==

- 规范化：生成一个符合适当范式的关系模式，使存储的信息便于检索，且无冗余；
  - 最常用的方法：函数依赖；

# ==Schedule==

- 仅学习前12章，12月31日前完成 chapter 1-12；
- 每天10-11页，30天完成334页；
- 整理新笔记：chapter 1、2、6、7、8，3.1；
- 整理旧笔记：每天整理旧版本笔记1-2章；
- Page 182，chapter 7 已完成；



- 2020.04.23分割线
  - 每天19页，5月上旬完成；
  - 处理所有面试题；
- Chapter 1 & 2 已整理完成；

# References

[1] 杨冬靑, 李红燕, 唐世渭. 数据库系统概念[M]. 机械工业出版社, 2013. 
[2] Forta B, 钟鸣, 刘晓霞. SQL 必知必会[J]. 2013.



# 旧版本笔记分割线

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

  

# Schedule

- 空值会给数据的访问和更新带来不便，应减少使用；