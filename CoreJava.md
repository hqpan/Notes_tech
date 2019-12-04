[TOC]



# 版权声明

- Java 系列读书笔记来源于 Cay S. Horstmann 所著《Java 核心技术 卷I 基础知识（第10版）》[1]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及科学研究；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；



# 1. Java 简介

- Java：
  - Java SE：提供底层支持，桌面程序开发；
  - Java ME：嵌入式开发；
  - Java EE：企业平台、互联网平台搭建；
- Java 的优点：
  - 面向对象；
  - 方便的内存回收处理机制；
  - 使用引用代替指针；
  - 是为数不多支持多线程开发的语言；
  - 高效的网络处理能力；
  - 可移植性；



# 3. Java的基本程序设计结构

- `console`：n. 控制台，仪表板；

- `src`：源码目录；

## 3.1 简单的Java应用程序

- 在命令行窗口运行程序：
  - `javac filename.java`：编译器将源码转换成字节码；
  - `java filename`：Java虚拟机执行存放在`filename.class`中的字节码；
    - 虚拟机将从指定类中的`main`方法（或称为函数） 开始执行；
    - 在类的源文件中必须包含一个`main`方法；
      - `main`方法必须为`public`类型；
    - 将自定义的方法添加到类中，并在`main`方法中调用其他方法；
- Java 区分大小写；
- `public`：访问修饰符（access modifier）；
- 类名以大写字母开头，若由多个单词构成，则采用驼峰命名法；
- 源码文件命名：源代码的文件必须与 `public` 类的名字相同，扩展名为 `.java`；
- 字节码文件：编译器将源码文件的名字，作为字节码文件名字，使用 `.class`作为扩展名，与原文件存储在同一个目录下；
- `object.method(parameter)`：E.g. System.out.println("Hello, world!")，使用`System.out`对象调用`println`方法；
  - `.print`方法输出时不换行；




## 3.2 注释

- Java支持三种注释形式：
  - 其中第三种，以`/**`开始，以`*/`结束，该种注释可自动生成文档；
  - 在Java中不允许`/* ... */`注释嵌套；



## 3.3 数据类型

- Java：一种强类型语言，为每一个变量声明一种类型；
- 整型：byte，short，int， long；
- 浮点类型：float，double；

### 3.3.1 整型

- 表示数值：
  - 长整型`long`：后缀`L`或`1`；
  - 十六进制：前缀`0x`或`0X`；
  - 八进制：前缀`0`；
    - 八进制表示法容易与数值混淆，尽量少用，E.g. 010；
  - 二进制：前缀`0b`或`0B`；
  - （可选）为数字添加下划线：E.g. `1_000_000`；
    - 增加可读性；
    - 编译器运行时，将去除这些下划线；



### 3.3.2 浮点类型

- 表示数值：
  - float：后缀`F`或`f`；
    - 无后缀的浮点数默认为 double 类型，E.g. `float f = 1.3`将报错，因为1.3默认是 double 类型；
  - （可选）double：后缀`D`或`d`；

- 三个特殊的浮点数：
  - 正无穷：
    - Float.POSITIVE_INFINITY；
    - Double.POSITIVE_INFINITY；
  - 负无穷：
    - Float.NEGATIVE_INFINITY；
    - Double.NEGATIVE_INFINITY；
  - NaN：not a number，各个NaN被认为不相同；
    - Float.NaN；
    - Double.NaN；
  - 检测某个值是否等于`Double.NaN`：
    - `if(Double.isNaN(x))`;
      - 正确；
    - `if(x==Double.NaN)`；
      - 错误；



### 3.3.3 char 类型

- 转义序列`\u`可出现在加引号的字符串之外；
  - 其他转义序列仅能出现在加引号的字符串之中；
- Unicode转义序列在解析代码之前得到处理；
  - 因此出现在注释中的转义序列，由于在解析代码之前被替换为字符，可能引发语法错误；



### 3.3.4 Unicode 和 char 类型

- code point（码点）与code unit（代码单元）：

  - 码点：编码表中某个字符对应的代码值；

  - 代码单元：每个字符用16位表示；
    - 常用 Unicode 字符使用一个代码单元表示；
    - 辅助字符使用两个代码单元表示；

- 在 Java 中，char 类型描述 UTF16 编码中的一个代码单元；

- 不建议使用 char 类型；

  - 除非需要处理 UTF16 代码单元；
  - 最好将字符串作为抽象数据类型处理；

- 返回代码单元数量：

```java
str1.length();			// 返回代码单元数量；
str2.codePointCount();	// 返回码点数量；
str3.charAt(n);			// 返回第 n 个代码单元；

// 获取第 i 个码点；
int index = str4.offsetByCodePoints(0, i);
int cp = str4.codePointAt(index);
```

  



### 3.3.5 boolean 类型

- boolean：adj.布尔数学体系的；
- 整型值与 boolean 值之间不能相互转换；



## 3.4 变量

