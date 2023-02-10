package formDesigner.entity;

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
@TableName("input")
public class Input implements Serializable {
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
    @TableField("compIcon")
    private String compIcon;
    @TableField("showLabel")
    private boolean showLabel;
    @TableField("type")
    private String type;
    @TableField("title")
    private String title;
    @TableField("disabled")
    private boolean disabled;
    @TableField("placeholder")
    private String placeholder;
    @TableField("clearable")
    private boolean clearable;
    @TableField("required")
    private boolean required;
}
