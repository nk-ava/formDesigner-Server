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
@TableName("upload")
public class Upload implements Serializable {
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
    @TableField("disabled")
    private Boolean disabled;
    @TableField("restrictions")
    @JSONField(name = "limit")
    private int restrictions;
    @TableField("showTips")
    private Boolean showTips;
    @TableField("tips")
    private String tips;
    @TableField("listStyle")
    private String listStyle;
    @TableField("acceptType")
    private String acceptType;
}