- 不提倡在一行中申明多个变量；

  - E.g. `int i,j`；

  - 逐一声明变量可提高程序可读性；

- 申明一个变量后，必须用赋值语句对变量进行显式初始化；

  - 不能使用未初始化的变量；
  - 变量申明与初始化可置于同一行中；

  - Java中的申明可置于代码中的任意位置；
    - 提倡将申明放在靠近变量第一次使用的地方；

- 常量;

  - `final`：定义常量；

    - 常量只能被赋值一次，此后不能修改；
    - 习惯上，常量名中的每个字母均使用大写；

  - `static final`：定义类常量；

    - 类常量定义于`main`方法的外部；
    - 可在一个类的多个方法中使用；
    - 若一个常量被声明为`public`，则其他类中的方法也可使用该常量；

  - `final`修饰符大多用于基本域类型和不可变类型；

    - E.g. `String`为不可变类型，未提供修改字符串的方法；

    - 对于可变的类不应使用`final`，令人费解；

      - E.g. 以下错误示例中`final`表示存储在`evaluations`变量中的对象引用，不会再指向其他的`StringBuilder`对象，但该对象可以修改；

      ```java
      private final StringBuilder evaluations;
      ```

      



## 3.5 运算符

- 若被除数和除数均为整数，则表示整数除法；
  - 否则表示浮点除法；
  - 整数除以0，产生异常；
  - 浮点数除以0，结果为无穷大或NaN（not a number）；

### 3.5.1 数学函数与常量

- `Math`类中，包含有各种数学函数；
  - `Math.sqrt(x)`：开方；
  - `Math.pow(x, a)`：x 的 a 次幂，两个参数均为 double 类型；
  - `Math.PI`：近似表示 π 的常量；
  - `Math.E`：近似表示 e 的常量；
- `floorMod(position+adjustment, 12)`，若除数为负数，取余/求模时余数亦为负数；
- 在源文件顶部加上`import static java.lang.Math.*;`，则不必在数学方法名和常量名前添加前缀`Math`；
- 运行速度与可移植性之间的权衡：
  - `Math`类中所有方法的例程：运行速度快；
  - `StaticMath`类：在各个平台上得到相同的结果；
  - 可移植性：确保同一个浮点数计算，在不同的虚拟机上运行得到相同的结果；



### 3.5.2 数值之间的转换

- 数值之间的转换：
  - 合法转换：不丢失信息的转换；
  - 强制类型转换：丢失信息的转换；
    - 截断小数部分；

- `Math.round()`方法：

  - 返回离该值最近的一个整数；
  - 该方法返回的数据类型为 long；

- 自增自减运算符会改变变量的值，因此它们的操作数不能是数值；

  - 前置的`++`或`--`会先完成加1；
  - 后置的`++`或`--`会使用变量原来的值；
  - 建议不在表达式中使用自增自减运算符，以免让人困惑；

- 三元操作符：

  ```java
  x<y?x:y
  ```

- 掩码（mask）：指一串二进制数字，通过与目标数字的按位操作，实现屏蔽指定位的功能；

- 位运算符：

  - `&`、`|`不使用短路求值；
  - `^`：位运算符，异或 xor；
  - `>>`：按位右移，高位用符号位填充；
  - `>>>`：按位右移，高位用0填充；
  - `<<`：按位左移，低位用0填充；
    - 不存在`<<<`；

- 使用位运算符的注意事项：

  - `float`、`double`不能执行移位操作；
  - `byte`、`short`移位前被自动转换为`int`类型，然后执行移位操作；
  - 若左操作数为`int`类型，则先将左操作数对32取模，然后执行移位操作；
    - E.g. `1<<35`等价于`1<<3`；
  - 若左操作数为`long`类型，则先将左操作数对64取模，然后执行移位操作；



## 3.6 字符串

### 3.6.1 术语

- `String`类对象：该类未提供用于修改字符串的方法，因此被称为不可变字符串；



### 3.6.2 子串

- `String`类的`substring(a,b)`方法：
  - 从字符串中提取第 a 至 b-1 个字符，组成新的字符串；
  - 提取出的子串长度为 b-a；



### 3.6.3 拼接

- 字符串拼接：使用`+`；

  - 当一个非字符串值与一个字符串进行拼接时，前者被转换为字符串；
  - 任何一个 Java 对象均可转换为字符串；

- 静态`join`方法：将多个字符串拼接后，使用界定符分隔；

  ```java
  String all = String.join("/", "S", "M", "L", "XL");
  ```



### 3.6.4 检测字符串是否相等

- 检测字符串的内容是否相同：

  ```java
  str1.equals(str2);				// 检查字符串内容是否相等；
  str1.equalsIgnoreCase(str2);	// 不区分大小写；
  ```

