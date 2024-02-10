package com.middleware.ubatis.reflection.invoker;

/**
 * @author Eric-ZC
 * @description 调用者
 */
public interface Invoker {

    Object invoke(Object target, Object[] args) throws Exception;

    Class<?> getType();

}
