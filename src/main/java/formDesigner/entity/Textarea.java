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
@TableName("textarea")
public class Textarea implements Serializable {
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
    @TableField("compIcon")
    private String compIcon;
    @TableField("type")
    private String type;
    @TableField("showLabel")
    private boolean showLabel;
    @TableField("title")
    private String title;
    @TableField("row_s")
    private int rows;
    @TableField("disabled")
    private boolean disabled;
    @TableField("required")
    private boolean required;
}
