package formDesigner.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import formDesigner.entity.CompValue;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DataMapper extends BaseMapper<CompValue> {

}
