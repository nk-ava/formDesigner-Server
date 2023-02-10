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
@TableName("form")
public class Form implements Serializable {
    //表单ID
    @TableId("id")
    private String id;
    @TableField("name")
    private String name;
    //模板ID
    @TableField("template_id")
    private String templateId;
    //提交时间
    @TableField("submit_time")
    private String submitTime;
    @TableField("detail")
    private String detail;
}
