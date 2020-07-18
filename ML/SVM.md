@[TOC]

# 版权声明
- machine learning 系列笔记来源于Andrew Ng 教授在 Coursera 网站上所授课程《machine learning》[^1]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及交流讨论；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 转载请注明出处；

# 1. 大间距分类器
## 1.1 最小化代价函数
- SVM：Support vector machine（支持向量机）；
  - 用于学习复杂的非线性方程；
  - 是一种监督学习算法；
  - SVM 优化问题是一种凸优化问题；
- 使用 SVM 的惯例：
  - SVM 表达式中没有 $\frac{1}{m}$ 这一项，即代价函数只是求解所有样本的误差之和，而不除以 m 取所有样本的误差均值；
    - 去除常数项，不影响最小化代价函数求解出的 $\theta$；
  - 在逻辑回归中 $A+\lambda B$，给 $\lambda$ 赋较大值，防止过拟合；
    在 SVM 中 $CA+B$，给正则化参数 C 赋较大值，防止过拟合； 
    若取 $C=\frac{1}{\lambda}$，则以上两种方式优化后得到的 $\theta$ 相同；
  - C值不宜过大，避免由于少数 outliers 导致决策边界发生剧烈变化；
- SVM 的代价函数：
  $J(\theta)=\min \limits_{\theta}C\sum_{i=1}^m[y^{(i)}cost_{1}(\theta^{T}x^{(i)})+(1-y^{(i)})cost_{0}(\theta^{T}x^{(i)})]+\frac{1}{2}\sum_{i=1}^n\theta_j^2$；
  - $cost_{0}(\theta^{T}x^{(i)})$ 用于取代逻辑回归中的 $-log(1-h_{\theta}(x^{(i)}))$；
  - $cost_{1}(\theta^{T}x^{(i)})$ 用于取代逻辑回归中的 $-log(h_{\theta}(x^{(i)}))$；
    - cost 的下标代表 $y^{(i)}$ 的取值；
- SVM 的决策边界：
  - 下图分别为 y=0、y=1 时的代价函数 $cost_{0}(\theta^{T}x^{(i)}),cost_{1}(\theta^{T}x^{(i)})$，用于取代 sigmoid 函数；
  - 若 $y^{(i)}=0$，有 $\theta^{T}x^{(i)}\leq-1$；
    若 $y^{(i)}=1$，有 $\theta^{T}x^{(i)}\geq1$；    
