

[TOC]

# 版权声明

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
  - 因为计算机访问内存的方式为：一次1字节；
  - `char`：字符型，2字节；
  - `float`：4字节，同`int`；

- `for`与`while`的区别：

  - `for`循环中的递增变量在循环结束后，通常是不可用的；
    - 原因：`for`循环中的递增变量通常定义在循环体内；
  - `while`循环中的递增变量在循环结束后仍是可用的；
  
- 除以0;

  - `2/0`，异常；
  - `2.0/0.0`：infinity，无穷大；
  
- 将数组中不再使用的项赋值为 null，便于系统回收内存；



## 1.2 数据抽象

- `new`关键字/构造函数返回对象的引用；
- 静态方法与实例方法的差别：
  - 静态方法：通过类名调用，参量为方法的参数；
  - 实例方法：通过对象名调用，参量为对象的引用和方法的参数；
- 包括数组在内的所有非原始数据类型的值均为对象；
- 封装类型：原始数据类型对应的引用类型；
  - 每种原始数据类型均有对应的封装类型，E.g. Boolean、Byte ...；
  - Java 将在必要时自动将原始数据类型转换为封装类型；
- `final`只能确保原始数据类型的不可变性，不能实现引用类型的不可变性；
  - 引用类型中的实例变量被`final`修饰时，表示该实例变量的值（即某个对象的引用）不可变；
  - E.g. 以下代码示例中，实例变量永远指向同一个对象，但对象的值本身仍可改变；

```java
public class Vector
{
    private final double[] coords;
    ...
}
```



## 1.3 背包、队列和栈

- 背包、队列、栈：bag、queue、stack；
  - 背包：
    - 不支持从中删除元素的集合数据类型；
    - 元素无序；
  - 队列：全称为先进先出（FIFO）队列
  - 栈：全称为下压栈，基于后进先出（LIFO）策略；
    - 遍历顺序与元素入栈顺序相反；
- 泛型中的类型参数必须为引用类型；
- Java 中不允许创建泛型数组，但可使用类型转换间接实现该目的；
  - Java 编译器将给出一条警告，但可以忽略；

```java
a = (Item[]) new Object[cap];	
// 创建一个 Object 类型的数组，并将其转换为泛型类型；
```

- 如何跳出由键盘输入的`while(!in.hasNext())`的循环：
  - 由于`in`一直等待输入，因此返回结果始终为`true`，导致循环无法退出；

```java
while(!in.hasNext())
{
    if(condition)		// 设置退出循环的条件；
        break;
}
```



- 游离：保存一个不需要的对象的引用；
- 迭代：需要实现接口`Iterable`，且该类中应实现方法`Iterator<I> iterator()`；
- 使用`javac`命令编译文件时，将为内部类生成单独的`.class`文件；
  - E.g. `Stack$Node.class`，表示`Node`是`Stack`的内部类；
- 私有嵌套类的特点：只有包含它的类能直接访问嵌套类的实例变量，因此无需指出嵌套类实例变量的访问限制符；



## 1.4 算法分析

- 常见的增长数量级函数：

  - $logN$，对数级别，对数的底数与增长的数量级无关，因此在符号中不体现底数；
  
  - $N$，线性级别；
  - $NlogN$，线性对数级别；
  
- 不同增长数量级函数的运行时间差异：

  - $NlogN>N>>logN>1$；
  - 处理大规模的问题，应设计对数级别、线性级别、线性对数级别的算法；



## 1.5 Union-find (并查集算法)

- 动态连通性问题：
  - 触点：指对象；
  - 分量/连通分量：指等价类，即具有连接关系的对象组成的最大集合；
- 并查集算法仅能判断两个对象之间是否存在连接，但不能给出连接路径；
- 归并：几个合在一起或一个合到另一个里去；
- 动态连通性问题：
  - union-find 算法；
  - quick-union 算法；
  - 加权 quick-union 算法：$2^n$个触点最坏情况下的输入，使树的深度为 n；
    - 由于将小树连接到大树的限制，使得树的深度不为$2^n$；





# 2. 排序

## 2.1 初级排序算法

-  原地排序算法：指无需额外内存的算法；
-  排序算法适用于任何实现了`Comparable`接口的数据类型；
   - 在该类中需要实现一个`compareTo()`方法，定义对象的自然次序；
-  倒置：指数组中两个元素的实际位置与排序位置相反；
-  部分有序：数组中倒置的数量小于数组大小的某个倍数；
-  初级排序算法：
   - 选择排序：
     - 运行时间与输入无关；
       - 无法利用输入的初始状态信息；
       - 某次遍历数组不能为下次遍历数组提供信息；
     - 数据移动较少；
     - 是一种不稳定的排序算法；
   - 插入排序：
     - 对于有序数组，其运行时间为线性的；
     - 对部分有序数组，插入排序的效率较高；
   - 希尔排序：shell sort;
     - 目前最好的递增序列：1, 5, 19, 41, 109, 209, ...
       - 表达式复杂；
       - 可使用$h=3h+1$替代，但效果稍差；
     - 对于中等规模的数组，运行时间尚可接受；
     - 希尔排序是基于插入排序的以下两点性质而提出的改进方法：
       - 插入排序在操作几乎已经排好序的数组时，效率可达到线性时间复杂度；
       - 但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位；



