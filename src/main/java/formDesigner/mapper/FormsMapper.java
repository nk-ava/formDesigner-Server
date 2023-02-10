package formDesigner.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import formDesigner.entity.FormInfo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FormsMapper extends BaseMapper<FormInfo> {
    //删除表单
    public  int deleteForm(int id);

}
