[toc]

# 版权声明

- Machine learning 系列笔记来源于 Andrew Ng 教授在 Coursera 网站上所授课程 *Machine learning* [1]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及科学研究；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 本文采用 [署名-非商业性使用-禁止演绎 4.0 国际 (CC BY-NC-ND 4.0)](https://creativecommons.org/licenses/by-nc-nd/4.0/deed.zh) 协议发布；

# 1. Multivariate linear regression（多元线性回归）

## 1.1 Gradient descent in practice
- gradient descent：
  - 参数初始化为0，不断调整参数值，使代价函数 $J(\theta)$ 达到最小值；
  - $\theta_0,\theta_1,\cdots$ 的值需要同时更新；
  - 梯度下降算法的作用：找出一组 $\theta$ 值，使 $J(\theta)$ 最小；
- 绘制 $J(\theta)$ 关于迭代次数的函数，若在某一次迭代中，$J(\theta)$ 减少量小于设定阈值，即可认为 cost function 收敛，此时的一组参数 $\theta$ 即为所求；

### 1.1.1  Feature scaling（特征缩放）

- 将各个特征值的范围缩放到接近于 $-1\leq x_{i} \leq1$ 的区间上；
  在同一数量级上为宜；
  有利于提高 gradient descent 的收敛速度；

### 1.1.2  Mean normalization（均值归一化）；

-  用 $x_{i} - u_{i}$ 取代 $x_{i}$，使各个特征值的均值为0；
  $u_{i}$ 是该特征值的均值；
  $x_{i} - u_{i}$ 除以该特征的极差（最大值 - 最小值）即可实现均值归一化；
  该特征的范围也可使用标准差替代，这两种方式所得结果不相同；
  此处极差和标准差均可用于表示特征的范围；
  不可应用于 $x_{0}$ ，因为 $x_{0}  = 1$，不必做均值归一化；

### 1.1.3 Learning rate

- $\alpha$ ：learning rate；
  若 $\alpha$  过大，则 $J(\theta)$ 会越过最小值点不再收敛，甚至发散；
  若 $\alpha$  过小，算法一定可收敛，且能使代价更接近最小值，但需耗费较长时间；
- 经验参考：To choose $\alpha$ , try
  ... , 0.001 , 0.003 , 0.01 , 0.03 , 0.1 , 0.3 , 1 , ... （三倍速增加）


![这里写图片描述](https://img-blog.csdn.net/20180911100951783?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70#pic_center)

![这里写图片描述](https://img-blog.csdn.net/20180911100959890?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70#pic_center)

- 上图中的两种情况均由于 $\alpha$ 过大引起；
- 设置可变学习率的方法 (Optional)：
  - $\alpha=\frac{const_1}{iterationNumber + const_2}$；
  - 优点：$\alpha$ 随迭代次数的增加而减小，前期收敛快，后期使所求代价函数更接近最小值；
  - 缺点：需要选择两个常数 $const_1$、$const_2$ 的大小，增加了算法复杂性；
  - 由于将学习率设置为常数时，所得结果也比较接近于最小值，因此也可不使用该方法设置学习率；

## 1.2 选择特征

- 可将 $x_{1} 、x_{2}、... 、x_{n}$ 排列组合相乘，构成新的 features ， e.g. $x_{1}x_{2}^{2}$ ；
- 选择新的 features ，注意使用 feature scaling ，使得各个 feature 范围接近于 $-1\leq x_{i} \leq1$ 的区间上；

# 2. Normal equation（正规方程）

## 2.1 Concept

- 	Normal equation：一种求解θ的解析解法，不再需要多次迭代求解θ，而是直接求解θ的最优值；
    该方法不需要做 feature scaling ， 不需要选择 learning rate ；
    求 $J(\theta)$ 对 $\theta_{i}$ 的偏导，解得令偏导为0时的 $\theta_{i}$ 值，即为 $J(\theta)$ 最小时的 $\theta$ 值；
-   结论：$\theta=(X^{T}X)^{-1}X^{T}y$ ，即可解得使 $J(\theta)$ 最小的 $\theta$ 值；
- 对于 linear regression 问题，normal equation 是一个很好的替代方法；


- comparison

|       gradient descent        |                 normal equation                  |
| :---------------------------: | :----------------------------------------------: |
|    need to choose $\alpha$    |            no need to choose $\alpha$            |
|     need many iterations      |              do not need to iterate              |
| works well even when is large |             slow if n is very large              |
|          $O(kn^{2})$          | $O(n^{3})$ , need to compute inverse of $X^{T}X$ |

- 经验参考：若 n > 10000，则不再考虑 normal equation ；

## 2.2 当矩阵不可逆时的处理方法

- 当 $X^{T}X$ 为不可逆矩阵时，存在冗余特征 (e.g.m ≤ n)；
  - Solution：删除部分特征或使用正则化；

# References

[^1]:https://www.coursera.org/learn/machine-learning/home/welcome