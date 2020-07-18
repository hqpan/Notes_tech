[TOC]

# 版权声明

- machine learning 系列笔记来源于Andrew Ng 教授在 Coursera 网站上所授课程《machine learning》[^1]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及交流讨论；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 转载请注明出处；

# 1. 评估学习算法

## 1.1 评估假设函数
- 一般按照 7:3 的比例将样本划分为训练集、测试集；
  - 若需要交叉验证集，则按 6:2:2 的比例将样本划分为训练集、交叉验证集（简称验证集，cross validation set，可简写为cv）、测试集；
  
- 交叉验证：
  - 简单交叉验证：按照 7:3 的比例将样本划分为训练集、测试集；
  - S-fold cross validation（S折交叉验证）：
    - 随机将样本划分为 S 个大小相同、互不相交的子集，使用 S-1 个子集用于训练，剩余的1个子集用于测试；
    - 由于 S-1 个子集的选取方法有S种，因此重复上述步骤 S 次，平均测试误差最小的模型即为最优结果；
  - 留一交叉验证：留一交叉验证是S折交叉验证的特例，令 S=N 即可；
    - N 为样本数量；
    - 常用于缺少数据的情形；
  - 按比例划分时，应随机抽取，确保训练集、测试集中的数据均服从某种分布规律；
- 符号：
    - $m_{train}、m_{cv}、m_{test}$ 分别表示训练集、交叉验证集、测试集样本数；
    - $(x_{cv}^{(i)},y_{cv}^{(i)})$ 表示交叉验证集中第 i 个样本；
    - $J_{train}(\theta)、J_{cv}(\theta)、J_{test}(\theta)$ 分别表示训练集、交叉验证集、测试集上的误差；
- 测试误差举例：在线性回归中应用平方误差时，$J_{test}(\theta)=\frac{1}{2m_{test}}\sum_{i=1}^{m_{test}}(h_{\theta}(x_{test}^{(i)})-y_{test}^{(i)})^{2}$；
  - 对 $J_{train}(\theta)、J_{cv}(\theta)$ 有类似表达式；
- 0/1 错分类率（误分类率）：
  - $err(h_{\Theta}(x),y)=\begin{cases}1 & h_{\Theta} \geq 0.5,y=0; h_{\Theta} < 0.5,y=1; \\0 & otherwise\end{cases}$；
  - Test Error = $\frac{1}{m_{test}}\sum_{i=1}^{m_{test}}err(h_{\Theta}(x_{test}^{(i)}),y_{test}^{(i)})$；
  - 0/1 错分类率（误分类率） = $\frac{被错误分类的测试样本数}{测试样本总数}$；

## 1.2 模型选择
### 1.2.1 concept
- 一般而言，若假设函数拟合某一组数据时表现非常好，则该假设在其它组数据上所得的误差不能作为实际的泛化误差；
- $d$：多项式的次数；
  - e.g. $d=2$，即假设函数为2次多项式；

### 1.2.2 为什么要将数据划分为训练集、验证集、测试集？
- Q：**用测试集选取模型（该方法不可取）**。若有10个假设函数，d=1,2,...,10，从训练集中学得各自的 $\theta$，将求得的参数值应用于测试集中，计算各自的测试误差 $J_{test}(\theta)$，选取测试误差最小的假设函数最为最优模型，是否合理？
  A：
  - 被选取的假设函数对测试集的拟合效果非常好（筛选出了在测试集上表现最好的参数 d），但不能由此推断该假设函数的泛化能力也很好，有可能该假设函数的参数 d 对测试集过拟合；
  - 用测试集选取模型，再用测试集求得的误差不再是泛化误差；
- Q：**用交叉验证集选取模型**。如何用 cross validation set 选取最优模型？
  A：
  - 从训练集中学得各自的 $\theta$；（求 $\theta$ 时公式中应包含正则化参数）
  - 将求得的参数值应用于交叉验证集中，计算各自的交叉验证误差 $J_{cv}(\theta)$；（求训练误差、交叉验证误差时公式中不包含正则化参数）
  - 选取交叉验证误差最小的模型，使用测试集估计所选模型的泛化误差；
  - 由于参数 d 未对测试集做拟合操作，因此结果可信；
  - 一般而言，由于有额外的参数 d 拟合了交叉验证集，因此交叉验证集的代价函数小于测试集；
