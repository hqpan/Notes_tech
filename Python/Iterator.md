[toc]

# 版权声明

- Python 系列笔记来源于 Magnus Lie Hetland 的著作《Python基础教程》[1]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及科学研究；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 本文采用 [署名-非商业性使用-禁止演绎 4.0 国际 (CC BY-NC-ND 4.0)](https://creativecommons.org/licenses/by-nc-nd/4.0/deed.zh) 协议发布；
# 1. 基本概念

- 以双下划线开头和结尾的方法，大多是**特殊方法**的名称；
  不可在函数中创建这种类型的名称；
- property：特性；
  iterator：迭代器；

# 2. 构造函数

- **构造函数（constructor）**：__init__()，用于初始化；
  构造函数将在对象创建时，被自动调用；
- **析构函数（destructor）**；

## 2.1 重写构造函数

- 若在子类中重写构造函数，则超类中的构造函数将不能在子类中被应用（因为被覆盖了）；
  solution：
  - 调用未关联的超类构造函数；
  - 使用函数 super（在较新版本的 Python 中，应使用本方法） ；

## 2.2 调用未关联的超类构造函数

```
>>> class Bird:
...     def __init__(self):
...         self.hungry = True
...     def eat(self):
...         if self.hungry:
...             print('Aaaah ...')
...             self.hungry = False
...         else:
...             print('No, thanks!')

... class SongBird(Bird):
...     def __init__(self):
...         Bird.__init__(self)           # 使用超类的构造函数初始化 SongBird 对象；
...         self.sound = 'Squawk!'
...     def sing(self):
...         print(self.sound)

```

- 对实例调用方法时，self 参数将自动关联到实例，称之为关联的方法；
- 通过类调用方法（e.g. Bird.\__init__），则没有实例与之相关联，称之为未关联的方法；

## 2.3 使用函数 super

- 使用 super().\__init__() 语句，替换 9.2.2 中的 Bird.\__init__(self)；
- 调用 super(当前类的名称， self) 函数时，使用当前类和当前实例（形参为 self ）作为参数，返回超类；
  此时调用的方法为超类的方法，而非当前类的方法；
- 调用 super() 函数时，可不提供任何参数；

# 3. 元素访问

## 3.1 基本的序列和映射协议-None

## 3.2 从 list、dict 和 str 派生

```
# 从 list 继承得到 CounterList ；
>>> class CounterList(list):
...     def __init__(self, *args):        # 重写 __init__() ；
...         super().__init__(*args)       # super() 函数调用超类的构造函数用于初始化；
...         self.counter = 0              # 引入新的 attribute ；
...     def __getitem__(self, index):
...         self.counter += 1             # 对新的 attribute 操作；
...         return super(CounterList, self).__getitem__(index)
                                          # super().__getitem__(index) 亦可；
                                          # 即不向 super() 函数提供参数（见 9.2.3 ）；
```

# 4. 特性

- property（特性）：通过存取方法定义的 attribute ；

## 4.1 函数 property

- 函数 property 可避免编写大量存取 attribute  的方法；
- property(fget, fset, fdel, doc)
  该函数四个参数均为可选参数；
  若不指定任何参数，则创建的特性不可读且不可写；
  
```
>>> class Rectangle:
...     def __init__(self):
...         self.width = 0
...         self.height = 0
...     def set_size(self, size):
...         self.width, self.height = size
                                          # 给 size 赋值时应为两个值，value1，value2 ；
...     def get_size(self):
...         return self.width, self.height    
...     size  = property(get_size, set_size)
                                          # 调用 property 函数创建一个特性，并与名称 size 关联； 
>>> r = Rectangle() 
>>> r.size = 150, 100                     # 创建的名称 size 如同 attribute 一样调用；                                    
                                             
```

## 4.2 静态方法和类方法

- 使用 staticmethod、classmethod 方法后，无需实例化即可调用类中定义的方法；

```

>>> class MyClass:
...     def smeth():                      # staticmethod 的定义中没有形参；
...         print('This is a static method')
...     smeth = staticmethod(smeth)       # 旧的 staticmethod ；
    
...     def cmeth(cls):                   # classmethod 方法的定义中的形参习惯上被命名为 cls ；
...         print('This is a class method of', cls)
...     cmeth = classmethod(cmeth)        # 旧的 classmethod ；

# --------------------------------------------------------------------------------------------------

>>> class MyClass:
    @staticmethod                         # 装饰器；
    def smeth():
        print('This is a static method')
    
    @classmethod                          # 装饰器；
    def cmeth(cls):
        print('This is a class method of', cls)

```


# 5. 迭代器协议

- iterate（迭代）：可用于 list 、dictionary 等对象或其他实现了方法 \__iter__的对象；
- 可迭代对象：实现了方法 \__iter__；
  迭代器：实现了方法 \__next__，该方法用于使迭代器返回下一个值；
  iterator 包含方法 \__iter__ 和 \__next__ ；
- 内置函数 iter() 将可迭代对象转换为迭代器， e.g. iter([1, 2, 3]) ；
- 内置函数 next() 返回可迭代对象的下一个值，若 iterator 没有可供返回的值，则引发 StopIterator 异常；

```
>>> class Fibs:
    def __init__(self):
        self.a = 0
        self.b = 1
    def __next__(self):
        self.a, self.b = self.b, self.a + self.b
        return self.a
    def __iter__(self):
        return self                       # __iter__() 返回迭代器本身；
fibs = Fibs()

# --------------------------------------------------------------------------------------------------

# 使用isinstance()判断一个对象是否为 Iterator ，返回布尔值；
>>> from collections import Iterator
>>> isinstance(fibs, Iterator)

```


# 6. generator（生成器）

- 包含 yield 语句的函数被称为 generator ；
- generator 返回 iterator ；
- 创建generator：把列表生成式的[]改成()即可；
- 执行含有关键字yield的generator函数：
  每次调用next()的时候执行，遇到yield语句返回，返回值断点在yield右侧，再次执行时从上次返回的yield语句处继续执行；


## 6.1 递归式生成器

```
# 将多层嵌套的 list 展开为一个 list ；
# 由于不确定嵌套的层数，因此使用递归；
>>> nest = [[1,[2, 3],  4], [5, 6], [7]]
>>> def flatten(nested):
...     try:
...         for sublist in nested:        # 每次递归拆分一层；
...             for element in flatten(sublist):
...                 yield element
...     except TypeError:
...         yield nested
... list(flatten(nest))

```

## 6.2 generator 的方法

 

 - 仅当 generator 被挂起后（遇到第一个 yield后），使用 send 才有意义；
   若需要在此前向 generator 提供信息，可使用生成器函数的参数；
 - generator 重新运行时，yield 返回通过 send 方法从外部世界发送的值；
   若使用的是 next()，则返回 None； 
 - generator 的 method;
   |     method      |                           function                           |
   | :-------------: | :----------------------------------------------------------: |
   | .send(argument) |                       向生成器提供信息                       |
   |      throw      | 用于在 generator 中（ yield 表达式处）引发异常，调用时可提供一个异常类型、一个可选值、一个 traceback 对象 |
   |      close      |                 停止 generator，不必提供参数                 |



# References

[1] Hetland, M. L. (2018). *Beginning Python: From Novice to Professional* (3rd ed.). Berkeley: Apress.