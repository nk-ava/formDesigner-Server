package com.formDesignerServer.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("template")
public class Template implements Serializable {
    //模板ID
    @TableId(value = "id")
    String id;
    //模板名称
    @TableField("name")
    String name;
    //更新时间
    @TableField("update_time")
    String date;
    //组件
    @TableField("components")
    String components;

    public Template() {
    }

    public Template(String id, String name, String date, String components) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.components = components;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }
}
