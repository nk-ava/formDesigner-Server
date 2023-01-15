package com.formDesignerServer.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("form")
public class Form implements Serializable {
    //表单ID
    @TableId(value = "id")
    String id;
    //模板ID
    @TableField("template_id")
    String template_id;
    //提交时间
    @TableField("submit_time")
    String submitTime;
    public Form() {
    }

    public Form(String id, String template_id, String submitTime) {
        this.id = id;
        this.template_id = template_id;
        this.submitTime = submitTime;
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

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }
}
