package com.definesys.sqlmd.parser;

import org.commonmark.node.*;
import org.commonmark.renderer.NodeRenderer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2020/1/29 11:39 AM
 * @history: 1.2020/1/29 created by jianfeng.zheng
 */
public class CoreSQLNodeRender extends AbstractVisitor implements NodeRenderer {

    private SQLRendererContext context;
    private SQLWriter sqlContent;

    public CoreSQLNodeRender(SQLRendererContext context) {
        this.context = context;
        this.sqlContent = context.getWriter();
    }

    @Override
    public Set<Class<? extends Node>> getNodeTypes() {
        return new HashSet<>(Arrays.asList(
                Document.class,
                Heading.class,
                Paragraph.class,
                BlockQuote.class,
                BulletList.class,
                FencedCodeBlock.class,
                HtmlBlock.class,
                ThematicBreak.class,
                IndentedCodeBlock.class,
                Link.class,
                ListItem.class,
                OrderedList.class,
                Image.class,
                Emphasis.class,
                StrongEmphasis.class,
                Text.class,
                Code.class,
                HtmlInline.class,
                SoftLineBreak.class,
                HardLineBreak.class
        ));
    }

    @Override
    public void render(Node node) {
        node.accept(this);
    }

    @Override
    public void visit(FencedCodeBlock fencedCodeBlock) {
        sqlContent.writeTableComment(fencedCodeBlock.getLiteral());
    }


    @Override
    public void visit(Heading heading) {
        if (heading.getLevel() == 1 && heading.getFirstChild() instanceof Text) {
            sqlContent.newTable();
            sqlContent.writeTableName(((Text) heading.getFirstChild()).getLiteral());
        } else if ((heading.getLevel() == 2 || heading.getLevel() == 3) &&
                heading.getFirstChild() instanceof Text) {
            sqlContent.newIndex();
            sqlContent.writeIndexName(((Text) heading.getFirstChild()).getLiteral());
        }
    }


    @Override
    public void visit(Text text) {
        if (text.getLiteral().contains("|")) {
            sqlContent.writeColumn(text.getLiteral());
        }else{
            sqlContent.writeIndex(text.getLiteral());
        }
    }

}
