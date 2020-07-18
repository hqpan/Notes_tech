[TOC]

# 版权声明

- Machine learning 系列笔记来源于Andrew Ng 教授在 Coursera 网站上所授课程 *Machine learning*[^1]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及交流讨论；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 转载请注明出处；

# 1. 在大规模数据集上应用梯度下降算法
- 处理大数据集的方法：
  - Stochastic gradient descent （随机梯度下降）；
  - Map reduce （映射化简）；

## 1.1 Stochastic gradient descent （随机梯度下降）
- Q：Batch gradient descent （批量梯度下降，即普通的随机梯度下降算法）与 Stochastic gradient descent （随机梯度下降）的区别？
  - 批量梯度下降：每次更新梯度值时，需要考虑所有的样本；
    - $\theta_j:=\theta_j-\alpha\frac{1}{m}\sum_{i=1}^m(h_\theta(x^{(i)})-y^{(i)})x^{(i)}_j$，当 m 较大时，计算量较大，因此该方法不适用于大规模数据集；
  - 随机梯度下降：每次更新梯度值时，只需考虑一个样本，运行速度快；
- 随机梯度下降算法的步骤：
  - Step 1：将所有样本顺序随机排列，确保按 Step 2 中每次读取样本的顺序是随机的，同时有助于加快算法收敛；
  - Step 2：
    - $cost(\theta,(x^{(i)},y^{(i)}))=\frac{1}{2}(h_\theta(x^{(i)})-y^{(i)})^2,J_{train}=\frac{1}{m}\sum_{i=1}^mcost(\theta,(x^{(i)},y^{(i)}))$；
      - 该步骤与普通的随机梯度下降算法相同；
    - 在两层循环`for i=1:m`、`for i=1:n`中，更新梯度 $\theta_j:=\theta_j-\alpha(h_\theta(x^{(i)})-y^{(i)})x^{(i)}_j$；
      - 该式中 $\frac{\partial}{\partial \theta_j}=cost(\theta,(x^{(i)},y^{(i)}))$；
      - 即每次读取一个样本，将所有特征参数更新一次；
      - 该方法在每次迭代时，代价函数不总是在减小，可能有些时候会增大，但最终将以迂回曲折的路径接近最小值；
      - Step 2 通常需要执行 1次，一般不超过 10 次；
  
## 1.2 Mini-Batch gradient descent （小批量梯度下降）
- Q：Batch gradient descent 、Stochastic gradient descent 与 Mini-batch gradient descent  之间的区别？
  A：
  - Batch gradient descent：每次更新梯度值时，需要考虑所有的样本；
  - Stochastic gradient descent：每次更新梯度值时，只考虑 1 个样本；
  - Mini-batch gradient descent：每次更新梯度值时，只考虑 b 个样本，$1<b<m$；
    - $\theta_j:=\theta_j-\alpha\frac{1}{b}\sum_{k=1}^b(h_\theta(x^{(k)})-y^{(k)})x^{(k)}_j$；
    - b 表示 batch，需要选择合适的参数 b，常见的取值范围为 2-100；
    - 当使用向量化的方法时，小批量梯度下降算法将比随机梯度下降算法速度更快；
  
## 1.3 随机梯度下降收敛
- 判断随机梯度下降是否收敛的步骤：
  - $cost(\theta,(x^{(i)},y^{(i)}))=\frac{1}{2}(h_\theta(x^{(i)})-y^{(i)})^2$；
  - 在使用 $(x^{(i)},y^{(i)})$ 更新 $\theta$ 之前，计算 $cost(\theta,(x^{(i)},y^{(i)}))$；
  - 例如，每 1000 次迭代中，求解 1000 个样本的 $cost(\theta,(x^{(i)},y^{(i)}))$ 的均值，并将所得值绘图；
    - 增大求均值的样本数量（即上例中的 1000）：
      - 优点：使曲线更加平滑，易于观察代价函数的变化趋势；
      - 缺点：增大求均值的样本数量，得到的关于算法的反馈信息有些延迟；

## 1.4 Online learning （在线学习）
- Online learning （在线学习）：数据集不是在算法运行之前全部准备好，而是以数据流的形式源源不断地提供给算法；
  - 算法依据当前批次的数据调整参数，此后将不再使用该组数据；
  - 可针对正在变化的用户偏好对模型做调整；

# 2. Map reduce and data parallelism
- Q：为什么引入 Map reduce 方法？
  A：
  - 为处理数据量较大的机器学习问题，需要使用多台计算机处理；
  - Map reduce 将一个较大的问题划分为多个小问题，分配给若干台计算机处理，然后将结果汇总；
  - 映射化简适用的情境：需要对样本求和；


# References
[^1]:https://www.coursera.org/learn/machine-learning/home/welcome