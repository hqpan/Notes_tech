[toc]

# 版权声明

- Spring 系列学习笔记来源 Craig Walls 的著作《Spring 实战》[1]；
- 该系列笔记不以盈利为目的，仅用于个人学习、课后复习及科学研究；
- 如有侵权，请与本人联系（hqpan@foxmail.com），经核实后即刻删除；
- 本文采用 [署名-非商业性使用-禁止演绎 4.0 国际 (CC BY-NC-ND 4.0)](https://creativecommons.org/licenses/by-nc-nd/4.0/deed.zh) 协议发布；

# 1. Overview

## 1.1 Spring 相关概念

- Spring 提供1个容器，亦称为 Spring 应用上下文；用于创建和管理应用组件；
- Spring 应用上下文：Spring application text；
- bean：组件，在 Spring 应用上下文中被装配到一起；
  - bean，n.[C] 豆；
- 依赖注入模式：DI，Dependency Injection；
  -  功能：将 bean 装配到一起；
  - 实现方式：使用单独的容器，创建并维护所有的组件，并将其注入到有需要的 bean 中；
    - 无需使用某个组件，创建其所依赖的组件，并管理所依赖组件的生命周期；
- Spring 应用上下文装配 bean 的方法：
  - 使用一个或多个 XML 文件；
  - 基于 Java 的配置；
    - `@Configuration`：该类为配置类，用于提供 bean；
    - `@bean`：配置类的方法；
      - 该方法返回的对象以 bean 的形式添加到上下文中；
      - 默认情况下，bean 对应的 bean ID 与定义 bean 的方法名相同；
  - 自动配置：Spring Boot 基于类路径中的条目、环境变量和其它因素，自动将组件装配到一起；
  - 注意：仅在无法使用自动配置的情况下使用显式配置（此处指基于 Java 的配置），基于 XML 的配置是过时的方式，不再考虑；

## 1.2 初始化 Spring 应用

- 

# ==Schedule==

- 学习进度：
  - 总页数：450 页；
  - 每天15页，5月30日完成；
  - 当前进度：15 页；



# References

[1] Craig Walls. Spring 实战[M]. 北京: 人民邮电出版社, 2020.