## 2.2 归并排序

- 归并排序：merge sort；
  - 优点：时间复杂度为$NlogN$；
    - 最坏情况下，基于比较的排序算法需要的比较次数为$NlogN$；
    - 归并排序是稳定的，即对相同的数排序时不改变其顺序；
  - 缺点：
    - 空间复杂度为$N$；
    - 对于含有以任意概率分布的重复元素的输入，无法保证最佳性能；
- 改进方法：
  - 对小规模子数组使用插入排序，E.g. 长度小于15；
  - 检测到子数组有序时，跳过归并算法；
  - 不将元素复制到辅助数组；
    - 即在递归调用的每个层次，交换输入数组和辅助数组的角色；
- 自顶向下的归并排序、自底向上的归并排序：
  - 自底向上的归并排序相较于标准递归方法（自顶向下的归并排序），代码量更少；
- 归并排序与希尔排序的运行时间差距在常数级别之内；



## 2.3 快速排序

- 快速排序：

  - 优点：

    - 时间复杂度为$NlogN$，只需要一个很小的辅助栈；

    - 原地排序；

  - 缺点：

    - 当切分不平衡时，算法效率低下；
      - Solution：在排序前将数组随机排列；
    - 快速排序不稳定；

- 改进快速排序的方法：

  - 对于5-15之间的小型子数组，使用插入排序；
  - 对于包含大量重复元素的数组，使用三项切分，能使排序时间降低到线性级别；
    - 若无大量重复元素，使用三项切分将在一定程度上增大时间开销；



## 2.4 优先队列

- 常见定义：

  - `平衡二叉树`、`完全二叉树`和`满二叉树`的区别：
    - 二叉树；
    - 二叉搜索树：根节点的值大于左子节点，小于右子节点；
    - 平衡二叉树：是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是平衡二叉树；
    - 完全二叉树：Complete Binary Tree，最后一层的所有节点都连续集中在左侧；
    - 满二叉树：Full Binary Tree，所有父节点均有两个子节点；
  - 堆有序：所有父节点大于等于子节点；
  - 二叉堆：一组可用堆有序的完全二叉树排列的元素；

    - 表示方法：数组中的$array[1]$存放根节点，$array[2],array[3]$存放两个子节点，$array[4],array[5],array[6],array[7]$存放子节点的子节点，以此类推。$array[0]$不使用；
    - 最大堆：根节点值大于等于所有子节点值；
    - 最小堆：根节点值小于等于所有子节点值；

- 计算用数组表示的完全二叉树的索引：

- E.g. 若某节点的索引为$k$，则该节点的父节点索引为$\frac{k}{2}$，该节点的两个子节点的索引分别为$2k,2k+1$；

- 优先队列与队列、栈之间的区别：
  - 队列：删除最早存放的元素；
  - 栈：删除最近存放的元素；
  - 优先队列：删除当前最大的元素；

- 使用数组与使用二叉堆实现的优先队列有何不同：

  - 使用数组实现的优先队列：插入、删除两种操作中，至少有一种操作的时间复杂度为$O(N)$；
  - 使用二叉堆实现的优先队列：插入、删除两种操作的时间复杂度均为$O(NlogN)$；

- 堆排序的实现方式：
  - 堆排序：使用基于堆的优先队列进行排序；
  - 使用无序数组实现的优先队列进行排序，相当于选择排序；



## 2.5 排序算法总结

|   排序算法   | 平均时间复杂度 | 空间复杂度 | 是否稳定 | 是否为原地排序 |
| :----------: | :------------: | :--------: | :------: | :------------: |
|   冒泡排序   |    O($n^2$)    |    O(1)    |    是    |       是       |
|   选择排序   |    O($n^2$)    |    O(1)    |    否    |       是       |
|   插入排序   |    O($n^2$)    |    O(1)    |    是    |       是       |
|   希尔排序   |  O($nlogn$)?   |    O(1)    |    否    |       是       |
|   归并排序   |   O($nlogn$)   |   O($n$)   |    是    |       否       |
|   快速排序   |   O($nlogn$)   | O($logn$)  |    否    |       是       |
| 三向快速排序 |   O($nlogn$)   | O($logn$)  |    否    |       是       |
|    堆排序    |   O($nlogn$)   |    O(1)    |    否    |       是       |

- 归并排序和快速排序的空间复杂度：
  - 归并排序：需要借助长度为 n 的辅助数组，实现子数组有序；
    - 以上所有排序算法中，仅有归并算法需要借助于辅助数组，故不为原地排序；
  - 快速排序：每次递归调用，均需要保存一个用于切分数组的值；
    - 最好情况下的空间复杂度为$O(log_2n)$，即每次将数组二分；
    - 最坏情况下的空间复杂度为$O(n)$，即每次仅能切分出一个元素；
    - 快速排序、三向切分快速排序算法的运行效率由概率提供保证，即需要先将数组乱序后，再进行排列；
    - 快速排序是最快的通用排序算法，原因如下：
      - 内循环中的指令较少；
      - 该算法总是顺序地访问数据，因此能利用缓存；
