package com.middleware.ubatis.session;

public interface SqlSession {

    /**
     * 根据指定的sqlID获取一条记录的封装对象
     * @param statement sqlID
     * @return Mapped object 封装后的对象
     * @param <T> the returned object type 封装之后的对象类型
     */
    <T> T selectOne(String statement);

    /**
     * 根据指定的sqlID获取一条记录的封装对象, 这个方法允许我们给sql传递一些参数
     * 实际使用中，这个参数传递的是pojo，或者Map和ImmutableMap
     * @param statement statement sqlID
     * @param parameter Unique identifier matching the statement to use
     * @return Mapped object 封装后的对象
     * @param <T> the returned object type 封装之后的对象类型
     */
    <T> T selectOne(String statement, Object parameter);

    /**
     * 得到映射器，使用泛型保证类型安全
     * @param type Mapper interface class
     * @return a mapper bound to this SqlSession
     * @param <T> the mapper type
     */
    <T> T getMapper(Class<T> type);


}
