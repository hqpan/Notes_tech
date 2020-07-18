[TOC]
# 版权声明

- Python 系列笔记来源于 Magnus Lie Hetland 的著作《Python基础教程》[1]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及科学研究；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 本文采用 [署名-非商业性使用-禁止演绎 4.0 国际 (CC BY-NC-ND 4.0)](https://creativecommons.org/licenses/by-nc-nd/4.0/deed.zh) 协议发布；
# 1. basic concept

 - mapping（映设）：通过key访问values；
 - dictionary是Python中唯一内置的映射类型；
 - dictionary格式：每个 item {key1: value1， key2： value2}，key不得重复；
 - dict、list、tuple、str 均为类，而非函数；
 - key应为不可变类型：浮点数（实数）、string or tuple ；
   value 则不作要求；
 - 字典各项无序存放，亦可将字典中的数据读取后，存放于list中，排序后再读取，即可实现有序输出；

# 2. 创建和使用dictionary

## 2.1 类 dict
```
# 类dict从其他mapping or key-values 创建字典；
>>> items = [('name', 'Gumby'), ('age', 42)] 
>>> d = dict(items)
>>> d
{'name': 'Gumby', 'age': 42}

>>> d = dict(name = 'Gumby', age = 42)
>>> d
{'name': 'Gumby', 'age': 42}

```

## 2.2 基本的字典操作

 - none

## 2.3 将 string 格式设置功能用于 dictionary
```
# 使用format_map，通过mapping 提供信息；
>>> phonebook = {'Beth': '9102', 'Alice':'2341', 'Cecil':'3528'}
>>> "Cecil's phone number is {Cecil}.".format_map(phonebook)
"Cecil's phone number is 3528."

```

## 2.4 dictionary的方法

-  .clear() 
```
# 删除所有字典项，就地执行，返回 none ；
>>> d = {}
>>> d['name'] = 'Gumby'
>>> d['age'] = 42
>>> return_value = d.clear()
>>> d
{}
>>> print(return_value)
None

# 辨析1
>>> x = {}
>>> y = x                 # 令 x 与 y 均指向同一个 dictionary ；            
>>> x['key'] = 'value'
>>> x = {}                # 将空 dictionary 赋值给 x ，将其清空；
>>> x                     # 该方式能清空 x ，却不能清空 y ；
{}
>>> y                     
{'key': 'value'}

# 辨析2
>>> x ={}
>>> y = x
>>> x['key'] = 'value'
>>> x.clear()             # 使用 .clear() 方法将 x 清空的同时， y 也被清空；
>>> x
{}
>>> y
{}

```

-  .copy()
```
# .copy() 为浅复制，返回一个新字典；
>>> x = {'username': 'admin', 'machines': ['foo', 'bar', 'baz']}
>>> y = x.copy()                                   # .copy() 浅复制；
>>> y['username'] = 'mlh'                          # 替换副本中的值，原件不受影响；
>>> y['machine'].remove('baz')                     # .remove() 就地删减副本的值，原件跟随变化；
>>> x
{'username': 'admin', 'machines': ['foo', 'baz']}
>>> y
{'username': 'mhl', 'machines': ['foo', 'baz']}

# copy 模块中的 deepcopy 函数为深复制；
>>> from copy import deepcopy
>>> d = {}
>>> d['names'] = ['Alfred', 'Bertrand']
>>> c = d.copy()                                   # .copy() 浅复制；
>>> dc = deepcopy(d)                               # .deepcopy() 深复制；
>>> d['names'].append('Clive')                     # .append() 就地修改原件后，浅复制的结果变动；
>>> c
{'names': ['Alfred', 'Bertrand', 'Clive']}
>>> dc                                             # .append() 就地修改原件后，深复制的结果不变；
{'names': ['Alfred', 'Bertrand']}

```

- .fromkeys()
```
# .fromkeys() 创建新 dictionary ，默认 value 值为 None ；
>>> dict.fromkeys(['name', 'age'])
{'name': None, 'age': None}
>>> dict.fromkeys(['name', 'age'], '(unknown)')    # 向 .fromkeys() 提供特定的值，取代默认值None；
{'name': '(unknown)', 'age': '(unknown)'}

```

- .get()
```
# .get() 返回 dictionary 中不存在的项，返回 None ；
>>> d = {}
>>> print(d.get('name'))
None

# 指定返回值替代 None ， 提高程序可读性；
>>> d ={}
>>> d.get('name', 'N/A')                          # 令返回值为 N/A ；
N/A

```

- .items()
- .items() 返回 **字典视图** ，可迭代、不复制；
- 字典视图为 list ，list 中的元素为（key， value）形式；
```
# .items() 返回字典视图；
>>> d = {'title': 'Python web site', 'url': 'http://www.python.org', 'spam': 0}
>>> d.items()
dict_items([('title', 'Python web site'), ('url', 'http://www.python.org'), ('spam', 0)])
# 不复制
>>> it = d.items()                                # 将字典视图复制给 it ;
>>> d['spam'] = 1                                 # 修改字典值，复制的字典视图值跟随改变；
>>> ('spam', 0) in it
Flase
>>> d['spam'] = 0
>>> ('spam', 0) in it
True

```

- .keys()
```
# .keys() 返回字典视图，结果仅包含字典中的 key ；
>>> d.keys()
dict_keys(['title', 'url', 'spam'])

```

- .values()
```
# .values() 返回字典视图，结果仅包含 dictionary 中的 value ；
>>> d = {1:1, 2:2, 3:3, 4:1}
>>> d.values()
dict_values([1, 2, 3, 1])

```

- .pop()
```
# 指定 key 值，删除对应的 key-value ；
>>> d = {'x': 1, 'y': 2}
>>> d.pop('x')
1                                                 # 返回被弹出的 value ；
```

- .popitem()
```
# 由于字典里各项无序存放，因此 .popitem() 不是删除最后一个元素，而是随机删除一个元素；
>>> d = {'url': 'http://python.org','spam':0, 'title':'Python web site'}
>>> d.popitem()
('title', 'Python web site')                      # 以 tuple 的形式返回 key 和 value ；

```

- .setdefault()
```
# 根据给定的 key ，返回 value ；
# 若给定的 key 存在，则像 .get() 一样返回 key-value ；
# 若给定的 key 不存在，则添加该 key ，value 默认为 None ；
>>> d = {}
>>> print(d.setdefault('name'))
None
>>> d
{'name': None}

# 指定 value 值，当给定的 key 不存在时，取代默认的value值 None ，提高程序可读性；
>>> d = {}
>>> d.setdefault('name', 'N/A')
'N/A'
>>> d
{'name': 'N/A'}

```

- .update()
```
# .update() 使用一个 dictionary 中的项更新另一个 dictionary 中的项；
# 更新所有 key 值相同的项；
>>> d = {
    'title': 'Python web site',
    'url': 'http://www.python.org',
    'change': 'Mar 14 22:09:15 MET 2016'
	}
>>> x = {'title': 'Python Language Website', 'change': 'a litter change'}
>>> d.update(x)
>>> d
{'title': 'Python Language Website',
 'url': 'http://www.python.org',
 'change': 'a litter change'}
 
```

# References

[1] Hetland, M. L. (2018). *Beginning Python: From Novice to Professional* (3rd ed.). Berkeley: Apress.