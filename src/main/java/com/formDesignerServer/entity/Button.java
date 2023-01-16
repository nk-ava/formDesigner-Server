package com.formDesignerServer.entity;

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
    private int position;
    //组件类型
    @TableField("compType")
    private String compType;
    //以下都为属性
    @TableField("compIcon")
    private String compIcon;
    @TableField("showLabel")
    private String showLabel;
    @TableField("ele")
    private String ele;
    @TableField("child")
    private String child;
    @TableField("size")
    private String size;
    @TableField("type")
    private String type;
    @TableField("plain")
    private boolean plain;
    @TableField("round")
    private boolean round;
    @TableField("circle")
    private boolean circle;
    @TableField("disabled")
    private boolean disabled;
    @TableField("icon")
    private String icon;
}
