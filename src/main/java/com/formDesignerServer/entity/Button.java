package com.formDesignerServer.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("button")
public class Button implements Serializable {
    //组件ID
    @TableId(value = "id")
    private String id;
    //所属模板ID
    @TableField("template_id")
    private String template_id;
    //所属位置
    @TableField("position")
    private int index;
    //组件类型
    @TableField("type")
    private String type;
    //以下都为属性
    @TableField("width")
    private String width;
    @TableField("label")
    private String label;
    @TableField("text")
    private String text;

    public Button() {
    }

    public Button(String id, String template_id, int index, String width, String label, String text) {
        this.id = id;
        this.template_id = template_id;
        this.index = index;
        this.width = width;
        this.label = label;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