- Java 系统库的排序算法：`java.util.Arrays.sort()`；
  - 对原始数据类型使用三向切分的快速排序；
  - 对引用类型使用归并排序；
- 归约：为解决某个问题而提出的算法，正好可解决另一个问题；



# 3. 查找

## 3.1 符号表

- 基本概念：
  
  - 符号表：亦被称为字典、索引，是一种键值对数据结构；
  - 索引与反向索引：
    - 索引：根据键查找值；
    - 反向索引：根据值查找键；
  
- 符号表的性质：
  - `key`、`value`均不能为`null`；
  - 每个键仅能对应一个值，即不允许存在重复的键；
  - 最好使用不可变类型作为键，以确保表的一致性；
  
- 优先队列与符号表的区别：
  
  - 优先队列中可以存在重复的键，而符号表中不可以；
  
- 简单符号表的两种常见方式：
  - 无序链表中的顺序查找；
  
    - 创建一个类，每个节点存储 `key`、`value` 和下一个节点的引用；
  
  - 有序数组中的二分查找；
  
    - 返回`lo`的原因：当查找结束时，`lo`即为表中小于待查找键的键的数量；
  
    ```java
    if (hi < lo)
        return lo;
    ```
  
- 使用`equals()`与`compareTo()`的场景有何不同？

  - 不是所有的数据产生的键值对都能比较大小；
  - E.g. 符号表中可能使用一首歌或一幅图片作为 key，在这种情况下，只能判断键值是否相等，而无法比较大小；
  
- Java 语法：

  - Java 中的构造器将引用类型默认初始化为 null；
  - Java 中不允许泛型的数组，因此需要先创建数组然后进行类型转换；
  - `hashcode()`方法：
    - 所有数据类型均继承了`hashcode()`方法，返回一个32位整数；
    - 默认的散列函数返回对象的内存地址；
    - 原始数据类型需要转换为相应的类型，才能使用`hashcode()`方法；
    - 如需为自定义数据类型定义散列函数，则需要同时重写`hashcode()`和`equals()`；
    - 若两个对象的`equals()`方法返回值相同，则`hashcode()`方法的返回值也相同；
    - 若两个对象的`hashcode()`方法返回值相同，`equals()`方法的返回值可能不相同，需使用`equals()`进一步判断两者是否相等；
      - 即键相同，值不同；



## 3.2 二叉查找树

- 链表与数组：
  - 链表：插入更灵活；
  - 数组：查找更高效；
- 二叉查找树：binary search tree；
- 遍历的种类：
  - 广度优先搜索：层次遍历；
  - 深度优先搜索：根据根节点的访问顺序区分；
    - 前序遍历：==根节点==-左子节点-右子节点；
    - 中序遍历：左子节点-==根节点==-右子节点；
    - 后序遍历：左子节点-右子节点-==根节点==；
- 递归：
  - 递归三要素：边界条件、递归前进段、递归返回段；
    - 当边界条件不满足时，递归前进；
    - 当边界条件满足时，递归返回；
  - 递归算法适用的场景：
    - 数据的定义是按递归定义的，E.g. Fibonacci 函数；
    - 数据的结构形式是按递归定义的，E.g. 二叉树、广义表等；
    - 问题解法按递归算法实现：问题本身没有明显的递归结构，但用递归求解比迭代求解更简单，E.g. Hanoi 问题；
  - 递归的缺点：
    - 相较于普通的循环，递归的运行效率较低；
    - 每次递归调用均需要开辟栈存储相应数据，当递归次数过多时容易造成栈溢出；



## 3.3 平衡查找树

- 二叉查找树与平衡查找树的对比：
  
  - 二叉查找树：不能实现平衡插入，在最坏情况下的性能较差；
  - 2-3查找树：能实现平衡插入；
  - 红黑查找树：兼具二叉树的高效查找和2-3树的平衡插入优点；
- 平衡查找树：
  
  - 2-3查找树；
  - 红黑二叉查找树：2-3查找树的具体实现，既是二叉查找树，也是2-3查找树；
- 树的生长方式：

  - 二叉查找树：自顶向下；
  - 2-3查找树：自底向上；
- 红黑树的定义：含有红黑链接并满足以下三个条件；

  - 红链接均为左链接；
    - 红链接表示3-节点的内部链接；
  - 任一节点均不能同时连接两条红链接；
    - 即不得出现4-节点；
  - 该树是完美黑色平衡的；
    - 即任意空连接到根节点路径上的黑链接数量相等；
- 红黑查找树在插入新的节点时，需要依次执行以下步骤：

  - 若右子节点为红色，且左子节点为黑色，则执行左旋转；
  - 若左子节点为红色，且左子节点的左子节点亦为红色，则执行右旋转；
  - 若左右子节点均为红色，则执行颜色转换（将左右子节点转换为黑色）；
- 红黑树的性质：
  - `范围查找`操作的运行时间与返回的键数量成正比；
  - 其它操作的运行时间均为对数级别；



## 3.4 散列表

### 3.4.1 Background

