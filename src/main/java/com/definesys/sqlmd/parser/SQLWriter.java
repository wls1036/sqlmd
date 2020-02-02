package com.definesys.sqlmd.parser;

import com.definesys.sqlmd.sql.Column;
import com.definesys.sqlmd.sql.Index;
import com.definesys.sqlmd.sql.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2020/1/29 11:39 AM
 * @history: 1.2020/1/29 created by jianfeng.zheng
 */
public class SQLWriter {

    private final static Pattern START_COLUMN_PT = Pattern.compile("^[-|\\|]+$");


    private List<Table> tables = new ArrayList<>();
    private Table currentTable;
    private Index currentIndex;
    private Boolean startColumn = false;


    public void newTable() {
        currentTable = new Table();
        tables.add(currentTable);
        startColumn = false;
        currentIndex = null;
    }

    public void newIndex() {
        currentIndex = new Index();
        currentTable.addIndex(currentIndex);
    }

    public void startColumn() {
        this.startColumn = true;
    }


    public void writeTableName(String tableName) {
        currentTable.setName(tableName);
    }

    public void writeIndexName(String indexName) {
        currentIndex.setName(indexName);
    }

    public void writeColumn(String column) {
        if (START_COLUMN_PT.matcher(column).find()) {
            this.startColumn = true;
            return;
        }
        if (this.startColumn) {
            String[] ss = column.split("\\|");
            Column c = new Column();
            if (ss[0].startsWith("*")) {
                c.setPrimaryKey(true);
                ss[0] = ss[0].substring(1);
            }
            c.setName(ss[0]);
            c.setType(ss.length > 1 ? ss[1] : null);
            c.setComment(ss.length > 2 ? ss[2] : null);
            c.setDefaultValue(ss.length > 3 ? ss[3] : null);
            currentTable.addColumn(c);
        }
    }

    public void writeTableComment(String comment) {
        currentTable.setComment(comment.trim());
    }


    public List<Table> getTables() {
        return tables;
    }


    public void writeIndex(String column) {
        if (currentIndex == null) {
            throw new RuntimeException("解析markdown异常");
        }
        this.currentIndex.addColumn(column);
    }
}
