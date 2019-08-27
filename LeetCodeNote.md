# 136
## 136.1 概述

- 参照题解有：
  - Approach 1：二重循环遍历数组（略）；
  - Approach 2：散列表；（==unsolved==）
  - Approach 3：异或运算；



## 136.2. 异或运算

- 异或运算：
  - $a\oplus a=0$；
    - 相同值经异或运算得0；
    - 恰对应于每个相同的元素出现偶数次；
  - $0\oplus a=a$；
    - 任何值与0进行异或运算，均不改变该值；
  - 异或运算满足交换律与结合律：$a\oplus b\oplus a=(a\oplus a)\oplus b=0\oplus b =b$；