- 哈希表：hash table，亦称散列表；

- 使用散列的查找算法分为两步：
  - Step 1：使用散列函数将待查找的键转换为数组的某个索引；
  - Step 2：处理散列冲突的情况；
    - Approach 1：拉链法；
    - Approach2：线性探测法；
  



### 3.4.2 散列函数

- 如需均匀分布各个键，则散列函数应使键的每一位在计算散列码的过程中作用相同；

- 不同数据类型的散列函数：
  
  - 正整数：若选择长度为 size 的数组，则可将正整数 num 对 size 取余；
    
    - 若 size 为素数，则散列值的分布更加均匀；
    
  - 浮点数：
    - 有缺陷的方法：若键值是0-1之间的实数，则可将其乘以 size 并四舍五入，从而得到一个 0-size-1 之间的索引值；
      - 该方法导致浮点数中高位的权重较高，而低位的权重较低；
    - 修正后的方法：将键表示为二进制数后，使用 size 对 num 取余数；
    
  - 字符串：除留余数法；
    
    - 每个字符需要用2个字节（16位）表示；
    - 将字符串当做一个 N 位 R 进制的值，将其对 size 取余；
      - N 为字符串长度，循环中的表达式相当于十进制中的`sum = 10 * sum + currentBit`；
      - R 应大于任何字符的二进制数值，以实现  N 位 R 进制；
      - R 不宜过大，以免 hash 值溢出；
    
    ```java
    int hash = 0;
    String str = "abcdef";
    for (int i = 0; i < str.length(); i++)
        hash = (R * hash + str.charAt(i)) % size;
    ```
    
  - 组合键：即键的类型含有多个整型变量，E.g. Date，year-month-day；
  
    - 除留余数法：
  
    ```java
    int hash = (((year * R + month) % size) * R + day) % size;
    ```
  
  
  

###  3.4.3 基于拉链法的散列表

- 动态调整数组大小：保持数组中每个链表的平均长度在2-8之间；


### 3.4.4 基于线性探测法的散列表

- 开放地址散列表：通过数组中的空位解决碰撞；
  
  - 基于线性探测法的散列表：即一种开放地址散列表；
- 键簇：指`基于线性探测法的散列表`的数组中，一组连续存储的键值；
- 动态调整数组大小的原因：
  - 若散列表的使用率过高（即数组中存储的键数量与数组长度的比值），则每次访问查找的时间开销增大;
  
  - 使用率为1时，将陷入无限循环中；
  
  - 需要保持散列表的使用率低于$\frac{1}{2}$；
    
    - 使用率：亦称负载因子或装填因子；
    
    - 注意：动态调整数组大小时，若使用率高于$\frac{1}{2}$，则数组长度加倍；若使用率低于$\frac{1}{8}$，则数组长度减半；



## 3.5 查找算法总结

### 3.5.1 符号表的实现方法对比

|     算法（数据结构）     | 最坏情况下查找 | 最坏情况下插入 | 平均情况下查找 | 平均情况下插入 |
| :----------------------: | :------------: | :------------: | :------------: | :------------: |
|   顺序查询（无序链表）   |       N        |       N        | $\frac{N}{2}$  |       N        |
|   二分查找（有序数组）   |      lgN       |       N        |      lgN       | $\frac{N}{2}$  |
| 二叉树查找（二叉查找树） |       N        |       N        |    1.39lgN     |    1.39lgN     |
|     2-3树（红黑树）      |      2lgN      |      2lgN      |    1.00lgN     |    1.00lgN     |
|    拉链法（链表数组）    |      <lgN      |      <lgN      | $\frac{N}{2M}$ | $\frac{N}{M}$  |
|  线性探测法（并行数组）  |      clgN      |      clgN      |      <1.5      |      <2.5      |

- 散列表中的符号含义：
  - N：键的数量；
  - M：链表数组的长度；



### 3.5.2 选择符号表的实现方法

- 选择符号表的实现方法：
  - 散列表：
    - 优点：查找时间最优，平均情况下插入和查找操作的时间开销为常数级别；
    - 缺点：散列后键的顺序信息丢失，因此查找最大最小键的时间复杂度为线性级别；
  - 二叉查找树和红黑树：
    - 优点：无需设计散列函数，能保证最坏情况下的性能（平衡插入），且支持更多有序性操作，E.g. 排序、选择、范围查找 ……
  - 注意：首选散列表，当需要执行有序性操作时，选择红黑树；
- Java 标准库中：
  - `java.util.TreeMap`：基于红黑树的符号表实现；
    - `put()`；
    - `get()`；
    - `remove()`；
    - `containsKey()`；
    - `containsValue()`；
    - `keySet()`：返回所有键的集合;
  - `java.util.HashMap`：基于拉链法的符号表实现；
    - `put()`；
    - `get()`；
    - `remove()`；
    - `containsKey()`；
    - `containsValue()`；
    - `keySet()`：返回所有键的集合;




# 4. 图

## 4.1 Background

- 常见的图模型：
  - 无向图；
  - 有向图；
  - 加权图；
  - 加权有向图；
