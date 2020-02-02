package com.definesys.sqlmd.sql;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2020/1/31 12:18 PM
 * @history: 1.2020/1/31 created by jianfeng.zheng
 */
public class Table {
    private String name;
    private String comment;
    private List<Column> columns = new ArrayList<>();
    private List<Index> indexs = new ArrayList<>();

    public Table(String tableName) {
        this.name = tableName;
    }

    public Table() {

    }

    public String getName() {
        return name;
    }

    public Table setName(String name) {
        this.name = name;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Table setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public Table setColumns(List<Column> columns) {
        this.columns = columns;
        return this;
    }

    public List<Index> getIndexs() {
        return indexs;
    }

    public Table setIndexs(List<Index> indexs) {
        this.indexs = indexs;
        return this;
    }

    public void addColumn(Column c) {
        columns.add(c);
    }

    public void addIndex(Index index) {
        this.indexs.add(index);
    }

    public List<String> getKeys() {
        List<String> keys = new ArrayList<>();
        for (Column c : columns) {
            if (c.isPrimaryKey()) {
                keys.add(c.getName());
            }
        }
        return keys;
    }
}
