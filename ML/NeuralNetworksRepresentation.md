[TOC]

# 版权声明

- Machine learning 系列笔记来源于Andrew Ng 教授在 Coursera 网站上所授课程 *Machine learning*[^1]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及交流讨论；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 转载请注明出处；

# 1. Neural Network 算法的意义- 非线性假设
- 与 logistic regression 算法的比较：
  - 实际问题中具有较多 feature（初始特征），排列组合能得到较多高次项（构造后得到的特征），难以全部包含在内；
  - 包含较多 feature 时容易 overfitting；
  - 若采用忽略部分相关项的方法，可减少多项式项数，但由于缺失信息，难以得到理想结果；
- 简单的增加二次项或三次项并不适合解决复杂的 non-linear 问题；
  - 初始特征较大时，将产生较多特征项；
- Neural Network 适用于复杂的非线性分类问题 （即输入的特征维数n较大/特征空间较大）；

# 2. model representation of Neural Network
## 2.1 model representation of Neural Network
- bias unit：偏置单位/偏置神经元，输入层和隐藏层均有，$x_{0}=1$；
- 激励函数： e.g. g(z)；
- 模型的权重：即参数 $\theta$；
- Hidden layer：隐藏层，即中间层；
- 符号含义：
  - $a_i^{(j)}$ ：第j层的第i个激活单元；
  - $\Theta^{(j)}$：第j层的参数矩阵/权重矩阵，控制从第 j 层到第 j+1 层的映射；
 - 神经网络示意图：
 ![神经网络结构](https://img-blog.csdnimg.cn/20190102112046807.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==,size_16,color_FFFFFF,t_70)
 - Forward propagation 求解 $a_i^{(j)}$ ：
   - $a_1^{(2)}=g(\Theta_{10}^{(1)}x_{0}+\Theta_{11}^{(1)}x_{1}+\Theta_{12}^{(1)}x_{2}+\Theta_{13}^{(1)}x_{3})$；
   - $a_2^{(2)}=g(\Theta_{20}^{(1)}x_{0}+\Theta_{21}^{(1)}x_{1}+\Theta_{22}^{(1)}x_{2}+\Theta_{23}^{(1)}x_{3})$；
   - $a_3^{(2)}=g(\Theta_{30}^{(1)}x_{0}+\Theta_{31}^{(1)}x_{1}+\Theta_{32}^{(1)}x_{2}+\Theta_{33}^{(1)}x_{3})$；
   - $h_{\Theta}(x)=a_1^{(3)}=g(\Theta_{10}^{(2)}a_0^{(2)}+\Theta_{11}^{(2)}a_1^{(2)}+\Theta_{12}^{(2)}a_2^{(2)}+\Theta_{13}^{(2)}a_3^{(2)})$ ；
   - 若神经网络第j层有 $S_{j}$ 个单元，第j+1层有 $S_{j+1}$ 个单元，则 $\Theta^{(j)}$ 为 $S_{j+1}\times(S_{j}+1)$ 维矩阵；
     - 输出不包含隐藏层的偏置单元，所以输出$S_{j}$ 个单元，而非$S_{j}+1$ 个单元；
- 神经网络不使用 $x_{1},x_{2},x_{3}$ 作为特征，而使用通过训练所得的 $a_1^{(2)},a_2^{(2)},a_3^{(2)}$ 作为特征，可以学到更复杂的假设函数；

## 2.2 神经网络的具体实现
- 定义：
  - $z_1^{(2)}=\Theta_{10}^{(1)}x_{0}+\Theta_{11}^{(1)}x_{1}+\Theta_{12}^{(1)}x_{2}+\Theta_{13}^{(1)}x_{3}$；
  - $z_2^{(2)}=\Theta_{20}^{(1)}x_{0}+\Theta_{21}^{(1)}x_{1}+\Theta_{22}^{(1)}x_{2}+\Theta_{23}^{(1)}x_{3}$；
  - $z_3^{(2)}=\Theta_{30}^{(1)}x_{0}+\Theta_{31}^{(1)}x_{1}+\Theta_{32}^{(1)}x_{2}+\Theta_{33}^{(1)}x_{3}$；
  - 对第 j=2 层的第 k 个节点：
  $z_k^{(2)}=\Theta_{k0}^{(1)}x_{0}+\Theta_{k1}^{(1)}x_{1}+\Theta_{k2}^{(1)}x_{2}+\cdot\cdot\cdot+\Theta_{kn}^{(1)}x_{n}$；

- 用向量化的方法实现前向传播：
  - $x=\begin{bmatrix} x_{0} \\ x_{1} \\ x_{2} \\ x_{3} \end{bmatrix},z=\begin{bmatrix}z_1^{(2)}\\z_2^{(2)}\\z_3^{(2)}\end{bmatrix}$；
  - $z^{(2)}=\Theta^{(1)}x,a^{(2)}=g(z^{(2)})$；

 

# References

[^1]:https://www.coursera.org/learn/machine-learning/home/welcome