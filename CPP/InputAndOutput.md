[TOC]

# 版权声明
- C++ 系列读书笔记来源于 Bjarne Stroustrup 所著《C++程序设计原理与实践（基础篇）》；[^1]
- 该系列笔记不以盈利为目的，仅用于记录学习过程中的知识要点和心得体会；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 转载请注明出处；

# 1. 打开文件
## 1.1 concept
- 与文件相关联的流：
  - ifstream;
  - ofstream;
  - fstream;
- 文件流离开作用域时，与之关联的文件被关闭；
  - 在创建 ifstream 和 ofstream 对象时隐式地打开文件；
  - 依靠流对象的作用域关闭文件； 

## 1.2 code
```cpp
cout << "Please enter input file name:";
string iname;
cin >> iname;
ifstream ist {iname};
if (!ist)
    error("can't open input file ", iname);
```
- 文件流与文件相关联，方可使用；

# 2. 阅读至 page227,10.5节已完成；
# References
[^1]:Bjarne S. C++程序设计原理与实践（基础篇）[M]. 任明明,王刚,李忠伟, 译. 北京: 机械工业出版社, 2017.