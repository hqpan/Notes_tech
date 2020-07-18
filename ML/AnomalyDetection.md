[TOC]

# 版权声明
- Machine learning 系列笔记来源于Andrew Ng 教授在 Coursera 网站上所授课程《Machine learning》[^1]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及交流讨论；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 转载请注明出处；

# 1. 异常检测原理
## 1.1 Symbol
- $p(x)$：随机变量 x 的概率分布；
  $p(x;\mu,\sigma^2)$：服从正态分布的随机变量 x 的概率分布，其均值为$\mu$，方差为 $\sigma^2$；
  $\mu_i$：随机变量 $x$ 第 i 个特征的均值；
## 1.2 Gaussian(Normal) distribution
- 高斯分布：即正态分布，服从该分布的随机变量 $x$ 均值为 $\mu$，方差为 $\sigma^2$，记为 $x\sim N(\mu,\sigma^2)$；
  - N 表示 Normal distribution；
  - 标准差 $\sigma$ 决定高斯分布概率密度函数的宽度；

## 1.3 异常检测算法
- 密度估计问题：即求解 $p(x)$；
  - 设随机变量 $x$ 有多个特征 $x_1,x_2,\dots,x_n$；
  - $p(x)=p(x_1;\mu_1,\sigma^2_1)p(x_2;\mu_2,\sigma^2_2)\dots p(x_n;\mu_n,\sigma^2_n)=\prod_{j=1}^np(x_j;\mu_j,\sigma^2_j)$；
>上式的前提为特征 $x_1,x_2,\dots,x_n$ 均独立，即使不满足该前提时，使用该式计算所得结果依然很好（语出 Andrew Ng 《Machine learning》 Week 9 视频：Algorithm 2:19 秒）；

- 异常检测算法的步骤：
  - Step 1：选取若干特征 $x_1,x_2,\dots,x_n$；
  - Step 2：拟合期望与方差；
    - $\mu_i=\frac{1}{m}\sum_{i=1}^mx^{(i)}_j$，向量化可得 $\mu=\begin{bmatrix}\mu_1 \\\mu_2 \\\vdots \\\mu_n \end{bmatrix}=\frac{1}{m}\sum_{i=1}^mx^{(i)}$；
    - $\sigma^2_j=\frac{1}{m}\sum_{i=1}^m(x^{(i)}_j-\mu_j)^2$，向量化可得 $\sigma^2=\frac{1}{m}\sum_{i=1}^m(x^{(i)}-\mu)^2$；
   - Step 3：对给定的新样本 $x$ ，计算 $p(x)$；
     - $p(x)=\prod_{j=1}^np(x_j;\mu_j,\sigma^2_j)=\prod_{j=1}^n\frac{1}{\sqrt{2\pi}\sigma_j}exp(-\frac{(x_j-\mu_j)^2}{2\sigma^2_j})$；
     - 取阈值为 $\epsilon$，若 $p(x)<\epsilon$，则新样本 $x$ 为异常点；

# 2. 构建异常检测系统
## 2.1 如何评价一个异常检测系统
- 异常检测系统的评价算法：
  - Step 1：将带标签的样本划分为训练集、交叉验证集和测试集，样本划分时使假设训练集中无异常样本，交叉验证集和测试集中有异常样本；
    - 样本划分案例：数据集中有 10000 个正常样本，20 个异常样本，则将其划分为训练集中有 6000 个正常样本，交叉验证集中有 2000 个正常样本和 10 个异常样本，测试集中有 2000 个正常样本和 10 个异常样本；
    - 训练集中正常样本数量较多，有利于拟合高斯分布中的参数；
  - Step 2：在训练集中拟合模型 $p(x)$ 及参数 $\mu_1,\sigma_1,\mu_2,\sigma_2,\dots,\mu_n,\sigma_n$；
    - 一般而言，异常检测问题中，正常样本数量远多于异常样本数量，因此评价偏斜类问题时，使用 $F_1$-score 作为判断依据；
    - 在交叉验证集中，使用多个 $\epsilon$，使 $F_1$-score 最大的 $\epsilon$ 即为最合适的阈值，也使用该方式决定选择那些特征；
  - Step 3：在测试集中评价算法；

