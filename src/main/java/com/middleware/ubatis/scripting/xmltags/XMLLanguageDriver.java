package com.middleware.ubatis.scripting.xmltags;

import com.middleware.ubatis.mapping.SqlSource;
import com.middleware.ubatis.scripting.LanguageDriver;
import com.middleware.ubatis.session.Configuration;
import org.dom4j.Element;

/**
 * @author Eric-ZC
 * @description XML语言驱动器
 */
public class XMLLanguageDriver implements LanguageDriver {

    @Override
    public SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType) {
        // 用XML脚本构建器解析
        XMLScriptBuilder builder = new XMLScriptBuilder(configuration, script, parameterType);
        return builder.parseScriptNode();
    }

}