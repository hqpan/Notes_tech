[TOC]

# 版权声明

- Machine Learning 系列笔记来源于Andrew Ng 教授在 Coursera 网站上所授课程《machine learning》[^1]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及交流讨论；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 转载请注明出处；

# 1. 线性回归和逻辑回归

- 在 classification 问题中，linear regression 的 hypothesis function 会由于 training example 不同而发生较大变化，因此不宜使用线性回归方法处理分类问题；
- 大多数实际问题并不严格遵从于某个 linear function ；
  ![defect of linear regression](https://img-blog.csdn.net/20180923202000915?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70#pic_center)
-  线性回归在预测二分类问题时，即使训练样本中均满足 $0<y<1$，仍可能出现 $h_{\theta}(x) < 0$ 或 $h_{\theta}(x) > 1$ 的情况；
- 逻辑回归中中可确保 $0\leq h_{\theta}(x) \leq1$ ；
  逻辑回归是一种分类算法，由于历史原因被命名为 regression ；
- 损失函数与代价函数：
  - 损失函数：描述单个训练样本的误差；
  - 代价函数：描述整个训练集损失函数的均值；

# 2. 假设表示

## 2.1 符号

- hypothesis function of logistic regression 含义：当输入为 x 时，y = 1 的可能性；
- hypothesis function 输出值的含义：$p(y=1|x;\theta)$，输入为 x，参数为 $\theta$时，y=1 的概率；
- decision boundary：决策边界；
  - 决策边界不是 training set 的属性，而是假设本身及其参数的属性；
  
## 2.2 要点

- 线性回归: $h_{\theta}(x)=\theta^{T}x$ ;
- 逻辑回归：$h_{\theta}(x)=g(\theta^{T}x)=\frac{1}{1+e^{-\theta^{T}x}}$;
  - g：sigmoid (adj. S型的) function / logistic function : $g(z)=\frac{1}{1+e^{-z}}$ ; [^2]
  - 当 $\theta^{T}x \geq 0$ 时，预测 y = 1 ;
    当 $\theta^{T}x < 0$ 时，预测 y = 0 ;
  - sigmoid function 将任意值映射到 0-1 区间上；

![logistics function](https://img-blog.csdn.net/20180923213620361?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70#pic_center)

# 3. 逻辑回归算法的代价函数

- 损失函数和代价函数：
  - 损失函数：Loss Function，衡量**单个**样本上的预测值与真实值之间的差异；
  - 代价函数：Cost Function，衡量**全体**样本上的预测值与真实值之间的差异，是各个样本上的损失函数结果的均值；
- 线性回归的损失函数 : $J(\theta)=\frac{1}{m}\sum_{i=1}^m\frac{1}{2}{(h_{\theta}(x^{(i)})-y^{(i)})}^{2}$；
  - 取 $h_{\theta}(x)=\frac{1}{1+e^{-\theta^{T}x}}$ ；
  - 添加系数 $\frac{1}{2}$ 是为了便于计算，即在求导时与 $(\cdots)^2$ 中的2相乘得1；
- Q：为什么逻辑回归中不使用平方误差作为损失函数？
  - A：在逻辑回归中，若使用平方误差作为代价函数，则 $J(\theta)$ 为非凸函数，因此需要另选代价函数；
- 为了使代价函数为凸函数，在逻辑回归中，取 
  - $Cost(h_{\theta}(x),y)=-log(h_{\theta}(x)), y=1;$
    $Cost(h_{\theta}(x),y)=-log(1-h_{\theta}(x)), y=0;$
  - 等价于 $Cost(h_{\theta}(x),y)=-ylog(h_{\theta}(x))-(1-y)log(1-h_{\theta}(x))$
- $J(\theta)=\frac{1}{m}\sum_{i=1}^mCost(h_{\theta}(x^{(i)}),y^{(i)})$；
- $J(\theta)=-\frac{1}{m}[\sum_{i=1}^my^{(i)}log(h_{\theta}(x^{(i)}))+(1-y^{(i)})log(1-h_{\theta}(x^{(i)}))]$；
  - Q：为什么代价函数式中有 $\frac{1}{m}$ 项？
    A：每一个样本 $(x^{(i)}，y^{(i)})$ 均有误差，将 m 个样本的误差累加后除以 m，即可得平均误差；
![Costfunction_y=1](https://img-blog.csdnimg.cn/20181228193237947.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==,size_16,color_FFFFFF,t_70#pic_center)
![Costfunction_y=0](https://img-blog.csdnimg.cn/20181228193307644.png#pic_center)

# 4. 多分类问题

- one-vs-all：一对多分类算法，将问题转换为多个二分类问题，然后使用 logistic regression 处理；
  - 得到 n 个 hypothesis function ，$h_{\theta}^{(i)}(x)=p(y=i|x;\theta),i=1,2,3...n$；
  - 预测时，在 n 个 hypothesis function 中输入 x，求输出结果，判定新输入的 x 值属于输出结果最大的类；

# 5. 处理过拟合问题

## 5.1 过拟合问题

- underfitting(欠拟合)/high bias(高偏差)：
  - 所用假设函数太简单或所用特征较少；
  - 即在训练集上的误差过大；
- overfitting(过拟合) /high variance(高方差)：所用假设函数过于复杂或所用特征过多；  
  - 过拟合：以高阶多项式（too many features）作为假设函数，能拟合几乎所有的数据，此时函数变量太多，缺少足够的数据约束该函数；
  - 过拟合的假设函数泛化能力差，即在训练集和交叉验证集上性能差距过大；
- 正则化的作用：避免过拟合，适用于存在大量特征，且每个特征对 y 均有微弱贡献；
- 处理过拟合的方法：
  - 减少特征数量：筛选特征的过程将丢失部分有用信息；
     - 手工选择需要保留的特征；
     - 使用模型选择算法，自动选择需要保留的特征；
  - 正则化：
    - 保留所有特征，但减少参数 $\theta_{j}$ 的量级；

## 5.2 带有正则化项的代价函数
- 参数 $\theta_{i}$ 较小时，假设函数比较简单，不容易出现 overfitting；
- 由于不知道需要缩小哪些参数，因此在代价函数表达式后，新增一个正则化项，缩小每个参数的值；
  - $J(\theta)=\frac{1}{2m}[\sum_{i=1}^m{(h_{\theta}(x^{(i)})-y^{(i)})}^{2}+\lambda\sum_{j=1}^n\theta_j^2]$ ;
  - $\lambda$：正则化参数；
    - $\lambda$ 的作用：使假设函数在拟合训练集的同时，保持参数较小（保持 hypothesis function 较简单）；
    - 若 $\lambda$ 设置过大，则对 $\theta_{i}$ 的惩罚过大，$h_{\theta}(x)\approx\theta_{0}$，出现 欠拟合的情况；
  - 在正则化项中，未对 $\theta_{0}$ 正则化，是否对 $\theta_{0}$ 正则化不影响结果；

## 5.3 线性回归正则化

- 线性回归中应用梯度下降算法，更新 $\theta_{i}$ 的值；
  - $\theta_{0}:=\theta_{0}-\alpha\frac{1}{m}\sum_{i=1}^m{(h_{\theta}(x^{(i)})-y^{(i)})}x_0^{(i)}$ ；
  - $\theta_{j}:=\theta_{j}(1-\alpha\frac{\lambda}{m})-\alpha\frac{1}{m}\sum_{i=1}^m{(h_{\theta}(x^{(i)})-y^{(i)})}x_j^{(i)},j\epsilon\{1,2...,n\}$ ；
    - 因为 $\alpha>0$ 且较小，$\lambda>0$，m 一般较大，$\alpha\frac{\lambda}{m}$ 是一个非常小的正数，$1-\alpha\frac{\lambda}{m}$ 接近于1但小于1；
    - 每次更新参数时，少量减少 $\theta_{i}$ 的值；
- 线性回归中应用 normal equation 算法，求解 $\theta_{i}$ 的值；
  - $\theta={ (X^{T}X+\lambda\left[
\begin{matrix}
   0  &  &  &  &      \\
   &  1  &  &  &      \\
   &  &  1  &  &      \\
   &  &  & \ddots & \\
   &  &  &  &  1      \\
\end{matrix}
\right]
)}^{-1}X^{T}y$ ；
    - 其中 $\left[
\begin{matrix}
   0  &  &  &  &      \\
   &  1  &  &  &      \\
   &  &  1  &  &      \\
   &  &  & \ddots & \\
   &  &  &  &  1      \\
\end{matrix}
\right]$ 为 $(n+1)\times(n+1)$ 维矩阵；
    - 上式的推导过程不要求掌握； 
  - 若 m<n，即样本数量小于特征数量时，$X^{T}X$ 不可逆，或称之为奇异矩阵、退化矩阵；
  - 若在 Octave 中应用 pinv 函数求解奇异矩阵，也能得到一个伪逆矩阵，但最终不会得到很好的假设模型；
  - 若 $\lambda>0$，则 ${X^{T}X+\lambda\left[
\begin{matrix}
   0  &  &  &  &      \\
   &  1  &  &  &      \\
   &  &  1  &  &      \\
   &  &  & \ddots & \\
   &  &  &  &  1      \\
\end{matrix}
\right]
}^{-1}$ 必为可逆矩阵；
    - 即 normal equation 算法还可解决 $X^{T}X$ 不可逆的问题；

## 5.4 逻辑回归正则化

## 5.4.1 优化逻辑回归的算法

- 优化逻辑回归的算法有：
  - 梯度下降算法；
  - advanced optimization methods；
  - 以上两种算法均需要计算 $J(\theta)$ 及其倒数的值；
- 逻辑回归中应用梯度下降算法，更新 $\theta_{i}$ 的值；
  - $J(\theta)=-\frac{1}{m}[\sum_{i=1}^my^{(i)}log(h_{\theta}(x^{(i)}))+(1-y^{(i)})log(1-h_{\theta}(x^{(i)}))]+\frac{\lambda}{2m}\sum_{j=1}^n\theta_j^2$；
    - 式中添加项 $\frac{\lambda}{2m}\sum_{j=1}^n\theta_j^2$ 为正则化项；
    - 由于不对 $\theta_{0}$ 做正则化，故正则化项从 j=1 开始；
    - 注意 MATLAB、octave 中元素索引从1开始；
  - $\theta_{0}:=\theta_{0}-\alpha\frac{1}{m}\sum_{i=1}^m{(h_{\theta}(x^{(i)})-y^{(i)})}x_0^{(i)}$ ；
  - $\theta_{j}:=\theta_{j}(1-\alpha\frac{\lambda}{m})-\alpha\frac{1}{m}\sum_{i=1}^m{(h_{\theta}(x^{(i)})-y^{(i)})}x_j^{(i)},j\epsilon\{1,2...,n\}$ ；
  - 此处假设函数不同于线性函数；
- 逻辑回归中应用 advanced optimization methods 求解参数值；
  - advanced optimization methods 包括：
    - conjugate gradient（共轭梯度法）；
    - Broyden fletcher goldfarb shann/BFGS（局部优化法）；
    - LBFGS（有限内存局部优化法）；
  - advantages of advanced optimization methods：
    - 无需手动选择 $\alpha$；
    - 比 gradient descent 运行速度快；
  - disadvantages of advanced optimization methods：算法复杂；
  - Octave 中的元素索引从1开始；

## 5.4.2 fminunc 函数
- fminunc：最小值优化函数，MATLAB、Octave 中均含有；
```
function [jVal, gradient] = costFunction(theta)
jVal = [...code to compute J(theta)...];
gradient = [...code to compute derivative of J(theta)...];
end
options = optimset('GradObj', 'on', 'MaxIter', '100');
initialTheta = zeros(2,1);
[optTheta, functionVal, exitFlag] = fminunc(@costFunction, initialTheta, options);
```

- options：该变量作为数据结构，存储想要的 options，设置梯度目标参数为打开（on），表示要为该算法提供一个梯度，设置最大迭代次数为100；
- @costFunction：指向自定义函数 costFunction 的指针；
- fminunc 函数中，要求输入的 theta 为向量，输出的 gradient 也为向量；
  - 使用 $ArrayName(:)$ 将矩阵转换为向量，使用 $reshape()$ 将向量转换为矩阵；



# References

[^1]:https://www.coursera.org/learn/machine-learning/home/welcome
[^2]:https://zh.wikipedia.org/wiki/S%E5%87%BD%E6%95%B0