package formDesigner.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import formDesigner.entity.Form;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FormMapper extends BaseMapper<Form> {

}
