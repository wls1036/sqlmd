package com.definesys.sqlmd;

import com.definesys.sqlmd.parser.SQLRender;
import com.definesys.sqlmd.util.AppUtil;
import org.commonmark.node.*;
import org.commonmark.parser.Parser;

/**
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2020/1/29 10:29 AM
 * @history: 1.2020/1/29 created by jianfeng.zheng
 */
public class Main {

    public static void main(String[] cmd) {
        String md = AppUtil.getSysClipboardText();
        if (md == null || md.trim().length() == 0) {
            System.out.println("请将markdown内容复制到剪贴板");
            return;
        }
        String vendor = "oracle";
        if (cmd.length > 0) {
            if ("mysql".equals(cmd[0]) || "oracle".equals(cmd[0])) {
                vendor = cmd[0];
            } else {
                System.err.println("目前只支持oracle和mysql数据库");
            }
        }
        Parser parser = Parser.builder().build();
        Node document = parser.parse(md);
        SQLRender renderer = SQLRender.builder().vendor(vendor).build();
        String sql = renderer.render(document);
        AppUtil.setSysClipboardText(sql);
        System.out.println(sql);
    }
}