- `str1 == str2`：用于判断字符串在内存中的地址是否相同；

  ```java
  String str1 = "hello";
  String str2 = "hello";
  // java 在缓冲区查找是否存在常量对象“hello”；
  // 若存在该对象，则将该对象地址赋值给 str2；
  
  new String str1 = "hello";
  new String str2 = "hello";
  // 直接在内存中开辟一个存储空间，并将该对象地址赋值给 str2；
  
  // new 操作符返回的是引用；
  ```




### 3.6.5 空串与 NULL 串

- 空串：长度为0，```""```；

  - 判断字符串是否为空串：

  ```java
  if(str.length()==0)						// 判断空串；
  if(str.euqals(""))						// 判断空串；	
  ```

- NULL 值上不能调用方法，会报错：

  - 因此先判断是否为 NULL 串，后判断是否为空串；

  ```java
  // 判断该字符串不是 NULL 串，也不是空串；
  if(str != null && str.length() != 0)
  ```

  

 ### 3.6.6 构建字符串

- 频繁使用字符串连接（`+`）效率低下，使用`StringBuilder`类避免该问题；

```Java
StringBuilder builder = new StringBuilder();
builder.append("ch");
builder.append("str");
String completedString = builder.toString();  
// 返回一个与构建器或缓冲器内容相同的字符串；
```

  

## 3.7 输入输出

### 3.7.1 读取输入

- 若使用的类不在`java.lang`包中，则应使用`import`加载该包；

  - `Scanner`类定义在`java.util`中；

- 构造一个`Scanner`对象，并与标准输入流`System.in`关联；

```java  
Scanner in = new Scanner(System.in);
String name = in.nextLine();		
// nextLine 方法读取一行；
// next 方法读取一个单词；

int age = in.nextInt();				
// nextInt 方法读取一个整数；
// nextDouble 方法读取一个浮点数；
```



### 3.7.2 格式化输出

- `print`、`println()`、`printf()`；

  - `printf()`支持格式化输出，用法类似于 C 语言；
- 在`printf`中输出一个`%`：`printf("%%")`；

  - `printf`中的转义字符为`%`；
  - 类似于`String.format("...");`；
- `\t`：
  - 对字符串：补全当前长度到4的整数倍；
  - 对数字：补全当前长度到8的整数倍；

  



## 3.8 控制流程

### 3.8.1 术语

|      英文       |  中文  | 释义 |
| :-------------: | :----: | :--: |
|      block      |   块   |  -   |
| block statement | 块语句 |  -   |

- Java 不允许在两个嵌套的块中声明同名变量；
- `else`与相距最近的`if`配对；
- 循环结构：`for`、`while`、`do...while`；
  - `do...while`：先执行一次，然后循环；
- 在`switch`中使用枚举常量时，不必在标签中指明枚举名，可由`switch`的表达式值确定；



### 3.8.2 中断控制流程语句

- Java 中提供带标签的`break`语句；
  - 跳转至带标签语句块的末尾；
  - 功能类似于`goto`语句；
  - 不提倡使用该语句；

```java
labelSample:
while(condition1)
{
    if(condition2)
        break labelSample;
}
```

## 3.9 大数值

- BigInteger 类  BigDecimal 类：
  - 不能使用`+，-，×，/`进行四则运算；
  - 应使用`add`、`subtract`等方法；



## 3.10 数组

- 声明、创建数组：
  - 数组的长度可为变量；
  - 允许匿名数组和长度为0的数组；
    - 长度为0的数组不同于 NULL；
  - `array.length`：获取数组长度；

```java
int[] a;					// 声明数组（推荐），将类型与变量名分开；
int a[];					// 声明数组；
int[] a = new int[100];		// 创建数组；
Arrays.toString(a)			// 返回结果，E.g.[1,2,3]；
Arrays.deepToString(a)		// 返回二维数组；
```

- `for each`语句依次处理数组中的所有元素；
```java
for(int element: a)
	statement;
```



### 3.10.3 数组拷贝

- 数组拷贝时，两个变量引用同一个数组；

  - 如需将一个数组中的值拷贝到新的数组中，则应使用`Array.copyOf()`；
  - `Array.copyOf()`应用与数组本身，通过改变参数，修改数组大小；
- `String[] args`：字符串数组，即命令行参数；



### 3.10.4 数组排序

```java
Arrays.sort(arrayName);		// 快速排序算法；
Math.random();				// 生成区间为[0,1)的一个随机浮点数；

import java,util.*;
Random()					// 构造一个新的随机数生成器；
int nextInt(int n);			// 返回一个 0-n-1 之间的随机数；
```

### 3.10.5 不规则数组

- 不规则数组：
  - Java 中没有多维数组，用“数组的数组”表示多维数组；
  - E.g. 二维数组中，每行对应的一维数组长度可以不同；

- 简化的初始化方式：

```java
int[][] arrayName = 
{
    {1, 2},
    {3, 4},
};

arrayName.length		// 数组的行数；
arrayName[i].length		// 第i行的项数；
```

  

# 4. 对象与类

## 4.1 面向对象程序设计 OOP

### 4.1.1 术语

