[TOC]

# 0. 版权声明
- Essence of linear algebra 系列笔记来源于 3Blue1Brown 在 YouTube 网站上发布的课程 *Essence of linear algebra*，bilibili 网站的 3Blue1Brown 中国官方账号亦提供了该课程资源 [1]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及科学研究；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 本文采用 [署名-非商业性使用-禁止演绎 4.0 国际 (CC BY-NC-ND 4.0)](https://creativecommons.org/licenses/by-nc-nd/4.0/deed.zh) 协议发布；

# 1. Term
| English | Chinese |
|:--:|:--:|
| scalar | n.标量；adj.标量的 |


# 2. Vectors, what even are they？
- 数值与向量相乘：实质是向量缩放，将向量中的每个分量缩放；
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190507164647428.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==,size_16,color_FFFFFF,t_70)

# 3. Linear combinations, span, and bias vectors （向量的线性组合，张成空间和基向量）
- Bias vectors（基向量）: $\overrightarrow{i},\overrightarrow{j}$;
- Span：给定向量通过线性组合张成的空间；
  - $a\overrightarrow{v}+b\overrightarrow{w}$：对给定基向量 $\overrightarrow{v},\overrightarrow{w}$，若保持其中一个标量 b 不变，仅改变标量 a，上式可表示二维空间中一条直线上的任一点；
  - $a\overrightarrow{v}+b\overrightarrow{w}+c\overrightarrow{u}$：对给定基向量 $\overrightarrow{v},\overrightarrow{w},\overrightarrow{u}$，若保持其中一个标量 c 不变，仅改变标量 a、b，上式可表示三维空间中一个平面上的任一点；

# 4. Linear transformations and matrices 
- Linear transformation:
  -  Transformation 与 function 含义相同，两者都将输入量转换为输出量；
  - Linear：
    - Intuition：
      - 直线在变换后仍为直线；
      - 原点位置不变；
      - 网格线保持平行且等距分布；
    - Definition：线性包括齐次性和叠加性两部分，$ax_1(t)+bx_2(t)\rightarrow ay_1(t)+by_2(t)$；
      - Scaling（齐次性）：$L(c\overrightarrow{v})=cL(\overrightarrow{v})$；
      - Additivity（叠加性）：$L(\overrightarrow{v}+\overrightarrow{w})=L(\overrightarrow{v})+L(\overrightarrow{w})$；
