# Ubatis - 一个自定义的 MyBatis ORM 框架

Ubatis 是一个完整实现的持久层 ORM 框架，旨在为日常 Java 应用提供高效的数据库操作解决方案。通过学习 MyBatis 源码并深入理解其原理，Ubatis 在以下方面有所突破：

- **ORM功能实现**：实现了解析、绑定、反射、缓存、会话和事务等ORM框架的基础功能，大量运用了代理与映射机制。Ubatis 支持动态生成SQL语句，通过 XML 配置文件或注解将 Java 方法和 SQL 语句进行关联，实现了面向对象和关系型数据库之间的映射。

- **设计模式应用**：运用了数十种设计模式，通过创建会话模型和统一调度执行器，实现了复杂操作的封装，并通过 SqlSessionFactory 工厂统一对外提供 SqlSession 服务。

- **插件设计**：实现了 MyBatis 插件设计模型，基于插件实现了日志监控、库表路由、字段加解密等操作，为项目提供了更多的解决方案。

- **缓存设计**：使用 HashMap 实现了一级缓存 (会话级别)，使用装饰器模式实现二级缓存 (Mapper级别)，提高了数据库查询的效率和性能。

- **与 Spring 整合**：完成了 Ubatis 与 Spring、SpringBoot 的整合实践，使其更易于在项目中集成和使用。

通过这些工作，Ubatis 提供了一种灵活而高效的数据库操作解决方案，为 Java 开发者带来了更多的便利和可能性。