- OOP：Object-oriented programming；
- instance：实例，由类构造的对象；
- instance field：实例域，即对象中的数据；
- state：状态，某个对象中实例域的集合；
- method：操纵数据的过程；
- 如何区分业务场景中的类和方法：名词作为类，动词作为方法；



### 4.1.2 类之间的关系

- uses-a：依赖（dependence），即一个类的方法操纵另一个类的对象；
  - 尽量减少类之间的相互依赖，即让类之间的耦合度最小；
- has-a：聚合（aggregation），类A的对象包含类B的对象；
- is-a：继承（inheritance）;
- UML：unified modeling language，统一建模语言，用于绘制类图；



## 4.2 使用预定义类

### 4.2.1 对象变量

- 构造器（constructor）：一种特殊的方法，用于构造并初始化对象；
  - 构造器的名称与类名相同；
- 对象变量：不包含对象本身，而是一个对“对象”的引用；
  - 可将对象变量设置为 NULL；
  - 若将 method 应用于未初始化的对象上，将产生编译错误；
  - 若将 method 应用于值为 NULL 的对象上，将产生运行时错误（runtime error）；
```java
Date birthday = new Date()；
birthday = NULL;
```



### 4.2.2 LocalDate 类

- UTC：coordinated university time，协调世界时/世界统一时间；
```java
LocalDate.now()			// 构造新对象表示当前日期；
LocalDate.of(2000,1,1)	// 提供年月日构造特定日期的对象；
.getYear()
.getMonthValue()
.getDayOfMonth()
```



### 4.2.3 更改器方法与访问器方法

- 更改器方法与访问器方法：

  - mutator method：更改器方法，修改对象，常命名为`setXXX`，E.g. `setSalary`；
  - accessor method：访问器方法，只访问而不修改对象；
    - 域访问器：只返回实例域值；
    - 常命名为`getXXX`，E.g. `getSalary`；



## 4.3 用户自定义类

### 4.3.1 类

- 一个源文件中仅能有一个公有类，可以有任意数量的非公有类；
  - 源文件名必须与 public 类一致；
  - 优先使用不可变的类；
  - 编译器将为每个类创建对应的类文件`.class`；

    - 不同的类可放置于不同源文件中（推荐），也可放置于同一源文件中；
  - 编译源程序的两种方法：


  ```java
  javac Empolyee*.java			// 使用通配符；
  javac EmployeeTest.java		// 编译含有main函数的文件;
  ```

- 关于实例域的要求：
  - 实例域应设置为 private 类型，以免破坏封装；
  - 不能在任何方法中命名与实例域同名的变量；
    - 以免同名变量在方法内部屏蔽实例域中命名的变量；
- 隐式参数：出现在方法名前面的对象；
  - E.g. `number007.salary`；
  - 关键字`this`可用于表示隐式参数；
- 不要编写返回引用可变对象的访问器方法；==未理解，page110-111==
  - 如需返回一个可变数据域的拷贝，应使用`clone`；



### 4.3.2 构造器

- 构造器与类同名；

- 每个类可有一个以上的构造器；

- 构造器无返回值，不带任何返回参数类型，不能写作`void`；

  - 构造器：任何情况下均不允许有返回值；

  - `void`：表示该函数无返回值，但可将`void`修改后，返回其他类型的值；

  ```java
  public Employee(String n, double s);
  ```

  

- 构造器总是与`new`操作符一起使用；
  - 构造器中不能重新声明与实例域重名的变量，否则会在构造器中屏蔽实例域；
  - 不能对已存在的实例域重新设置实例域；



### 4.3.3 静态域与静态方法

- 静态域：亦称类域；

- `static`变量：

  - 被所有对象共享，在内存中仅有一个副本；
  - 不需要通过类的对象进行访问，可以通过类名直接使用；

  - 非静态变量：每个对象都有各自的副本，副本之间互不影响；

- 静态方法：

  - 没有`this`参数；
  - 不需要使用对象调用静态方法；
  - `main`方法是一种静态方法，不对任何对象进行操作；

- factory method：工厂方法，不借助于`new`，通过使用静态方法对外提供自身实例；



### 4.3.4 main 方法

- 每个类中可有一个main方法，用于对类进行单元测试（unity test）；

  ```java
  java Employee;		// 运行Employee类；
  ```



## 4.4 方法参数

- 参数传递方式;
  - call by value：按值调用；
  - call by reference：按引用调用；
- Java中总是使用按值调用；
  - Java 中的对象引用是按值传递的；



## 4.5 对象构造

### 4.5.1 重载

- Java 允许重载任何方法；
  - 方法的签名：signature，包括方法名及参数类型，不包括返回值类型；
    - 不能有两个名字相同、参数类型相同，但具有不同返回类型的方法；
  - 协变返回类型指的是子类中的成员函数的返回值类型，不必严格等同于父类中被重写的成员函数的返回值类型，而可以是更 "狭窄" 的类型；
    - 允许子类将覆盖方法的返回类型定义为原返回类型的子类型；

