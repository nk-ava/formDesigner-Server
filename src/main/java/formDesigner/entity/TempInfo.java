package formDesigner.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

//表单模板实体类
@Data
public class TempInfo {
    //模板名
    private String formName;

    //模板id
    private String  id;

    //控件列表
    private List<Object> items;

    //更新时间
    private Date updateTime;
}
