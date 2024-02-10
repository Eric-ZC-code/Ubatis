package com.middleware.ubatis.reflection.wrapper;

import com.middleware.ubatis.reflection.MetaObject;

/**
 * @author Eric-ZC
 * @description 对象包装工厂
 */
public interface ObjectWrapperFactory {

    /**
     * 判断有没有包装器
     */
    boolean hasWrapperFor(Object object);

    /**
     * 得到包装器
     */
    ObjectWrapper getWrapperFor(MetaObject metaObject, Object object);

}