- 将矩阵视为对空间的特定变换：
  - E.g. 若二维空间中有向量 $\overrightarrow{x}=a\overrightarrow{v}+b\overrightarrow{w}$，现对二维空间做线性变换，仅需使用变换后的基向量 $\overrightarrow{v'},\overrightarrow{w'}$，即可描述线性变换后的向量 $\overrightarrow{x'}=a\overrightarrow{v'}+b\overrightarrow{w'}$，标量 a、b不发生改变；
    - 若变换后的基向量 $\overrightarrow{v'},\overrightarrow{w'}$ 线性相关，则在本案例中会将二维空间压缩为一条直线；
  - E.g. 以二维空间为例，将变换后的基向量 $\overrightarrow{v'}$ 的坐标作为矩阵的第一列，将$\overrightarrow{w'}$ 的坐标作为矩阵的第二列，即可构造一个 $2\times2$ 矩阵，将该矩阵与原空间中的向量相乘，即可得变换后空间中的对应向量；
    - $\begin{bmatrix}v^{'}_x & w^{'}_x \\v^{'}_y & w^{'}_y \end{bmatrix}\begin{bmatrix}x \\y \end{bmatrix}=x\begin{bmatrix}v^{'}_x\\v^{'}_y \end{bmatrix}+y\begin{bmatrix}w^{'}_x \\w^{'}_y \end{bmatrix}=\begin{bmatrix}v^{'}_xx+w^{'}_xy\\v^{'}_yx+ w^{'}_yy \end{bmatrix}$；
      - 变换前：$\overrightarrow{w}=\begin{bmatrix}0 \\1 \end{bmatrix}$；
      - 变换后：$\overrightarrow{w}=\begin{bmatrix}w^{'}_x \\w^{'}_y \end{bmatrix}$；
      - $w^{'}_x$：变换后的 $\overrightarrow{w^{'}}$ 在 $x^{'}$ 轴方向上的分量；
      - $v^{'}_xx+w^{'}_xy$： $x^{'}$ 轴方向分量之和；
    - 矩阵左乘一个向量，表示对该向量做一个特定的线性变换；
    - 多个矩阵依次左乘一个向量，表示对该向量做多个线性变换；
    - 矩阵与矩阵相乘，表示两个线性变换组合成为一个复合变换；
    - 推广可知，用 $n\times n$ 矩阵描述 n 维空间的线性变换；

# 5. The determinant （行列式）
  - 由“网格线保持平行且等距分布”这一事实可知，只需要分析原空间中单位正方形的面积变化，即可知空间中任一大小的其它方格均有相同变化（空间面积变化的比例相同）；
- 行列式的值表示线性变换后，二维空间中面积发生改变的比例（对三维空间则表示体积变化的比例）；
  - E.g. 若一个表示线性变换的矩阵，其行列式的值为2，则表示该线性变换将原空间中单位正方形的面积放大2倍；
  - E.g. 若一个表示线性变换的矩阵，其行列式的值为-2，则表示该线性变换将原空间中单位正方形的面积放大2倍，且空间翻转（以二维平面为例，空间翻了个面）；
  - E.g. 若一个表示线性变换的矩阵，其行列式的值为0，则表示该线性变换将原空间压缩到更低的维度上；
- E.g. $det(\begin{bmatrix}a & b \\c & d \end{bmatrix})=(a+b)(c+d)-ac-bd-2bc=ad-bc$；
  - a 表示对 $\overrightarrow{i}$ 在 x 轴方向的缩放；
  - d 表示对 $\overrightarrow{j}$ 在 y 轴方向上的缩放；
  - bc 表示平行四边形在对角方向上拉伸或压缩了多少；
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190507164738411.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==,size_16,color_FFFFFF,t_70)

# 6. Inverse matrices, column space, rank and null space（逆矩阵，列空间，秩，零向量）
- E.g. 求解线性方程组：
  - 求解线性方程组 $A\overrightarrow{X}=\overrightarrow{b}$ 的含义：寻找一个向量 $\overrightarrow{X}$，对其执行变换 A 后，使得 $\overrightarrow{X}$ 恰与 $\overrightarrow{b}$ 重合；
    - 当 $det(A)\neq0$ 时，存在唯一的向量 $\overrightarrow{X}$ 与向量 $\overrightarrow{b}$ 对应，即线性方程组存在唯一解；
      - 当空间不发生降维时，原空间与变换后空间中的非零向量一一对应；
  - 逆矩阵的含义：与变换 A 相对应的逆向变换；
    - 当 $det(A)\neq0$ 时，$\overrightarrow{X}=A^{-1}\overrightarrow{b}$；
    - 当 $det(A)=0$ 时，不存在与矩阵 A 相对应的逆变换；
      - 当 $det(A)=0$ 时，空间被压缩（E.g. 三维空间被压缩为二维平面、直线……），此时高维空间中的多个向量被映射到低维空间中的同一个向量，多个输入对应同一个输出；
      - 由于函数的定义中不允许一个输入对应多个输出，因此不存在一种逆变换将低维空间解压为高维空间；
      - 当 $det(A)=0$ 时，若 $\overrightarrow{b}$ 恰好存在于压缩后的低维空间上，则线性方程组有解，否则无解；
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190507164758815.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==,size_16,color_FFFFFF,t_70)

- 列空间：矩阵 A 的列张成的空间；
  - $\overrightarrow{0}$ 一定会被包含在列空间之中；
  - 对于一个满秩变换，只有零向量在变换后能落在原点；
    - 这是因为满秩变换不会对空间降维，其它向量经变换后不会出现在原点；
  - 当 $det(A)=0$ 时，空间被压缩；
    - E.g. 以三维空间被压缩为二维空间为例，会有一整条直线上的所有点被压缩到原点；
    - E.g. 以三维空间被压缩为一维空间为例，会有一个平面上的所有点被压缩到原点；
    - Null space/Kernel (矩阵的零空间/核)：变换后落在原点的向量构成的空间；
      - 当 $\overrightarrow{b}=\overrightarrow{0}$ 时，零空间即为该线性方程组的所有解；
- 秩：变换后的空间维数，即列空间的维数；（==本句待核实==）
  - full rank (满秩)：秩与 A 的列数相等（==列空间的维数与输入空间的维数相等==，本句待核实）；


# 7. 当线性方程组的系数矩阵 A 为非方阵的时候
- E.g. $A = \begin{bmatrix}3 & 1 \\4 & 1 \\5 & 9\end{bmatrix}$；
  - A 有两列：表明输入空间有两个基向量；
  - A 有三行：表明每个基向量在变换后都可以用三个独立的坐标来表示；
  - A 将二维空间映射到三维空间中；
