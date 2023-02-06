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
@TableName("barcode")
public class BarCode implements Serializable {
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
    @TableField("showLabel")
    private Boolean showLabel;
    @TableField("title")
    private String title;
    @TableField("lineColor")
    private String lineColor;
    @TableField("background")
    private String background;
    @TableField("barWidth")
    private int barWidth;
    @TableField("barHeight")
    private int barHeight;
    @TableField("displayValue")
    private Boolean displayValue;
    @TableField("fontSize")
    private int fontSize;
    @TableField("text")
    private String text;
}
