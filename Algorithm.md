

[TOC]

# 0. 版权声明
- Algorithms 系列学习笔记来源于 Kevin Wayne 和 Robert Sedgewick 在 Coursera 网站上所授课程 *Algorithms, parts I and II* [1,2]，课程教材 *Algorithms 4th edition* [3]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及科学研究；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 本文采用 [署名-非商业性使用-禁止演绎 4.0 国际 (CC BY-NC-ND 4.0)](https://creativecommons.org/licenses/by-nc-nd/4.0/deed.zh) 协议发布；

# 1. 算法基础
- 算法与数据结构的区别：
  - 算法：method for solving a problem；
  - 数据结构：method to store information；
- 设计算法的步骤：
  -  建立数学模型；
  -  设计一种算法求解；
  -  若不满足对内存和运行速度的要求，则查找原因，修改算法；

## 1.1 基础编程模型

- ADT：abstract data type，抽象数据类型；

- API：application programming interface，应用程序接口；

- 格式化输出：
  - 若输出值的宽度大于指定的宽度，则指定无效；
  - 对于字符串，小数点后的值表示截取的长度；
  
- 结束输入流：`ctrl+d`或`ctrl+z`，取决于具体的终端应用程序；

- 管道：将一个程序的输出重定向为另一个程序的输入；

- 使用未经初始化的变量，将出现编译错误；

- Q：静态方法与函数的区别？

  - 在一些其他语言中，静态方法被称为函数；
  - 使用`static`区分静态方法和实例方法；

- 数组：

  - 数组的声明、创建和初始化：

  ```java
  int[] array;			// 声明；
  array = new int[10];	// 创建；
  ```

  - Q：为什么创建数组时需要指出数组大小？
    - 便于编译器预留空间；

- 原始数据类型：

  - `boolean`：布尔型，1字节；

  - `char`：字符型，2字节；

- `for`与`while`的区别：

  - `for`循环中的递增变量在循环结束后，通常是不可用的；
    - 原因：`for`循环中的递增变量通常定义在循环体内；
  - `while`循环中的递增变量在循环结束后仍是可用的；
  
- 除以0;

  - `2/0`，异常；
  - `2.0/0.0`：infinity，无穷大；



## 1.2 数据抽象

- `new`关键字/构造函数返回对象的引用；
- 静态方法与实例方法的差别：
  - 静态方法：通过类名调用，参量为方法的参数；
  - 实例方法：通过对象名调用，参量为对象的引用和方法的参数；
- 包括数组在内的所有非原始数据类型的值均为对象；
- 



==Schedule==

- 每日15页，40天完成；
- 处理所有课后题；




# 2. Union-find (并查集算法)
- 并查集算法仅能判断两个对象之间是否存在连接，但不能给出连接路径；
- Connected component (连通分量)：具有连接关系的对象组成的最大集合；
  - E.g. 下图中有3个连通分量；
![在这里插入图片描述](https://img-blog.csdnimg.cn/2019051020271037.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==,size_16,color_FFFFFF,t_70#pic_center)



# N. 典型案例

### N.1 杨辉三角

- 对于不符合公式的部分结果，单独予以计算；
```java
// 定义二维不规则数组；
int rowNum = 7;
int[][] array = new int[rowNum][];
for(int i=0; i<rowNum; i++)
{
	array[i] = new int[i+1];
}

// 计算结果并填充数组；
// 当j=0时，k<=j不成立，因此不进入第三层循环，计算结果为1；
// 当j=array[i].length-1时，计算结果恰为1；
int defaultNum;
for(int i=0; i<rowNum; i++)
{
    for(int j=0; j<array[i].length; j++)
    {
        defaultNum = 1;
        for(int k=1; k<=j; k++)
        {
            defaultNum = defaultNum*(i-k+1)/k;
        }
        array[i][j] = defaultNum;
    }
}
```



## N.2 根据离散概率返回值

- Description：数组`array`中存放着一组离散概率值，`array[i]`表示数字`i`出现的概率，请根据数字的概率随机返回 i 值；
- Approach：
  - 将各个数字出现的概率对应到数轴上的一个区间；
  - 各个概率之和为1，对应于各个区间首尾相接，覆盖数轴上从0-1的区域；
  - 现随机生成一个[0,1)之间的数，该数落入某个区间的概率为该区间的长度，即`a[i]`所表示的概率值；



# References

[1] https://www.coursera.org/learn/algorithms-part1?.
[2] https://www.coursera.org/learn/algorithms-part2?.
[3] Sedgewick, R. & Wayne, K. (2016). *Algorithms Fourth Edition*. Boston: Addison-Wesley.