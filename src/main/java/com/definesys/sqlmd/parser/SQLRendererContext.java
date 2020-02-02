package com.definesys.sqlmd.parser;

/**
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2020/1/29 3:47 PM
 * @history: 1.2020/1/29 created by jianfeng.zheng
 */
public class SQLRendererContext {
    private SQLWriter writer;

    public SQLRendererContext(SQLWriter sqlWriter) {
        this.writer=sqlWriter;
    }

    public SQLWriter getWriter() {
        return writer;
    }

    public SQLRendererContext setWriter(SQLWriter writer) {
        this.writer = writer;
        return this;
    }
}
