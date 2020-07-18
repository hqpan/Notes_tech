[toc]

# 版权声明

- Machine learning 系列笔记来源于：
  - Andrew Ng 教授在 Coursera 网站上所授课程 *Machine learning* [1]；
  - 李航博士的《统计学习方法》[2]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及科学研究；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 本文采用 [署名-非商业性使用-禁止演绎 4.0 国际 (CC BY-NC-ND 4.0)](https://creativecommons.org/licenses/by-nc-nd/4.0/deed.zh) 协议发布；

# 1. Introduction of machine learning
## 1.1 Notation
- $\alpha$：学习率；
  `arg max f(x)`：Arguments of the maxima，当表达式 $f(x)$ 取最大值时对应的 $x$ 值；
  h：hypothesis function，通过学习算法获得；
  m : the number of training example；
  $m_{test}$：测试样本总数；
  n: the number of features；    
  $n_x$（有时也简写为 $n$）：特征向量的维度；
  $J(\theta)$ ：cost function ；
  $J_{train}(\theta)$：训练误差；
  $J_{cv}(\theta)$：交叉验证误差（cross validation error）；
  $p(x)$：随机变量 x 的概率分布；
  $p(x;\mu,\sigma^2)$：服从正态分布的随机变量 x 的概率分布，其均值为 $\mu$，方差为 $\sigma^2$；
  $\mathbb{R}^{n+1}$ ：n+1维向量（加一是由于下角标从0开始）；
  $||u||$：向量 u 的范数，即欧几里得长度；  
  $X=\begin{bmatrix}| & | & & | \\x^{(1)} & x^{(2)} & \cdots & x^{(m)} \\ | & | & & |  \end{bmatrix}$，$X$ 为 $n\times m$ 矩阵（或称为$n_x\times m$ 矩阵）；
  $x^{(i)}$：input (features) of $i^{(th)}$ training example；
  $x_j^{(i)}$：value of feature j in $i^{(th)}$ training example；
  $(x_{test}^i,y_{test}^i)$：第 i 组测试样本；  
  $z^{(i)}=w^Tx^{(i)}+b$；
  大写字母：矩阵；
  小写字母：标量、向量、数值、元素；
  `:=`：赋值运算符；

## 1.2 model and cost function

 - cost function：预测值与真实值之间的差值；

## 1.3 parameter learning
- 线性回归的代价函数是凸函数，有且仅有一个极小值，该极小值即为最小值；
  因此在线性回归问题中，使用 gradient descent 一定能得到 global optimum ；

## 1.4 linear algebra review
- 逆矩阵一定是方阵；
- 可逆矩阵即是非奇异矩阵；

# 2. 统计学习三要素
- 统计学习/统计机器学习：监督学习、非监督学习、半监督学习（Semi-supervised learning）、强化学习；
- 统计学习方法的三要素：模型、strategy（策略）、算法；
  - 模型（函数）：输入空间到输出空间的映射关系；
    - 监督学习的模型：
      - 概率模型：由条件概率分布 $\widehat{P}(Y|X)$ 表示；
      - 非概率模型：由决策函数 $Y=\widehat{f}(X)$ 表示；
  - Strategy：选择模型的标准，选择模型的方法包括正则化、交叉验证；
    - Loss function/Cost function（损失函数/代价函数）：评估模型某一次预测的效果；
    - Risk function/Expected loss（风险函数/期望损失）：即损失函数的期望，评估平均意义下的模型预测效果；
- $L(Y,f(X))$：损失函数；
  - 0-1损失函数：$L(Y,f(X))=\begin{cases}1 & Y \neq f(X)\\0 & Y=f(X)\end{cases}$；
  - 平方损失函数：$L(Y,f(X))=(Y-f(X))^2$；
  - Absolute loss function：$L(Y,f(X))=|Y-f(X)|$；
  - 对数损失函数/对数似然函数：$L(Y,P(Y|X))=-logP(Y|X)$；
- 期望风险 $R_{exp}(f)$ 与经验风险 $R_{emp}(f)$：
  - 期望风险：$R_{exp}(f)=E_p[L(Y,f(X))]=\int_{\mathcal X\times\mathcal Y}L(y,f(x))P(x,y)dxdy$；
    - 监督学习问题是一个病态问题（ill-formed problem）：由于联合分布 $P(X,Y)$ 是未知的，因此 $R_{exp}(f)$ 不能直接计算；
  - $R_{emp}(f)$：称为经验风险（Empirical risk）/经验损失（Empirical loss），模型 $f(X)$ 在训练集上的平均损失；
    - $R_{emp}(f)=\frac{1}{N}\sum_{i=1}^NL(y_i,f(x_i))$；
    - $R_{emp}(f)$ 定义在样本集上，因此可求；
    - 由大数定律，当样本容量N趋于无穷时，经验风险 $R_{emp}(f)$ 趋近于期望风险 $R_{exp}(f)$，因此可用 $R_{emp}(f)$ 估计 $R_{exp}(f)$；
- 经验风险最小化与结构风险最小化：
  - 经验风险最小化：认为 $R_{emp}(f)$ 最小的模型为最优模型；（样本容量较小时，容易过拟合）
  - 结构风险最小化：认为 $R_{srm}(f)$ 最小的模型为最优模型；
    - $R_{srm}(f)=\frac{1}{N}\sum_{i=1}^NL(y_i,f(x_i))+\lambda J(f),\lambda\geq0$；
      - $J(f)$：$J(f)$ 是定义在假设空间 $\mathcal F$ 上的泛函，表示模型的复杂度，模型 $f$ 越复杂，$J(f)$ 越大；
    - 结构风险最小化等价于正则化，在 $R_{emp}(f)$ 的基础上，加上正则化项或罚项（Penalty term）；
- 假设空间：所有可能的模型（函数）的集合；
- 特征空间：特征向量所在空间；
- 训练误差、测试误差与模型复杂度之间的关系（见下图）：
  - 因此需要使用结构风险最小化/正则化，避免模型过于复杂；
![训练误差、测试误差与模型复杂度之间的关系](https://img-blog.csdnimg.cn/20190401211248388.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==,size_16,color_FFFFFF,t_70#pic_center =400x270)

# 3. 生成模型与判别模型
- 监督学习方法可划分为：
  - 生成方法/生成模型；
    - 通过数据学习联合概率分布 $P(X,Y)$，然后求条件概率分布 $P(Y|X)$；
  - 判别方法/判别模型；
    - 通过数据直接学习决策函数 $f(X)$ 或条件概率分布 $P(Y|X)$；
    - 典型的判别模型：k近邻法、感知机、决策树、逻辑回归、最大熵模型、SVM、提升方法和条件随机场；
- 生成方法的特点：
  - 可以还原出联合概率分布 $P(X,Y)$，收敛速度更快；
  - 存在隐变量时，生成方法仍然使用；但此时判别方法不再适用；
- 判别方法的特点：
  - 直接学习决策函数 $f(X)$ 或条件概率分布 $P(Y|X)$，直面预测问题，准确率更高；
  - 可对数据进行各种程度上的抽象、定义特征、使用特征，有助于简化问题；

# 4. 监督学习
- 监督学习问题：
  - 分类：
    - 常见方法：k近邻法、感知机、朴素贝叶斯、决策树、决策列表、逻辑回归、SVM、提升方法、贝叶斯网络、神经网络、Winnow；
  - 标注：输入观测序列，输出标记序列；
    - 常见方法：隐马尔可夫模型、条件随机场；
  - 回归：等价于函数拟合；
    - 按输入变量：可划分为一元回归和多元回归；
    - 按输入与输出之间关系的类型（即模型的类型）：可划分为线性回归和非线性回归；
    - 回归问题中常用平方损失函数，由最小二乘法求解；

# 5. Generalization error bound (泛化误差上界)
- 泛化误差上界：
  - 用于比较不同模型之间的泛化能力强弱；
  - 当假设空间中仅包含有限个函数的情况下，可用如下方法求解泛化误差上界；
- 泛化误差上界的定义：
  - 对二分类问题，当假设空间为有限个函数的集合 $\mathcal{F}={f_1,f_2,\cdots,f_d}$ 时，对任意一个函数 $f\in \mathcal{F}$，至少以概率 $1-\delta$，有下式成立：
    - $R(f)\leq\widehat{R}(f)+\varepsilon(d,N,\delta)$，其中 $\varepsilon(d,N,\delta)=\sqrt{\frac{1}{2N}(\log_{}{d}+\log_{}{\frac{1}{\delta}})}$；
    - $R(f)$ 是泛化误差，$\widehat{R}(f)+\varepsilon(d,N,\delta)$ 为泛化误差上界；
    - $\widehat{R}(f)$ 为训练误差，训练误差越小，泛化误差也越小；
    - $\varepsilon(d,N,\delta)$：
      - 是关于 $N$ 的单调递减函数，当 $N$ 趋于无穷时，该值趋于0；
      - 假设空间中包含的函数越多，该值越大；
- 泛化误差上界的性质：
  - 是样本容量的函数，当样本容量增加时，泛化误差上界趋于0；
  - 是假设空间的函数，假设空间越大，通过学习的方法获得模型的过程越困难，泛化误差上界越大；

# References
 [1] https://www.coursera.org/learn/machine-learning/home/welcome
 [2] 李航. 统计学习方法[M]. 北京: 清华大学出版社, 2012.