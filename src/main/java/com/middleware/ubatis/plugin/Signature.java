package com.middleware.ubatis.plugin;

/**
 * @author Eric-ZC
 * @description 方法签名
 */
public @interface Signature {

    /**
     * 被拦截类
     */
    Class<?> type();

    /**
     * 被拦截类的方法
     */
    String method();

    /**
     * 被拦截类的方法的参数
     */
    Class<?>[] args();

}