package com.middleware.ubatis.scripting.xmltags;

/**
 * @author Eric-ZC
 * @description SQL 节点
 */
public interface SqlNode {

    boolean apply(DynamicContext context);

}