- **用交叉验证集选取合适的特征**：
  - 分析在交叉验证集中出现误分类的样本，可以针对性的构造一些新的特征；

# 2. bias and variance
## 2.1 判断过拟合 or 欠拟合
- underfitting(欠拟合)/high bias(高偏差)：d 较小；
  overfitting(过拟合) /high variance(高方差)：d 较大；

![bias and variance](https://img-blog.csdnimg.cn/2019011319424643.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==,size_16,color_FFFFFF,t_70#pic_center)

- 判断 underfitting or overfitting 的方法：
  - underfitting/high bias：交叉验证误差、训练误差均较大，两者较为接近；
  - overfitting/high variance：交叉验证误差较大，训练误差较小，前者远大于后者；

## 2.2 正则化与偏差、方差
- $\lambda$ 较小，容易导致过拟合；
- $\lambda$ 较大，容易导致欠拟合；
- Q：如何选择 $\lambda$ 值？
  A：
  - 选取一系列 $\lambda$ 值，e.g. $\lambda=0,0.01,0.02,0.04,0.08,...,10.24$，一般而言，步长以2倍速增长，从0开始，直到某一较大值；
  - 从训练集中学得各自的 $\theta$；
  - 将求得的参数值应用于交叉验证集中，计算各自的交叉验证误差 $J_{cv}(\theta)$ ；
  - 绘制曲线如下图所示，当训练误差与交叉验证误差均适中时，对应的 $\lambda$ 最好；
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190113203607348.PNG#pic_center)
  - 选取交叉验证误差最小的模型，使用测试集估计所选模型的泛化误差；
  - 由于划分训练集和验证集时具有随机性，因此有时训练误差会高于验证误差，属于正常现象，示例如下（来自 Andrew Ng 作业5）；
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190114200716607.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==,size_16,color_FFFFFF,t_70)

## 2.3 绘制学习曲线判断算法是否出现高偏差 or 高方差的情况
- 当算法出现高偏差时：
  - 训练误差、交叉验证误差均较大，随着样本数增加，二者逐渐趋于平稳且接近；
  - 两条曲线接近的原因：模型未出现过拟合，泛化能力较好，虽然模型误差较大，但作用与交叉验证集时，能够取得与训练集相同的效果；
  - 增大样本数量不能减小误差；
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190113212308918.PNG#pic_center)

- 当算法出现高方差时：
  - 训练误差随训练样本数增加而增加，交叉验证误差随训练样本数增加而减小，但两条曲线仍存在一定间距（不同于高偏差时曲线较为接近）；
  - 两条曲线不接近的原因：模型在训练集上过拟合，泛化能力差；
  - 增大样本数量有助于减小误差，使两条曲线趋于接近（但曲线间仍有一定的差距）；
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190113212339342.PNG#pic_center)

- 当模型既没有出现高偏差，也没有出现高方差时：
  - 训练误差和交叉验证误差曲线均较小，且随训练样本数增加而逐渐接近；
  - 示例如下（来自 Andrew Ng 作业5）：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190114193811919.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==,size_16,color_FFFFFF,t_70)

## 2.4 应用机器学习的建议
### 2.4.1 修正学习算法
- 	增加训练样本：解决高方差；
	减少特征数量：解决高方差；
	增加特征数量：解决高偏差；
	增加多项式特征：解决高偏差；
	减小 $\lambda$：解决高偏差；
	增大 $\lambda$：解决高方差；

### 2.4.2 选择神经网络的层数
- 将数据划分为训练集、交叉验证集、测试集；
  用交叉验证集，分别计算含有一层、两层、三层。。。隐藏层的神经网络的误差值；
  从中选出层数合适的模型；

### 2.4.3 绘制学习曲线的细节
- 计算训练误差时，不包含正则化项；
- 计算训练误差时，逐步增加训练样本数，直至训练样本为整个训练集；
  计算交叉验证误差时，直接在整个交叉验证集上计算；

