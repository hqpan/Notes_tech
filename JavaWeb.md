[TOC]

# 1. XML

## 1.1 XML overview

- XML：Extensible Markup Language，可扩展标记语言；
  - 用途：
    - 用于系统间数据交换，不同于 HTML 被用于显示数据；
    - 表示数据间的关系；
    - 用做配置文件；
  - XML 的标签未被预定义，需要用户自行定义；
  - 版本：XML 1.0，XML 1.1，目前常用 XML 1.0，因为 XML 1.1 不能向下兼容；



## 1.2 XML 语法

### 1.2.1 文档声明

- 文档声明：必须作为文件第一行；
  - 编码：
    - GBK：“国标”、“扩展”的汉语拼音首字母，汉字内码扩展规范；
    - UTF-8；
    - ISO8859-1：不含中文；

```xml
<?xml version="1.0" encoding="gbk"?>
```

- 避免中文字符乱码：
  - XML 文档声明中指定的编码标准，用于读取文档内容；
  - 在保存 XML 文档时，应选择相同的编码标准；
  - 若两者不一致，则发生乱码；



### 1.2.2 定义标签和属性

- 标签：亦称元素，可嵌套；
  - 一个文件中有且仅有一个根标签；
  - 空格和换行：被视为标签内容的一部分，而不是为提高可读性引入的内容；
  - 含有内容的标签；
  - 不含内容的标签；

```xml
<tag1>content</tag1>
<tag2/>
```

- 标签命名要求：
  - 区分大小写；
  - 不含冒号、空格；
  - 不能以数字、下划线开头；
  - 不能以`XML`、`Xml`、`xml`开头；
- 属性定义要求：
  - 属性命名要求与标签命名要求相同；
  - 一个标签上可有多个属性；
  - 每个属性有不同的名称和各自的取值，属性值用单引号或双引号限定；
  - 标签属性也可作为子标签；



### 1.2.3 注释和转义字符

- XML 注释：不能嵌套；

```xml
<!-- comment -->
```

- 转义字符：

| 特殊字符 | 替代符号 |
| :------: | :------: |
|    &     |  `&amp;`   |
|    <     |   `&lt;`   |
|    >     |   `&gt;`   |
|    "     |  `&quot;`  |
|    '     |  `&apos;`  |

- CDATA ：Character Data，将其中的标签视为普通文本，无需转义；

```xml
<![CDATA[content]]>
```



### 1.2.4 处理指令

- PI：Processing Instruction，处理指令，设置 XML 的样式；
  - XML 声明；
  - xml-stylesheet 指令：指定 XML 文档使用的 CSS 样式 XSL，但对中文无效；

```xml
<?xml-stylesheet type="text/css" href="some.css"?>
```



# 2. DTD

## 2.1 DTD Overview

- XML 约束：
  - DTD 约束；
  - Schema 约束；
- DTD：Document Type Definition，文档定义类型;
  - 文件名后缀为`.dtd`；
  - 对根标签、子标签分别加以约束；

```dtd
<!ELEMENT tagName (subTagName1,subTagName2,...)>
<!ELEMENT subTagName1 (#PCDATA)>
<!ELEMENT subTagName2 (#PCDATA)>
```



## 2.2 DTD 的引入方式

- DTD 的引入方式::
  - 内部 DTD 文件；
  - 外部 DTD 文件；
  - 网络上的 DTD 文件；

```dtd
<!-- Inner DTD file-->
<!DOCTYPE rootTagName [
    <!ELEMENT person (name,age)>
    <!ELEMENT name (#PCDATA)>
    <!ELEMENT age (#PCDATA)>
]>

<!-- Outer DTD file -->
<!DOCTYPE rootTagName SYSTEM "FilePath">

<!-- DTD file from Internet -->
<!DOCTYPE rootTagName PUBLIC "fileName" "URL">
```



## 2.3 使用 DTD 定义元素

- 使用 DTD 定义元素：
  - 定义简单元素：无嵌套；
  - 定义复杂元素：有嵌套；
