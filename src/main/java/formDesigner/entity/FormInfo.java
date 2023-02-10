package formDesigner.entity;

import lombok.Data;

import java.util.Date;

//表单实体
@Data
public class FormInfo {
    //表单名
    private String name;

    //表单id
    private String  id;

    //控件列表
    //private List<Object>   items;

    //更新时间
    private Date updateTime;

    //创建时间
    private Date createTime;

    //描述
    private String detail;


}
