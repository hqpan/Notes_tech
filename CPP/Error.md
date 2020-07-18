

[toc]

# 版权声明

- C++ 系列读书笔记来源于 Bjarne Stroustrup 所著《C++程序设计原理与实践（基础篇）》；[^1]
- 该系列笔记不以盈利为目的，仅用于记录学习过程中的知识要点和心得体会；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除； 
- 转载请注明出处；


# 1. 定义

- term（n.术语，学期，项，条款）
  - compile：编译；
  - debug：调试；
  - exception：n.异常，例外；
  - syntax：n.语法，句法；
  - invariant：n.不变式；adj.不变的；
    - variant：n.变量，变体；adj.多样的；


---

- container（容器）：数据集合；
- assertion（断言）：陈述一个不变式的语句；
- pre-condition & post-condition
  - pre-condition（前置条件）：函数对参数的要求；
  - post-condition（后置条件）；


# 2. 错误

![在这里插入图片描述](https://img-blog.csdnimg.cn/20181214203545846.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==,size_16,color_FFFFFF,t_70)


## 2.1 错误类型

- 链接时错误：
  - 变量、类型、函数：具有同一名字的实体只能定义一次，但可声明多次；
  - 声明类型应当一致；
- 运行时错误：
  - 在调用程序中处理错误；
  - 在被调用程序中处理错误：有利于提高程序可读性，降低计算复杂度；

## 2.2 try,throw,catch
- 标准异常库：```#include<stdexcept>```；
  - 常用异常有：
    - ```exception```：最常见的问题；
    - ```runtime_error```：仅在运行时出现的问题；
- 异常处理流程：
  1. 在本函数中寻找匹配的 catch；
  2. 若未找到匹配的 catch，则终止该函数，在其调用者中寻找；
  3. 重复 step2，若始终未匹配，则执行系统函数 terminate()  库，非正常中止程序；
- 使用异常处理的原则：
  - 应用多个 catch 语句时，具体的异常捕获分支在前，通用的异常捕获分支在后；

# 3. syntax（n. 语法，句法）

- cerr：用法同 cout，专门用于 error 输出；
  cerr 也可用于向 console（n.控制台）输出，但未经优化；



# References
[^1]: Bjarne S. C++程序设计原理与实践（基础篇）[M]. 任明明,王刚,李忠伟, 译. 北京: 机械工业出版社, 2017. 