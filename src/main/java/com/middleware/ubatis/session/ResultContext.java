package com.middleware.ubatis.session;

/**
 * @author Eric-ZC
 * @description 结果上下文
 */
public interface ResultContext {

    /**
     * 获取结果
     */
    Object getResultObject();

    /**
     * 获取记录数
     */
    int getResultCount();

}