- 词汇：
  - adjacency, n. 邻接；
    - adjacency list，邻接表；
  - vertex，n. 顶点；
  - graph, n. 图表，曲线图；
  - digraph, n. 有向图；
    - 相似的词汇：diagram, n. 图表，图解；



## 4.2 无向图

### 4.2.1 无向图相关的定义

- 度数：依附于某个顶点的边的数量；

- 平行边和自环：

  - 平行边：

    - 无向图中的平行边：连接一对顶点的无向边多于1条；

    - 有向图中的平行边：连接一对顶点且起点和终点相同的有向边多于1条；
- 自环：某条边连接的两个顶点为同一点；
  - 没有自环的顶点，仍可到达自身，E.g. 若顶点 V 无自环，仍认为 V 点可到达自身；
- 简单图与多重图：
  - 简单图：不含平行边和自环的图；
  - 多重图：含有平行边的图；
- 简单环和简单路径：

  - 简单路径：一条无重复顶点的路径；
  - 简单环：除起点和终点外，不含有重复顶点和边的环；
- 连通图、极大连通子图和非连通图：

  - 连通图：从图中任一顶点均存在一条路径到达另一顶点；
  - 极大连通子图；
  - 非连通图：其中包含多个连通分量（或称连通子图）；
- 树、森林、生成树和生成树森林：

  - 树：一幅无环连通图；
  - 森林：一组互不相连的树组成的集合；
  - 生成树：连通图的生成树是该图的一幅子图，含有图中所有的顶点，且是一棵树；
  - 生成树森林：所有连通子图的生成树的集合；
    - 仅在非连通图中，才有多个连通子图；
- 图的密度、稀疏图、稠密图和二分图：

  - 图的密度：已连接的顶点对占所有可能连接的顶点对的比例；
  - 稀疏图：图的密度低；
  - 稠密图；
  - 二分图：二分图的所有顶点可被分成两个互斥的集合，使得所有边连接的一对顶点分别属于两个集合；
    - 等价定义：二分图中任一环的顶点数量为偶数；
- 无向图中常见问题的解决方案：

|     问题     |     解决方案     |
| :----------: | :--------------: |
|  单点连通性  |       DFS        |
|   单点路径   |       DFS        |
| 单点最短路径 |       BFS        |
|    连通性    | Union-find / DFS |
|    检测环    |       DFS        |
|  检测二分图  |       DFS        |



### 4.2.2 图的表示方法

- 图的表示方法应满足的条件：
  - 空间开销小；
  - 对各种实例方法的实现效率高；
  - 能表示平行边；
- 常见的图表示方法：
  - 邻接矩阵：
    - 空间开销大：$O(n)$；
    - 不能表示平行边；
  - 边的数组：`Edge`类中含有2个`int`实例变量表示一条边；
    - 对部分实例方法的实现效率低；
    - E.g. 返回与给定点相邻的所有点的集合；
  - 邻接表：即邻接表数组，以顶点为索引的`bag`数组；
    - 该方法满足图表示方法中的要求；
  - 邻接集：相较于邻接表，具有较高的时间、空间复杂度；
    - 使用符号表代替由顶点索引构成的数组；
      - 便于增删顶点；
    - 使用`set`替代`bag`；
      - 便于删除某条边；
      - 便于检查判断某条边的存在性；
  - 符号图：用字符串表示顶点；
    - 使用符号表将字符串映射为数值索引；
    - 使用`String`数组实现反向索引；
    - 使用邻接表表示无向图；



### 4.2.3 DFS & BFS

- 深度优先搜索：
  - 用到的数据结构：栈；
  - 求解两点是否连通；
- 广度优先搜索：
  - 用到的数据结构：队列；
  - 求解两点是否连通及最短路径；



### 4.2.4 连通分量

- Union-find 算法与DFS的对比：

  - DFS：
    - 优点：常数时间内实现图的连通性查询；
    - 缺点：构建图的过程需要一定的时间开销；

  - Union-find：
    - 优点：连通性查询的时间开销接近于常数；
  - 注意：
    - 若仅需完成连通性查询和插入操作，则使用 Union-find 算法；
    - 若处理已有图数据，则使用 DFS；

- 检测图中是否有环：若搜索到此前已访问过的节点，则表明有环；

- 判断一幅图是否为二分图：

  - 构建一个`boolean`数组，给每个顶点染色；
  - 若搜索到此前已访问过的节点，则比较当前顶点的颜色与已访问顶点的颜色，当两者相同时，该图不为二分图；



## 4.3 有向图

### 4.3.1 有向图的相关定义

- 入度与出度：
  - 入度：指向该顶点的边的总数；
  - 出度：由该顶点指出的边的总数；
- 简单有向环和有向环：

  - 简单有向环：除起点和终点外，不含有重复的顶点和边；
- 可达性：约定每个顶点均能到达自身；

  - 由于每条边都有方向，有向图中的可达性不同于无向图中的连通性；
- 有向无环图：DAG，Directed Acylic Graph；

  - acylic，adj. 非循环的，非周期的；
- 拓扑排序：

  - 给定一幅有向图，将所有顶点排序，使得所有的有向边均从排序靠前的元素指向排序靠后的元素；
  - 如若无法实现该过程，则应予以说明；
