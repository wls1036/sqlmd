package com.definesys.sqlmd.sql;

/**
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2020/1/31 12:19 PM
 * @history: 1.2020/1/31 created by jianfeng.zheng
 */
public class Column {
    private String name;
    private String type;
    private Boolean primaryKey = false;
    private String comment;
    private String defaultValue;

    public String getName() {
        return name;
    }

    public Column setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public Column setType(String type) {
        this.type = type;
        return this;
    }

    public Boolean getPrimaryKey() {
        return primaryKey;
    }

    public Boolean isPrimaryKey() {
        return primaryKey;
    }

    public Column setPrimaryKey(Boolean primaryKey) {
        this.primaryKey = primaryKey;
        return this;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public Column setDefaultValue(String defaultValue) {
        if ("-".equals(defaultValue) || (defaultValue != null && defaultValue.trim().length() == 0)) {
            return this;
        }
        this.defaultValue = defaultValue;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Column setComment(String comment) {
        this.comment = comment;
        return this;
    }
}
