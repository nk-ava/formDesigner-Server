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
@TableName("date")
public class DatePicker implements Serializable {
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
    @TableField("clearable")
    private boolean clearable;
    @TableField("size")
    private String size;
    @TableField("type")
    private String type;
}