- 强连通：两个顶点相互可达；

  - 自反性：任意顶点与其自身为强连通关系；

  - 两顶点间为强连通关系：当且仅当两个顶点处于同一个有向环中；

  - 强连通性：一幅有向图中任意两个顶点为强连通关系；
- 强连通分量：互为强连通的顶点组成的最大子集；



### 4.3.2 环与有向无环图

- 优先级限制下的调度问题：
  - 该问题等价于计算有向无环图中所有顶点的拓扑排序；
  - 若存在有向环，则该问题无解；
- 使用 DFS 遍历图中各顶点的顺序：
  - pre：前序，在递归调用之前将顶点加入队列；
  - post：后序，在递归调用之后将顶点加入队列；
  - reversePost：逆后序，递归调用之后将顶点加入栈；
    - 有向无环图的逆后序排列即为拓扑排序结果；
- 为什么需要新建一个`boolean`数组表示递归调用时栈上的所有顶点，而不是借助于表示已被访问顶点的数组？
  - E.g. 在 [3] Page 372，寻找有向环的源程序：
    - 布尔型数组`marked`表示已被访问的顶点，表示当前已访问过的所有顶点；
    - 布尔型数组`onStack`表示对某个顶点执行 DFS 时栈上的所有点，即单条路径上的所有点，便于检测有向环；



### 4.3.3 强连通性

- 有向图 G 的强连通分量与反向图$G^R$的强连通分量相同；
- Kosaraju 算法：
  - 对于一幅给定的有向图 G，使用 DFS 求解其反向图$G^R$的逆后序排列；
  - 按照上一步骤得到的逆后序排列，使用 DFS 遍历各个顶点；
  - 在同一次 DFS 递归调用中被访问到的节点均属于同一个强连通分量；



## 4.4 加权图

### 4.4.1 加权图的相关定义

- 权重：可为0或负值；
- 生成树：一幅图的生成树，指的是含有图中所有顶点的无环连通子图；
- 最小生成树：MST，minimum spanning tree，加权图的最小生成树是一棵各条边权值之和最小的生成树；
  - 若图中各条边的权重均不相同，则最小生成树可唯一确定；
- 最小生成树森林：当图中存在多个连通分量时，对每个连通分量求得的最小生成树的集合；
- 树的两个性质：
  - 用一条边连接树中的任意两个顶点，将产生一个环；
  - 从树中删除任一条边，将得到两棵树；
- 切分定理：对一幅加权图做任意形式的切分，权重最小的横切边必属于最小生成树；
  - 切分：将顶点集划分为两个非空且互不重叠的子集；
  - 横切边：连接切分后两个子集的边；
  - 切分时有可能产生多条属于最小生成树的横切边，它们分别用于连接不同的顶点；
  - 连接相同顶点的属于最小生成树的横切边有且仅有一条；



### 4.4.2 Prim 算法 & Kruskal 算法

- 最小生成树算法：
  - Prim 算法；
  - Kruskal 算法；
- Prim 算法：每次添加一条边，该边连接当前树中的顶点与不在树中的顶点，且为权重最小的横切边；
  - 延时实现：优先队列中含有已失效的边（即边的两个节点均已被纳入最小生成树中）；
  - 即时实现：存储不在树中的顶点与树中顶点相连的权重最小的边；
- Kruskal 算法：按照边的权重由大到小排列，使用权重最小的边连接各顶点，从而得到最小生成树；



## 4.5 加权有向图

### 4.5.1 加权有向图的相关定义

- SPT：Shortest Path Tree，最短路径树；
  - 包含起点至任意可达顶点的最短路径；
- 实现最短路径的数据结构：
  - 数组`edgeTo[]`：存储最短路径树中连接该顶点与父节点的边；
  - 数组`distTo[]`：从起点至该顶点的最短路径长度；
  - 约定：对起点 s 有`edgeTo[s]=null, distTo[S]=0`，从起点至不可达顶点的距离为无穷大；
- 松弛操作：relaxation；
  - 已知从起点 s 顶点 v 、w 的最短路径长度分别为$distTo[v]、distTo[w]$，边$v\rightarrow w$的权重为$e.weight()$；
  - 若$distTo[v]+e.weight() < distTo[w]$，则更新$distTo[w]$；
- 负权重环：环上各边的总权重为负；
  - 含有负权重环时，不存在最短路径；



### 4.5.2 适用于有向加权图的算法

- Dijkstra 算法：

  - 算法描述：对离起点最近的非树顶点依次执行松弛操作；

  - 适用范围：非负权值的有向图，无论图中是否有环均可处理；
  - 注意：将无向图视为强连通图，即可将无向图视为有向图处理；
- 无环加权有向图中的最短路径算法：

  - 算法描述：按照拓扑排序，依次放松各顶点；
  - 适用范围：无环加权有向图，可处理负权值的边；
  - 时间复杂度：$O(E+V)$，线性时间复杂度下，求解最短路径树（单点最短路径问题）；
  - 注意：处理无环加权有向图时，该方法相较于 Dijkstra 算法速度更快；
