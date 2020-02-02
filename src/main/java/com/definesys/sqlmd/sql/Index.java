package com.definesys.sqlmd.sql;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2020/1/31 12:20 PM
 * @history: 1.2020/1/31 created by jianfeng.zheng
 */
public class Index {
    private String name;
    private List<String> columns = new ArrayList<>();
    private String type;

    public String getName() {
        return name;
    }

    public Index setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getColumns() {
        return columns;
    }

    public Index setColumns(List<String> columns) {
        this.columns = columns;
        return this;
    }

    public String getType() {
        return type;
    }

    public Index setType(String type) {
        this.type = type;
        return this;
    }

    public void addColumn(String column) {
        this.columns.add(column);
    }
}
