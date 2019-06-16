[TOC]



# 0. 版权声明

- Java 系列读书笔记来源于 Cay S. Horstmann 所著《Java 核心技术 卷I 基础知识（第10版）》[1]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及科学研究；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；



# 1. Java的基本程序设计结构

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
- `public`：访问修饰符（access modifier），用于控制程序的其他部分对这段代码的访问级别；
- 类名以大写字母开头，若由多个单词构成，则采用驼峰命名法；
- 源码文件命名：源代码的文件必须与 `public` 类的名字相同，扩展名为 `.java`；
- 字节码文件：编译器将源码文件的名字，作为字节码文件名字，使用 `.class`作为扩展名，与原文件存储在同一个目录下；
- 方法的代码用大括号括起来，用大括号划分程序的各个部分（称之为“块”）；
  - 空白符会被编译器忽略，因此使用不同风格的大括号均可；

- `object.method(parameter)`：E.g. System.out.println("Hello, world!")，使用`System.out`对象调用`println`方法；
  - 每次调用`println`都会在新的一行上显示输出，然后终止该输出行；
  - `.print`方法输出时不换行；
  - 仅有`()`表明不带参数；



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

- 浮点数值存在舍入误差；
  - 计算机使用二进制存储数值存在舍入误差；
  - Solution：使用`BigDecimal`类（不是一种数据类型），可表示任意精度；

- 表示数值：
  - float：后缀`F`或`f`；
    - 无后缀的浮点数默认为double类型；
  - （可选）double：后缀`D`或`d`；
- 三个特殊的浮点数：
  - 正无穷：
    - Float.POSITIVE_INFINITY；
    - Double.POSITIVE_INFINITY；
  - 负无穷：
    - Float.NEGATIVE_INFINITY；
    - Double.NEGATIVE_INFINITY；
  - NaN：不是一个数，各个NaN被认为不相同；
    - Float.NaN；
    - Double.NaN；
  - 检测某个值是否等于`Double.NaN`：
    - `if(Double.isNaN(x))`;
      - 正确；
    - `if(x==Double.NaN)`；
      - 错误；

# References

[1] Cay S. Horstmann. Java 核心技术 卷I 基础知识（第10版）[M]. 周立新,陈波,叶乃文 等译. 北京: 机械工业出版社, 2016. ︎

