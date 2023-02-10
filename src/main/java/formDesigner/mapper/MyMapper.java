package formDesigner.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MyMapper {
    List<Map<String,Object>> findFormByTemp(@Param("temp_id")String id);
    List<Map<String,Object>> findButtonComps(@Param("temp_id")String tempId);
    List<Map<String,Object>> findSwitchComps(@Param("temp_id")String tempId);
    String getCompValue(@Param("form_id")String formId,@Param("component_id")String compId,@Param("type")String type);
    List<Map<String,Object>> getAllTemp();
    int updateValue(@Param("form_id")String formId,@Param("type")String type,@Param("comp_id")String compId,@Param("value")String value);
    List<Map<String,Object>> findDividerComps(@Param("temp_id")String tempId);
    List<Map<String,Object>> findSliderComps(@Param("temp_id")String tempId);
    List<Map<String,Object>> findTextareaComps(@Param("temp_id")String tempId);
    List<Map<String,Object>> getFormById(@Param("form_id")String id);
    List<Map<String,Object>> fuzzyGetTempByName(@Param("name")String name);
}
