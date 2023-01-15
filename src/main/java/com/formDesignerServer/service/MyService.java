package com.formDesignerServer.service;

import java.util.List;
import java.util.Map;

public interface MyService {
    String saveTemplate(String id, String name, List<Map<String, Object>> list, String temp_id) throws Exception;

    void saveForm(String temp_id, String form_id, List<Map<String, Object>> list) throws Exception;

    List<Map<String, Object>> getAllFormInfo();

    List<Map<String, Object>> getAllTemp();

    void updateTemp(String temp_id, String update_time, List<Map<String, Object>> list) throws Exception;

    void deleteTemp(String temp_id) throws Exception;

    void updateForm(String form_id, String update_time, List<Map<String, Object>> list) throws Exception;

    void deleteForm(String form_id) throws Exception;

    void test();
}