- 无环加权有向图中的最长路径算法：

  - 算法描述：复制原始无环加权有向图，得到一个副本，将副本中的所有边的权重取相反数，在副本中求得的最短路径即为原图中的最长路径；
- 关键路径方法：解决优先级限制下的并行任务调度问题；
  - 算法描述：将优先级限制下的并行任务调度问题转换为无环加权有向图中的最长路径问题；
    - 创建一幅无环加权有向图，包含一个起点和一个终点；
    - 为每个任务创建一条边$v\rightarrow w$，权重为该项任务的耗时；
    - 添加一条边，从一个任务的终点指向另一个任务的起点，权重为0，用于表示任务之间的优先级限制；
    - 添加一条边从起点指向$v$，权重为0；
    - 添加一条边从终点指向$w$，权重为0；
    - 注意：优先级限制下的任务调度中若存在环路，则该问题无解；
  - 关键路径：即最长路径；
  - 时间复杂度：线性时间复杂度；
- 加权有向图中的最短路径算法：相对最后期限限制下的并行任务调度问题（可能存在环和负权值）：
  - 算法描述：将相对最后期限限制下的并行任务调度问题转换为加权有向图中的最短路径问题；
    - 若两个任务间存在最后期限限制，则添加一条边从某个任务的起点指向另一个任务的终点，权重为期限限制值的相反数；
    - 将所有边的权重取反，使问题转换为求解最短路径；
    - 注意：相对最后期限限制下的任务调度中可能存在环路，且该问题有解；



### 4.5.3 一般加权有向图中的最短路径问题

- 一般加权有向图：可能含有环和负权重边；
- 最短路径的存在性：若起点至某一顶点路径上的所有节点，均不属于任意负权重环时，则存在从起点至该顶点的最短路径；
- Bellman-ford 算法：
  - 算法描述：将被成功放松的边所指向的顶点加入队列，并周期性地检查`edge[]`表示的子图中是否存在负权重环；
  - 适用范围：求解一般加权有向图中的单点最短路径；
    - 检测是否含有负权重环，避免陷入死循环；
  - 时间复杂度：$O(EV)$；
  - 空间复杂度：$O(V)$；



## 4.6 图搜索算法总结

- 表示时间开销的符号含义：

  - V：vertex，图中顶点数量；
  - E：edge，图中边的数量；

- 各项任务的时间开销：

  - 在有向图中，DFS 标记由一个集合的顶点可达的所有顶点所需的时间与被标记的所有顶点的出度之和成正比；
  - 使用 DFS 对有向无环图进行拓扑排序：

    - 时间开销：2(V+E)；
    - 分析：2次 DFS；
      - 第一次 DFS：检测是否存在有向环；
      - 第二次 DFS：获取顶点的逆后序排列，作为拓扑排序结果；
  - 有向图部分各项任务的时间开销：
    - Kosaraju 算法：
      - 时间开销：与 V+E 成正比；

      - 分析：将有向图反向，并执行2次 DFS；

        - 将有向图反向：与 V+E 成正比；
        - 第一次 DFS：检测是否存在有向环；
        - 第二次 DFS：获取顶点的逆后序排列，作为拓扑排序结果；
  - 加权图部分各项任务的时间开销：
    - Prim 算法的延时实现：
      - 时间复杂度：$O(ElogE)$；
      - 空间复杂度：$O(E)$；
    - Prim 算法的即时实现：
      - 时间复杂度：$O(ElogV)$；
      - 空间复杂度：$O(V)$；
    - Kruskal 算法：
      - 时间复杂度：$O(ElogE)$；
      - 空间复杂度：$O(E)$；
    - 注意：Kruskal 算法稍慢于 Prim 算法；
  - 有向加权图部分各项任务的时间开销：
    - Dijkstra 算法：
      - 时间复杂度：
        - 一般情况：$O(ElogV)$；
        - 最坏情况：$O(ElogV)$；
      - 空间复杂度：$O(V)$；
      - 适用范围：边的权重必须为正；
      - 优点：最坏情况下仍有较好性能；
    - 基于拓扑排序的最短路径算法：
      - 时间复杂度：
        - 一般情况：$O(E+V)$；
        - 最坏情况：$O(E+V)$；
      - 空间复杂度：$O(V)$；
      - 适用范围：仅适用于无环加权有向图；
      - 优点：无环图中的最优算法；
    - Bellman-Ford 算法：基于最小优先队列；
      - 时间复杂度：
        - 一般情况：$O(E+V)$；
        - 最坏情况：$O(EV)$；
      - 空间复杂度：$O(V)$；
      - 适用范围：图中无负权重环；
      - 优点：适用领域广泛；

- 各种图的表示方法：

  |  图的类型  | 表示方法 |                   备注                   |
  | :--------: | :------: | :--------------------------------------: |
  |   无向图   |  邻接表  |     链表中的节点为某条边的另一个顶点     |
  |   有向图   |  邻接表  |     链表中的节点为某条边的另一个顶点     |
  |   加权图   |  邻接表  | 链表中的节点为某条边的两个顶点和边的权重 |
  | 有向加权图 |  邻接表  | 链表中的节点为某条边的两个顶点和边的权重 |


