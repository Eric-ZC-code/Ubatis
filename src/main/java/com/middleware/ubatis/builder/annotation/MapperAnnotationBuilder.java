package com.middleware.ubatis.builder.annotation;



import com.middleware.ubatis.annotations.Delete;
import com.middleware.ubatis.annotations.Insert;
import com.middleware.ubatis.annotations.Select;
import com.middleware.ubatis.annotations.Update;
import com.middleware.ubatis.binding.MapperMethod;
import com.middleware.ubatis.builder.MapperBuilderAssistant;
import com.middleware.ubatis.executor.keygen.Jdbc3KeyGenerator;
import com.middleware.ubatis.executor.keygen.KeyGenerator;
import com.middleware.ubatis.executor.keygen.NoKeyGenerator;
import com.middleware.ubatis.mapping.SqlCommandType;
import com.middleware.ubatis.mapping.SqlSource;
import com.middleware.ubatis.scripting.LanguageDriver;
import com.middleware.ubatis.session.Configuration;
import com.middleware.ubatis.session.ResultHandler;
import com.middleware.ubatis.session.RowBounds;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

/**
 * @author 小傅哥，微信：fustack
 * @description 注解配置构建器 Mapper
 * @date 2022/6/14
 * @github https://github.com/fuzhengwei/CodeDesignTutorials
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class MapperAnnotationBuilder {

    private final Set<Class<? extends Annotation>> sqlAnnotationTypes = new HashSet<>();

    private Configuration configuration;
    private MapperBuilderAssistant assistant;
    private Class<?> type;

    public MapperAnnotationBuilder(Configuration configuration, Class<?> type) {
        String resource = type.getName().replace(".", "/") + ".java (best guess)";
        this.assistant = new MapperBuilderAssistant(configuration, resource);
        this.configuration = configuration;
        this.type = type;

        sqlAnnotationTypes.add(Select.class);
        sqlAnnotationTypes.add(Insert.class);
        sqlAnnotationTypes.add(Update.class);
        sqlAnnotationTypes.add(Delete.class);
    }

    public void parse() {
        String resource = type.toString();
        if (!configuration.isResourceLoaded(resource)) {
            assistant.setCurrentNamespace(type.getName());

            Method[] methods = type.getMethods();
            for (Method method : methods) {
                if (!method.isBridge()) {
                    // 解析语句
                    parseStatement(method);
                }
            }
        }
    }

    /**
     * 解析语句
     */
    private void parseStatement(Method method) {
        Class<?> parameterTypeClass = getParameterType(method);
        LanguageDriver languageDriver = getLanguageDriver(method);
        SqlSource sqlSource = getSqlSourceFromAnnotations(method, parameterTypeClass, languageDriver);

        if (sqlSource != null) {
            final String mappedStatementId = method.getName();
            SqlCommandType sqlCommandType = getSqlCommandType(method);

            KeyGenerator keyGenerator;
            String keyProperty = "id";
            if (SqlCommandType.INSERT.equals(sqlCommandType) || SqlCommandType.UPDATE.equals(sqlCommandType)) {
                keyGenerator = configuration.isUseGeneratedKeys() ? new Jdbc3KeyGenerator() : new NoKeyGenerator();
            } else {
                keyGenerator = new NoKeyGenerator();
            }

            boolean isSelect = sqlCommandType == SqlCommandType.SELECT;

            String resultMapId = null;
            if (isSelect) {
                resultMapId = parseResultMap(method);
            }

            // 调用助手类
            assistant.addMappedStatement(
                    mappedStatementId,
                    sqlSource,
                    sqlCommandType,
                    parameterTypeClass,
                    resultMapId,
                    getReturnType(method),
                    keyGenerator,
                    keyProperty,
                    languageDriver
            );
        }
    }

    /**
     * 重点：DAO 方法的返回类型，如果为 List 则需要获取集合中的对象类型
     */
    private Class<?> getReturnType(Method method) {
        Class<?> returnType = method.getReturnType();
        if (Collection.class.isAssignableFrom(returnType)) {
            Type returnTypeParameter = method.getGenericReturnType();
            if (returnTypeParameter instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) returnTypeParameter).getActualTypeArguments();
                if (actualTypeArguments != null && actualTypeArguments.length == 1) {
                    returnTypeParameter = actualTypeArguments[0];
                    if (returnTypeParameter instanceof Class) {
                        returnType = (Class<?>) returnTypeParameter;
                    } else if (returnTypeParameter instanceof ParameterizedType) {
                        // (issue #443) actual type can be a also a parameterized type
                        returnType = (Class<?>) ((ParameterizedType) returnTypeParameter).getRawType();
                    } else if (returnTypeParameter instanceof GenericArrayType) {
                        Class<?> componentType = (Class<?>) ((GenericArrayType) returnTypeParameter).getGenericComponentType();
                        // (issue #525) support List<byte[]>
                        returnType = Array.newInstance(componentType, 0).getClass();
                    }
                }
            }
        }
        return returnType;
    }

    private String parseResultMap(Method method) {
        // generateResultMapName
        StringBuilder suffix = new StringBuilder();
        for (Class<?> c : method.getParameterTypes()) {
            suffix.append("-");
            suffix.append(c.getSimpleName());
        }
        if (suffix.length() < 1) {
            suffix.append("-void");
        }
        String resultMapId = type.getName() + "." + method.getName() + suffix;

        // 添加 ResultMap
        Class<?> returnType = getReturnType(method);
        assistant.addResultMap(resultMapId, returnType, new ArrayList<>());
        return resultMapId;
    }

    private SqlCommandType getSqlCommandType(Method method) {
        Class<? extends Annotation> type = getSqlAnnotationType(method);
        if (type == null) {
            return SqlCommandType.UNKNOWN;
        }
        return SqlCommandType.valueOf(type.getSimpleName().toUpperCase(Locale.ENGLISH));
    }

    private SqlSource getSqlSourceFromAnnotations(Method method, Class<?> parameterType, LanguageDriver languageDriver) {
        try {
            Class<? extends Annotation> sqlAnnotationType = getSqlAnnotationType(method);
            if (sqlAnnotationType != null) {
                Annotation sqlAnnotation = method.getAnnotation(sqlAnnotationType);
                final String[] strings = (String[]) sqlAnnotation.getClass().getMethod("value").invoke(sqlAnnotation);
                return buildSqlSourceFromStrings(strings, parameterType, languageDriver);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Could not find value method on SQL annotation.  Cause: " + e);
        }
    }

    private SqlSource buildSqlSourceFromStrings(String[] strings, Class<?> parameterTypeClass, LanguageDriver languageDriver) {
        final StringBuilder sql = new StringBuilder();
        for (String fragment : strings) {
            sql.append(fragment);
            sql.append(" ");
        }
        return languageDriver.createSqlSource(configuration, sql.toString(), parameterTypeClass);
    }

    private Class<? extends Annotation> getSqlAnnotationType(Method method) {
        for (Class<? extends Annotation> type : sqlAnnotationTypes) {
            Annotation annotation = method.getAnnotation(type);
            if (annotation != null) return type;
        }
        return null;
    }

    private LanguageDriver getLanguageDriver(Method method) {
        Class<?> langClass = configuration.getLanguageRegistry().getDefaultDriverClass();
        return configuration.getLanguageRegistry().getDriver(langClass);
    }

    private Class<?> getParameterType(Method method) {
        Class<?> parameterType = null;
        Class<?>[] parameterTypes = method.getParameterTypes();
        for (Class<?> clazz : parameterTypes) {
            if (!RowBounds.class.isAssignableFrom(clazz) && !ResultHandler.class.isAssignableFrom(clazz)) {
                if (parameterType == null) {
                    parameterType = clazz;
                } else {
                    parameterType = MapperMethod.ParamMap.class;
                }
            }
        }
        return parameterType;
    }

}
