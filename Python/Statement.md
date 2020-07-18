[TOC]

# 版权声明

- Python 系列笔记来源于 Magnus Lie Hetland 的著作《Python基础教程》[1]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及科学研究；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 本文采用 [署名-非商业性使用-禁止演绎 4.0 国际 (CC BY-NC-ND 4.0)](https://creativecommons.org/licenses/by-nc-nd/4.0/deed.zh) 协议发布；
# 版权声明

- Python 系列笔记来源于 Magnus Lie Hetland 的著作《Python基础教程》[1]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及科学研究；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 本文采用 [署名-非商业性使用-禁止演绎 4.0 国际 (CC BY-NC-ND 4.0)](https://creativecommons.org/licenses/by-nc-nd/4.0/deed.zh) 协议发布；

# 1. print and import

## 1.1 打印多个参数
- print 函数可打印 string and number ；
```
# 用逗号分隔多个表达式，并打印；
>>> greeting = 'Hello,'
>>> salutation = 'Mr'
>>> name = 'Gumby'
>>> print(greeting, salutation, name)        # 默认分隔符为 spacing ；
Hello, Mr Gumby

# 用 '+' 连接的 string 打印时中间无分隔符；
>>> greeting = 'Hello'
>>> print(greeting + ',', salutation, name)

# 自定义分隔符，默认的分隔符为 spacing ；
>>> print('I', 'wish', 'to', 'register', 'a', 'complaint', sep = '_')
I_wish_to_register_a_complaint

# 自定义结束符，默认的结束符为换行符；
>>> print('Hello,', end = '')
>>> print('world!')
Hello,world!

```

## 1.2 导入时重命名
1. import modules and functions；
```
>>> import somemodule
>>> from somemodule import somefunction
>>> from somemodule import function1, function2, function3
>>> from somemodule import *                   # 导入 module 中所有 function ；

```

2. 若导入的两个 module 中包含相同函数名；
```
>>> module1.open(...)                          # 根据函数名分别调用；
>>> module2.open(...)
                                               
>>> from module1 import open as open1          # 重命名后调用；
>>> from module2 import open as open2

```

# 2. 赋值魔法

## 2.1 序列解包（unpack; v. 打开，取出）
- 序列解包/可迭代对象解包：将序列 or 可迭代对象解包，所得值并行赋值给其他变量；
- 序列解包时，等号左右元素数量须保持相等，否则引发异常；

```
# 并行赋值
>>> x, y, z = 1, 2, 3
>>> x, y, z = (1, 2, 3)
# 变量交换数值
>>> x, y = y, x
# 使用星号收集多余值，存放于一个 list 中；
>>> a, b, *rest = [1, 2, 3, 4]
>>> test
>>> [3, 4]

>>> name = "Albus Percival Wulfric Brian Dumbledore"
>>> first, *middle, last = name.split()
>>> middle
['Percival', 'Wulfric', 'Brian']

```

## 2.2 链式赋值

```
# 将多个变量关联到同一值；
>>> x = y = somefunction()
```

## 2.3 增强赋值
- 增强赋值可用于 number and string ；

```
>>> x += 1                                     # 等价于 x = x + 1;
>>> fnord = 'bar'
>>> fnord *= 2                                 # 等价于 fnord = fnord * 2;

```

## 2.4 代码块：缩进的乐趣
- Python 中代码块标准缩进为4个空格；

# 3. 条件和 if 语句

## 3.1 布尔值
- 布尔值：亦称真值；



```
# 0与False ，1与True作用相同；
>>> True = 1
True
>>> True + False + 42
43

# bool([]) 、 bool("") 、 False 均为假，但并不相等；
>>>  () != False
True

# .startswith() 与 .endswith() 检查 string 的开头与结尾字符，返回 bool 值；
>>> name = input("Please input your name: ")
>>> num1 = name.startswith('Andrew')
>>> num2 = name.endswith('Pan')
>>> print(num1)
>>> print(num2)

```

## 3.2 if 语句
- None

## 3.3 else 子句

```
# else 子句可省略；
# 条件表达式，条件 A + if + 真值 + else + 条件 B ； 
# 真值为 True 则执行 A ，真值为 Flase 则执行 B ；
>>> name = input('What is your name? ')
>>> status = 'friend' if name.endswith('Gumby') else 'stranger'
>>> print(status)

```

## 3.4 更复杂的条件

### 3.4.1 比较运算符
|   表达式   |             功能              |
| :--------: | :---------------------------: |
|   x is y   |       x、y是同一个对象        |
| x is not y |       x、y是不同的对象        |
|   x in y   |  x是容器（e.g. 序列）y的成员  |
| x not in y | x不是容器（e.g. 序列）y的成员 |

```
# Python支持链式比较；
>>> num = int(input('Enter a number between 1 and 10: '))
>>> if 1 <= num <= 10:                         # 等价于 if num <= 10 and num >= 1 :
...	    print('Great!')

```

 


```
# is 相同运算符
>>> x = y = [1,2,3]
>>> z = [1,2,3]
>>> x is y                                     # x、y 指向同一个 list ， 是同一个对象；
True
>>> x is z                                     # x、y 指向不同 list ， 不是同一个对象；
False

```

```
# in 成员资格运算符
>>> name = input('What is your name? ')
>>> if 's' in name:                            
...	    print('Your name contains the letter "s".')
...	else:
...	    print('Your name does not contain the letter "s".')

```

```
# string 和 序列的比较；
# 字母均为Unicode字符，按码点排列； 
# 函数 ord ，输入 string 参数，返回对应的码点；
# 大写字母与大写字母间，小写字母与小写字母间排序有规律；
# 可用 .lower() 方法均转换为小写字母，再做比较；
>>> ord('A')
65
>>> ord('a')
97

# 序列比较，从第一个对象的大小开始比较；
>>> [1,2] < [2,1]
True
>>> [2, [1,4]] < [2,[1,5]]
True

```

### 3.4.2 布尔运算符
- 布尔运算符：and、or、not；
- 短路逻辑：亦称 延迟求值 ；
  e.g. x and y，
  若 x 为假，则表达式不再继续判断，而是立即返回 x ；
  若 x 为真，则返回 y ；

```
>>> name = input('Please enter your name: ') or '<unknown>'
...	Please enter your name:                    # 此处不输入 name ，而是直接敲回车键；
>>> name
'<unknown>'
 
```

## 3.5 断言

```
# assert 布尔表达式
# assert 布尔表达式， '描述性 string'
# 上述两种格式均可；
>>> age = -1
>>> assert 0 < age < 100, 'The age must be realistic'
---------------------------------------------------------------------------
AssertionError                            Traceback (most recent call last)
<ipython-input-36-547a53562a58> in <module>()
      1 age = -1
----> 2 assert 0 < age < 100, 'The age must be realistic'

AssertionError: The age must be realistic

```

# 4. 循环

## 4.1 while 循环

```
>>> name = ''
>>> while not name:                            # 以 string 的布尔值为循环条件；
...     name = input('Please enter your name: ')
>>> print('Hello, {}!'.format(name))

>>> name = ''
>>> while name.isspace() or not name:          # 可用 .isspace() 判断 name 是否为空格；
...     name = input('Please enter your name: ')
>>> print('Hello, {}!'.format(name))

```

## 4.2 for 循环
- 可迭代对象：可使用 for 循环遍历的对象；
- 函数 range()：
  range(0,10)：返回整数列表[0,1,2,3,4,5,6,7,8,9]；
  range(10)：默认起始值为0，返回整数列表[0,1,2,3,4,5,6,7,8,9]；

```
# for 循环遍历 list ；
>>> for number in range(1,101):
...     print(number)

```

## 4.3 迭代字典

```
>>> d = {'x':1, 'y':2, 'z':3}
>>> for key in d:                              # 遍历 dictionary 默认获取 key 值；
...     print(key, 'correspond to', d[key])

>>> d = {'x':1, 'y':2, 'z':3}
>>> for key in d.keys():                       # 使用 .keys() 与 .values() 获取所有的 key or value ；
...     print(key,'correspond to',d[key])

>>> d = {'x':1, 'y':2, 'z':3}       
>>> for key,value in d.items():                # 使用 .items() 以 tuple 形式返回 key-values ，可使用序列解包；
...     print(key,'correspond to',value)

```

## 4.4 一些迭代工具
### 4.4.1 并行迭代

```
# 以 i 作为循环索引并行迭代；
>>> names = ['anne', 'beth', 'george', 'damon']
>>> ages = [12, 45, 32, 102]
>>> for i in range(len(names)):
...     print(names[i], 'is', ages[i], 'years old')

# 函数 zip 将两个序列“缝合”为元组组成的序列；
>>> for name,age in zip(names, ages):          # 将 zip 返回的 tuple 解包；
...     print(name, 'is', age, 'years old')

# 若两个 list 的长度不同，则 zip 函数将按较短 list “缝合”；
>>> list(zip(range(5), range(10000)))
[(0, 0), (1, 1), (2, 2), (3, 3), (4, 4)]

```

### 4.4.2 迭代时获取索引

```
# 函数 enumerate() 将可迭代对象转换为[(index1, data1), (index2, data2), ...]；
# string 替换
>>> for index,string in enumerate(strings):
...     if 'xxx' in string:
...         strings[index] = '[censored]'


```

## 4.5 列表推导

```
# 列表推导
>>> [x*x for x in range(10) if x % 3 == 0]
[0, 9, 36, 81]
>>> [(x,y) for x in range(3) for y in range(3)]
[(0, 0), (0, 1), (0, 2), (1, 0), (1, 1), (1, 2), (2, 0), (2, 1), (2, 2)]

# 字典推导
# i 为 key ，"{} squared is {}".format(i, i**2) 为 value ，两者用冒号分隔；
>>> squares = {i:"{} squared is {}".format(i, i**2) for i in range(10)}
>>> squares[8]
'8 squared is 64'

```

# 5. del和exec

## 5.1 del

```
# 垃圾收集：Python解释器将没有关联名称的对象删除；
>>> x = 1
>>> y = x                                      # 使 x、y 指向同一个对象；
>>> x                                          # x、y 正常输出；
1
>>> y
1
# ------------------------------------------------------------------------------------------------
>>> x = None                                   # 将 x 赋值为 None ；
>>> x
>>> y
1
# ------------------------------------------------------------------------------------------------
>>> y = None                                   # 将 y 赋值为 None ；
>>> x                                          # 由于没有名称与该对象相关联，Python解释器将该对象删除；
>>> y
# ------------------------------------------------------------------------------------------------
>>> x = 1
>>> y = x
>>> del x                                      # 用 del 语句删除指向对象的名称,而非删除值；
>>> y
1
>>> x
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
NameError: name 'x' is not defined

```

## 5.2 exec and eval

### 5.2.1 exec

```
# 函数 exec 将 string 作为代码执行（execute）；
# 为避免字符串中的代码污染命名空间（作用域），需要向 exec 函数提供第二个参数，作为命名空间；
# 提供的全局命名空间应为 dictionary ，提供的局部命名空间可为任何映射；
>>> from math import sqrt
>>> scope = {}
>>> exec('sqrt = 1', scope)
>>> sqrt(4)
2.0
>>> scope['sqrt']
1
>>> scope.keys()
dict_keys(['__builtins__', 'sqrt'])            # __builtins__ 为自动添加的值；

```

### 5.2.2 eval

```
# eval 计算用 string 表示的 Python 表达式的值（evaluate）；
>>> eval(input("Enter an arithmetic expression: "))
Enter an arithmetic expression: 6 + 18 * 2
42

```
# References
[1] Hetland, M. L. (2018). *Beginning Python: From Novice to Professional* (3rd ed.). Berkeley: Apress.