package com.formDesignerServer.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("time")
public class TimePicker implements Serializable {
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
    @TableField("readonly")
    private boolean readonly;
    @TableField("disabled")
    private boolean disabled;
    @TableField("editable")
    private boolean editable;
    @TableField("is_range")
    @JSONField(name = "is-range")
    private boolean rangeWidth;
    @TableField("clearable")
    private boolean clearable;
    @TableField("range_separator")
    @JSONField(name = "range-separator")
    private String rangeSeparator;
    @TableField("start_placeholder")
    @JSONField(name = "start-placeholder")
    private String startPlaceholder;
    @TableField("end_placeholder")
    @JSONField(name = "end-placeholder")
    private String endPlaceholder;
    @TableField("placeholder")
    private String placeholder;
}
