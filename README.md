# Ubatis - 一个自定义的 MyBatis ORM 框架

Ubatis 是一个完整实现的 MyBatis ORM 框架，旨在为日常 Java 应用提供高效的数据库操作解决方案。通过学习 MyBatis 源码并深入理解其原理，Ubatis 在以下方面有所突破：

- **原理深入理解**：通过学习 MyBatis 的源码，深入理解了 MyBatis 如何通过接口和配置文件实现 SQL 语句的关联，以及代理与映射机制背后的原理。

- **设计模式应用**：掌握了多种设计模式的运用，特别是 MyBatis 管理多边服务的设计。通过创建会话模型和统一调度执行器，实现了复杂操作的封装，并通过 SqlSessionFactory 工厂统一对外提供 SqlSession 服务。

- **插件设计**：熟练掌握了 MyBatis 插件设计模型，基于 MyBatis 实现了日志监控、库表路由、字段加解密等操作，为项目提供了更多的解决方案。

- **缓存设计**：使用装饰器模式实现了一级缓存、二级缓存的功能，提高了数据库查询的效率和性能。

- **与 Spring 整合**：完成了 Ubatis 与 Spring、SpringBoot 的整合实践，使其更易于在项目中集成和使用。

通过这些工作，Ubatis 提供了一种灵活而高效的数据库操作解决方案，为 Java 开发者带来了更多的便利和可能性。