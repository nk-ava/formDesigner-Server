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
@TableName("slider")
public class Slider implements Serializable {
    @TableId("id")
    private String id;
    @TableField("template_id")
    private String templateId;
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
    @TableField("min")
    private int min;
    @TableField("max")
    private int max;
    @TableField("disabled")
    private boolean disabled;
    @TableField("step")
    private int step;
    @TableField("show_stop")
    @JSONField(name = "show-stop")
    private boolean showStop;
    @TableField("required")
    private boolean required;
}
