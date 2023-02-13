package formDesigner.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("comp_value")
public class CompValue implements Serializable {
    //表单ID
    @TableField("form_id")
    private String formId;
    //主键ID
    @TableField("component_id")
    private String componentId;
    //值
    @TableField("value")
    private String value;
    //组件类型
    @TableField("compType")
    private String compType;
}
