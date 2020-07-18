[TOC]

# 版权声明

- Python 系列笔记来源于 Magnus Lie Hetland 的著作《Python基础教程》[1]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及科学研究；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 本文采用 [署名-非商业性使用-禁止演绎 4.0 国际 (CC BY-NC-ND 4.0)](https://creativecommons.org/licenses/by-nc-nd/4.0/deed.zh) 协议发布；
# 1. 异常

- 异常：是某个类的实例；
- 大多数异常是 Exception 的子类，SystemExit、KeyboardInterrupt 等例外，这些异常由BaseException 派生而来；
  BaseException 是 Exception 的超类；
- 自定义的异常必须是 Exception 的子类；
- 异常对象未被捕获时，程序终止并显示一条 traceback （错误信息）；

![内置的异常类](https://img-blog.csdn.net/2018082615355096?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)


# 2. 让事情沿你指定的轨道出错

## 2.1 raise 语句



```
# 
>>> raise Exception                            # 引发通用异常；
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
Exception

# --------------------------------------------------------------------------------------------------

>>> raise Exception('hyperdive overload')      # 添加错误信息； 
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
Exception: hyperdive overload

```

## 2.2 自定义异常类

- 自定义的异常类必须是 Exception 的子类；
- 定义方法与常规的类定义相同；

```
# 亦可在自定义的异常类中添加方法；
>>> class SomeCustomException(Exception):
...     pass
>>> raise SomeCustomException()
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
__main__.SomeCustomException

```

# 3. 捕获异常

```
# try/except 语句；
# 输出对用户更友好的信息；
>>> try:
...     x = int(input('Enter the first number: '))
...     y = int(input('Enter the second number: '))
...     print(x/y)
>>> except ZeroDivisionError:
...     print("The second number can't be zero!")
```

## 3.1 不用提供参数

- 捕获异常后，可调用 raise ，且不提供任何参数（也可显式提供捕获到的异常），用于重新引发该异常（即继续向上传播）；
- 若无法处理异常，可在 except 子句中使用不带参数的 raise 子句；

```
#
>>> class MuffledCalculator:
...     muffled = False
...     def calc(self, expr):
...         try:
...             return eval(expr)
...         except ZeroDivisionError:
...             if self.muffled:               # 使用属性 muffled 控制“抑制”功能的开与关；
...                 print('Division by zero is illegal')
...             else:
...                 raise                      # 捕获异常，并继续向上传播；

# --------------------------------------------------------------------------------------------------

# 引发另一个异常；
>>> try:
...     1/0
... except ZeroDivisionError:                  # 进入 except 子句的异常被作为上下文语句存储，并打印输出；
...     raise ValueError                       # 被指定引发的异常也会打印输出；

# --------------------------------------------------------------------------------------------------

# 使用 None 禁用上下文，仅显示指定的异常；
>>> try:
...     1/0
... except ZeroDivisionError:
...     raise ValueError from None             # raise ValueError from ...，用于指定上下文；

```

## 3.2 多个 except 子句

```
# 多个except 子句检查多种异常；
>>> try:
...     x = int(input('Enter the first number: '))
...     y = int(input('Enter the second number: '))
...     print(x/y)
... except ZeroDivisionError:
...     print("The second number can't be zero!")
... except ValueError:
...     print("That wasn't a number, was it?")

```

## 3.3 一个 except 子句捕获多种异常

```
# 向 except 子句提供包含多种异常名称的 tuple ；
# 该括号不可省略，表示为 tuple ；
>>> try:
...     x = int(input('Enter the first number: '))
...     y = int(input('Enter the second number: '))
...     print(x/y)
... except (ZeroDivisionError, TypeError, NameError):
...     print("Your numbers were bogus ...")


```

## 3.4 捕获对象

```
# 捕获异常对象存储在变量中，可打印；
>>> try:
...     x = int(input('Enter the first number: '))
...     y = int(input('Enter the second number: '))
...     print(x/y)
... except (ZeroDivisionError, ValueError, NameError) as e:
...     print(e)

```

## 3.5 捕获所有异常

```

>>> except:                                    # except 语句后不指定任何异常类型，即可捕获所有异常；
                                               # 不提倡这样做，可能隐藏各种未知错误；
>>> except Exception as e:                     # 如此有利于检查异常；                                             

```

## 3.6 循环输入直至满足要求

```
>>> while True:
...     try:
...         x = int(input('Enter the first number: '))
...         y = int(input('Enter the second number: '))
...         value = x/y
...         print('x / y is', value)
...     except:                                # except 子句捕获所有异常；
...         print('Invalid input. Please try again.')
...     else:                                  # else 子句，直至输入符合要求才可结束循环；
...         break

```

- 本方案的缺陷：except 子句只能捕获 Exception 及其子类的异常；
- except Exception as e:
  该语句打印更具有提示性的错误信息；

## 3.7 finally 子句 

```
>>> x = None                                   #            
>>> try:
...     x = 1/0
... finally:                                   # finally 子句在发生异常后执行清理工作；
...     print('Clean up ...')
...     del x

```

- 初始化 x 的原因：
  若不初始化 x ，则出现异常后，ZeroDivisionError 没有机会对 x 赋值，在 finally 子句中执行 del 操作时引发未捕获的异常；

# 4. warn 与 filterwarnings，警告与抑制警告

```
# warnings 模块中的 warn 函数，用于显示警告（仅显示一次，若再次运行代码，则不显示）；
>>> from warnings import warn
>>> warn("I've got a bad feeling about this.")

# --------------------------------------------------------------------------------------------------

# warnings 模块中的 filterwarnings 函数抑制警告；
>>> from warnings import filterwarnings
>>> filterwarnings("ignore")                   # 参数为 ignore ，表示抑制警告；         
>>> warn("Anyone out there")
>>> filterwarnings("error")
>>> warn("Something is very wrong!")           # 参数为 error ，表示不抑制警告；

```
# References

[1] Hetland, M. L. (2018). *Beginning Python: From Novice to Professional* (3rd ed.). Berkeley: Apress.