## 2.2 异常检测与监督学习的差异
- Q：在异常检测系统中使用了带标签的样本，为什么不使用监督学习的方法解决该问题？
  A：
  - 异常检测方法的适用场景：
    - 正常样本较多，异常样本较少；
    - 异常的种类较多，难以学到所有的异常类型，处理此前从未见过的异常类型；（导致引擎故障的原因有很多，算法难以通过较少的样本，学到所有可能导致引擎故障的类型，也难以处理从未见过的新的异常类型）
  - 监督学习方法的适用场景：
    - 正常样本和异常样本均较多；
    - 算法能够通过数量足够的样本学到故障类型，判断新样本时仅需将其与已有的故障类型对应即可；

## 2.3 调整样本分布
- Q：如果样本不服从 Gaussian distribution 如何处理？
  A：
  - 异常检测算法中使用高斯分布建模，画出样本的各个特征数据或用直方图表示，将各特征数据调整至接近高斯分布；
    - 即使特征不服从高斯分布，算法通常也可以得到较好的结果；（语出 Andrew Ng 《Machine learning》 Week 9 视频：Choosing What Feature to Use  0:50 秒）
  - e.g. 对特征 $x_1$，可使用 $log(x_1),log(x_1+c),\sqrt[c]{x_1}$ 等替换 $x_1$，从而使特征分布接近高斯分布；
