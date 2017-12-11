package com.dao.commonsdbutils.processor;

import org.apache.commons.dbutils.BeanProcessor;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * ClassName: AnnotationBeanProcessor<br/>
 * Function: Bean转换注解处理类.<br/>
 * date: Aug 1, 2014 11:18:57 AM<br/>
 * @version 1.0
 */
public class AnnoBeanProcessor extends BeanProcessor {

    @Override
    public <T> T toBean(ResultSet rs, Class<T> type) throws SQLException {

        try {
            return this.createBean(rs, type);
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> toBeanList(ResultSet rs, Class<T> type) throws SQLException {

        List<T> results = new ArrayList<T>();

        if (!rs.next()) {
            return results;
        }

        do {
            try {
                results.add(this.createBean(rs, type));
            } catch (Exception e) {

                throw new RuntimeException(e);
            }
        } while (rs.next());

        return results;
    }

    private <T> T createBean(ResultSet rs, Class<T> type) throws InstantiationException,
            IllegalAccessException {

        T instance = type.newInstance();

        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {

            String columnName = null;
            Column column = field.getAnnotation(Column.class);

            if (null == column) {
                columnName = field.getName();
            } else {
                columnName = column.value();
            }

            field.setAccessible(true);
            field.set(instance, this.processColumn(rs, columnName, field.getType()));

        }

        return instance;
    }

    /**
     * 字段值类型转换处理. <br/>
     *
     * @param rs
     * @param columnName
     * @param propType
     * @return
     */
    protected Object processColumn(ResultSet rs, String columnName, Class<?> propType) {

        try {
            if (!propType.isPrimitive() && rs.getObject(columnName) == null) {
                return null;
            }

            if (propType.equals(String.class)) {
                return rs.getString(columnName);
            } else if (propType.equals(Integer.TYPE) || propType.equals(Integer.class)) {
                return Integer.valueOf(rs.getInt(columnName));
            } else if (propType.equals(Boolean.TYPE) || propType.equals(Boolean.class)) {
                return Boolean.valueOf(rs.getBoolean(columnName));
            } else if (propType.equals(Long.TYPE) || propType.equals(Long.class)) {
                return Long.valueOf(rs.getLong(columnName));
            } else if (propType.equals(Double.TYPE) || propType.equals(Double.class)) {
                return Double.valueOf(rs.getDouble(columnName));
            } else if (propType.equals(Float.TYPE) || propType.equals(Float.class)) {
                return Float.valueOf(rs.getFloat(columnName));
            } else if (propType.equals(Short.TYPE) || propType.equals(Short.class)) {
                return Short.valueOf(rs.getShort(columnName));
            } else if (propType.equals(Byte.TYPE) || propType.equals(Byte.class)) {
                return Byte.valueOf(rs.getByte(columnName));
            } else if (propType.equals(Timestamp.class)) {
                return rs.getTimestamp(columnName);
            } else {
                return rs.getObject(columnName);
            }
        } catch (SQLException e) {
        }

        return null;

    }

}