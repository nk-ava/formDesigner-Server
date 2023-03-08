package formDesigner.controller;

import cn.hutool.http.body.MultipartBody;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.alibaba.fastjson.JSON;
import formDesigner.entity.Template;
import formDesigner.service.MyService;
import formDesigner.service.TemplateService;
import formDesigner.utils.R;
import formDesigner.utils.RS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/FORM")
public class SimpleController {
    @Autowired
    private MyService myService;
    @Autowired
    private TemplateService templateService;

    @PostMapping("/saveForm")
    public RS api0(@RequestBody Map<String, Object> mp) {
        String temp_id, time, formName, detail, tempName;
        List<Map<String, Object>> list;
        if (mp.get("tempId") != null) temp_id = mp.get("tempId").toString();
        else temp_id = null;
        if (mp.get("subTime") != null) time = mp.get("subTime").toString();
        else return RS.error().message("缺少参数subTime");
        if (mp.get("list") != null) list = (List<Map<String, Object>>) mp.get("list");
        else return RS.error().message("缺少参数list");
        if (mp.get("formName") != null) formName = mp.get("formName").toString();
        else return RS.error().message("缺少参数formName");
        if (mp.get("detail") != null) detail = mp.get("detail").toString();
        else return RS.error().message("缺少参数detail");
        if (mp.get("tempName") != null) tempName = mp.get("tempName").toString();
        else tempName = null;
        try {
            myService.saveForm(temp_id, formName, time, list, detail, tempName);
            return RS.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return RS.error().message(e.toString());
        }
    }

    @PostMapping("/saveTemp")
    public RS api1(@RequestBody Map<String, Object> mp) {
        String save_time, name;
        List<Map<String, Object>> list;
        if (mp.get("saveTime") != null) save_time = mp.get("saveTime").toString();
        else return RS.error().message("缺少参数saveTime");
        if (mp.get("name") != null) name = mp.get("name").toString();
        else return RS.error().message("缺少参数name");
        if (mp.get("list") != null) list = (List<Map<String, Object>>) mp.get("list");
        else return RS.error().message("缺少参数list");
        try {
            myService.saveTemplate(save_time, name, list, null);
            return RS.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return RS.error().message(e.toString());
        }
    }

    @GetMapping("/getAllTemp")
    public List<Map<String, Object>> api2() {
        return myService.getAllTemp();
    }

    @GetMapping("/getAllFormInfo")
    public List<Map<String, Object>> api3() {
        return myService.getAllFormInfo();
    }

    @GetMapping("/deleteTempById")
    public RS api4(@RequestParam("id") String id) {
        try {
            myService.deleteTemp(id);
            return RS.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return RS.error().message(e.toString());
        }
    }

    @GetMapping("/deleteFormById")
    public RS api5(@RequestParam("id") String id) {
        try {
            myService.deleteForm(id);
            return RS.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return RS.error().message(e.toString());
        }
    }

    @PostMapping("/updateFormById")
    public RS api6(@RequestBody Map<String, Object> mp) {
        String form_id, time;
        List<Map<String, Object>> list;
        if (mp.get("formId") != null) form_id = mp.get("formId").toString();
        else return RS.error().message("缺少参数formId");
        if (mp.get("subTime") != null) time = mp.get("subTime").toString();
        else return RS.error().message("缺少参数subTime");
        if (mp.get("list") != null) list = (List<Map<String, Object>>) mp.get("list");
        else return RS.error().message("缺少参数list");
        try {
            myService.updateForm(form_id, time, list);
            return RS.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return RS.error().message(e.toString());
        }
    }

    @PostMapping("/updateTempById")
    public RS api7(@RequestBody Map<String, Object> mp) {
        String temp_id, time;
        List<Map<String, Object>> list;
        if (mp.get("tempId") != null) temp_id = mp.get("tempId").toString();
        else return RS.error().message("缺少参数tempId");
        if (mp.get("saveTime") != null) time = mp.get("saveTime").toString();
        else return RS.error().message("缺少参数saveTime");
        if (mp.get("list") != null) list = (List<Map<String, Object>>) mp.get("list");
        else return RS.error().message("缺少参数list");
        try {
            myService.updateTemp(temp_id, time, list);
            return RS.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return RS.error().message(e.toString());
        }
    }

    @GetMapping("/getAllForm")
    public List<Map<String, Object>> api8() {
        return myService.getAllForm();
    }

    @GetMapping("/getFormById")
    public List<Map<String, Object>> api9(@RequestParam("id") String id) {
        return myService.getFormById(id);
    }

    @GetMapping("/getTempById")
    public List<Map<String, Object>> api10(@RequestParam("id") String id) {
        return myService.getTempById(id);
    }

    @GetMapping("/updateTempName")
    public RS api11(@RequestParam("id") String tempId, @RequestParam("name") String name) {
        try {
            myService.updateTempName(tempId, name);
            return RS.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return RS.error().message(e.toString());
        }
    }

    @GetMapping("/getTempByName")
    public R api12(@RequestParam("name") String name, @RequestParam("current") long current, @RequestParam("limit") long limit) {
        Page<Template> page = new Page<>(current, limit);
        QueryWrapper<Template> wrapper = new QueryWrapper<>();
        //判断条件值是否为空，如果不为空拼接条件
        if (!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name", name);
        }
        templateService.page(page, wrapper);
        long total = page.getTotal();//总记录数
        List<Template> records = page.getRecords(); //数据list集合
        return R.ok().data("total", total).data("rows", records);
    }

    @PostMapping("/test")
    public boolean api13(@RequestBody Map<String, Object> mp) {
        String str = JSON.toJSONString(mp.get("value"));
        return (mp.get("value") instanceof String);
    }

    @GetMapping("/reFormName")
    public RS api14(@RequestParam("id") String id, @RequestParam("name") String name) {
        try {
            myService.reFormName(id, name);
            return RS.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return RS.error().message(e.toString());
        }
    }

    @GetMapping("/reFormDetail")
    public RS api15(@RequestParam("id") String id, @RequestParam("detail") String detail) {
        try {
            myService.reFormDetail(id, detail);
            return RS.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return RS.error().message(e.toString());
        }
    }

    @PostMapping("/upload")
    public RS api16(@RequestBody MultipartFile file) {
        try {
            String name = myService.uploadImg(file);
            return RS.ok().message(name);
        } catch (Exception e) {
            e.printStackTrace();
            return RS.error().message(e.toString());
        }
    }

    @GetMapping(value = "/image/{name}",produces = "image/jpeg")
    public byte[] api17(@PathVariable String name) {
        return myService.getImage(name);
    }
}
