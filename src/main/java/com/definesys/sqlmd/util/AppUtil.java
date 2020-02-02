package com.definesys.sqlmd.util;


import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

/**
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2020/1/31 6:45 PM
 * @history: 1.2020/1/31 created by jianfeng.zheng
 */
public class AppUtil {

    public static final Integer BUF_SIZE = 1024;

    public static String loadResourceText(String resource) {
        InputStream inputStream = AppUtil.class.getClassLoader().getResourceAsStream(resource);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        AppUtil.copyStream(inputStream, bout);
        String content = null;
        try {
            content = bout.toString("utf-8");
            inputStream.close();
            bout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 流复制
     *
     * @param from
     * @param to
     */
    public static void copyStream(InputStream from, OutputStream to) {
        byte[] buf = new byte[BUF_SIZE];
        int size = 0;
        do {
            try {
                size = from.read(buf);
                if (size > 0) {
                    to.write(buf, 0, size);
                } else {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (true);
    }

    public static String joinUpper(Collection list) {
        return join(list, true);
    }

    public static String join(Collection list) {
        return join(list, false);
    }

    public static String join(Collection list, Boolean upper) {
        StringBuffer buf = new StringBuffer();
        for (Object item : list) {
            if (buf.length() > 0) {
                buf.append(",");
            }
            if (item != null) {
                buf.append(item.toString());
            }
        }
        if (upper) {
            return buf.toString().toUpperCase();
        }
        return buf.toString();
    }

    /**
     * 1. 从剪切板获得文字。
     */
    public static String getSysClipboardText() {
        String ret = "";
        Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 获取剪切板中的内容
        Transferable clipTf = sysClip.getContents(null);

        if (clipTf != null) {
            // 检查内容是否是文本类型
            if (clipTf.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                try {
                    ret = (String) clipTf
                            .getTransferData(DataFlavor.stringFlavor);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return ret;
    }

    /**
     * 2.将字符串复制到剪切板。
     */
    public static void setSysClipboardText(String writeMe) {
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText = new StringSelection(writeMe);
        clip.setContents(tText, null);
    }
}
