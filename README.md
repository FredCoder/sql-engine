## SQL-Engine 
是一个用于解析规定格式的 _XML_ ，取出 _SQL_ 交由 _JDBC_ 执行的 _DAO_ 工具项目。<br>可以看成一个没有 _ORM_ 的 _Mybatis_ 。在构建小型项目时，可以快速构建 _DAO_ 层。<br>不需要构建繁琐的 _ORM_ 映射，返回的结果可以转成标准 _json_ 格式的数据。<br>

#### [改造日志](https://magiatelier.cc/2018/01/07/%E4%B8%80%E4%B8%AA%E5%9F%BA%E4%BA%8EXML%E8%A7%A3%E6%9E%90%E7%9A%84SQL%E8%A7%A3%E9%87%8A%E5%99%A8[%E4%B8%8A])<br>

### 待办列表
 - [ ] _SQL_ 结果匿名映射
 - [x] 添加 _DefaultCache_ 
 - [x] 添加 _Cache_ 丢弃回收机制
 - [ ] 添加 _Druid_ 连接池
 - [ ] 移除 _xstream_ 使用原生 _XML_ 解析替代

### 功能简介

