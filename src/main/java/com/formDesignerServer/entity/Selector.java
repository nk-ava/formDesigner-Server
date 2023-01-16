package com.formDesignerServer.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.formDesignerServer.utils.ListToString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "selector",autoResultMap = true)
public class Selector implements Serializable {
    @TableId("id")
    private String id;
    @TableField("template_id")
    private String template_id;
    @TableField("position")
    private int position;
    @TableField("compType")
    private String compType;

    @TableField("ele")
    private String ele;
    @TableField("title")
    private String title;
    @TableField("compIcon")
    private String compIcon;
    @TableField("showLabel")
    private boolean showLabel;
    @TableField("multiple")
    private boolean multiple;
    @TableField("disabled")
    private boolean disabled;
    @TableField("size")
    private String size;
    @TableField("clearable")
    private boolean clearable;
    @TableField("placeholder")
    private String placeholder;
    @TableField("required")
    private boolean required;
    @TableField(value = "child",typeHandler = ListToString.class)
    private List<Map<String,Object>> child;
}
