[toc]
# 版权声明

- Python 系列笔记来源于 Magnus Lie Hetland 的著作《Python基础教程》[1]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及科学研究；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 本文采用 [署名-非商业性使用-禁止演绎 4.0 国际 (CC BY-NC-ND 4.0)](https://creativecommons.org/licenses/by-nc-nd/4.0/deed.zh) 协议发布；
# 1. 打开文件

- open(filename， mode) ，mode（文件模式）为可选参数；

| value | description                            | 备注                                                         |
| ----- | -------------------------------------- | ------------------------------------------------------------ |
| r     | 读取模式（默认值）                     |                                                              |
| w     | 写入模式                               | 若文件不存在，则创建文件；若文件已存在，则删除既有内容（截断），重新写入 |
| x     | 独占写入模式                           | 若文件不存在，则引发 FileExistError                          |
| a     | 附加模式                               | 在既有文件的文本末尾继续写入                                 |
| b     | 二进制模式（与其他模式结合使用）       |                                                              |
| t     | 文本模式（默认值，与其他模式结合使用） |                                                              |
| +     | 读写模式（与其他模式结合使用）         |                                                              |
| r+    | 读写模式                               | 不截断文件                                                   |
| w+    | 读写模式                               | 截断文件                                                     |

# References
[1] Hetland, M. L. (2018). *Beginning Python: From Novice to Professional* (3rd ed.). Berkeley: Apress.