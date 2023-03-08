package formDesigner.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface MyService {
    String saveTemplate(String id, String name, List<Map<String, Object>> list, String temp_id) throws Exception;

    void saveForm(String temp_id, String formName, String form_id, List<Map<String, Object>> list, String detail, String name) throws Exception;

    List<Map<String, Object>> getAllFormInfo();

    List<Map<String, Object>> getAllTemp();

    void updateTemp(String temp_id, String update_time, List<Map<String, Object>> list) throws Exception;

    void deleteTemp(String temp_id) throws Exception;

    void updateForm(String form_id, String update_time, List<Map<String, Object>> list) throws Exception;

    void deleteForm(String form_id) throws Exception;

    List<Map<String,Object>> getAllForm();

    List<Map<String,Object>> getFormById(String id);

    List<Map<String,Object>> getTempById(String id);

    void updateTempName(String temp_id,String name) throws Exception;

    List<Map<String,Object>> getTempByName(String name);

    void test();

    void reFormName(String formId,String name) throws Exception;

    void reFormDetail(String formId,String detail) throws Exception;

    String uploadImg(MultipartFile multipartFile) throws Exception;

    byte[] getImage(String name);
}