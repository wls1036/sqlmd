package com.definesys.sqlmd.parser;

import com.definesys.sqlmd.util.AppUtil;
import com.definesys.sqlmd.util.TemplateEngine;
import org.commonmark.internal.renderer.NodeRendererMap;
import org.commonmark.node.Node;
import org.commonmark.renderer.Renderer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2020/1/29 11:31 AM
 * @history: 1.2020/1/29 created by jianfeng.zheng
 */
public class SQLRender implements Renderer {
    private String vendor;


    private SQLRender() {
    }

    private SQLRender(String vendor) {
        this.vendor = vendor;
    }

    @Override
    public void render(Node node, Appendable output) {
        SQLRendererContext context = new SQLRendererContext(new SQLWriter());
        NodeRendererMap nodeRendererMap = new NodeRendererMap();
        nodeRendererMap.add(new CoreSQLNodeRender(context));
        nodeRendererMap.render(node);
        TemplateEngine engine = new TemplateEngine();
        Map data = new HashMap<>();
        data.put("items", context.getWriter().getTables());
        data.put("util", AppUtil.class);
        engine.setEngineData(data);
        try {
            output.append(engine.evaluateFromResource(vendor + ".vm"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String render(Node node) {
        StringBuilder sb = new StringBuilder();
        render(node, sb);
        return sb.toString();
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private String vendor = "mysql";

        public SQLRender build() {
            return new SQLRender(vendor);
        }

        public Builder vendor(String vendor) {
            this.vendor = vendor;
            return this;
        }
    }
}
