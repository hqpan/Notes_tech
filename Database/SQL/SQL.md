[toc]

# 版权声明

- MySQL 系列学习笔记来源于 Ben Forta 的著作《MySQL必知必会》[1]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及科学研究；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 本文采用 [署名-非商业性使用-禁止演绎 4.0 国际 (CC BY-NC-ND 4.0)](https://creativecommons.org/licenses/by-nc-nd/4.0/deed.zh) 协议发布；

# 1. Introduction

## 1.1 MySQL 概述

- DDL 和 DML：
  - DDL：Data-Defination Language，数据定义语言；
  - DML：Data-Manipulation Language，数据操纵语言；
- MySQL ：
  - 不区分大小写；
  - 建议将关键字大写，列名和表名小写，提高代码可读性；
  - 语句执行时，忽略其中所有空格，多条语句用分号分隔；
  - 语句可写为一行，也可写为多行，分成多行，便于阅读和调试；
  - 注释：
    - 单行注释：`-- `、`#`；
    - 多行注释：`/*...*/ `；

## 1.2 定义

- 完全限定表名、完全限定列名：区分重名的表、列；
  - 完全限定表名：数据库名、表名用句点分隔；
  - 完全限定列名：表名、列名用句点分隔，E.g. `Orders.cust_id`；

## 1.3 语法

- 字符串用单引号限定；

## 1.4 操作符

|        操作符        |         含义         |
| :------------------: | :------------------: |
|         `=`          |         等于         |
|         `!=`         | 不等于（等同于`<>`） |
| `<`、`>`、`<=`、`>=` |          -           |
|       BETWEEN        |  在指定的两个值之间  |
|     NOT BETWEEN      | 不在指定的两个值之间 |
|         MOD          |         取余         |
|       IS NULL        |      为 NULL 值      |

- 操作符：
  - AND、OR：连接过滤条件，前者优先级更高，可用括号改变处理顺序；
  - IN：匹配一组值；
  - NOT：否定过滤条件；
  - BETWEEN：限定范围为闭区间，E.g. `WHERE prod_price BETWEEN 5 AND 10`；
  - MOD 使用示例：
    - `WHERE ID MOD 2 = 1`；
    - `WHERE MOD(ID, 2) = 1`；

# 2. 检索、排序和筛选

## 2.1 各子句的顺序

|   子句   |        功能        |        是否必须使用        |
| :------: | :----------------: | :------------------------: |
|  SELECT  | 待返回的列或表达式 |             是             |
|   FROM   |  从中检索数据的表  |  仅当从表中选择数据时使用  |
|  WHERE   |       过滤行       |             否             |
| GROUP BY |        分组        | 仅当分组实现聚集计算时使用 |
|  HAVING  |      过滤分组      |             否             |
| ORDER BY |        排序        |             否             |
|  LIMIT   |  限制返回结果数量  |             否             |

- WHERE 和 HAVING 的区别：
  - WHERE：在分组前过滤行；
  - HAVING：
    - 在分组后过滤分组，应用于分组中聚集计算的结果；
    - 任意出现在`having`子句中，且未被聚集的属性，必须出现在`group by`中；

## 2.2 LIMIT

- `LIMIT`语法：
  - `LIMIT 5`：返回前5行；
  - 等价语法示例：从第3行起，取4行；
    - `LIMIT 3,4`；
    - `LIMIT 4 OFFSET 3`；
- 行数索引：从0开始计数；
- 若`LIMIT`所需返回的行数不足，则仅返回满足条件的行；

## 2.3 ORDER BY

- `SELECT`检索返回的数据无规律：检索返回的数据顺序为其添加至表中的顺序，数据更新或删除后，该顺序随之变化；
- 通过非选择的列排序：`ORDER BY`中指定的列可以不是`SELECT`中的列；
- `ORDER BY`语法：指定列名排序、使用列的相对位置排序，两种方法可混用；
  - 按多列排序：`ORDER BY`后提供多个列名，用逗号分隔；
  - 指定排序方式：可为每列指定不同排序方式；
  
    - 升序：`ASC`，默认值，可缺省；
    - 降序：`ORDER BY column_name1 DESC`；

## 2.4 WHERE

- 逻辑操作符：用于连接多个`WHERE`子句；
  - `AND`；
  - `OR`；
  - `IN`：`WHERE vend_id IN (1002, 1003)`，作用类似于多个`OR`；
  - `NOT`：对其后条件语句取反；
  - 注意：使用括号区分计算次序；

## 2.5 通配符

- 字符串的表示：
  - SQL 中使用单引号表示字符串；
  - 若字符串中含有单引号，则使用连续两个单引号表示一个单引号，E.g. `''`；
- 搜索模式：由字面量和通配符组成的搜索条件；
  - 通配符：不使用等号，使用比较运算符`LIKE`，只能匹配文本字段；
    - `%`：匹配$[0,+\infty)$个任意字符，不能匹配空值；
    - `_`：匹配1个任意字符；
  - 使用示例：`WHERE column_name LIKE '[abc]'`；
    - `[abc]`：匹配集合内的所有字符；
    - `[^abc]`：不匹配集合内的所有字符；
  - 避免将通配符置于搜索模式的开头，否则将增大时间开销；

# 3. 正则表达式

## 3.1 基本字符匹配

- `.`：在正则表达式中，表示匹配任意一个字符；
- `|`：在正则表达式中，表示进行`OR`匹配；
- `[]`：在正则表达式中，表示待匹配的字符集合，E.g. `[123]`；
- 示例`[1-5]`、`[a-z]`：在正则表达式中，表示待匹配的字符数值和字母字符集合范围；
- 正则表达式匹配：
  - 默认不区分大小写；
  - `BINARY`区分大小写：`WHERE prod_name REGEXP BINARY ‘JetPack .000’`；
- `LIKE`和`REGEXP`的区别：
  - `LIKE`：匹配整个字符串；
  - `REGEXP`：匹配子字符串；
- 转义字符：在正则表达式中，使用`\\`转义匹配特殊字符；
  - `\\.`；
  - `\\|`；
  - `\\[]`；
- 空白元字符：

| 空白元字符 |    含义    |
| :--------: | :--------: |
|    \\\f    |    换页    |
|    \\\n    |    换行    |
|    \\\r    |    回车    |
|    \\\t    |    制表    |
|    \\\v    |  纵向制表  |
|   \\\\\    | 转义反斜杠 |

- 可使用`SELECT`测试计算结果：不使用数据库表，即无`FROM`子句；
  - 测试计算结果：`SELECT 2*3`；
  - 使用`SELECT`测试正则表达式；
    - 未匹配时返回0；
    - 匹配时返回1；
    - E.g. `SELECT 'hello' REGEXP '[0-9]'`；

## 3.2 匹配字符类

- 字符类：预定义的字符集合；

|  字符类   |            所含字符             |
| :-------: | :-----------------------------: |
| [:digit:] |       任意数字（同[0-9]）       |
| [:lower:] |     任意小写字母（同[a-z]）     |
| [:upper:] |     任意大写字母（同[A-Z]）     |
| [:alpha:] |     任意字符（同[a-zA-Z]）      |
| [:alnum:] | 任意字母和数字（同[a-zA-Z0-9]） |
|     …     |                …                |

## 3.3 重复元字符

- 重复元字符：指示位于其前面的字符的重复次数；

| 重复元字符 |             含义             |
| :--------: | :--------------------------: |
|     *      |        0个或多个匹配         |
|     +      |  1个或多个匹配（等于{1,}）   |
|     ?      |  0个或1个匹配（等于{0,1}）   |
|    {n}     |        指定数目的匹配        |
|    {n,}    |     不少于指定数目的匹配     |
|   {n,m}    | 匹配数目的范围（m不超过255） |

## 3.4 定位元字符

- 定位元字符：
  - `^`：
    - 在`[]`内部，表示否定，E.g. `[^123]`；
    - 在`[]`前面，表示定位元字符；

| 定位元字符 |    含义    |
| :--------: | :--------: |
|     ^      | 文本的开始 |
|     $      | 文本的结尾 |
|  [[:<:]]   |  词的开始  |
|  [[:>:]]   |  词的结束  |

# 4. 计算字段

- 字段：field，指通过计算得到的列；
- `Concat()`函数：拼接列或字符串；
  - concatenate， vt. 把...联系起来；
  - 使用示例：`SELECT Concat(vand_name, '(', vend_country, ')')`；
  - 

- AS：用于指定列别名或表别名；
  - 列别名：为列或计算字段指定别名，别名亦称导出列（derived column）；
  - 表别名；

# 5. 数据处理函数

## 5.1 文本处理函数

|    函数     |                   作用                    |
| :---------: | :---------------------------------------: |
|   LTrim()   |            去除字符串左侧空格             |
|   RTrim()   |            去除字符串右侧空格             |
|   Trim()    | 去除字符串两侧空格，trim， vt. & n.修剪； |
|   Left()    |             返回串左边的字符              |
|   Right()   |             返回串右边的字符              |
|   Upper()   |              将串转换为大写               |
|   Lower()   |              将串转换为小写               |
|  Locate()   |            找出串的第一个子串             |
| SubString() |                  取子串                   |
|  Length()   |              返回字符串长度               |
|  Soundex()  |           返回字符串的Soundex值           |

- `Soundex()`：
  - 返回字符串的 Soundex 值，用于读音相似的字符串匹配；
  - 用于检索虽拼写错误，但发音相近的文本串；

```sql
WHERE SOUNDEX(cust_contact) = SOUNDEX('Michael Green');
```

## 5.2 日期和时间处理函数

- 日期格式：yyyy-mm-dd；
  - 使用示例：`WHERE Date(order_date) = ‘2000-01-01’`；
- 时间格式：HH:MM:SS；

|     函数      |              作用              |
| :-----------: | :----------------------------: |
|   AddDate()   |    增加一个日期（天、周等）    |
|   AddTime()   |    增加一个时间（时、分等）    |
|   CurDate()   |          返回当前日期          |
|   CurTime()   |          返回当前时间          |
|    Date()     |     返回日期时间的日期部分     |
|  DateDiff()   |        计算两个日期之差        |
|  Date_Add()   |     高度灵活的日期运算函数     |
| Date_Format() |  返回一个格式化的日期或时间串  |
|     Day()     |     返回一个日期的天数部分     |
|  DayOfWeek()  | 对于一个日期，返回对应的星期几 |
|    Hour()     |     返回一个时间的小时部分     |
|   Minute()    |     返回一个时间的分钟部分     |
|    Month()    |     返回一个日期的月份部分     |
|     Now()     |       返回当前日期和时间       |
|   Second()    |      返回一个时间的秒部分      |
|    Time()     |   返回一个日期时间的时间部分   |
|    Year()     |     返回一个日期的年份部分     |

## 5.3 数值处理函数

|  函数  |        作用        |
| :----: | :----------------: |
| Abs()  | 返回一个数的绝对值 |
| Cos()  | 返回一个角度的余弦 |
| Exp()  | 返回一个数的指数值 |
| Mod()  |  返回除操作的余数  |
|  Pi()  |     返回圆周率     |
| Rand() |   返回一个随机数   |
| Sin()  | 返回一个角度的正弦 |
| Sqrt() | 返回一个数的平方根 |
| Tan()  | 返回一个角度的正切 |

# 6. 聚集函数

- 5 个聚集函数：
  - AVG：
    - 只能用于单个列，如需获得多个列的均值，则使用多个`AVG()`；
    - 计算时忽略值为 NULL 的行；
  - COUNT：
    - 若以`*`作为参数，则不忽略值为 NULL 的行；
    - 若以列名为参数，则忽略值为 NULL 的行；
  - MAX：
    - 返回数值或文本的最大值；
    - 若用于文本数据，则返回该列排序后的最后一行；
    - 计算时忽略值为 NULL 的行；
  - MIN;
    - 返回数值或文本的最小值；
    - 若用于文本数据，则返回该列排序后的第一行；
    - 计算时忽略值为 NULL 的行；
  - SUM：计算时忽略值为 NULL 的行；

|  函数   |       作用       |
| :-----: | :--------------: |
|  AVG()  | 返回某列的平均值 |
| COUNT() |  返回某列的行数  |
|  MAX()  |  返回某列的行数  |
|  MIN()  |  返回某列的行数  |
|  SUM()  |  返回某列值之和  |

- ALL 与 DISTINCT：

  - ALL：默认值，可省略；
  - DISTINCT：去重；
    - 仅能作用于列名；
    - 不能用于`*`、计算或表达式；
    - 由于该关键字必须作用于列名，因此不可用于`COUNT(*)`中，即使用`*`统计元组数量时不得去重；

# 7. 分组数据

- 使用 GROUP BY 分组后，聚集函数作用于各个分组而非整个关系；
- `group by`的使用要求：
  - 可包含任意数目的列，实现嵌套分组，数据将在最后规定的分组上进行汇总；
  - 后接的每一列必须是检索列或有效的表达式，而不能是聚集函数；

  - 若在`SELECT`中使用表达式，则必须在`group by`子句中指定相同的表达式，不能使用别名；
  - 除聚集函数中的属性之外，`SELECT`语句中的其它属性必须均在`GROUP BY`中给出；
  - 该子句将待分组列中值为`null`的所有行视为一组；

# 8. 使用子查询

- 查询：一般指代 SELECT 语句；
- 子查询：指被嵌套的查询；
  - 将子查询返回的结果用于 WHERE 子句中；
  - 作为计算字段使用子查询，用于 SELECT 语句中；
- 子查询的使用要求：
  - 子查询执行顺序：先内后外；
  - 子查询通常返回单个列；
  - 嵌套子查询无数量限制，但不宜过多；

# 9. 连接

- 笛卡尔积：
  - 将两个关系中的元组拼接得到$n\times m$个元组，对于重名的属性使用关系名加以区分；
  - 实现方式：不提供 WHERE 等限制条件时，所得结果为笛卡尔积；
- 自连接：表与其自身连接；
  - 实现方式：通过给表取两个相同的别名，然后使用等值连接；
- 自然连接：
  - 在笛卡尔积的基础上，仅拼接所有相同属性上具有相等值的元组，运算结果中不含重复属性；
  - 若两个关系中不含有相同属性，则自然连接的结果与笛卡尔积的结果相同；
  - 实现方式：使用 WHERE 子句，对两张表的对应属性做匹配；
- 内连接：亦称等值连接，基于两个表之间的相等测试；
  - 内连接的计算结果为一张表，作为 FROM 子句的参数；
  - 关键字：`INNER JOIN … ON`；
  - 实现方式：`FROM vendors INNER JOIN products ON vendors.vend_id = products.vend_id`；
  - 与自然连接的差别：
    - 自然连接：自动匹配同名属性；
    - 内连接：需指定待匹配的属性名；
- 外连接：保留没有关联行的行；
  - 左外连接：
    - 保留左表中没有关联行的行，将缺失的内容记为 NULL；
    - 关键字：`LEFT OUTER JOIN … ON`；
  - 右外连接：
    - 保留右表中没有关联行的行，将缺失的内容记为 NULL；
    - 关键字：`RIGHT OUTER JOIN … ON`；

# 10. 集合运算

## 10.1 定义

- 集合运算：将多个`SELECT`语句的返回结果执行集合运算后输出；
  - 并运算：`union`；
  - 交运算：`intersect`；
  - 并运算：`except`；

## 10.2 集合运算

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
  - 对组合查询结果排序：`ORDER BY`子句仅在最后一条`SELECT`语句后出现一次；

# 11. 全文本搜索

- MySQL 支持的数据库引擎：
  - MyISAM：支持全文本搜索；
  - InnoDB：不支持全文本搜索；
- 在创建表时启用全文本搜索：
  - 在 CREATE TABLE 语句中添加 FULLTEXT() 子句，以待索引的列名作为子句参数；
  - 索引被定义后，MySQL 在增删改查时自动更新该索引；
- 全文本搜索示例：

```SQL
SELECT note_text
FROM productnotes
WHERE Match(note_text) Against('rabbit');
```

|   函数    |      作用      |
| :-------: | :------------: |
|  Match()  | 指定待搜索的列 |
| Against() | 指定搜索表达式 |

- 全文本搜索对结果排序，先返回具有较高等级的行；
  - 根据词的数目、唯一词的数目、整个索引中词的总数以及包含该词的行的数目计算等级；
  - 排序多行时考虑多个待检索词的出现频率；

```SQL
SELECT note_text, Match(note_text) Against('rabbit') AS rank
FROM productnotes
```

# 12. 插入数据

## 12.1 插入完整行

- INSERT 语句使用要求：
  - 尽量使用指定列名的 INSERT 语句；
  - 对未指定值的项，赋值为 NULL；
  - 对于自动增量的列，赋值为 NULL；

```SQL
-- 插入时指定列名
INSERT INTO Customers (cust_id, cust_name)
VALUES('1000000006', 'Toy Land');
-- 插入时不指定列名
INSERT INTO Customers				
VALUES('1000000006', 'Toy Land');
```

## 12.2 插入行中的部分项

- 使用指定列名的`INSERT`语句，可省略部分项不填充，但须满足以下某个条件：
  - 在表的定义中，允许该列的值为`NULL`；
  - 在表的定义中，该属性具有默认值；
  - 即省略部分项时，将使用`NULL`或默认值；

## 12.3 插入检索出的数据

- `INSERT  ... SELECT ...`语句：

  - 根据列的位置填充，而非根据列名填充；
- 即`SELECT`中的第一列填充至`INSERT`中的第一列；
  
```sql
INSERT INTO Customers(cust_name, cust_id)
SELECT cust_name, cust_id
FROM CustNew;
```

## 12.4 将数据从一张表复制到另一张表

```sql
CREATE TABLE Customers AS
SELECT *
FROM Customers;
```

# 13. 更新和删除

## 13.1 更新

- 更新：UPDATE 语句中可使用子查询；

```mysql
UPDATE tableName SET
	columnName1 = value1,
    columnName2 = value2,
    ...
WHERE ...;
```

## 13.2 删除

- 删除：DELETE 删除表中的行，但不删除表本身；

```mysql
-- 删除关系r及其模式
DROP FROM r;			
WHERE ...;
-- 仅删除关系r中的所有记录
DELETE FROM r;			
WHERE ...;
```

# 14. 创建和操纵表

## 14.1 创建表

- 创建表：
  - IF NOT EXISTS：仅当不存在同名表时创建该表；
  - AUTO_INCREMENT：设置主键自增长，应对整数类型使用；
    - 每个表仅有一列可被设置为自增长；
    - 通过使该列成为主键，确保其被索引；
  - NOT NULL：键值非空约束；
  - DEFAULT：设置默认值，默认值必须为常量，不能为函数；
  - UNIQUE：键值唯一性约束；
  - `ENGINE=InnoDB`：设置引擎为`InnoDB`；
- ==面试题== 手写创建表的 SQL 代码：

```mysql
-- 创建表
CREATE TABLE IF NOT EXISTS customers 
(
	cust_id		int			NOT NULL	AUTO_INCREMENT,
	cust_name 	char(50)	NOT NULL,
    cust_address	varchar(10)	NULL,
    quantity		int		NOT	NULL	DEFAULT 1,
    PRIMARY KEY (cust_id)
)	ENGINE=InnoDB;
```

- 创建表后设置主键的方式：
  - 添加主键，`ALTER TABLE tableName ADD PRIMARY KEY(columnName)`；
  - 删除主键：`ALTER TABLE tableName DROP PRIMARY KEY`；
-  last_insert_id()：返回最后一个 AUTO_INCREMENT 值；
- 引擎：管理数据；
  - MyISAM：性能高，支持全文本搜索；
  - InnoDB：可靠，支持事务处理；

## 14.2 更新表

- 添加和删除列：

```mysql
-- 添加列
ALTER TABLE Vendors
ADD vend_phone CHAR(20);
-- 删除列
ALTER TABLE Vendors
DROP COLUMN vend_phone;
```

- 定义外键：
  - 一对一关系：一个表的主键同时也是外键；
  - 多对多关系：创建中间表（关联表），即需要三张表，关联表使用两个外键分别引用另两张表的主键；

```mysql
-- 创建表时添加外键
CONSTRAINT foreignKeyName
FOREIGN KEY (columnName)
REFERENCES tableName(primaryKeyName);
-- 修改表时添加外键约束
ALTER TABLE tableName
ADD CONSTRAINT foreignKeyName
FOREIGN KEY (columnName)
REFERENCES tableName (primaryKeyName);
-- 删除外键
ALTER TABLE tableName
DROP FOREIGN KEY foreignKeyName;
```

- 删除表：`DROP TABLE tableName`；
- 重命名表：`RENAME TABLE tableName1 TO tableName2`；

# 15. 视图

- 

# ==旧版本笔记==

### 3.1.1 DDL

#### 3.1.2.1 数据库和表的创建修改

- 查看：
  - `SHOW DATABASES`：查看所有数据库名单；
  - `SHOW TABLES`：查看所有表名；
  - `DESC tableName`：查看表的详细信息，description；
- 使用并选择数据库：`USE databaseName`；
- 创建数据库：
  - `CREATE DATABASE test`；
  - `CREATE DATABASE IF NOT EXISTS test`；
- 删除：
  - 删除数据库：
    - `DROP DATABASE databaseName`；
    - `DROP DATABASE IF EXISTS databaseName`；
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

### 3.1.5 函数

- `ifnull(expression1, expression2)`:
  - 若表达式1不为空，则返回该表达式；
  - 否则返回表达式2；

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

## 3.3 SQL 查询的基本结构

### 3.3.1 查询、排序和自然连接

- `select`子句中可使用运算符：$+、-、\times、\div$；
  - 运算对象：常数、属性；
  - 与空值相加结果为空；
  - 若运算对象不为数值，则视为0参与计算；
  - 返回一个关系中的所有属性：`SELECT R1.*;`；

### 3.3.2 附加基本运算

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
    - `select distinct`：若两个元组各个属性值均相等，即被视为相同元组，即使其中部分值为空，`DISTINCT`作用域所有列；
    - 谓词中`null=null`将返回`unknown`，`null`不是一个数，各个`null`被认为不相同；
- 检测`null`和`unknown`：
  - `is null`；
  - `is not null`；
  - `is unknown`；
  - `is not unknown`；

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

- `CASE`语句：选择；

```mysql
CASE
	WHEN pred1 THEN result1
	WHEN pred2 THEN result2
	ELSE result0
END;
```



# 4. 中级 SQL

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

- 函数：允许函数同名，只需保持函数的参数个数或类型不完全相同即可；
- 过程：允许过程同名，只需保持参数个数不同即可；

# TODO

- Task：Chapter 1-22；
- 当前进度：Chapter 21 已完成；
- 参考笔记整理进度：
  - 每次整理2节；
  - 已完成：1-16；

# References

[1] Forta B. MySQL 必知必会[M]. 北京: 人民邮电出版社, 2009.