![y=0](https://img-blog.csdnimg.cn/20190121223745782.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==,size_16,color_FFFFFF,t_70)
![y=1](https://img-blog.csdnimg.cn/20190121223718557.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==,size_16,color_FFFFFF,t_70)

## 1.2 large margin classification 的数学原理
- SVM 具有较好的鲁棒性；
  - 因为其努力使用较大间距将正负样本分离；
- ==由线性代数理论知==，决策边界与向量 $\theta$ 垂直；
- 向量内积：
  - 若 $\overrightarrow{u}=\begin{bmatrix}u_{1}\\u_{2} \end{bmatrix}，\overrightarrow{v}=\begin{bmatrix}v_{1}\\v_{2} \end{bmatrix}$，由范数定义有$||u||=\sqrt{u_1^2+u_2^2}$，则 $\overrightarrow{u}\cdot\overrightarrow{v}=u^{T}v=p\cdot||u||$；
    - 其中 p 指 $\overrightarrow{v}$ 在 $\overrightarrow{u}$ 上的投影长度，p值可正可负，取决于两向量的夹角；
    - 即内积在数值上等于一个向量在另一个向量上的投影长乘以该向量的范数；
  - 由向量内积的数学原理推得，$\min \limits_{\theta}\frac{1}{2}\sum_{j=1}^n\theta_j^2=\frac{1}{2}||\theta||^{2}$；
  - 若 $y^{(i)}=1$，则 $p^{(i)}\cdot||\theta||\geq1$；
    若 $y^{(i)}=0$，则 $p^{(i)}\cdot||\theta||\leq-1$；
    此处的 $p^{(i)}$ 是 $x^{(i)}$ 在向量 $\theta$ 上的投影；
  - 由于 $\min \limits_{\theta}\frac{1}{2}\sum_{j=1}^n\theta_j^2=\frac{1}{2}||\theta||^{2}$，所以  $||\theta||$ 应取较小值，为使 $p^{(i)}\cdot||\theta||\geq1$，$p^{(i)}\cdot||\theta||\leq-1$，则 $p^{(i)}$ 的绝对值应取较大值，即 $x^{(i)}$ 在向量 $\theta$ 上的投影要尽可能长，即训练样本到决策边界的距离较大；
- 为简化为问题，此处取 $\theta_{0}=0$；
  - $\theta_{0}=0$ 时，决策边界过原点；
  
# 2. kernels（核函数）
## 2.1 kernels 相关概念
- Q：为什么有些情况下不宜使用高阶项作为特征变量？
  A：若输入为图像的像素，使用高阶项作为特征变量，运算量较大，因此需要构造新的特征变量；
- 相似度函数（此处选用的相似度函数为高斯核函数）：
  - 手动取 landmarks（标记点） $l^{(1)},l^{(2)},l^{(3)}$；
  - 用相似度函数，构建三个对应的特征：
    $f_{1}=Similarity(x,l^{1}）=e^{-\frac{||x-l^{(1)}||^{2}}{2\sigma^{2}}}=e^{-\frac{\sum_{j=1}^n{(x_{j}-l_j^{(1)})}^{2}}{2\sigma^{2}}}$；
    $f_{2}=Similarity(x,l^{2}）=e^{-\frac{||x-l^{(2)}||^{2}}{2\sigma^{2}}}$；
    $f_{3}=Similarity(x,l^{3}）=e^{-\frac{||x-l^{(3)}||^{2}}{2\sigma^{2}}}$；
    可将核函数简记为 $k(x,l^{(i)})$，$\sigma^{2}$ 是高斯核函数的参数；
  - 若 $\theta_{0}+\theta_{1}f_{1}+\theta_{2}f_{2}+\theta_{3}f_{3}\geq0$，则预测 y=1；
    若 $\theta_{0}+\theta_{1}f_{1}+\theta_{2}f_{2}+\theta_{3}f_{3}<0$，则预测 y=0；
  - 若 x 离 $l^{(1)},l^{(2)},l^{(3)}$ 较近，则 $f_{i}$ 趋近于1；
    若 x 离 $l^{(1)},l^{(2)},l^{(3)}$ 较远，则 $f_{i}$ 趋近于0；
    - $f_{i}$ 的函数图像示例：
      - 若 $l^{(1)}=\begin{bmatrix}3 \\5 \end{bmatrix}$ 为二维向量，将样本点 x 的两个分量分别记为 $x_{1},x_{2}$，则有 $f_{1}$ 函数图像如下：
      - 若样本点与 $l^{(1)}$ 趋于重合，则 $f_{i}$ 趋近于1；
        若样本点远离 $l^{(1)}$ ，则 $f_{i}$ 趋近于0；
  ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190122191948343.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==,size_16,color_FFFFFF,t_70#pic_center)    
      - 若减小 $\sigma^{2}$ ，则 $f_{i}$ 从1下降到0的趋势将加快；
        若增大 $\sigma^{2}$ ，则 $f_{i}$ 从1下降到0的趋势将减缓；
![减小sigma](https://img-blog.csdnimg.cn/20190122194301249.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==,size_16,color_FFFFFF,t_70#pic_center)
![增大sigma](https://img-blog.csdnimg.cn/2019012219434262.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==,size_16,color_FFFFFF,t_70#pic_center)
- SVM 选取 kernels 的方法：
  - 将所有样本点均选定为标记点，即 $l^{(1)}=x^{(1)},l^{(2)}=x^{(2)},...,l^{(m)}=x^{(m)}$；
  - 对应可得相似度函数：
    $f_{1}=Similarity(x,l^{(1)})$；
    $f_{2}=Similarity(x,l^{(2)})$；
    ...
  - 取 $f_{0}=1$，有 $\begin{bmatrix}f_{0} \\f_{1} \\f_{2} \\ \vdots \\f_{m} \end{bmatrix}$；
- 用于描述第 i 个样本 $x^{(i)}$ 的特征向量，$f \in \mathbb{R}^{m+1}$：
  - $f^{(i)}=\begin{bmatrix}f_0^{(i)} \\f_1^{(i)} \\f_2^{(i)} \\ \vdots \\f_m^{(i)} \end{bmatrix}=\begin{bmatrix}1 \\Similarity(x^{(i)},l^{(1)}) \\Similarity(x^{(i)},l^{(2)})\\ \vdots\\ \\Similarity(x^{(i)},l^{(m)})\end{bmatrix}$；
  - 衡量第 i 个样本与其它样本的远近程度；
  - 其中 $f_i^{(i)}=1$；

## 2.2 SVM
- 使用 SVM 的过程：
  - Hypothesis：假设已知参数 $\theta$，
    - 当 $\theta^{T}f=\theta_{0}f_{0}+\theta_{1}f_{1}+\dots+\theta_{m}f_{m}\geq0$，预测 y=1；
    - 当 $\theta^{T}f=\theta_{0}f_{0}+\theta_{1}f_{1}+\dots+\theta_{m}f_{m}<0$，预测 y=0；
    - 此处样本数量记为 m，则有样本特征个数 n=m；
    - 即样本数量与特征数量相同，因此在此处正则化项中 $\sum_{j=1}^n$ 等价于 $\sum_{j=1}^m$；
  - Training：
    - 将此前代价函数 $J(\theta)=\min \limits_{\theta}C\sum_{i=1}^m[y^{(i)}cost_{1}(\theta^{T}x^{(i)})+(1-y^{(i)})cost_{0}(\theta^{T}x^{(i)})]+\frac{1}{2}\sum_{i=1}^n\theta_j^2$ 中的样本 $x^{(i)}$ 替换为新的特征 $f^{(i)}$；
    - 可得新的代价函数 $J(\theta)=\min \limits_{\theta}C\sum_{i=1}^m[y^{(i)}cost_{1}(\theta^{T}f^{(i)})+(1-y^{(i)})cost_{0}(\theta^{T}f^{(i)})]+\frac{1}{2}\sum_{i=1}^n\theta_j^2$ 中的样本 $x^{(i)}$ 替换为新的特征 $f^{(i)}$；
    - 通过最小化代价函数求解参数 $\theta$；
- 如何调整支持向量机的参数：
  - 正则化参数 C（作用类似于 $\frac{1}{\lambda}$）：
    - Large C: Lower bias, high variance;
      Small C: Higher bias, low variance;
  - $\delta^{2}$：
    - Large $\delta^{2}$：特征 $f_{i}$ 变化将变得平滑，高偏差，低方差；
      Small $\delta^{2}$：特征 $f_{i}$ 变化将变得不平滑，低偏差，高方差；
    - 注：若 $\delta^{2}$ 较小，则预测值稍微远离样本点时，代价迅速增大，为了使代价函数最小化，模型将对训练集有较好的拟合，因此出现低偏差、高方差现象；（==这句话未经核实，待修改完善==）
- Q：为什么核函数等思想未用于逻辑回归等算法中？
  A：尽管核函数的思想可用于逻辑回归算法中，但 SVM 相关的计算技巧、高级优化技巧是专门为 SVM 开发的，逻辑回归和核函数一起运行时速度缓慢，不宜搭配使用；

# 3. SVM in practice
## 3.1 SVM 处理二分类问题
- 现有许多优秀的 SVM software package (e.g. liblinear, libsvm)，因此不建议自行编写代码求解参数 $\theta$；
- 需要指明：
  - 选择参数 C；
  - 选择 kernel (similarity function)；
    - No kernel (linear kernel)，即当 $\theta^{T}x\geq0$ 时，预测 y=1；
      即为一个线性分类器；
    - Gaussian kernel（高斯核函数）：若样本数值差异较大，则在使用高斯核函数之前应使用均值归一化；
      - 使用高斯核函数的 SVM 在样本数量较多时，运行速度较慢；
    - 其它核函数；
      - 在 SVM 最初的设想中，为了能够使用巧妙的数值优化技巧，快速求解参数，约定只有满足 Mercer's Theorem 的相似度函数才能作为核函数；

- Q：什么情况下适合使用 linear kernel？
  A：若特征变量较多，样本数量较少，则不宜使用核函数（或称之为“使用线性核函数”），防止在高维空间拟合出非常复杂的函数，导致过拟合；
- Q：什么情况下适合使用 Gaussian kernel？
  A：若特征变量较少，样本数量较多，则可用高斯核函数拟合更复杂的非线性判定边界；
- Q：如何判断在 SVM 中哪一组 C、$\delta^2$ 参数更加合适？
  A：根据不同组参数在交叉验证集上的表现加以判断；

## 3.2 SVM 处理多分类问题
- 多分类问题处理步骤：
  - 对 K 分类问题，训练 K 个 SVMs；
  - 对应得到 K 组参数，$\theta^{(1)},\theta^{(2)},...,\theta^{(k)}$，其中 $\theta^{(i)}$表示将第 i 类与其它类区分开的参数值；
  - 求 K 组 ${(\theta^{(i)})}^Tx$ ，取最大值对应的类别，作为预测类别；

## 3.3 选取恰当的分类方法
- 若特征数量很大，样本数量较小，则应使用逻辑回归或没有核函数的 SVM；
- 若特征数量较小，样本数量适中，则应使用带有高斯核函数的 SVM；
- 若特征数量较小，样本数量较大，则应构建或添加新的特征，然后使用逻辑回归或没有核函数的 SVM；
- 神经网络在上述各种情况中都可能有较好效果，但训练速度慢；
  - 神经网络的运行速度可能会比 SVM 慢，尤其是当特征数量较小，样本数量适中时；

# References

[^1]:https://www.coursera.org/learn/machine-learning/home/welcome