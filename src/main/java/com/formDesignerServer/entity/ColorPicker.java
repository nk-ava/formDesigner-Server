package com.formDesignerServer.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.formDesignerServer.utils.ListToString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "color_picker",autoResultMap = true)
public class ColorPicker implements Serializable {
    @TableId("id")
    private String id;
    @TableField("template_id")
    private String templateId;
    @TableField("position")
    private int position;
    @TableField("compType")
    private String compType;
    @TableField("compIcon")
    private String compIcon;
    @TableField("ele")
    private String ele;
    @TableField("title")
    private String title;
    @TableField("showLabel")
    private boolean showLabel;
    @TableField("required")
    private boolean required;
    @TableField("disabled")
    private boolean disabled;
    @TableField("size")
    private String size;
    @TableField("show_alpha")
    @JSONField(name = "show-alpha")
    private boolean showAlpha;
    @TableField(value = "predefine",typeHandler = ListToString.class)
    private List predefine;
}