- overloading resolution：重载解析，编译器根据调用时提供的参数和值类型，选择具体执行哪一种方法；

- 使用构造器初始化：

  - 若编写一个类时，没有编写构造器，则系统将提供一个无参数的构造器；

  - 无参数的构造器：将所有实例域设置为默认值；

    - 数值型数据的默认值：0；
    - 布尔型数据的默认值：`false`；
    - 对象引用的默认值：`null`；

  - 可在声明的同时，为实例域赋值；

    - 当一个类中的所有构造器均需要为某个实例域赋相同值时，可使用该方法；

  - 参数变量命名技巧：

    - 使用与实例域相同的名字命名参数变量，则参数变量将在块内屏蔽实例域；

    - 在块内使用`this`访问实例域；
  ```java
  public Employee(String name)
  {this.name = name;}
  ```

  - 在一个构造器中调用另一个构造器：

    - 意义：初始化代码中的公共部分仅编写一次；

    - 如何实现：构造器中的第一个语句中使用`this`；
  ```java
  public Employee(double s)
  {this("Andrew", 1000)}		
  // call Employee(String, double)
  ```

  - 初始化的3种实现方法：

    - 构造器；

    - 在声明的时候赋值；

    - 初始化块；

    ```java
    {
        id = nextId;
    }  
    
    static				// 静态初始化块；
    {
        id = nextId;
    }
    ```

    
### 4.5.2 对象析构与finalize方法

- Java 不支持析构器，有自动的垃圾回收器，不需要人工回收内存；
- 可以为任何类添加`finalize`方法；



## 4.6 包

### 4.6.1 类的导入与静态导入

- 所有标准的 Java 包都处于`java`、`javax`包中；

- 使用`import java.time.*;`语句，不影响代码的大小；

- 可将因特网域名以逆序的形式作为包名；

- 导入的两个包中有同名类：

  - 若程序中需要使用两个同名类，则使用完整的包名；

  - 若仅使用同名类中的某一个类，则补充一个特定的`import`语句；

  ```java
  import java.util.*;
  import java.sql.*;
  import java.util.date.*;	// 补充该语句消除歧义；
  ```

- 导入静态方法和静态域；
```java
import static java.lang.System.*;
```



### 4.6.2 将类放入包中

- 包的名字应置于源文件的开头，出现在定义类的代码之前；
- 若未使用`package`语句，则该类将被放置于 default package 中；
  - default package 是一个没有名字的包；

```java
package com.horstmann.corejava;
```

- 从及目录编译、运行类；
  - 编译器对文件（带有文件分隔符和扩展名）进行操作；
  - Java 解释器加载类（带有`.`分隔符）；
```java
javac com/mycompany/PayrollApp.java
java com.mycompany.PayroolApp
```

- 编译器在编译源文件时不检查目录结构，即使目录中不包含相关包，编译器也不报错，但无法运行；



### 4.6.3 包作用域

- 访问修饰符（access modifier）：
  - 未指定访问修饰符的部分（类、方法、变量）可被同一个包中的所有方法访问；
  - `protected`：对本包和所有子类可见；



## 4.7 类路径

- JAR：Java archive，Java 归档文件；
- 类路径：

  - Unix 中，类路径中的不同项之间使用冒号分隔；
  - Windows 中，类路径中的不同项之间使用分号分隔；
- 类路径的构成：
  - 基目录；
  - 当前目录，用句点`.`表示；
  - JAR 文件；
- 编译与运行时，类路径的设置：
  - 编译器总是在当前目录中查找文件，但Java 虚拟机仅在类路径中含有`.`时才查看当前目录
  - 若未设置类路径，则默认的类路径中将包含`.`目录；
  - 若已设置类路径，却未包含`.`目录，则程序可以编译，却不能运行；
- 使用`-classpath`或`-cp`指定类路径；



## 4.8 文档注释

- javadoc：JDK 中的一个工具，可由源文件生成一个 HTML 文档；
  - 包；
  - 公有类、接口；
  - `public`、`protected`的构造器及方法；
  - `public`、`protected`的域；

- 类注释：置于`import`语句之后，类定义之前；

- 方法注释：
  - @param；
  - @return；
  - @throws；

- 域注释：只需要对公有域（常指静态常量）建立文档；

- utility：n.实用，效用，实用程序；

- free-form text：自由格式文本；

  
# 5. 继承（==反射部分暂且跳过==）
## 5.1 定义子类

### 5.1.1 超类与子类

- 术语：

  - superclass/base class/parent class：超类/基类/父类；

  - subclass/derived class/ child class：子类/派生类/孩子类；
  - 多态：一个对象变量可指示多种类型；

- 子类中不可删除继承所得的域和方法；
- 可在子类中覆盖超类中的方法：

  - `super`不同于`this`，不是一个对象的引用，仅仅只是一个指示编译器调用超类方法的关键字；

