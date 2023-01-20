package com.formDesignerServer.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.MapKey;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("divider")
public class Divider implements Serializable {
    @TableId(value = "id")
    private String id;
    @TableField("template_id")
    private String templateId;
    @TableField("position")
    private int position;
    @TableField("compType")
    private String compType;

    @TableField("ele")
    private String ele;
    @TableField("content_position")
    @JSONField(name = "content-position")
    private String contentPos;
    @TableField("child")
    private String child;
    @TableField("showLabel")
    private boolean showLabel;
    @TableField("compIcon")
    private String compIcon;
}
