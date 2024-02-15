package com.middleware.ubatis.builder.xml;

import com.middleware.ubatis.builder.MapperBuilderAssistant;
import com.middleware.ubatis.mapping.ResultMap;
import com.middleware.ubatis.mapping.ResultMapping;

import java.util.List;

/**
 * @author Eric-ZC
 * @description 结果映射解析器
 */
public class ResultMapResolver {
    private final MapperBuilderAssistant assistant;
    private String id;
    private Class<?> type;
    private List<ResultMapping> resultMappings;

    public ResultMapResolver(MapperBuilderAssistant assistant, String id, Class<?> type, List<ResultMapping> resultMappings) {
        this.assistant = assistant;
        this.id = id;
        this.type = type;
        this.resultMappings = resultMappings;
    }

    public ResultMap resolve() {
        return assistant.addResultMap(this.id, this.type, this.resultMappings);
    }
}