```java
public double getSalary()
{
    double baseSalary = super.getSalary();
    return baseSalary + bonus;
}
```



### 5.1.2 子类构造器

- 可通过`super`调用超类的构造器，实现对子类的初始化，该语句必须是子类的第一条语句；

```java
super(name, salary, ...);		// 调用超类中含有这些参数的构造器；
```

- 若子类的构造器未显式地调用超类中的构造器，则将自动调用超类中默认的构造器（无参数的构造器）；
- `this`的用途：
  - 引用隐式参数；
  - 调用该类其它的构造器；
- `super`的用途：
  - 调用超类的方法；
  - 调用超类的构造器；
- 调用构造器的语句只能作为另一个构造器的第一条语句出现；



### 5.1.3 多态

- 对象变量是多态的：
  - E.g. Employee 变量既可以引用 Employee 类对象，又可以引用 Employee 类的任何一个子类的对象；
  - 不能将超类的引用赋给子类变量；
  - 如下所示，`boss`与`staff[0]`引用同一个对象，但编译器将`staff[0]`视为Employee对象；

```java
Manager boss = new Manager(...);
Employee staff = new Employee[3];
staff[0] = boss;

boss.setBonus(5000);		// ok;
staff[0].setBonus(5000);	// error;
```

- 类型转换：

  - 允许将子类的引用赋值给超类变量；

  - 超类的引用赋给子类变量时必须进行类型转换；
  - 只能在继承层次内进行类型转换；
  - 尽量少使用类型转换；
  - 将超类转换为子类前，应使用`instanceof`进行检查，确认能否成功进行转换；

```java
if(boss instanceof staff[1])
{...}
```





### 5.1.4 方法调用

- 静态绑定（static binding）：`private`方法、`static`方法、`final`方法、构造器；
- 动态绑定：运行时根据隐式参数的类型，自动选择调用哪个方法；
- 虚拟机预先为每个类创建了一个方法表，其中列出了所有的方法签名和实际调用的方法；
  - 减少每次调用方法时的搜索时间开销；
- 在覆盖一个方法时，子类方法的可见性不能低于超类方法的可见性；
  - E.g. 若超类方法的访问修饰符为`public`，子类方法一定要申明为`public`；
  - 可在子类中的访问修饰符前加上`@Override`标记，若该方法未覆盖超类中的任何方法，编译器将报错；



### 5.1.5 阻止继承

- 使用`final`修饰符修饰类：该类不允许被继承；
  - 该类中的所有方法自动生成为`final`方法，不包括域；
- 使用`final`修饰符修饰类中特定的方法：不允许子类覆盖该方法；
- 内联：inlining，若一个方法没有被覆盖，且该方法很短，则编译器将对其进行优化处理；
  - E.g. 调用`e.getName()`将被替换为访问`e.name`域；



### 5.1.6 抽象类

- 抽象类：
  - 包含一个或多个抽象方法的类，也应被声明为抽象的；
  - 类即使不含抽象方法，也可被声明为抽象类；
  - 抽象类不能被实例化；
    - 可定义抽象类的对象变量，但其只能引用非抽象子类的对象；
- 若不在超类中定义抽象方法，仅在子类中定义该方法，则无法通过超类的对象变量调用该方法；



## 5.2 Object：所有类的超类

### 5.2.1 Object

- 可使用`Object`类型的变量引用任何类型的对象；
  - 如需操作其中的具体内容，应进行相应的类型转换；
- 编写`equals`方法的建议：
  - 将显式参数设为`Object`类型，用于覆盖超类中的`equals`方法；
  - 检测`this`与显式参数是否引用同一个变量；
  - 检测显式参数是否为`null`；
  - 检测隐式参数与显式参数是否为同一个类；
    - 若`equals`的语义在每个子类中有所改变，则使用`getClass`检测；
    - 若所有的子类中`equals`的语义相同，则使用`instanceof`检测；
  - 将`otherObject`转换为相应的类型；
  - 比较各个域；
    - 为防止部分实例域为空（不能对空值使用方法），使用`Objects.equals(a,b)`取代`a.equals(b)`；

```java
Public class Employee
{
    ...
    public boolean equals(Object otherObject)
    {
        if(this == otherObject) return true;
        
        if(otherObject == null) return false;
        
        if(getClass() != otherObject.getClass())
            return false;
        
        Employee other = (Employee) otherObject;
        
        return name.equals(other.name)
            && salary == other.salary
            && hireDay.equals(other.hireDay);
    }
}
```



### 5.2.2 hashCode 方法

- 在自定义的类中需要覆盖的方法：

  - `equals`；

  - 若重新定义`equals`方法，则需重新`hashCode`方法，以便用户将对象插入到散列表中；
  - `toString`，以便用户获取有关对象状态的必要信息；

- `hashCode`：该方法定义在`Object`类中，因此每个对象均有默认的散列码；
  - 若参数为`null`，则该方法返回0；
