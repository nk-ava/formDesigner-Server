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
@TableName("text")
public class Text implements Serializable {
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
    @TableField("showLabel")
    private Boolean showLabel;
    @TableField("ele")
    private String ele;
    @TableField("align")
    private String align;
    @TableField("color")
    private String color;
    @TableField("size")
    private int size;
    @TableField("bold")
    private int bold;
    @TableField("text")
    private String text;
}
