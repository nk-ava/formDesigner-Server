package com.formDesignerServer.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;

import java.io.Serializable;
import java.util.NavigableMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("switch")
public class Switch implements Serializable {
    //组件ID
    @TableId(value = "id")
    private String id;
    //所属模板ID
    @TableField("template_id")
    private String template_id;
    //所属位置
    @TableField("position")
    private int position;
    //组件类型
    @TableField("compType")
    private String compType;
    //以下都是控件属性
    @TableField("ele")
    private String ele;
    @TableField("title")
    private String title;
    @TableField("disabled")
    private boolean disabled;
    @TableField("compIcon")
    private String compIcon;
    @TableField("width")
    private String width;
    @TableField("active_text")
    @JSONField(name = "active-text")
    private String activeText;
    @TableField("inactive_text")
    @JSONField(name = "inactive-text")
    private String inactiveText;
    @TableField("active_color")
    @JSONField(name = "active-color")
    private String activeColor;
    @TableField("inactive_color")
    @JSONField(name = "inactive-color")
    private String inactiveColor;
    @TableField("showLabel")
    private boolean showLabel;
    @TableField("required")
    private boolean required;
}
