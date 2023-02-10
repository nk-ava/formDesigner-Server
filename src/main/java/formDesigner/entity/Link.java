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
@TableName("link")
public class Link implements Serializable {
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
    private boolean showLabel;
    @TableField("child")
    private String child;
    @TableField("type")
    private String type;
    @TableField("underline")
    private boolean underline;
    @TableField("disabled")
    private boolean disabled;
    @TableField("href")
    private String href;
    @TableField("icon")
    private String icon;
}
