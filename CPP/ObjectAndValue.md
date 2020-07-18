[TOC]

# 版权声明

- C++ 系列读书笔记来源于 Bjarne Stroustrup 所著《C++程序设计原理与实践（基础篇）》；[^1]
- 该系列笔记不以盈利为目的，仅用于记录学习过程中的知识要点和心得体会；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 转载请注明出处；

# 1. input

## 1.1 notation & explanation

- cin: abbreviation of "character input" ;
- cin 读取 string 时，遇空白符则终止， e.g. 空格、换行、tab ；
- 自定义的名称避免使用下划线开头，下划线开头的名称是为实现和系统实体保留的；
   避免使用全部使用大写字母的名称，该种名称通常被保留作为宏；
   自定义的类型可将首字母大写，因为 CPP 和标准库不使用大写字母；
   避免使用 0、o、1、i 命名，以免混淆；
 - typedef：定义类型别名；
   - `typedef int INT` ：定义类型 int 的别名为 INT；
- decltype；
  - `decltype(i) a`：定义变量 a，其变量类型与 i 相同；

## 1.2 function

| function | description |
| :------: | :---------: |
|  sqrt()  |  求平方根   |

# 2. 变量

```cpp
// ***********************************************************************************************

string name = "Andrew" + ''Viterbi" ;  
                     
// 定义变量与赋值同时进行；
// string 可用 ' + ' 进行连接运算；

// ***********************************************************************************************

cin >> first_name >> age ;       
                              
// cin 语句可读取多个值； 
// Windows 中 Ctrl+Z，代表循环输入结束；
// Unix / Linux 中 Ctrl+D，代表循环输入结束；

// ***********************************************************************************************

#include <iostream>
#include <string>             
// If you want to use std::string reliably, you must #include <string> ;

using namespace std;
int main()
{
	cout << "Please input your first name(followed by 'enter'):\n" << endl;
	string first_name;
	cin >> first_name;
	cout << "Hello, " << first_name << " !\n";
	return 0;
}

// ---------------------------------------------------------------------------------------------------------------

remark:
- error: no operator ">>" matches these operands;
  solution: 缺少 #include <string> ;

// ---------------------------------------------------------------------------------------------------------------

// 等价于按照 << 符号分行书写；
 cout << "Hello, " << first_name << " !\n" << endl;
 
 cout << "Hello, "
	     << first_name
	     << "!\n";
	     
// ---------------------------------------------------------------------------------------------------------------

```

# 3. 类型安全

- 在 CPP 中，养成初始化变量的习惯；
- 安全转换；
  不安全转换；
- 'a' + 1 等同于 int{'a'} + 1 ；
  
```cpp
  int a = 20000;
  char c = a;   
```
- remark:
  不安全转换：int 占 4 个字节，char 占 1 个字节，将 int 赋值给 char ，数值被改变（亦称“窄化”，narrowing）；


```cpp

 // 通用统一初始化，避免 narrowing 问题；
 // 使用该方法在赋值前将检查被复制的对象是否能够完整存储数据；
 // {}，该符号用于赋值，使用方式同 ' = ' ；
 double x {2.7};         
 // true;
 int y {x};                  
 // false, narrowing;
 int a {1000};
 // true;
 char b {a};
 // false, narrowing; 
```

- 显式类型转换：使读者易于看出代码思路；
```cpp
int x1 = int(x);
int x2 = static_cast<int>(x); 
```

- 类型转换；
- 显式类型转换；


# References
[^1]: Bjarne S. C++程序设计原理与实践（基础篇）[M]. 任明明,王刚,李忠伟, 译. 北京: 机械工业出版社, 2017.