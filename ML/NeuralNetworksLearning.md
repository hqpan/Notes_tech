[TOC]

# 版权声明
- Machine learning 系列笔记来源于Andrew Ng 教授在 Coursera 网站上所授课程 *machine learning*[^1]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及交流讨论；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 转载请注明出处；

# 1. cost function and back propagation
## 1.1 cost function
- L：神经网络层数；
- $s_{l}$：第 i 层神经元数目（不包含bias unit）；
- $\{(x^{(1)},y^{(1)}),(x^{(2)},y^{(2)}),...,(x^{(m)},y^{(m)})\}$：m 个训练样本；
- 神经网络中逻辑回归的代价函数表达式：
- $J(\Theta)=-\frac{1}{m}[\sum_{i=1}^m\sum_{k=1}^Ky_k^{(i)}log(h_{\Theta}(x^{(i)}))_{k}+(1-y_k^{(i)})log(1-h_{\Theta}(x^{(i)}))_{k}]+\frac{\lambda}{2m}\sum_{l=1}^{L-1}\sum_{i=1}^{s_{l}}\sum_{j=1}^{s_{l+1}}(\Theta_{ji}^{(l)})^2$；
  - K：分类的类别数目，即输出单元数；
  - $h_{\Theta}(x)\epsilon \mathbb{R}^{K}$：one-vs-all 输出的特征向量是 K 维特征向量；
  - ${(h_{\Theta}(x))}_{i}$：特征向量中第 i 个元素；
  - $J(\Theta)$ 中累加的含义：
    - $\sum_{k=1}^K$：将每个输出单元的代价相加；
    - $\sum_{i=1}^m$：将每个样本的代价相加；
    - $\sum_{l=1}^{L-1}\sum_{i=1}^{s_{l}}\sum_{j=1}^{s_{l+1}}$：L 层参数平方累加，每层中所有参数 $\Theta_{ji}^{(l)}$ 累加；

## 1.2 back propagation algorithm
- 反向传播算法的作用：求 $\theta$；
  - 需要计算：
    - $J(\Theta)$；
    - $\frac{\partial }{\partial \Theta_{ij}^{(l)}}J(\Theta)$；
- Forward Propagation(FP)：计算每个神经元的激励值；
  Back Propagation(BP)：计算 $\frac{\partial }{\partial \Theta_{ij}^{(l)}}J(\Theta)$ 、误差值；
- $\delta_j^{(l)}$：第 $l$ 层第 $j$ 个节点上的误差；
  - 令 $cost(t)=y^{(t)}log(h_{\Theta}(x^{(t)}))+(1-y^{(t)})log(1-h_{\Theta}(x^{(t)})),\delta_j^{(l)}=\frac{\partial }{\partial z_j^{(l)}}cost(t)$；
  - 对 log() 形状的代价函数求倒数， 由函数曲线图知，斜率越大，正确率越低；
