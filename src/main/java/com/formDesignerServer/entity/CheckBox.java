package com.formDesignerServer.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.formDesignerServer.utils.ListToString;
import com.formDesignerServer.utils.MapToString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "checkbox",autoResultMap = true)
public class CheckBox implements Serializable {
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
    @TableField("min")
    private int min;
    @TableField("max")
    private int max;
    @TableField("text_color")
    @JSONField(name = "text-color")
    private String textColor;
    @TableField("fill")
    private String fill;
    @TableField("childIndex")
    private int childIndex;
    @TableField(value = "child",typeHandler = ListToString.class)
    private List<Map<String,Object>> child;
    @TableField(value = "childAttr",typeHandler = MapToString.class)
    private Map<String,Object> childAttr;
    @JSONField(name = "value")
    @TableField(exist = false)
    private List value = new ArrayList();
}
