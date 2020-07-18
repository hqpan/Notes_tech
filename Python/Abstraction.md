[toc]

# 版权声明

- Python 系列笔记来源于 Magnus Lie Hetland 的著作《Python基础教程》[1]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及科学研究；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 本文采用 [署名-非商业性使用-禁止演绎 4.0 国际 (CC BY-NC-ND 4.0)](https://creativecommons.org/licenses/by-nc-nd/4.0/deed.zh) 协议发布；
# 1. 自定义函数

```python
# 使用函数 callable() 判断对象是否可调用，返回布尔值；
>>> x = 1
>>> callable(x)
False

# --------------------------------------------------------------------------------------------------

# 定义函数的格式
>>> def hello(name):                              # 函数名与参数间也允许有空格；
...     return 'Hello, ' + name + '!'             # 在交互式解释器中需要手动缩进4个空格；
>>> print(hello('Andrew'))

```

## 1.1 docstring （文档字符串 document string）

- docstring 可放置于 def 语句后，以及模块、类的开头；
- 当 docstring 置于 def 语句后时，作为函数的一部分存储；

```python
# __doc__ 是函数的一个 attribute（属性）；
>>> def square(x):
...     'Calculates the square of the number x.'  # docstring ;
...     return x * x
>>> square.__doc__                                # 访问函数的 __doc__ 属性；
'Calculates the square of the number x.'

>>> help(square)                                  # 使用 help 函数访问函数信息，其中包含 docstring ；
Help on function square in module __main__:

square(x)
    Calculates the square of the number x.

```

## 1.2 函数返回值

- Python中，允许函数不包含 return 语句，允许 return 语句后无指定值；
- 若无指定返回值，则默认返回 None ；
- 若在多分支语句（if 等语句）中返回值，则应确保其他分支也返回值，以免调用者期望返回一个序列（举个例子）时，不小心返回了 None ；

# 2. 参数

## 2.1 形参与实参-None

## 2.2 修改参数

- 参数存储在**局部作用域**内，在函数内修改变量，函数外部的变量不受影响；
- string、number、tuple是不可变对象，不能修改值，只能替换为新值；

```python
# 构造 tuple ；
>>> string1 = 'pan', 'han', 'qi'                  # 在两侧加上圆括号亦可；
>>> string1
('pan', 'han', 'qi')

# --------------------------------------------------------------------------------------------------

# 可变数据结构作为参数
>>> def change(n):
... 	n[0] = 'Mr.Gumby'
>>> names = ['Mrs.Entity', 'Mrs.Thing']
>>> change(names)                                 # 两个变量（n 与 names）均指向同一个list；     
>>> names
['Mr.Gumby', 'Mrs.Thing']                         # 在函数中修改 n ，names 亦被修改；

>>> change(names[:])                              # 将切片所得副本作为实参传递给函数；
>>> names
['Mrs.Entity', 'Mrs.Thing']                       # 在函数中修改 n ，names 不被修改；

```

## 2.3 关键字参数与默认值

- positional argument（位置参数）：实参的顺序与形参一致；
- 关键字参数：不关注参数传递的顺序；
- 定义函数时，无默认值的形参应在有默认值的形参之前，否则报错；
- 尽量避免同时使用 positional argument 和关键字参数；

```python
# 关键字参数
>>> def hello_3(name, greeting = 'Hello'):
...	    print('{},{}'.format(greeting, name))
>>> hello_3('Andrew')    

# --------------------------------------------------------------------------------------------------

# 使用单个星号收集剩余参数，存放于 tuple 之中；
# 带星号的形参不一定需要作为最后一个形参，也可放在其他位置，但须使用名称额外指定后续参数的值；
>>> def in_the_middle(x, *y, z):
>>> print(x, y, z)
>>> in_the_middle(1, 2, 3, 4, 5, z = 7)
1 (2, 3, 4, 5) 7

# --------------------------------------------------------------------------------------------------

# 单个星号不收集关键字参数，2个星号可收集关键字参数；
>>> def print_params_4(x, y, z = 3, *pospar, **keypar):
...     print(x, y, z)
...     print(pospar)
...     print(keypar)
>>> print_params_4(1, 2, 3, 4, 5, 6, 7, foo = 1, bar = 2)
1 2 3
(4, 5, 6, 7)
{'foo': 1, 'bar': 2}                              # 收集关键字所得为 dictionary ；

```

## 2.4. 分配参数

- 用单个星号分配 tuple ；
- 用2个星号分配 dictionary ；

```python
# 用单个星号分配 tuple ；
>>> def add(x, y):
...     return x + y
>>> params = (1, 2)
>>> add(*params)                                  # 分配参数； 
3

```

# 3. 作用域

- 变量与值之间的关系：即**命名空间** or **作用域**；
- 除全局作用域外，每次函数调用时均创建一个命名空间；
- 函数内的局部变量与全局变量不在同一个命名空间内，因此重名无影响；

```python
# “遮盖”问题：若函数内存在一个局部变量与全局变量重名，则在该函数内读取全局变量时应使用 globals()['参数名'] ；
# globals() 返回一个包含全局变量的 dictionary，locals() 返回一个包含局部变量的字典；
>>> def combine(parameter):
...     print(parameter + globals()['parameter'])
>>> parameter = 'berry'
>>> combine('Shrub')

# --------------------------------------------------------------------------------------------------

# 使用 global 语句，在函数内声明全局变量；
>>> def change_global():
...     global x                                  # 在函数内声明 x 为全局变量；         
...     x = x + 1

```

- 作用域嵌套：函数2定义在函数1内部；
- 返回的函数携带着自身所在的环境和局部变量；
- 由于Python的嵌套作用域效果，可在内部函数中访问外部函数的局部作用域中的变量；
- 闭包：存储其所在作用域的函数；
- 使用关键字 nonlocal，可在内部函数中向外部函数的变量赋值；

```python

>>> def multiplier(factor):
...     def multiplyByFactor(number):
...         return number * factor
...     return multiplyByFactor                   # 外部函数的返回值是一个函数；
>>> double = multiplier(2)
>>> double(5)
10

>>> multiplier(5)(4)                              # multiplier(5) 返回一个函数，(4)为该返回函数提供参数；
20

```

# 4. 递归

- 嵌套：在函数1中定义函数2；
- 递归：函数调用自身；
  每次递归调用，均为新函数创建新的命名空间；
- 无穷递归：将在超过最大递归深度后（耗尽所有内存空间），导致程序终止并报错；
- 递归函数包含两部分：
  基线条件：针对最小问题，直接返回一个值；
  递归条件：在调用过程中不断简化问题，最终化为可用基线条件解决的最小问题；


----------


- 高阶函数：接收另一个函数作为参数，称为函数式编程；
- 函数式编程：
  lambda 表达式；
  用于函数式编程的函数：map、filter、reduce；
- 变量可指向函数：即函数本身也可赋值给变量（给函数取别名）；
  函数名也是变量，也可给函数名赋值，但将导致函数名不再指向该函数，不具有原功能；

```python
# 高阶函数；
>>> def add(x, y, f):
...     return(f(x) + f(y))
>>> add(-5, 6, abs)

# --------------------------------------------------------------------------------------------------

# map(func, seq[, seq[, seq, ...]])，对序列中的所有元素执行函数；
>>> list(map(str, range(0, 10)))
['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']

# 列表推导；
>>> [str(i) for i in range(10)]
['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']

# --------------------------------------------------------------------------------------------------

# filter(func, seq)，返回一个列表，将执行函数时结果为True的元素存放于其中；
>>> def func(x):
...     return x.isalnum()
>>> seq = ["foo", "x41", "?!", "***"]
>>> list(filter(func, seq))
['foo', 'x41']

# 列表推导；
>>> [x for x in seq if x.isalnum()]
['foo', 'x41']

# --------------------------------------------------------------------------------------------------

# reduce(func, seq[, initial]);
# reduce()接收两个参数：
# 一个接收两个参数的函数和一个序列；
# 该函数接收序列中的两个参数，并将计算结果继续作为函数参数，和序列的下一个元素做累积计算，如此循环往复；
>>> numbers = [72, 101, 108, 108, 111, 44, 32, 119, 111, 114, 108, 100, 33]
>>> from functools import reduce                  # reduce()函数在库functools中；
>>> reduce(lambda x, y: x + y, numbers)           # lambda表达式的作用：x = x + y，y = numbers；
                                                  # reduce 函数循环执行这一过程；

```

# References

[1] Hetland, M. L. (2018). *Beginning Python: From Novice to Professional* (3rd ed.). Berkeley: Apress.