![使特征分布接近高斯分布](https://img-blog.csdnimg.cn/20190217162609309.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==,size_16,color_FFFFFF,t_70#pic_center)

# 3. 多元高斯分布
## 3.1 多元高斯分布的概念
- 多元高斯分布不分别对特征 $x_1,x_2,\dots,x_n$ 建模，而是将 $p(x)$ 作为一个整体建模，可以描述特征之间的相关性；
  - 若将各个特征单独分析，则有可能导致误判，例如下图中的绿色样本点；
  - 一元高斯分布会将某个同心圆上的所有样本点视为有相同的异常概率，而下图中红色样本点的实际分布并非如此；
  - 多元高斯分布通过调整协方差矩阵中各元素的值，使等高线变成椭圆；
![多元高斯分布的优点](https://img-blog.csdnimg.cn/20190217183933576.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==,size_16,color_FFFFFF,t_70#pic_center)  
- 多元高斯分布的概率密度估计模型：$p(x;\mu,\Sigma)=\frac{1}{(2\pi)^\frac{n}{2}|\Sigma|^{\frac{1}{2}}}exp(-\frac{1}{2}(x-\mu)^T\Sigma^{-1}(x-\mu))$，积分值为1；
  - $\mu\in\mathbb{R}^n,\Sigma\in\mathbb{R}^{n\times n}$，此处的 $\Sigma$ 为协方差矩阵；
  - $|\Sigma|$ 表示求协方差矩阵行列式的值；

## 3.2 使用多元高斯分布做异常检测
- 多元高斯分布应用于异常检测的步骤：
  - Step 1：拟合模型 $p(x)$，即多元高斯分布的参数估计：
    - $\mu=\frac{1}{m}\sum_{i=1}^mx^{(i)}$；
    - $\Sigma=\frac{1}{m}\sum_{i=1}^m(x^{(i)}-\mu)(x^{(i)}-\mu)^T$；
  - Step 2：对给定的新样本，计算多元高斯分布的概率密度函数：
    - $p(x;\mu,\Sigma)=\frac{1}{(2\pi)^\frac{n}{2}|\Sigma|^{\frac{1}{2}}}exp(-\frac{1}{2}(x-\mu)^T\Sigma^{-1}(x-\mu))$；
    - 若 $p(x)<\epsilon$ 则表示新样本异常；

## 3.3 一元高斯分布与多元高斯分布
### 3.3.1 一元高斯分布与多元高斯分布之间的关系
- 一元高斯分布：$p(x)=p(x_1;\mu_1,\sigma^2_1)p(x_2;\mu_2,\sigma^2_2)\dots p(x_n;\mu_n,\sigma^2_n)$；
  - 概率密度函数的等高线沿轴向（$x_1,x_2$ 所在坐标轴）分布；
- 多元高斯分布：$p(x;\mu,\Sigma)=\frac{1}{(2\pi)^\frac{n}{2}|\Sigma|^{\frac{1}{2}}}exp(-\frac{1}{2}(x-\mu)^T\Sigma^{-1}(x-\mu))$；
  - 概率密度函数的等高线可沿任意方向；
  - 当 $\Sigma=\begin{bmatrix}\sigma^2_1 & & & & \\& \sigma^2_2 & & \\& & \ddots \\ & & & \sigma^2_n\end{bmatrix}$，即主对角线以外的元素为 0 时，多元高斯分布即等同于一元高斯分布；

### 3.3.2 一元高斯分布与多元高斯分布的适用场景
- 一元高斯分布：
  - 适用于构建少量新的特征变量用于捕获异常；
  - 该方法计算量较小，适用于特征数量较多的场景；
  - 训练样本较少时也有较好效果；
- 多元高斯分布：
  - 适用于自动捕获特征之间的相关性，避免手动构建新特征变量的繁琐；
  - 该方法计算量较大，不适用于特征数量较多的场景；
  - 必须满足 $m>n$ 否则 $\Sigma$ 不可逆；
    - 即确保有足够的数据用于拟合参数；
    - 经验法则：$m>10n$ 时使用该方法较为合理；

# 4. 推荐系统
- 构造推荐系统的方法：
  - 基于内容的推荐：已知样本特征，求参数；
  - Collaborative filtering (协同过滤)：也被称为 Low rank matrix factorization (低秩矩阵分解)，样本特征、参数均未知，随机初始化后求解；

## 4.1 用公式描述推荐系统问题
- 在预测观众对电影的评分案例中用到的符号：
  - $n_u$：用户数量；
  - $n_m$：电影数量；
  - $m^{(j)}$：用户评价过的电影数量；
  - $r(i,j)$：若用户 j 对电影 i 有评分记录，则该值为1，否则该值为0；
  - $y_{(i,j)}$：用户 j 对电影 i 的评分，评分值为1-5星，该变量仅在 $r(i,j)=1$ 时有定义；
  - $Y$：由 $y_{(i,j)}$ 组成的 $n_m\times n_u$ 矩阵；
- 基于内容的推荐系统：
  - 将观众对每个电影的评分作为回归问题，$x^{(i)}$ 表示第 i 部电影的特征向量（包含偏置项），$\theta^{(j)}$ 表示第 j 位观众的参数向量，$(\theta^{(j)})^Tx^{(i)}$ 即为第 j 位观众对第 i 部电影的评分预测值；
  - 优化目标（即学得 $\theta^{(j)}$ 的过程）：
    - 类似于线性回归问题有：$\min\limits_{\theta^{(j)}}\frac{1}{2m^{(j)}}\sum_{i:r(i,j)=1}((\theta^{(j)})^Tx^{(i)}-y^{(i,j)})^2+\frac{\lambda}{2m^{(j)}}\sum_{k=1}^n(\theta^{(j)}_k)^2$；
      - 能使该代价函数最小的 $\theta^{(j)}$ 即为所求；
      - 上式中求和符号表示将满足 $r(i,j)=1$ 的所有 i 值相加；
    - 为简化表达式，将常数 $\frac{1}{m^{(j)}}$ 去除，不影响优化结果；
      - 对第 j 位观众有，$J(\theta^{(1)},\cdots,\theta^{(n_u)})=\min\limits_{\theta^{(j)}}\frac{1}{2}\sum_{i:r(i,j)=1}((\theta^{(j)})^Tx^{(i)}-y^{(i,j)})^2+\frac{\lambda}{2}\sum_{k=1}^n(\theta^{(j)}_k)^2$；
      - 对所有观众有，$J(\theta^{(1)},\cdots,\theta^{(n_u)})=\min\limits_{\theta^{(1)},\cdots,\theta^{(n_u)}}\frac{1}{2}\sum_{j=1}^{n_u}\sum_{i:r(i,j)=1}((\theta^{(j)})^Tx^{(i)}-y^{(i,j)})^2+\frac{\lambda}{2}\sum_{j=1}^{n_u}\sum_{k=1}^n(\theta^{(j)}_k)^2$； 
  - 代价函数的梯度：
    - $\frac{\partial J}{\partial x^{(i)}_k}=\sum_{j:r(i,j)=1}((\theta^{(j)})^Tx^{(i)}-y^{(i,j)})\theta^{(j)}_k+\lambda x^{(i)}_k$；
    - $\frac{\partial J}{\partial \theta^{(j)}_k}=\sum_{i:r(i,j)=1}((\theta^{(j)})^Tx^{(i)}-y^{(i,j)})x^{(i)}_k+\lambda \theta^{(j)}_k$； 
  - 梯度更新：
    - 当 $k=0$ 时，$\theta^{(j)}_k:=\theta^{(j)}_k-\alpha\sum_{i:r(i,j)=1}((\theta^{(j)})^Tx^{(i)}-y^{(i,j)})x^{(i)}_k$；
    - 当 $k\neq0$ 时，$\theta^{(j)}_k:=\theta^{(j)}_k-\alpha(\sum_{i:r(i,j)=1}((\theta^{(j)})^Tx^{(i)}-y^{(i,j)})x^{(i)}_k+\lambda\theta^{(j)}_k)$；

## 4.2 协同过滤
- Q：为什么使用协同过滤？
  A：基于内容的推荐系统需要选取特征，但选取特征的过程较为困难，而协同过滤算法能够自动学习需要使用的特征；
- 协同过滤求需要使用的特征：
  - 求某一部电影的特征 $x^{(i)}$：$\min\limits_{x^{(i)}}\frac{1}{2}\sum_{j:r(i,j)=1}((\theta^{(j)})^Tx^{(i)}-y^{(i,j)})^2+\frac{\lambda}{2}\sum_{k=1}^n(x^{(i)}_k)^2$，使该代价函数最小的 $x^{(i)}$ 即为所求；
  - 求所有电影的特征 $x^{(1)},\cdots,x^{(n_m)}$：$\min\limits_{x^{(1)},\cdots,x^{(n_m)}}\frac{1}{2}\sum_{i=1}^{n_m}\sum_{j:r(i,j)=1}((\theta^{(j)})^Tx^{(i)}-y^{(i,j)})^2+\frac{\lambda}{2}\sum_{i=1}^{n_m}\sum_{k=1}^n(x^{(i)}_k)^2$；
- 协同过滤算法的优化目标：
  - 将求解参数 $\theta^{(j)}$ 和特征 $x^{(i)}$ 结合为一个代价函数表示：$J(x^{(1)},\cdots,x^{(n_m)},\theta^{(1)},\cdots,\theta^{(n_u)})=\frac{1}{2}\sum\limits_{(i,j):r(i,j)=1}((\theta^{(j)})^Tx^{(i)}-y^{(i,j)})^2+\frac{\lambda}{2}\sum_{i=1}^{n_m}\sum_{k=1}^n(x^{(i)}_k)^2+\frac{\lambda}{2}\sum_{j=1}^{n_u}\sum_{k=1}^n(\theta^{(j)}_k)^2$； 
  - 此处 $x,\theta\in\mathbb{R}^n$，即均不含有偏置项，若算法需要一个恒为1的参数值，可通过学习得到，而不需要人为设置这样一个参数；
  - Q：为什么在神经网络中需要偏置项？
    A：神经网络中需要偏置项设置激活值，而此处拟合所有参数不需要（phq）；
- 梯度更新：
  - $x^{(i)}_k:=x^{(i)}_k-\alpha(\sum_{j:r(i,j)=1}((\theta^{(j)})^Tx^{(i)}-y^{(i,j)})\theta^{(j)}_k+\lambda x^{(i)}_k)$；
  - $\theta^{(j)}_k:=\theta^{(j)}_k-\alpha(\sum_{i:r(i,j)=1}((\theta^{(j)})^Tx^{(i)}-y^{(i,j)})x^{(i)}_k+\lambda\theta^{(j)}_k)$；
- 协同过滤算法：
  - Step 1：将 $x^{(1)},\cdots,x^{(n_m)}，\theta^{(1),\cdots,\theta^{(n_u)}}$ 随机初始化为较小值；
  - Step 2：使用梯度下降或高级优化算法求解 $x^{(1)},\cdots,x^{(n_m)}，\theta^{(1),\cdots,\theta^{(n_u)}}$ ；
  - Step 3：用 $\theta^Tx$ 预测新样本的标签值；

## 4.3 协同过滤算法的向量化实现

- 预测结果 $=X\Theta^T$；
  - 其中 $X=\begin{bmatrix}- & (x^{(1)})^T & - \\- & (x^{(2)})^T & -  \\ & \vdots & \\- & (x^{(n_m)})^T & -  \end{bmatrix},\Theta=\begin{bmatrix}- & (\theta^{(1)})^T & - \\- & (\theta^{(2)})^T & -  \\ & \vdots & \\- & (\theta^{(n_u)})^T & -  \end{bmatrix}$；


# References
[^1]:https://www.coursera.org/learn/machine-learning/home/welcome