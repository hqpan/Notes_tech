[TOC]

# 版权声明
- C++ 系列读书笔记来源于 Bjarne Stroustrup 所著《C++程序设计原理与实践（基础篇）》；[^1]
- 该系列笔记不以盈利为目的，仅用于记录学习过程中的知识要点和心得体会；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 转载请注明出处；

# 1. elements
## 1.1 concept

- 标准容器类型：vector、map、list；
- ```double*```：指向 double 的指针；
  - e.g. ```double e = 2.71828; double* pd = &e;```
- ```*```：内容运算符，亦称**解引用（dereference）运算符**，指向内容；
  - e.g. ```*p = 1;```
- ```sizeof()```也可返回数据类型的大小；
  - e.g. ```sizeof(int)```；
- 避免使用一个指针为另一个指针赋值；
  - 此时两个指针指向同一个内存区域，若其中一个指针被释放，将导致另一个指针指向不存在的区域；
- 成员访问运算符：```.```，```->```可访问数据成员、函数成员；

## 1.2 analysis
### 1.2.1 不同类型的指针不能相互赋值

```cpp
int x = 17;
int* pi = &x;
char* pc = pi;	// error;
pi = pc;		// error;
```

- char 与 int 分配的内存空间大小不同，而内存中的值按照地址存放；
- 若将 int* 赋值给 char* ，则将覆盖内存中相邻区域的值；

# 2. 自由空间和指针
## 2.1 从自由空间中分配内存
- ```new```：从自由空间（free store / 又称为堆 heap）中分配内存；

```cpp
int* pi = new int;
int* qi = new int[4];
double* qd = new double[n];	
```

- 分配1个 int 的内存空间；
- 分配4个 int 的内存空间；
- 分配对象的数量可通过变量指定；

## 2.2 初始化
```cpp
double* p1 = new double{5.5};
double* p2 = new double[5];
double* p3 = new double[5]{1,2,3,4,5};
double* p4 = new double[]{1,2,3,4,5};
```

- 指向一个初始化的 double 变量；
- 指向未初始化的5元素数组；
- 指向初始化的5元素数组；
- 若提供元素初始值，可省略元素数目；

## 2.3 nullptr（空指针）
- nullptr 为 C++11 新特性；
- 旧代码中使用0、NULL 代替 nullptr，容易导致混淆和错误；

```cpp
double* p0 = nullptr;
if(p0 != nullptr)
if(p0)
```

- 若指针未被初始化，应赋值为 nullptr；
- 检测指针是否有效；
- 同上；

## 2.4 delete （释放自由空间）
- delete：释放内存空间，避免泄露；
- 尽量将 new 放在构造函数中，将 delete 放在析构函数中；

```cpp
delete p;
delete[] p;
```

- 释放分配给单个对象的内存；
- 释放分配给数组的内存；

## 2.5 类型混用：void*和类型转换
- ```void*```：指向编译器不知道类型的内存空间；
- 任何类型的指针都可赋值给```void*```；
- ```void*```不可赋值给其他类型的指针；

```cpp
void* pv = nullptr;
*pv = 7;							// error;
pv[2] = 9;							// error;
int*pi = static_cast<int*>(pv);
```
- 初始化；
- error: ```void*```类型不确定，不能解引用；
- error: ```void*```类型不确定，无法根据下标计算内存单元的位置；
- ```static_cast```: 用于两种相关的指针类型之间，显式类型转换；
  - 只在确有必要时，使用```static_cast```；
  - ```reinterpret_cast```：用于两种不相关的指针类型之间类型转换；（）
  - ```const_cast```：去除`const`；

## 2.6 指针和引用
## 2.6.1 definition
- 为指针赋值：改变指针自身的值，而非指针指向的值；
- 为引用赋值：改变引用指向的值，而非引用自身的值；（深拷贝）

```cpp
int x = 0;
int y = 10;
int& r = y;
r = &x;			// error;
```

- r 为 y 的引用（y 的别名），不能将地址赋值给引用；

## 2.6.2 指针参数和引用参数

```cpp
void incr_p(int* p){++*P;}
void incr_r(int& r){++r;}
```
- 传递指针，解引用并使其递增；
  - 注意考虑 nullptr 作为参数的情况；
- 传递引用，使其递增；

# References
[^1]:Bjarne S. C++程序设计原理与实践（基础篇）[M]. 任明明,王刚,李忠伟, 译. 北京: 机械工业出版社, 2017.