- E.g. $A = \begin{bmatrix}3 & 1 &4 \\1 & 5 & 9\end{bmatrix}$；
  - A 有三列：表明输入空间有三个基向量；
  - A 有两行：表明每个基向量在变换后都可以用两个独立的坐标来表示；
  - A 将三维空间映射到二维空间中；
- E.g. 将二维空间转换为一维空间：即将平面转换为直线，转换后的向量对应于数轴上的一个数，如下图所示；
![在这里插入图片描述](https://img-blog.csdnimg.cn/2019050716481964.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01heGltaXplMQ==,size_16,color_FFFFFF,t_70)

- 关于线性性质的直观理解：若二维空间中一条直线上存在若干等距分布的点，通过线性变换后，将该二维空间映射到一维空间中，这些点仍然保持等距分布；

#  8. Dot products and duality (点积与对偶性)==未完全理解，下次再看2019.06.23==
- 点积的数学含义：
  - 令向量$\overrightarrow{x}$对向量$\overrightarrow{y}$做投影，将投影结果与向量$\overrightarrow{y}$相乘，结果即为点积；
    - 点积结果可正可负，取决于两个向量之间的相对位置关系；
  - Intuition: $\begin{bmatrix}x_1 & y_1 \end{bmatrix}\begin{bmatrix}x_2 \\y_2 \end{bmatrix}=x_1\cdot x_2+y_1\cdot y_2​$;
    - 将$\begin{bmatrix}x_1 & y_1 \end{bmatrix}$视为一种空间变换，将二维空间压缩为一维空间；
  - 更一般的情况，$\begin{bmatrix}x_1 & x_2 & \cdots & x_n \end{bmatrix}\begin{bmatrix}y_1 \\ y_2 \\ \vdots \\ y_n \end{bmatrix}=\sum_{i=1}^nx_iy_i​$;
      - 向量（空间变化）$\begin{bmatrix}x_1 & x_2 & \cdots & x_n \end{bmatrix}$将n维空间压缩为一维空间；
      - 换言之，每个从n维空间压缩为一维空间的变换，都对应一个n维空间中的向量；



# 9. Cross products in the light of linear transformations

- `Cross product/Vector product`：叉积 [2];
  - $\overrightarrow{c}=\overrightarrow{a}\times \overrightarrow{b}​$；
  - $\overrightarrow{c}$的模长：等于以$\overrightarrow{a}，\overrightarrow{b}​$为边的平行四边形的面积；
  - $\overrightarrow{c}$的方向：满足右手定则；
    - 在计算结果上表现为，叉积的结果有正负之分；
  - 若两个向量方向相同或相反（即它们非线性无关），亦或任意一个的模长为零，那么它们的叉积为零；
- 叉积与线性变换的关系：
  - 对某个线性变换（即矩阵）求行列式，行列式的值表示，相对于空间变换前的单位面积$\overrightarrow{i}\times \overrightarrow{j}​$变化的比例，即平行四边形的面积；
  - 二维向量的叉积：对两个二维向量组成的矩阵求行列式，即可得模长；
- 三维向量的叉积：$\begin{bmatrix}x_1 \\ y_1 \\ z_1 \end{bmatrix} \times \begin{bmatrix}x_2 \\ y_2 \\ z_2 \end{bmatrix}=det\left(\begin{bmatrix}\widehat{i} & x_1 & x_2 \\ \widehat{j} & y_1 & y_2 \\ \widehat{k} & z_1 & z_2 \end{bmatrix}\right)​$；
  - 等式右侧的行列式的几何意义：由三个向量在空间中构成的六边形的体积；
  - 推导过程：
    - 对于$\begin{bmatrix}p_1 \\ p_2 \\ p_3 \end{bmatrix} \times \begin{bmatrix}x \\ y \\ z \end{bmatrix}=det\left(\begin{bmatrix}x & x_1 & x_2 \\ y & y_1 & y_2 \\ z & z_1 & z_2 \end{bmatrix}\right)​$，当且仅当向量$\begin{bmatrix}p_1 \\ p_2 \\ p_3 \end{bmatrix}​$模长等于$\begin{bmatrix}x_1 \\ y_1 \\ z_1 \end{bmatrix}​$和$\begin{bmatrix}x_2 \\ y_2 \\ z_2 \end{bmatrix}​$构成的平行四边形的面积时，该式成立；
    - 若取$\begin{bmatrix}x \\ y \\ z \end{bmatrix}=\begin{bmatrix}\widehat{i} \\ \widehat{j} \\ \widehat{k} \end{bmatrix}$，则$\begin{bmatrix}p_1 \\ p_2 \\ p_3 \end{bmatrix} \times \begin{bmatrix}\widehat{i} \\ \widehat{j} \\ \widehat{k} \end{bmatrix}=det\left(\begin{bmatrix}\widehat{i} & x_1 & x_2 \\ \widehat{j} & y_1 & y_2 \\ \widehat{k} & z_1 & z_2 \end{bmatrix}\right)$；
      - 等式左侧表示向量$\overrightarrow{p}=p_1\overrightarrow{i}+p_2\overrightarrow{j}+p_3\overrightarrow{k}$，该向量的模长等于$\begin{bmatrix}x_1 \\ y_1 \\ z_1 \end{bmatrix}$和$\begin{bmatrix}x_2 \\ y_2 \\ z_2 \end{bmatrix}$构成的平行四边形的面积；
      - 因此可用$det\left(\begin{bmatrix}\widehat{i} & x_1 & x_2 \\ \widehat{j} & y_1 & y_2 \\ \widehat{k} & z_1 & z_2 \end{bmatrix}\right)​$表示叉积所得的向量；

# 10. Change of basis（基变换）

- 矩阵乘法可视为一种线性变换；
  - 变换后的向量仍旧是相同的线性组合，差别在于使用了新的基向量；
  - 线性变换的实质是将原空间中的基向量转换为新空间中的基向量；
- $A^{-1}MA$：表示一种转移作用；
  - 左乘矩阵A：将现有的坐标空间转换为标准坐标空间；
  - 左乘矩阵M：对标准坐标空间做线性变换；
  - 左乘矩阵$A^{-1}$：将标准坐标空间还原为原坐标空间；
  - 综上所述：矩阵$A,A^{-1}$实现视角转换，矩阵M表示在标准空间中的变换；



# 11. 特征值与特征向量

- 特征向量：变换后的向量仍然留在变换前的向量张成的空间里；

  - 即变换后，向量仅发生拉伸或压缩，方向不发生改变（反向除外）；
- 特征值：特征向量在变换中拉伸或压缩的比例；
- 应用：描述三维空间中的旋转；

  - 旋转不缩放任何向量，因此特征值为1；
- 求解特征向量与特征值：$A\overrightarrow{v}=\lambda \overrightarrow{v}$；
  - 对空间做线性变换A，恰有向量$\overrightarrow{v}$满足线性变换后，大小缩放 $\lambda$倍；
  - 将上式变形有：$(A-\lambda I)\overrightarrow{v}=\overrightarrow{0}$；
    - 由行列式相关知识有，当且仅当线性变换将空间压缩至更低维度时，才会存在一个非零向量，使得该矩阵与该向量乘积为零向量；
    - 空间压缩时，矩阵行列式为0；
- 几种特殊情况：
  - 特征向量可能不存在；
    - E.g. 二维空间旋转$90^{\circ}$时，空间中的所有向量的方向均发生改变；
    - $(A-\lambda I)\overrightarrow{v}=\overrightarrow{0}$没有实数解，表明特征向量$\overrightarrow{v}$不存在； 

  - 一个特征值对应多个特征向量，多个特征向量出现在不同的直线上；

    - E.g. 将空间拉伸为原空间的两倍，平面内的所有向量特征值均为2；

  - 对于对角矩阵有：

    - 该空间中的特征向量均为基向量；
      - 因为对角矩阵仅对向量进行拉伸或压缩，不改变向量的方向；
    - 对角元为特征值；

  - 为了充分利用特征向量，可通过$A^{-1}MA$变换，将原有的基向量转换为特征基；

    - $A^{-1}MA$变换：将矩阵对角化的过程；
      - 不是所有的矩阵都能对角化；
        - E.g. 剪切变换中，由于特征向量不够多，不能张成全空间，因此不能对角化；

    - 特征基：用特征向量作为基向量；
    - 矩阵对角化带来的计算优势：
      - E.g. $\begin{bmatrix}1 & -1 \\0 & 1 \end{bmatrix}^1\begin{bmatrix}3 & 1 \\0 & 2 \end{bmatrix}\begin{bmatrix}1 & -1 \\0 & 1 \end{bmatrix}=\begin{bmatrix}3 & 0 \\0 & 2 \end{bmatrix}​$；
      - 计算$\begin{bmatrix}3 & 1 \\0 & 2 \end{bmatrix}^{100}$；



# 12. 抽象向量空间

- ==unsolved==

# n. Plan
- 周计划：2+2+2，6月26日完成全部内容；










# References
[1] 3Blue1Brown. 线性代数的本质[DB/OL]. https://www.bilibili.com/video/av6731067. 2016-10-18/2019-06-24.

[2] 维基百科. 叉积[DB/OL]. https://zh.wikipedia.org/wiki/叉积. 2019-01-05/2019-06-24.