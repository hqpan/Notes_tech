[TOC]

# 版权声明

 - C++ 系列读书笔记来源于 Bjarne Stroustrup 所著《C++程序设计原理与实践（基础篇）》；[^1]
-  该系列笔记不以盈利为目的，仅用于记录学习过程中的知识要点和心得体会；
-  如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除； 
-  转载请注明出处；

# 1. elements

## 1.1 concept

- expression：表达式；
  iteration：迭代；
  vector：向量；
  repetition：循环；
  block / compound statement：程序块/复合语句（ {} 间的部分）；
  formal argument：形参；
  member function：成员函数；
  

## 1.2 function

|     function     |        description         |    library    |
| :--------------: | :------------------------: | :-----------: |
|      sort()      |            排序            | std_library.h |
|     .size()      |     返回 vector 的长度     |               |
| .pushback(value) | 将 value添加到 vector 末尾 |               |

- function definition 不带分号；
  function declaration 带分号，分号代替了函数定义中的 function body；
- .pushback() 是 vector 的一个成员函数；

# 2. expression、statement、function & vector

## 2.1 constant expression

-  定义 constant 的 key words :
  constexpr: constant expression ;
  const: constant ;
- consexpr ：定义的符号常量必须在编译时指定一个已知的值；
  const ：定义的符号常量在编译时不必指定值；

```cpp
consexpr double pi = 3.14                     // 定义、赋值
consexpr double pi = 3.14159                  // 修改

```

## 2.2 statement

- if-else-if -else 嵌套语句中，实际上是将2条 if-else 语句组合使用；
  CPP 中 else-if 语句；

### 2.2.1 switch

```cpp
switch(value1)
{
case 'character' : ... ; break;
case value2: ... ; break;
default: ... ; break;
}

```

- remark :
  - value1 必须是 int、char、enum，不能使用 string ；
  - value2 不能是变量；
  - 2个 case 语句的 value2 不能相同；
  - 1个 case 语句中可使用多个 case 常量；
  - case、default 语句末尾需要加 break；（不加 break 不报错）
  
## 2.2.2 while & for

- while 语句的循环控制变量需要在 while 语句前定义并初始化；
- for(int i = 0; i < 100; ++i) { }
  - i 的作用域仅在 for 循环中；
  - 不影响其他部分使用该变量名；
  
## 2.2.3 vector

```cpp
vector<string>vs(8);               // 若定义 vector 时不赋值，默认赋缺省值；
vector<int>v= {1,2,3};             // 定义与赋值； 
```
- remark
  - string 的缺省值为 "" ，即空字符串；
  - int 的缺省值为 0 ；

```cpp
for(int x:v)                      // 对 vector 的每个元素 x 执行循环操作；
{}           

// *************************************************************************

for(double temp; cin>>temp;)
	temps.push_back(temp);

// *************************************************************************

double temp;
while (cin>>temp)
	temps.push_back(temp);

```

- 定义变量 temp 类型；
  正确输入数据，则 cin>>temp 返回 true；
  cin 作为 for 循环的条件，返回 false 时终止（此处读入非 double 类型的数据则终止循环）；
- 若定义的变量类型为 string，所有输入字符均可被存入 string，则上述方式不能终止循环；
  此时可用 Ctrl+z (Windows)、Ctrl+D (Unix) 终止输入流；
  





# 3. error

- a < b < c
  a < b 的结果是布尔值（true or false），而非判断 b 是否介于 a、c 之间；


# 4. code specification

- 简单的表达式不宜加括号；
  e.g. a\*b + c*d
  reason：降低可读性；
  solution：熟悉运算符的优先级；
- 不宜使用过于复杂的表达式；
- 多使用符号常量，少使用字面常量；
- 为防止用户的非法输入，在 switch 语句中尽量加上 default 语句；
  虽然没有 default 语句也不会报错；
- for 循环将初始化、循环条件和增量操作集中到一起，便于理解和维护；
  在这一点上，for 优于 while ；
  
  

# References
[^1]:Bjarne S. C++程序设计原理与实践（基础篇）[M]. 任明明,王刚,李忠伟, 译. 北京: 机械工业出版社, 2017. 