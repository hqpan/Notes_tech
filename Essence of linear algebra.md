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
    - 将$\begin{bmatrix}x_1 & y_1 \end{bmatrix}​$视为一种空间变换；



# 9. Cross products in the light of linear transformations

- ==unsolved==

# n. Plan
- 周计划：2+2+2，6月26日完成全部内容；










# References
[1] https://space.bilibili.com/88461692
