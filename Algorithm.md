

[TOC]

# 0. 版权声明
- Algorithms 系列学习笔记来源于 Kevin Wayne 和 Robert Sedgewick 在 Coursera 网站上所授课程 *Algorithms, parts I and II* [1,2]，课程教材 *Algorithms 4th edition* [3]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及科学研究；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 本文采用 [署名-非商业性使用-禁止演绎 4.0 国际 (CC BY-NC-ND 4.0)](https://creativecommons.org/licenses/by-nc-nd/4.0/deed.zh) 协议发布；

# 1. 算法基础
- 算法与数据结构的区别：
  - 算法：method for solving a problem；
  - 数据结构：method to store information；
- 设计算法的步骤：
  -  建立数学模型；
  -  设计一种算法求解；
  -  若不满足对内存和运行速度的要求，则查找原因，修改算法；

## 1.1 术语

- ADT：abstract data type，抽象数据类型；
- API：application programming interface，应用程序接口；



==Schedule==

- 每日15页，40天完成；
- 处理所有课后题；




# 2. Union-find (并查集算法)
- 并查集算法仅能判断两个对象之间是否存在连接，但不能给出连接路径；
- Connected component (连通分量)：具有连接关系的对象组成的最大集合；
  - E.g. 下图中有3个连通分量；
![在这里插入图片描述](https://img-blog.csdnimg.cn/2019051020271037.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==,size_16,color_FFFFFF,t_70#pic_center)

# References
[1] https://www.coursera.org/learn/algorithms-part1?.
[2] https://www.coursera.org/learn/algorithms-part2?.
[3] Sedgewick, R. & Wayne, K. (2016). *Algorithms Fourth Edition*. Boston: Addison-Wesley.