- 如需组合多个参数的散列值时，可调用`Objects.hash`，并提供多个参数；
  - 该方法将对各个参数调用`Objects.hashCode`，并组合这些散列值；

- 对`double`类型的数据使用`hashCode`方法：

```java
return super.hashCode() + 13*new Double(salary).hashCode();	
// 创建Double对象；
return super.hashCode() + 13*Double.hashCode(salary);	
// 使用静态方法避免创建Double对象；
```



## 5.3 泛型数组列表

- `ArrayList`：采用类型参数的泛型类；
  - 添加或删除元素时，可自动调节数组容量；
  - 其对象不允许为基本类型；
- 若数组中存储的元素数量较多，且需要在中间位置插入、删除元素，则效率低下；
  - Solution：使用链表；



## 5.4 对象包装器与自动装箱

- wrapper：包装器，即各个基本类型各自对应的类；
  - 对象包装器类不可变，不允许更改其中的值；
  - 对象包装器类由`final`修饰，因此不能定义他们的子类；
- autoboxing：自动装箱；



## 5.5 枚举类

- 所有的枚举类型都是`enum`类的子类；



## 5.7 反射

### 5.7.1 Class 类

- `Class`用于“保存运行时的类型”信息；

- 获取`Class`类对象的方式：

  - 对`getClass()`方法返回的类型实例使用`getName()`，返回类名；

  - 返回某个类型对应的类对象：`Class Cl = AnyType.class`；

  - `Object`类中的`getClass()`方法返回一个`Class`类型的实例；

    - package 的名字也作为类名的一部分；

    - 返回类名为`className`的`Class`对象；

    ```java
    static Class forName(String className);
    ```

- `newInstance(Object[] args)`：创建某个类型的实例，默认调用无参数构造器；

  
### 5.7.2 异常

- 异常：
  - 未检查异常：编译器将不检查是否提供了处理器（handler），这些问题可通过精心编写代码解决；
  - 已检查异常；
- `Throwable`是`Exception`的超类；



# 6. 接口、lambda 表达式与内部类

## 6.1 接口

### 6.1.1 接口的概念

- 每个类仅能有一个超类（Java 不支持多重继承），但可实现多个接口；
- 接口中的所有方法自动属于`public`，不必提供该关键字；
- 接口中可以含有常量；
  - 接口中的域被自动设置为`public static final`；
- 接口中不能含有实例域；
- 接口不是类，不能使用`new`实例化一个接口；
- 接口中可声明非抽象方法；
- 接口可被扩展；
  - 类似于类与类之间的继承，一个借口可由另一个接口扩展得到；
- 可声明接口的变量，引用实现了接口的类对象；
  - `instanceof`：检查某对象是否实现了特定的接口；
- 让类实现接口的步骤：

  - 将类声明为实现指定的接口；
  - 在类中实现接口中的所有方法；
- 回调：callback，指定某个特定事件发生时应执行的动作；



### 6.1.2 默认方法

- 默认方法：为设置默认的接口方法，使用`default`修饰；
- 解决默认方法之间的冲突：
  - 超类优先：若超类、接口中同时提供同名且参数类型相同的默认方法，则以超类中的方法为准；
  - 接口冲突：若两个接口同时提供同名且参数类型相同的默认方法，则应在类中覆盖该方法；



## 6.2 克隆

- 克隆：不同于为对象的引用建立副本；
- `clone`方法：`Object`的`protected`方法；
  - 子类只能调用`protected`的`clone`克隆自己的对象；
  - 必须重新定义`clone`为`public`才能允许所有方法克隆对象；
  - 所有的数组类型均有一个`public`的`clone`方法；
- 标记接口：tagging interface/marker interface，其中不包含任何方法，仅用于提示类设计者；
  - 建议不在自行设计的程序中使用标记接口；



## 6.3 lambda 表达式

- lambda 表达式：带参数变量的表达式；
  - 作用：传递代码；
  - 本质：匿名函数；
- 若一个 lambda 表达式的参数类型经推导可得，则可不书写参数类型；
- 若 lambda 的方法中仅有一个参数，且参数类型经由推导可得，则可省略修饰参数的小括号；
- 无需指定 lambda 表达式的返回类型；
  - 编译器经由上下文可推得；
- lambda 表达式仅在部分语句分支中才有返回值的做法不合法；



### 6.3.2 函数式接口与方法引用

- 函数式接口：对于仅有一个抽象方法的接口，如需使用该接口的对象，则可提供一个 lambda 表达式；
- 方法引用：不能独立存在，总是会转换为函数式接口的实例；
  - `object::insteanceMethod`；
  - `Class::staticMethod`；
  - `Class::instanceMethod`；



### 6.3.3 构造器引用

- 构造器引用：E.g. `Person::new`；

- 可用数组类型建立构造器引用：E.g. `int[]::new`；
- Java 无法构造泛型类型 T 的数组；



### 6.3.4 变量作用域

