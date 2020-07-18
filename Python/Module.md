[toc]

# 版权声明

- Python 系列笔记来源于 Magnus Lie Hetland 的著作《Python基础教程》[1]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及科学研究；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 本文采用 [署名-非商业性使用-禁止演绎 4.0 国际 (CC BY-NC-ND 4.0)](https://creativecommons.org/licenses/by-nc-nd/4.0/deed.zh) 协议发布；
# 1. module

- standard library（标准库）；

## 1.1 创建与导入 module

- sys.path 包含一个目录列表（列表中元素均为 string），解释器在这些被存储的路径中查找 module ；

```
>>> import sys                             # 指出文件路径；
>>> sys.path.append('C:/Users/Administrator/Desktop/test')
                                           # 指出文件路径的语句应该置于 import hello 语句之前；
                                           # 否则无法导入 hello 模块；
>>> import hello                           # 首次导入 module 将执行代码，第二次导入则不做任何操作；

>>> import importlib
>>> hello = importlib.reload(hello)        # 使用 importlib 模块中的 reload 函数重新加载 hello；
                                           # 将加载的结果仍然赋值给 hello，替换原有版本；
```

- 若使用旧版本的模块实例化，即使重新加载，实例仍为旧版本；

## 1.2 在 module 中添加测试代码

 - 直接在 module 内添加测试代码，在导入时将所有代码自动执行一次，将引入不便（不希望测试代码此时被执行）；
 - 检查变量 \__name__ 的值：
    若 module 作为程序运行，则 \__name__ = \__main__ ；
    若 module 被导入另一个程序，则 \__name__ = 该模块的名称；

```
# 以下是 hello4.py 文件中的内容；
>>> def hello():
...     print("Hello, world!")    
... def test():                            # 将测试代码放在函数 test 中；
...     hello()     
... if __name__ == '__main__':test         # 如果作为主程序运行，则调用 test() 做测试；
                                           
# 以下是主程序；
>>> import sys
>>> sys.path.append("C:/Users/Administrator/Desktop/test")
>>> import hello4                 
>>> hello4.hello()                         # 作为被导入的 module，则调用 hello()；

```

## 1.3 让 module 可用

- 添加搜索路径的方法：
  将 module 放置于目录列表存储的路径下；
  修改 sys.path ；
  将模块所在目录包含在环境变量 PYTHONPATH 中，见如下代码示例；
  使用路径配置文件（文件扩展名为 .pth），包含要添加到 sys.path 中的目录；

```
# 打印目录列表，即所有搜索路径；
>>> import sys, pprint                    
>>> pprint.pprint(sys.path)                # 使用 pprint 模块下的 pprint 函数，打印输出目录列表；

# --------------------------------------------------------------------------------------------------

# 将 ~/python 附加到环境变量 PYTHONPATH 的末尾；
>>> export PYTHONPATH=$PYTHONPATH:~/python # /Python 代指模块所在目录；

```

## 1.4 package（包）

 - module 存储在扩展名为 .py 的文件中；
 - package ：是一个目录，可视为另一种 module，可包含其他 module ；
   package 必须包含文件 \__init__.py ；
   向 package 中加入 module：将 module 文件放在 package 的目录中；


```
# 若像普通 module 一样导入包，则文件 \__init__.py 的内容就是包的内容；
# 假定有一个 package 名为 constants，文件 constants/__init__.py 包含语句 PI = 3.14；
>>> import constants
>>> print(constants.PI)

```

![ package 布局](https://img-blog.csdn.net/20180829204713606?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

# 2. 探索 package

## 2.1 module 包含什么

```
# dir ：列出对象的所有 attribute，对于 module ，则列出所有函数、类、变量；
>>> import copy
>>> dir(copy)

# --------------------------------------------------------------------------------------------------

# 变量 __all__ ：定义函数的公有接口，包含一个函数列表， module 中大量其他不需要的变量、函数和类不包含于其中；
# 若不设置 \__all__ ，import * 将导入所有不以下划线开头的全局名称；

>>> from copy import *                     # 该语句只能导入变量 __all__ 中的函数；
>>> from copy import PyStringMap           # 若有需要，其他函数只能显示地导入；

```

## 2.2 使用 help 获取帮助

- 相较于查看 document string，使用 help 可获取更多信息；

## 2.3 使用源代码

- 学习 Python 语言的方式：阅读源代码；
- 学习Python 编程，亦可阅读：（均可在线观看）
  《Python 库参考手册》：描述 standard library 中的所有 module；
  《Python 语言指南》
  《Python 语言参考手册》
- 查找源代码的方式：
  - 通过 sys.path 查找；
  - 查看 module 特性；

```
# 查看 module 特性；
>>> print(copy.__file__)                   # 返回 copy.py 文件路径；

```

# 3. standard library：常用 module

## 3.1 集合、堆和双端队列

- dictionary：即散列表；
- list：动态数组；
- set：无序不重复的元素集合，可变对象；
  由内置类 set 实现；
  可使用可迭代对象创建 set 或使用花括号显示指定；
  只能包含不可变的值，因此不能包含其他集合；
- heap（堆）；
- 双端队列：set 和双端队列均从可迭代对象创建；

## 3.2 set 

```
# 不能仅用花括号创建空 set ，此时将创建一个空字典；
>>> type({})
dict

# -------------------------------------------------------------------------------------------

# 可对 set 执行的操作，按位操作运算符等；
>>> a = {1, 2, 3}
>>> b = {2, 3, 4}
>>> a.union(b)                             # 方法 .union()，求并集；
>>> a | b                                  # 按位或，可用于 set 求并集；
>>> c = a & b                              # 求a、b 的交集；
>>> a.intersection(b)                      # 求a、b 的交集；
>>> c.issubset(a)                          # 检查 c 是否是 a 的子集，返回布尔值；
>>> c <= a                                 # 检查 c 是否是 a 的子集，返回布尔值；
>>> c.issuperset(a)                        # 检查 a 是否是 c 的子集，返回布尔值；
>>> c >= a                                 # 检查 a 是否是 c 的子集，返回布尔值；
>>> a.difference(b)                        # 返回在 a 中且不在 b 中的元素集合；
>>> a - b                                  # 返回在 a 中且不在 b 中的元素集合；
>>> a.symmetric_difference(b)              # 返回 a、b 交集的补集；
>>> a ^ b                                  # 返回 a、b 交集的补集；

# -------------------------------------------------------------------------------------------

# 
>>> a = set()                               
>>> b = set()
>>> a.add(frozenset(b))                    # set 是可变对象，不能用作字典中的键；
                                           # set 只能包含不可变的值，不能包含其他 set ；
                                           # frozenset() 表示不可变的 set ；
                                           # a.add(set(b)) 将会报错；
```

## 3.3 heap

- heap：可以任意顺序添加对象，并随时找出（并删除）最小的元素；
- module 名称 heapq （其中 q 表示队列）；
- heap property （堆特征）：位置 i 处的元素大于位置 i // 2处的元素；
- 具备 heap property 的 list 才可使用堆函数；
- 若 heap 不是由 heappush 创建，则应使用 heapify() 将 list 变为合法的 heap，使之具备 heap property；

![模块 heapq 中的一些重要函数](https://img-blog.csdn.net/20180830160915844?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)


## 3.4 双端队列

- 模块 collections 中包含类型 deque；

```
>>> from collections import deque
>>> q = deque(range(5))                    # 创建双端队列；
>>> q.append(5)                  
>>> q.appendleft(6)                        # 左侧添加元素；
>>> q.pop()
>>> q.popleft()                            # 左侧弹出元素；
>>> q.rotate(-1)                           # 元素移位；
                                          
```

# References

[1] Hetland, M. L. (2018). *Beginning Python: From Novice to Professional* (3rd ed.). Berkeley: Apress.