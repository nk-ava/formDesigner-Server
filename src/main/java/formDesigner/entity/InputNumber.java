package formDesigner.entity;

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
@TableName("input_number")
public class InputNumber implements Serializable {
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
    @TableField("min")
    private int min;
    @TableField("max")
    private int max;
    @TableField("step")
    private int step;
    @TableField("size")
    private String size;
    @TableField("disabled")
    private boolean disabled;
    @TableField("accuracy")
    @JSONField(name = "precision")
    private int accuracy;
    @TableField("controls")
    private boolean controls;
    @TableField("controls_position")
    @JSONField(name = "controls-position")
    private String controlsPos;
    @TableField("required")
    private boolean required;
}
