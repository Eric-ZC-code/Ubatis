package com.middleware.ubatis.executor.result;

import com.middleware.ubatis.session.ResultContext;

/**
 * @author Eric-ZC
 * @description 默认结果上下文
 */
public class DefaultResultContext implements ResultContext {

    private Object resultObject;
    private int resultCount;

    public DefaultResultContext() {
        this.resultObject = null;
        this.resultCount = 0;
    }

    @Override
    public Object getResultObject() {
        return resultObject;
    }

    @Override
    public int getResultCount() {
        return resultCount;
    }

    public void nextResultObject(Object resultObject) {
        resultCount++;
        this.resultObject = resultObject;
    }

}
