package com.middleware.ubatis.scripting;

import com.middleware.ubatis.mapping.SqlSource;
import com.middleware.ubatis.session.Configuration;
import org.dom4j.Element;

/**
 * @author Eric-ZC
 * @description 脚本语言驱动
 */
public interface LanguageDriver {

    SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType);

}
