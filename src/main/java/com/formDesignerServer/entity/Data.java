package com.formDesignerServer.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("comp_value")
public class Data implements Serializable {
    //表单ID
    @TableField("form_id")
    String form_id;
    //主键ID
    @TableField("component_id")
    String component_id;
    //值
    @TableField("value")
    String value;
    //组件类型
    @TableField("compType")
    String compType;

    public Data() {
    }

    public Data(String form_id, String component_id, String value, String compType) {
        this.form_id = form_id;
        this.component_id = component_id;
        this.value = value;
        this.compType = compType;
    }

    public String getForm_id() {
        return form_id;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }

    public String getComponent_id() {
        return component_id;
    }

    public void setComponent_id(String component_id) {
        this.component_id = component_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCompType() {
        return compType;
    }

    public void setCompType(String compType) {
        this.compType = compType;
    }
}