- 闭包：closure，Java 中的 lambda 即为闭包，表示代码块及自由变量值；
- lambda 表达式只能引用值不会改变的变量；
- lambda 表达式与嵌套块的作用域相同；



## 6.4 内部类

- 内部类：

  - 内部类可访问外围类对象的数据域；

  - 内部类中声明的所有静态域必须为`final`；
  - 内部类不能有`static`方法；

- 编译器将内部类编译为“用`$`分隔外部类名与内部类名的常规类文件”；

- 局部内部类：不能使用访问修饰符，作用域为声明该局部类的块中；




# 7. 异常、断言和日志

- Java 中处理系统错误的机制：

  - 断言：
    - 断言失败是致命的错误；
    - 断言检查仅用于开发和测试阶段；

  - 日志：记录下出现的问题；
    - 程序的整个生命周期均可使用；



## 7.1 处理错误

- 方法抛出一个封装了错误信息的对象后，该方法立即退出，不返回任何值；调用该方法的代码也无法继续执行，异常处理机制搜索对应的异常处理器；



### 7.1.1 异常分类

- `Throwable`：
  - `Error`：系统内部错误、资源耗尽错误；（无法控制）
  - `Exception`：
    - `RuntimeException`：程序错误导致的异常；（竭力避免）
    - `IOException`：程序自身无错误，I/O 错误导致的异常；
- 异常分类：
  - unchecked exception：非受查异常，派生于`Error`、`RuntimeException`的异常；
  - checked exception：受查异常；
    - 编译器将核查是否为所有的受查异常提供了异常处理器；
- 若子类覆盖超类中的一个方法，则子类中的受查异常不能比超类中声明的异常更通用；
  - 子类中可选择抛出更特定的异常；
  - 子类中可选择不抛出异常；



### 7.1.2 创建异常类

- 习惯上，所有派生的异常类应包含两个构造器：
  - 默认的构造器；
  - 带有描述信息的构造器；
    - 超类`Throwable`的`toString`方法将打印该信息；



## 7.2 捕获异常

- 在方法的首部添加`throws`说明符，告知调用者该方法可能会抛出异常；
  - 此时无`catch`语句，仅传递该异常；
- `catch`子句可合并：
  - 当捕获多个异常时，异常变量隐含为`fianl`变量，不能在以下子句体中为`e`赋不同的值； 

```java
catch (FileNotFoundException | UnknownHostException e)
```



## 7.3 使用异常机制的技巧

- 使用异常机制的技巧：

  - 不使用异常处理替代简单的测试；
    - 原因：前者的时间开销大；



## 7.4 使用断言

- 断言中表达式的值将被传入`AssertError`的构造器，并转换为一个消息字符串；
  - `AssertError`不存储表达式的值；



## 7.5 日志（==unsolved==）

- 未被任何变量引用的日志记录器可能会被垃圾回收；
  - 因此可使用一个静态变量存储日志记录器的一个引用；



# 8. 泛型

- 泛型提供类型参数；
- 实例化泛型类型：用具体的类型替换变量类型；



## 8.1 泛型方法

- 泛型方法可以定义在泛型类或普通类中；
  - 类型变量`<T>`；

```java
public static <T> T getMiddle(T... a)
```

- 类型变量的限定：
  - 限制类型变量是某个类的子类，或实现某种特定的接口，故使用`extends`关键字；
  - 多个限定类型使用`&`分隔；
  - Java 不允许多类继承，因此限定类型中至多只能有一个类，可以有多个接口；
    - 若限定类型中有类，则应作为限定列表中的第一个参数；

```java
public static <T extends Comparable> T min(T[] a)
```



# 8.2 泛型代码与虚拟机

### 8.2.1 类型擦擦

- 虚拟机中没有泛型，只有普通的类和方法；
- 定义一个泛型类型后，将自动提供一个相应的原始类型；
- 桥方法被合成来保持多态；
- 擦书类型变量，并替换为第一个限定类型；
  - 无限定的变量使用`Object`；



## 8.7 泛型类型的继承规则

- `pair`类之间无继承关系；



# 9. 集合

- 链表不支持快速随机访问；
  - 如需访问链表中的第 n 个元素，则应从从开始，越过前 n-1 个元素；
  - 每次查找元素，均需从链表的头部开始搜索；
- 使用动态数组时，在`ArrayList`与`Vector`之间的取舍：
  - `Vector`类的所有方法都是同步的，允许两个线程安全地访问同一个对象；
    - 若仅有一个线程访问时，不宜使用`Vector`类，以减小同步操作的时间开销；
  - `ArrayList`类的方法不是同步的；



# ==Schedule==

- Java 编程入门课时3；
- 每天5课时，12月上旬完成《编程入门》；

# References

[1] Cay S. Horstmann. Java 核心技术 卷I 基础知识（第10版）[M]. 周立新,陈波,叶乃文 等译. 北京: 机械工业出版社, 2016. ︎