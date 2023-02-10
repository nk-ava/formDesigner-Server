package formDesigner.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import formDesigner.entity.TimePicker;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TimePickerMapper extends BaseMapper<TimePicker> {
}
