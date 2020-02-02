package com.definesys.sqlmd.util;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2020/1/31 4:04 PM
 * @history: 1.2020/1/31 created by jianfeng.zheng
 */
public class TemplateEngine {

    public static final int BUFFER_SIZE = 1024;

    private VelocityEngine engine;

    private Map<String, Object> engineData = new HashMap<>();

    private String basePath;

    private String sp = System.getProperty("file.separator");

    public TemplateEngine() {

    }

    /**
     * 设置上下文数据
     *
     * @param data
     */
    public void setEngineData(Object data) {
        this.toEngineData(data);
    }

    /**
     * 将模板file转换为to
     *
     * @param resource
     */
    public String evaluateFromResource(String resource) {
        String tpl = AppUtil.loadResourceText(resource);
        return this.evaluate(tpl, this.engineData);
    }

    /**
     * 获取生成的文件名
     *
     * @param from
     * @param to
     * @return
     */
    private String getTargetFileName(String from, String to) {
        int index = to.lastIndexOf(sp);
        //to如果是文件直接返回
        if (index != -1 && to.substring(index).indexOf(".") != -1) {
            return to;
        }
        int size = basePath.length();
        String t = to + from.substring(size);
        return t;
    }


    /**
     * 处理路径
     *
     * @param path
     * @return
     */
    private String evaluatePath(String path) {
        if (path.endsWith(sp)) {
            path = path.substring(0, path.length() - 1);
        }
        if (path.indexOf(sp) == -1) {
            path = System.getProperty("user.dir") + sp + path;
        }
        return path;
    }

    /**
     * 处理基础路径
     *
     * @param path
     * @return
     */
    private String evaluateBashPath(String path) {
        File f = new File(path);
        if (f.isDirectory()) {
            return path;
        }
        int index = path.lastIndexOf(sp);
        if (index != -1) {
            return path.substring(0, index);
        }
        return System.getProperty("user.dir");
    }


    /**
     * 模版转换
     *
     * @param tpl
     * @param data
     */
    public String evaluate(String tpl, Map<String, Object> data) {
        this.initEngie();
        VelocityContext context = new VelocityContext();
        for (String key : data.keySet()) {
            context.put(key, data.get(key));
        }
        StringWriter writer = new StringWriter();
        try {
            engine.evaluate(context, writer, "bud", tpl);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return writer.getBuffer().toString();
    }

    /**
     * 创建目录
     *
     * @param path
     */
    private void mkdir(String path) {
        int index = path.lastIndexOf(sp);
        if (index > 0) {
            File f = new File(path.substring(0, index));
            f.mkdirs();
        }
    }

    /**
     * 模板初始化
     */
    private void initEngie() {
        if (engine == null) {
            engine = new VelocityEngine();
            engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
            engine.init();
        }
    }

    /**
     * 将pojo数据转换为map
     *
     * @param ob
     */
    private void toEngineData(Object ob) {
        if (ob instanceof Map) {
            this.engineData = (Map<String, Object>) ob;
        } else {
            this.engineData = new HashMap<>();
            Method[] methods = ob.getClass().getDeclaredMethods();
            for (Method m : methods) {
                String name = m.getName();
                if (!name.startsWith("get")) {
                    continue;
                }
                System.out.println(name);
                name = name.substring(3);
                try {
                    this.engineData.put(name.substring(0, 1).toLowerCase() + name.substring(1), m.invoke(ob));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}