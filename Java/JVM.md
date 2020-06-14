[toc]

# 版权声明

- JVM 学习笔记来源周志明的著作《深入理解 Java 虚拟机》[1]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及科学研究；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 本文采用 [署名-非商业性使用-禁止演绎 4.0 国际 (CC BY-NC-ND 4.0)](https://creativecommons.org/licenses/by-nc-nd/4.0/deed.zh) 协议发布；

# 1. Java 技术体系

- JCP：Java Community Process，Java 社区；
- JCP 定义的 Java 技术体系：
  - JDK：Java Development Kit，支持 Java 程序开发的最小环境；
    - Java 程序设计语言；
    - Java 虚拟机：HotSpot 是 Sun/Oracle JDK 和 OpenJDK中的默认虚拟机，也是当前主流的虚拟机之一；
    - Java 类库 API；
  - `.class`文件格式；
  - 来自商业机构和开源社区的第三方 Java 类库；
- 按业务场景划分 Java 技术体系：
  - Java Card：支持 Java 小程序运行在小内存设备上的平台；
  - Java ME：Java Micro Edition，支持 Java 程序运行在移动终端上的平台，Android 不属于 Java ME；
  - Java SE：Java Standard Edition，支持面向桌面级应用（E.g. Windows 下的应用程序）的 Java 平台；
  - Java EE：Java Enterprise Edition，支持使用多层架构的企业应用的 Java 平台；
- JRE：Java Runtime Environment，支持 Java 程序运行的标准环境；
  - Java SE API：Java 类库 API 的子集；
  - Java 虚拟机；
- 核心包和扩展包：
  - 核心包：`java.*`；
  - 扩展包：`javax.*`；
  - 注意：部分曾是扩展包的 API 后来进入核心包中，因此核心包中也有部分包名为`java.*`；
- LTS：Long Term Support，长期支持版；

# TODO

- [ ] Schedule：
  - 总页数：485 页；
  - 每天16页，7月12日完成；
  - 当前进度：42 页，Chapter 1 已完成；

# References

[1] 周志明. 深入理解 Java 虚拟机[M]. 北京: 机械工业出版社, 2019.