# 5. String

## 5.1 Background

- Java 提供的8种基本数据类型：byte、short、int、long、float、double、char、boolean；
  - 助记：4种整型，2种浮点型，1种字符型，1种布尔型；
  - String 不是基本数据类型；
- 字符串的2种表示方法：
  - 字符数组：
    - 转换方式：`chars = str.toCharArray()`；
  - String：
    - 转换方式：`str=new String(chars)`；
- 基数和“基数”方法：
  - 基数：即进制数；
  - “基数”方法：一次只处理一位数的方法；

## 5.2 字符串排序

- 字符串排序方法：
  - 低位优先：LSD，Least-Significant-Digit First；
  - 高位优先：MSD，Most-Significant-Digit First；
- 键索引计数法：
  - 步骤：统计频率、根据频率求索引、数据分类、回写；
  - 适用范围：小整数键排序；
  - 时间复杂度：$O(11N+4R+1)$；
  - 注意：通过`compareTo()`访问数据的时间复杂度下限为$nlogn$，但键索引计数法通过`键`访问数据，因此该方法进行排序的时间复杂度为线性级别；
  - 符号说明：
    - N：输入的字符串个数；
    - R：基数，即字母表中的字符数量；
- 低位优先的字符串排序：
  - 基于键索引计数法；
  - 适用范围：仅适用于等长字符串排序；
- 高位优先的字符串排序：
  - 基于键索引计数法；
  - 适用范围：可处理通用字符串排序问题，即不等长字符串排序；

5.3 单词查找树

- 单词查找树：又称字典树，trie，发音同 try，E.Fredkin 在1960年根据该数据结构的作用（取出数据，retrieval）为其命名；
  - 用途：统计、排序和保存大量字符串（但不仅限于字符串）；
  - 优点：利用字符串的公共前缀减少查询时间，减少无谓的字符串比较，查询效率高于哈希树；
- R 向单词查找树：字母表中含有 R 个字符的单词查找树；
- 单词查找树的性质：
  - 单词查找树的链表结构（形状）和键的插入、删除顺序无关；
  - 插入和删除操作需要访问数组的次数：
    - 最坏情况下为待处理字符串的长度加一；
    - 由数学证明知，在随机键构造的单词查找树中，未命中查找的成本与键的长度无关（通常仅需检查3-4个节点）；
- 三向单词查找树：TST，每个节点含有一个字符，三条连接（对应的键值分别小于、等于和大于当前键）和一个值；
  - 优点：三向单词查找树所需的空间小；
    - 三向单词查找树每个节点含有3条连接，而单词查找树中每个 节点含有 R 个连接；
- 字符串符号表性能对比：
  - 二叉查找树：BST；
  - 2-3 树（红黑树）：能保证最坏情况下的性能（实现平衡插入）；
  - 哈希表；
  - 字典树（R 向单词查找树）：随机情况下仅需常数次比较即可完成查找，空间开销大；
  - 字典树（三向单词查找树）：需要执行对数次比较操作即可完成查找，空间开销小于 R 向单词查找树；

## 5.4 子字符串查找

### 5.4.1 各类字符串查找算法相关定义

- 相关概念：
  - 模式：pattern，即子字符串；
  - DFA：Deterministic Finite Automaton，确定有限状态自动机；
  - NFA：Nondeterministic Finite Automaton，非确定有限状态自动机；
- 子字符串查找算法：
  - 暴力子字符串查找算法；
  - KMP 子字符串查找算法：即 Knuth-Morris-Pratt 算法；
    - 优点：保障线性级别的性能，且无需在文本中回退；

### 5.4.2 各类字符串查找算法总结

|     算法     | 最坏情况 | 一般情况 | 文本中回退 | 额外空间需求 |
| :----------: | :------: | :------: | :--------: | :----------: |
|   暴力查找   |    MN    |   1.1N   |     是     |      1       |
| KMP 子串查找 |   M+N    |          |     否     |      MR      |

- 符号说明：
  - N：文本长度；
  - M：模式长度；
  - R：基数，即字母表中的字符数量；

# 6. 典型案例

## 6.1 杨辉三角

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

## 6.2 根据离散概率返回值

- Description：数组`array`中存放着一组离散概率值，`array[i]`表示数字`i`出现的概率，请根据数字的概率随机返回 i 值；
- Approach：
  - 将各个数字出现的概率对应到数轴上的一个区间；
  - 各个概率之和为1，对应于各个区间首尾相接，覆盖数轴上从0-1的区域；
  - 现随机生成一个[0,1)之间的数，该数落入某个区间的概率为该区间的长度，即`a[i]`所表示的概率值；

## 6.3  设置退出条件

```java
private static void sort(double[] array, int lo, int hi)
{
    if(lo >= hi)
        return ;
    double value = array[lo];
}
```

- 注意：先设置退出条件，再操作数组，否则容易出现访问越界错误；

# References

[1] https://www.coursera.org/learn/algorithms-part1?.
[2] https://www.coursera.org/learn/algorithms-part2?.
[3] Sedgewick, R. & Wayne, K. (2016). *Algorithms Fourth Edition*. Boston: Addison-Wesley.