# 3. 机器学习系统设计
## 3.1 机器学习案例——垃圾邮件分类器
- Q：如何降低垃圾邮件分类器的误差？
  A：
  - 增加样本数量；
  - 从邮件标题、正文中构造更复杂的特征；
  - 设计算法纠正邮件中被刻意错误拼写的单词；
  - 垃圾邮件中特殊的标点符号用法；
  - 邮件来源于不寻常的路由器；
- 训练机器学习系统的方法：
  - 快速实现一个简单的算法，并在交叉验证集上测试；
  - 绘制学习曲线，判断是否需要更多的数据或特征；
  - 误差分析：在交叉验证集上手动检查导致算法出现错误的样本，判断是否需要更多数据、构造新的特征变量或设计新的算法；
  - 在交叉验证集上（不宜在测试集上分析误差），将新设计的算法与原算法的错误率作比较，判断新算法是否有效；

## 3.2 类偏斜的误差度量
### 3.2.1 为什么不使用分类精确度/分类误差处理类偏斜问题
- skewed classes：偏斜类；
  - 一个类的样本数相较于另一个类的样本数多很多；
  - e.g. 测试集中，有0.5%的人患有癌症，逻辑回归拟合出的可能是一条直线y=0，该结果的误差仅为0.5%；
  ```matlab
  function = y = predictCancer(x)
      y = 0;    % ignore x;
  return
  ```
- 在偏斜类中，不适宜使用分类误差或分类精确度衡量算法优劣；
  
  - e.g. 若拟合结果分类精确度为99.2%（0.8% error），而忽略x，直接输出y=0，分类精确度为99.5%（0.5% error），但并不能认为后者的结果优于前者，总是预测y=0或y=1并不是一个好的分类模型；

### 3.2.2 使用查准率与召回率评估偏斜类问题模型优劣
- Precision：查准率，即预测的正类中，有多少真的是正类；越高越好；
  Recall：召回率，即所有的正类有多少被指出；越高越好；
- Precision and Recall
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190116110123822.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==,size_16,color_FFFFFF,t_70)
  - 此处将y=1视为样本数量较少的类，通过 Precision、Recall 衡量模型优劣；若有需要，可根据实际问题调整为y=0，计算查准率和召回率的公式发生相应变化；

  - $Precision = \frac{True\space positives}{Predicted\space as\space positive}=\frac{True\space positives}{True\space positives+False\space positives}$；
  - $Recall = \frac{True\space positives}{Actual\space positives}=\frac{True\space positives}{True\space positives+False\space negatives}$；
  
### 3.2.3 如何兼顾查准率与召回率
- Predict 1 if $h_{\theta}(x)\geq threshold$；
  - 调整阈值大小，可使模型获得高查准率、低召回率，或低查准率、高召回率；
  - 没有方法可以自动计算合适的阈值；
- Q：为什么 $\frac{P+R}{2}$ 不适用于评估模型优劣？
  A：对 skewed classes 问题，总是预测y=0或y=1时，$\frac{P+R}{2}$ 会得出较高的值，但总是预测y=0或y=1并不是一个好的分类模型；
- $F_{1}$ Score (F score)： 
  - $F_{1}$ Score: $\frac{2PR}{P+R}$；
  - $F_{1}$ Score 是查准率和召回率的调和平均值；
  - $F_{1}$ Score 在兼顾查准率、召回率平均值的同时，会给其中较低值更高的权重；

## 3.3 使用大规模数据集
- 满足以下两个条件时，使用大规模数据集能提高算法效果；
  - 样本包含的信息足以预测 y 值（检验方式：人类专家能否依据这些信息分析出 y 值）；
  - 所选用的算法包含有大量参数，可用于拟合出复杂函数；
- 选用参数稍多的算法，防止 high bias；
  使用大规模数据集时，由于样本数量远大于参数数量，因此不容易出现 high variance；





# References
[^1]:https://www.coursera.org/learn/machine-learning/home/welcome