- 定义简单元素时的约束：
  - `(#PCDATA)`：元素为字符串类型；
  - `EMPTY`：元素为空；
  - `ANY`：元素可为任意值；
- 约束复杂元素的出现次数：
  - `?`：出现0次或1次；
  - `*`：出现0次或多次；
  - `+`：出现1次或多次；
  - `|`：只出现多个元素中的某1个；
  - `,`：用逗号分隔的元素按顺序出现；

```dtd
<!-- 定义简单元素 -->
<!ELEMENT tagName constraint>
<!-- 定义复杂元素 -->
<!ELEMENT person (name,age,sex,school)>
```





## 2.4 使用 DTD 定义属性

- 使用 DTD 定义属性的格式：跟在元素定义之后；

```dtd
<!ATTLIST tagName
	tagName tagType constraint
    ...
>
```

- 属性值类型：
  - `CDATA`：属性值为字符串；
  - `ID`：属性值不能重复，且只能由字母和下划线组成，不能有空白；
  - `(Value1|Value2|...)`：枚举类型无关键字，由`|`符号体现；
- 属性约束：
  - `#REQUIRED`：该属性必须出现；
  - `#IMPLIED`：该属性可有可无；
  - `#FIXED`：该属性的取值为一个固定值；
  - 直接值：无关键字，即给出默认值；



## 2.6 定义实体

- 实体：尽量将实体定义在内部 DTD 中，因为部分浏览器无法正常读取外部 DTD 中的实体；
  - 定义实体；
  - 引用实体；

```dtd
<!-- Definition -->
<!ENTITY name "value">
<!-- Reference -->
&name;
```



# 3. 解析XML

## 3.1 Overview

- W3C：World Wide Web Consortium，万维网联盟；
- XML 解析方式：
  - DOM：Document Object Model，文档对象模型，W3C 推荐的解析方式；
    - 根据 XML 的层级结构，在内存中分配一个树形结构，将文件中的标签、属性和文本都封装成对象；
    - 优点：便于实现增删改操作；
    - 缺点：若文件过大，则内存溢出；
  - SAX：Simple API for XML，几乎所有的 XML 解析器都支持；
    - 采用事件驱动，边读边解析，解析到某个对象后返回对象名称；
    - 优点：不会造成内存溢出，便于实现查询操作；
    - 缺点：无法实现增删改操作；
- XML 解析开发包：
  - JAXP：SUN 公司推出的解析标准实现；
  - dom4J：由开源组织 dom4J 推出，更常用；
  - JDOM：由开源组织 JDOM 推出；



## 3.2 JAXP

- JAXP：Java API for XML Processing，是 Java SE 的一部分；
  - org.w3c.dom：提供 DOM 方式解析 XML 的标准接口；
  - org.xml.sax：提供 SAX 方式解析 XML 的标准接口；
  - javax.xml：提供解析 XML 的类；
  - javax.xml.parsers：定义了几个工厂类，借助其可得到对 XML 进行解析的 DOM 和 SAX 解析器对象；
    - DOM：
      - DocumentBuilder：解析器类；
        - 抽象类，可通过解析器工厂类获取实例；
      - DocumentBuilderFactory：解析器工厂；
    - SAXParserFactory：parser，n. 解析器；
- 注意：添加节点时，由于对 XML 的操作均在内存中进行，因此需要将操作后的结果回写到 XML 文件中；



# 4. Schema

## 4.1 Overview

- Schema：W3C 组织的标准，逐步取代 DTD；
  - 优点：
    - 符合 XML 语法，即为 XML 语句；
    - 对名称空间支持较好；
    - 支持的数据类型多于 DTD，且支持用户自定义的数据类型；
    - 可对 XML 做出细致的语意限制；
  - 缺点：语法比 DTD 更复杂；
- 一个 XML 中可有多个 Schema，多个 Schema 使用名称空间区分（类似于 Java 中的包名）；



# ==待整理的内容==

- 