[toc]

# 版权声明

- Python 系列笔记来源于 Magnus Lie Hetland 的著作《Python基础教程》[1]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及科学研究；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 本文采用 [署名-非商业性使用-禁止演绎 4.0 国际 (CC BY-NC-ND 4.0)](https://creativecommons.org/licenses/by-nc-nd/4.0/deed.zh) 协议发布；

# 1. concepts and notations
## 1.1 concepts
1. 字符串不可变；
## 1.2 notations
| notation |               function               |
| :------: | :----------------------------------: |
|    %s    | 转换说明符，将指定值转换为字符串类型 |
|   %.3f   |         带有3位小数的浮点数          |
|   \\\    |    输出转义字符需要用转义字符转义    |

## 1.3 字符串基本操作

```
# 字符串分行显示；
>>> print('''Hello,                                # 使用三个单引号；
... world.''')
Hello, 
world.

>>> print("""Hello,                                # 使用三个双引号；
... world""")
Hello, 
world

>>> print('Hello, ' \                              # 使用反斜杠连接2个 string ，打印显示在同一行；
...       'world.')
Hello, world.

```



# 2. 设置字符串格式
## 2.1 格式设置运算符%
- 针对单个值（string or number）
- 针对多个值（tuple）
```
>>> format = "Hello, %s. %s enought for ya?"
>>> values = ('world', 'Hot')
>>> format % values
```
## 2.2 字符串方法format
```
# 待替换字段-无名称
>>> "{}, {} and {}".format("first", "second", "third")
'first, second, third'

# 待替换字段-以索引为名称
>>> "{0}, {1} and {2}".format("first", "second", "third")
'first, second and third'
>>> "{3}, {0}, {2}, {1}, {3}, {0}".format("be", "not", "or", "to")
'to be or not to be'

# 替换多个字段，参数顺序无关紧要
# 指定格式说明符.2f（带有2位小数的浮点数），用冒号将格式说明符与字段名隔开
>>> from math import pi
>>> "{name} is approximately {value: .2f}.".format(value = pi, name = "pi")

# 变量与待替换字段同名，可使用f字符串（f""）
>>> from math import e
>>> f"Eluer's constant is roughly {e}"
>>> "Eluer's constant is rougtly 2.718281828459045"
```

# 3. 设置字符串的格式：完整版

## 3.1 替换字段名
```
# 未命名字段与命名字段，未命名字段按顺序排列；
>>> "{foo}{}{bar}{}".format(1, 2, bar = 4, foo = 3)
# 指定索引与命名字段；
# 未命名字段不得与指定索引方式混用，以免出现混乱；
>>> {foo}{1}{bar}{0}".format(1, 2, foo = 3, bar = 4)
# format 输出 list 中的部分值；
>>> fullname = ["Alfred", "Smoketoomuch"]
>>> "Mr {name[1]}".format(name = fullname)
# format 输出导入模块中的方法、属性、变量、函数；
>>> import math
>>> tmpl = "The {mod.__name__} module defines the value {mod.pi} for pi"
>>> tmpl.format(mod = math)
```

## 3.2 基本转换
```
# !s 使用str转换为字符串输出；
# !r 使用repr转换为Python表示输出；
# !a 使用ascii转换为ASCII字符输出；
>>> print("{pi!s} {pi!r} {pi!a}".format(pi = 'π'))
π 'π' '\u03c0'
# 以浮点数形式输出；
>>> "The number is {num:f}".format(num = 42)
```
![格式说明符 part1](https://img-blog.csdn.net/20180809203019383?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)
![字符串格式说明符 part2](https://img-blog.csdn.net/20180809203053530?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

## 3.3 宽度、精度和千位分隔符
```
# 宽度针对整数部分，指定宽度为10位；
>>> "{num:10}".format(num = 3)
# 精度针对小数部分，指定精度为2位；
>>> from math import pi
>>> "Pi day is {pi:.2f}".format(pi = pi)
# 为string指定精度；
>>> "{:.5}".format("Guido van Rossum")
# 同时指定宽度和精度；
>>> "{num2:10.2f}".format(num2 = 3.141592653)
# 添加千分位分隔符
>>> "One googol is {:,}".format(10**100)
# 添加千分位分隔符并指定其他格式设置;
# 千分位分隔符（逗号）放在宽度值与表示精度的句点之间；
>>> "{num1:1000,.2f}".format(num1 = 10**100 + pi)
```

## 3.4 符号、对齐、用0填充
```
# 左对齐、右对齐、居中，分别为 < 、 ^ 、 > ；
# 冒号前的索引为序号；
>>> print("{0:<10.2f}\n{1:^10.2f}\n{0:>10.2f}".format(pi, 1))
# 使用指定的填充字符（不限于$,其它字符亦可）；
>>> "{:$^30}".format("WIN BIG")
# = 的作用：使用默认的填充字符 blank space 填充在符号和数字之间；
# 等号的位置在冒号和宽度值之间；
>>> print("{0:10.2f}\n{1:=10.2f}".format(pi, -pi))
      3.14
-     3.14
# 符号说明符是否有负号，均不影响输出结果，有负号-是默认状态；
# 符号说明符位于冒号后，句点前；
>>> print('{0:.2}\n{1:.2}'.format(pi, -pi))
>>> print('{0:-.2}\n{1:-.2}'.format(pi, -pi))
# 符号说明符为正号，则正数输出时显示正号，若为负数，则不受正号影响；
>>> print('{0:+.2}\n{1:+.2}'.format(pi, -pi))
# 符号说明符为 black space，则在正数前加一个空格，负数则不受影响；
# black space，且仅能加一个空格，否则报错；
>>> print('{0: .2}\n{1: .2}'.format(pi, -pi))
# 进制转换（不显示B、O、D、H）
>>> "{:g}".format(42)
# 进制转换（显示B、O、D、H）
>>> "{:#g}".format(42)
```
- number默认右对齐，string 默认左对齐；


## 3.5 输出花括号
```
# 若要在最终结果中显示花括号，则使用2个花括号指定；
>>> "{{test}}".format()
'{test}'
```

# 4. 字符串方法
## 4.1 .center()
```
# .center(number)，默认在两侧填充 spacing，使 string 居中；
>>> "The middle by Jimmy Eat World".center(39)
'     The middle by Jimmy Eat World     '
# .center(number, "character")
>>> "The Middle by Jimmy Eat World".center(39, "*")
'*****The Middle by Jimmy Eat World*****'

```

## 4.2 .find()
```
# .find()在 string 中查找子串，如果查找到子串，则返回第一个子串的索引，否则返回-1；
>>> "Monty Python's Flying Circus".find('Monty')
0
>>> title = "Monty Python's Flying Circus"
>>> title.find('Zirquss')
-1
# .find('string', 起点索引， 终点索引)，搜索范围包含起点，不包含终点；
>>> subject = '$$$ Get rich now!!! $$$'
>>> subject.find('$$$')                            # 缺省起点和终点；
>>> subject.find('$$$', 1)                         # 仅指定起点；
>>> subject.find('!!!', 0, 16)                     # 指定起点与终点；

```

## 4.3 .join()
```
# .join() 用于仅能用于合并string；
# 将 sep 插入到 seq 的每个元素间；
>>> sep = '+'
>>> seq = ['1', '2', '3', '4', '5']
>>> sep.join(seq)
'1+2+3+4+5'
# 输出转义字符，需要先使用转义字符转义；
>>> dirs = '', 'usr', 'bin', 'env'
>>> print('C:' + '\\'.join(dirs))
C:\usr\bin\env

```

## 4.4 .split()
```
# .split()拆分 string；
# 第一个/最后一个转义字符 被去除时，认为其前/其后有空格；
>>> '/usr/bin/env/'.split('/')
['', 'usr', 'bin', 'env', '']
# 若不指定分隔符，则默认在多个连续空白字符（spacing空格，tab制表符, line break换行符）处拆分；
>>> 'Using the default'.split()
['Using', 'the', 'default']
```

## 4.5 .replace()
```
# .replace('string1', 'string2')，将子串 string1 替换为 string2 ；
>>> 'This is a test'.replace('is','eez')

```

## 4.6 .lower() ; .title() 
```
# .lower()将 string 中所有 characters 转换为小写；
>>> 'Trondheim Hammer Dance'.lower()
# .title()将 string 中所有首字母大写；
# .title()确定单词边界的方式不合理可能引起结果异常；
>>> "that's all folks".title()
"That'S All Folks"
```

## 4.7 .strip()
```
# .strip()删除字符串开头与结尾的空白；
>>> '     internal whitespace is kept       '.strip()
# 指定要删除的字符，且该方法仅能删除开头与结尾的 string ，示例中的星号与感叹号并不要求连在一起；
>>> '*** SPAM * for * everyone!!! ***'.strip(' *!')
'SPAM * for * everyone'

```

## 4.8 .translate()
```
# .translate()可同时替换多个单字符串；
# 对字符串类型 str 调用方法 maketrans ，该方法接收2个长度相同的字符串，并将前一字符串中的每个字符替换为后一字符串中的字符；
>>> table = str.maketrans('cs', 'kz')
>>> 'this is an incredible test'.translate(table)
'thiz iz an inkredible tezt'

# maketrans 方法可接收第三个参数，用于删除指定的字母；
>>> table = str.maketrans('cs', 'kz', 'a')
>>> 'this is an incredible test'.translate(table)

```

# References

[1] Hetland, M. L. (2018). *Beginning Python: From Novice to Professional* (3rd ed.). Berkeley: Apress.