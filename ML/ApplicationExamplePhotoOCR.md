[TOC]

# 版权声明

- Machine learning 系列笔记来源于Andrew Ng 教授在 Coursera 网站上所授课程 *Machine learning*[^1]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及交流讨论；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 转载请注明出处；

# 1. Problem description and pipeline
- Optical character recognition (OCR，光学字符识别)；
- Photo OCR pipeline：
  - 文本检测（Text detection）：找出图像中有文字的区域；
    - 用滑动窗找出可疑点，用展开器将可疑点扩展连接为可疑区域；
  - 字符分割（Character segmentation）：将文本所在区域划分为一个个字符；
    - 应用监督学习的方法，使用一维滑动窗，判断图像块是否可分割；
      - 因为 Text detection 中已经找出了可疑区域，故此处使用一维滑动窗；
  - 字符分类（Character recognition）：识别字符内容；
  - 拼写校正；

# 2. Sliding windows
- Sliding windows （滑动窗）：一种分类器；
  image patch：图像块；
- step size/stride parameter：步长/步幅参数；
  - Sliding windows 每次移动的距离；
  - 常见的步长值有 4 个或 8 个像素等；
  - 由于检测器中输入图像的尺寸是不变的，因此选用较大的滑动窗取 image patch 时，需要将其压缩至检测器可以接收的图像尺寸；
- Expansion operator（展开器）：将检测器检测出的一个点扩展为一个小块，即将临近像素点也视为可疑目标，将相邻的字符转换为文本块；

# 3. 获取更多的数据
- 在扩展数据集之前，先确认模型是否处于 low bias 状态，此时扩展数据才有意义；
  - Low bias 状态下，将数据规模扩展到原数据集的 10 倍，通常可以大幅度提高算法效果；
  - 扩展数据集之前，估算扩展数据大约需要多少时间，有时候仅需要很短的时间就能完成，并且提高算法性能；
- Artificial data synthesis （人工数据合成）的两种类型：
  - 从头开始创建新的数据；
    - E.g. 下载各种字体，通过缩放、旋转等操作使之变形，粘贴到随机背景上；
  - 将较小的训练集扩展为更大的训练集；
    - E.g. 将原有图像做各种不同类型的扭曲；
    - 应使扩展的数据有可能出现在真实事件中，不可引入毫无意义的扩展数据；

# 4. Ceiling analysis（上限分析）
- 上限分析：避免将时间浪费在不能提升系统性能的工作上；
  - 分别测试各个模块，并量化表示各个模块的效果，即可知继续完善哪个模块能够更有效地提升系统性能；

# References
[^1]:https://www.coursera.org/learn/machine-learning/home/welcome 