- 反向传播算法计算示例：
![四层神经网络结构示意图](https://img-blog.csdnimg.cn/20190106130141694.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==,size_16,color_FFFFFF,t_70)
  - 向量化的表示（此处暂未考虑正则化项）：（==公式待推导，参见西瓜书==）
    - $\delta^{(4)}=a^{(4)}-y$；
    - $\delta^{(3)}=(\Theta^{(3)})^{T}\delta^{(4)}.*g^{'}(z^{(3)})$；
    - $\delta^{(2)}=(\Theta^{(2)})^{T}\delta^{(3)}.*g^{'}(z^{(2)})$；
    - $\delta^{(1)}$：不存在该项，因为输入项没有误差；
      - 对 sigmoid 函数 $g(z)$ 求导知：$g^{'}(z)=\frac{\text{d}}{\text{d}z}g(z)=g(z)(1-g(z))$；
        - $g(z)$、$(1-g(z))$ 之间为对应元素相乘；
      - $g^{'}(z^{(l)})=a^{(l)}.*(1-a^{(l)})$；
    - 若 $j=0$，$D_{ij}^{(l)}=\frac{1}{m}\Delta_{ij}^{(l)}$；
      若 $j\neq0$，$D_{ij}^{(l)}=\frac{1}{m}\Delta_{ij}^{(l)}+\frac{\lambda}{m} \Theta_{ij}^{(l)}$；
      - $D_{ij}^{(l)}=\frac{\partial }{\partial \Theta_{ij}^{(l)}}J(\Theta)$；
      - $\Delta_{ij}^{(l)}=a_j^{(l)}\delta_i^{(l+1)}$；

# 2. gradient checking
- 有时 $J(\theta)$ 虽然随迭代次数的增加而下降，但仍存在 bug，采用 gradient checking 的方法予以验证；
- 单边导数与双边倒数：
  - 双边导数：$\frac{\text{d}}{\text{d}\theta}J(\theta)\approx\frac{J(\theta+\epsilon)-J(\theta-\epsilon)}{2\epsilon}$；
    - $\frac{\text{d}}{\text{d}\theta_{j}}J(\theta)\approx\frac{J(\theta_{1},...,\theta_{j}+\epsilon,...,\theta_{n})-J(J(\theta_{1},...,\theta_{j}-\epsilon,...,\theta_{n})}{2\epsilon}$；
  - 单边导数：$\frac{\text{d}}{\text{d}\theta}J(\theta)\approx\frac{J(\theta+\epsilon)-J(\theta)}{\epsilon}$；
  - 双边导数的精度高于单边导数，一般使用双边导数求解近似的代价函数的偏导数值；
  - 为平衡计算精度和计算量，一般取 $\epsilon=10^{-4}$；
- gradient checking：使用求解的近似代价函数的偏导数与反向传播算法求解的偏导数作比较，检验反向传播的过程中是否出现了 bug；
- 由于 gradient checking 运行速度较慢，在检查 back propagation 没有错误后，在开始训练分类器之前，关闭 gradient checking ，以提高运行速度；

# 3. 随机初始化参数值
- 对神经网络初始化，不能采用类似于逻辑回归初始化的方法，即不能取参数初始值为0；
  - 若将所有参数初始化为0，通过反向传播算法，求解每个隐层中所有单元误差值都相同，出现高度冗余现象；（==推导反向传播算法，重新理解这句话，并修改这句话==）
  - 随机初始化的作用：打破对称性（symmetry breaking）；
- 生成 $[-x,x]$ 区间内随机数的方法：
  - ```Theta = rand(10, 11)*(2*x) - x```；
  - 其中 rand 函数生成 0-1 之间的随机数矩阵；
  - 通常将权重初始化为很小的值，接近于0；
  - 选择 $x$ 值的有效方法之一：$x=\frac{\sqrt{6}}{\sqrt{s_{l}+s_{l+1}}}$；
    - $s_{l}$、$s_{l+1}$ 为 $\Theta^{(l)}$ 前后两层的单元数；

# 4. putting it together
## 4.1 设计神经网络结构
- 输入单元数量：为特征数量；
  输出单元数量：待分类的类别数；
- 设计神经网络结构的默认规则：
  - 默认使用含单个隐层的网络；
  - 若使用含多个隐层的网络，则各个隐层的单元数量应相同；
  - 隐藏层数越多，效果越好，但计算量也越大；
  - 每层中隐藏单元数目要大于输入特征数目 n；

## 4.2 训练神经网络
- 训练神经网络的步骤：
  - 随机初始化权重参数；
  - 执行前向传播算法求解 $h_{\Theta}(x^{(i)})$；
  - 计算代价函数；
  - 执行反向传播算法求解 $\frac{\partial }{\partial \Theta_{jk}^{(l)}}J(\Theta)$；
  - 梯度检查：将使用数值估计方法得到的代价函数的偏导数，与使用反向传播算法得到的代价函数的偏导数比较，确保反向传播算法运行正确；
    - 然后关闭梯度检查算法；
- 使用梯度下降算法或更加高级的算法，配合反向传播算法求解权重参数的值；
- end；
---
- 神经网络的代价函数是非凸函数，梯度下降算法或更加高级的算法将收敛于局部最小值；
- 反向传播算法能够让更复杂的、维度更大的、非线性的函数模型与数据拟合；

# References
[^1]:https://www.coursera.org/learn/machine-learning/home/welcome