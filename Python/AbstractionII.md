@[toc]
# 版权声明

- Python 系列笔记来源于 Magnus Lie Hetland 的著作《Python基础教程》[1]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及科学研究；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 本文采用 [署名-非商业性使用-禁止演绎 4.0 国际 (CC BY-NC-ND 4.0)](https://creativecommons.org/licenses/by-nc-nd/4.0/deed.zh) 协议发布；
# 1. 类

## 1.1 类的相关概念

- 在python中，首字母大写的名称指的是类；
- 超类（基类）：鸟类是云雀的超类；
  子类：云雀是鸟类的子类；
  实例：阳台上的那只云雀（特指）；

## 1.2 创建自定义类

- 在类中定义的函数称为 method（方法）；
- docstring 可放置于 def 语句后，以及模块、类的开头；
- 形参中的 self 应置于其他形参之前，必不可少，名称可另取，习惯上取做 self ；

```
# 
>>> class Person:                              # class 语句创建独立的命名空间，用于定义函数；
...     def set_name(self, name):              
...         self.name = name
    
...     def get_name(self):
...         return self.name
    
...     def greet(self):
...         print("Hello, world! I'm {}.".format(self.name))
>>> foo = Person()                             # 创建一个实例；
>>> foo.set_name('Luke Skywalker')
>>> foo.get_name()                             # 从内部访问 attribute（属性）；
>>> foo.name                                   # 从外部访问 attribute（属性）；
>>> tem = foo.get_name                         # 让变量指向某个方法并调用；
>>> tem()

```

## 1.3 隐藏

- 命名 method or attribute 时，若以两个下划线开头，则将其私有，即不能从外部访问；
- 私有的幕后处理：在类定义中，对所有以2个下划线开头的名称进行转换，在开头加上一个下划线和类名；
- from module import * 语句不会导入以**1个下划线开头**的名称；

```
>>> class Secretive:
...     def __inaccessible(self):              
...          print("Bet you can't see me ...")
>>> s = Secretive()
>>> s._Secretive__inaccessible()               # 若已知私有的幕后处理方式，仍能访问私有的属性和方法；
                                               # 不应该这样做；

```

## 1.4 类的命名空间

 - class 语句创建独立的命名空间，类的所有成员均可访问该命名空间；

```
#
>>> class MemberCounter:
...     members = 0
...     def init(self):
...         MemberCounter.members += 1
>>> m1 = MemberCounter()
>>> m1.init()
>>> MemberCounter.members                      # 每个实例均可访问类中的变量；
1
>>> m2 = MemberCounter()
>>> m2.init()
>>> MemberCounter.members
2
>>> m1.members = 'Two'                         # 在实例中给属性 members 赋值；
>>> m1.members
'Two'
>>> m2.members                                 # **Why?待处理**
2



```

## 1.5 继承

```
>>> class Filter:
...     def init(self):
...         self.blocked = []
        
...     def filter(self, sequence):
...         return [x for x in sequence if x not in self.blocked]
    
>>> class SPAMFilter(Filter):                  # 括号内为需要继承的超类；
...      def init(self):                       # 重写超类中的方法；
...          self.blocked = ['SPAM']
>>> f = Filter()
>>> f.init()
>>> f.filter([1, 2, 3])
[1, 2, 3]

# --------------------------------------------------------------------------------------------------

# 方法 issubclass(A, B) 判断 A 是否是 B 的子类；
>>> issubclass(SPAMFilter, Filter) 
>>> True

# 访问特殊属性 __bases__ ，可知某个类的基类；                        
>>> SPAMFilter.__bases__                      # SPAMFilter 是 Filter 的子类；
(__main__.Filter,)
>>> Filter.__bases__                          # Filter 自身为超类；
(object,)

# isinstance(A, B) 用于确定A是否是B的实例 or 判断A是否属于类型B，e.g. str ；
>>> s = SPAMFilter()
>>> isinstance(s, SPAMFilter)                 # s 是SPAMFilter的（直接）实例，也是Filter的（间接）实例；
True
>>> isinstance(s, str)
False

# __class__ 可得对象属于哪个类，type()具有相同作用；
>>> s.__class__
__main__.SPAMFilter


```

## 1.6 多个超类

```
# 多重继承：某一个子类继承了多个超类；
>>> class Calculator:
... 	pass
	class Talker:
... 	pass
	class TalkingCalculator(Calculator, Talker):
... 	pass                                  # 括号中为要继承的超类；
                                              # 若两个超类中存在重名的方法，则由前一个超类覆盖后者；
```

## 1.7 接口和内省

```
# hasattr(A, B)，检查实例A中方法B是否存在，返回布尔值；
>>> hasattr(tc, 'talk')

# getattr() 指定属性不存在时使用默认值；
# 对 getattr() 返回的对象调用 callable()，判断属性'talk'是否可调用；
>>> callable(getattr(s, 'talk', None))        # 返回布尔值；

```

## 1.8 抽象基类

- 继承抽象基类的子类必须重写**抽象方法**，否则不能实例化；

```
# 抽象基类：指定子类必须提供哪些功能；
>>> from abc import ABC, abstractmethod
>>> class Talker(ABC):
...     @abstractmethod                       # 装饰器；
...     def talk(self):
...         pass
>>> class Knigget(Talker):
...      def talk(self):                      # 重写抽象方法；
...          print("Ni!")

```

# 2. 小结
- 类：表示一组 or 一类对象；
- 对象：包括 attribute 和 method ； 
			 attribute：属于对象的变量；
			 method：类中定义的函数；
- 多态：无需知道对象属于哪个类即可调用其方法；

|             语句             |             功能             |
| :--------------------------: | :--------------------------: |
|   random.choice(sequence)    |  随机从非空序列中取一个元素  |
| setattr(object, name, value) | 将对象的指定属性设定为指定值 |

# References

[1] Hetland, M. L. (2018). *Beginning Python: From Novice to Professional* (3rd ed.). Berkeley: Apress.