[TOC]

# 版权声明
- C++ 系列读书笔记来源于 Bjarne Stroustrup 所著《C++程序设计原理与实践（基础篇）》；[^1]
- 该系列笔记不以盈利为目的，仅用于记录学习过程中的知识要点和心得体会；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 转载请注明出处；

# 1.  elements

## 1.1 concept

- 标准库类型：string、vector、ostream 等；
- CPP 的两类自定义数据类型：
	- class：member 默认是 private ；
		- struct：member 默认为 public 的 class；
	- 枚举；
- inline function（内联函数）：定义在 class 中：
  - 函数体若在 class 外，则需在函数定义前使用 inline 关键字；
  - 简短的函数定义放在 class 中；
  - 不直接在 class 中定义复杂的函数，使用户易于找到接口；
	 5行以上代码不会从 inline function 中获得性能提升；
- member function 的参数列表中，不需要有 class 中的 data member；
  - member function 默认可以操作 data member；

# 2. class 与 enum（枚举类型）

## 2.1 class

```cpp
class Year{
	static const int min = 1800;
	static const int max = 2200;
public:
	// ...
private:
	// ... 
}
```

- static const int min = 1800;
  - 使用 static 限定类成员，确保在程序中仅有一份 copy，而不是 class 的每个对象各有一份；

# 2.1.1 default constructor（默认构造函数）

```cpp
class Date {
public:
	Date();
private:
	// ...
};

Date::Date()
	 :y{ 2001 }, m{ Month::jan }, d{ 1 }		// 初始化列表；
{
}
```
  - constructor 与 class 同名，参数列表为空；

---

```cpp
class Date {
public:
	Date();
private:									// 类内初始化；			
	int y{ 2001 };
	Month m{ Month::jan };
	int d{ 1 };
};
```
- 类内初始化：类成员在声明时指定初始值；


### 2.1.2 const 成员函数
```cpp
class Date {
public:
	int day() const;			// 常量成员；
	Month month() const;		
	int year() const;

	void add_day(int n);		// 非常量成员；
	void add_month(int n);
	void add_year(int n);
private:
	int y;
	Month m;
	int d;
};

Date d{ 2000, Month::jan, 20 };
const Date cd{ 2001, Month::feb, 21 };
```

- 常量对象 cd 仅可使用常量成员函数，该函数不修改对象；


## 2.2 enum

### 2.1 enum class 作用域枚举
```cpp
enum class Month{
	jan=1, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec
};

// enum class Month 中的 class 表示枚举量在枚举量的作用域内；
// 即需要用 Month::jan 表示 jan ；
// 若不人为对枚举量赋值，编译器默认从0开始，将步长为1的值赋给枚举量；
// 若人为对枚举量赋部分值，编译器将为未赋值的枚举量赋值，值为上一枚举量的值加一；
// int(Month::jan) 若与其他数据类型的值比较大小，需要做类型转换；
// 将 int 数值转换为 Month 时，不做检查；
```

- Month::jan 
  - 在类名、枚举名、名字空间名后使用 “ :: ” ；
  - 在对象名后使用 “ . ” ；

### 2.2 enum 平坦枚举
```cpp
enum Month{
	// ...
}

// 平坦枚举将枚举量的作用域隐式导出到枚举类型所在的作用域内；
// 将枚举量的数据类型隐式转换到 int；
// 平坦枚举易污染枚举类型所在的作用域，应尽可能使用作用域枚举；
// 将 int 数值转换为 Month 时，不做检查；
```

# 3. operator overloading（运算符重载）

- 返回类型与参数： 
  - 参数列表中至少包含一个由用户自定义的类型；
  - 形参中不能有缺省值；
  - 返回类型不宜为 void；
  - 成员函数中 this 指向当前对象，因此双目运算符重载，作为成员函数使用时，只需在参数列表中提供一个形参；
  - 单目运算符宜使用成员函数，借助于 this 参数，可不必提供其他形参；
- operator overloading：
  - 作为成员函数时：第一个参数为 this ，指向当前对象；
  - 作为友元函数时：不包含参数 this；
- 成员函数 or 友元函数：
  - 必须为成员函数的情况：赋值、下标、调用、成员访问箭头；
  - 适宜为成员函数：改变对象状态的运算符；
  - 适宜为友元函数：具有对称性的运算符，e.g. 算术、相等性、关系、位运算符；

# References

[^1]:Bjarne S. C++程序设计原理与实践（基础篇）[M]. 任明明,王刚,李忠伟, 译. 北京: 机械工业出版社, 2017.