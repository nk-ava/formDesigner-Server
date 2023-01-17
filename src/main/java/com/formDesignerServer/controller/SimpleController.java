package com.formDesignerServer.controller;

import com.formDesignerServer.service.MyService;
import com.formDesignerServer.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/example")
public class SimpleController {
    @Autowired
    private MyService myService;

    @PostMapping("/saveForm")
    public R api0(@RequestBody Map<String, Object> mp) {
        String temp_id, time;
        List<Map<String, Object>> list;
        if(mp.get("tempId")!=null) temp_id = mp.get("tempId").toString();
        else temp_id = null;
        if (mp.get("subTime") != null) time = mp.get("subTime").toString();
        else return R.error().message("缺少参数subTime");
        if (mp.get("list") != null) list = (List<Map<String, Object>>) mp.get("list");
        else return R.error().message("缺少参数list");
        try {
            myService.saveForm(temp_id, time, list,(String)mp.get("name"));
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().message(e.toString());
        }
    }

    @PostMapping("/saveTemp")
    public R api1(@RequestBody Map<String, Object> mp) {
        String save_time, name;
        List<Map<String, Object>> list;
        if (mp.get("saveTime") != null) save_time = mp.get("saveTime").toString();
        else return R.error().message("缺少参数saveTime");
        if (mp.get("name") != null) name = mp.get("name").toString();
        else return R.error().message("缺少参数name");
        if (mp.get("list") != null) list = (List<Map<String, Object>>) mp.get("list");
        else return R.error().message("缺少参数list");
        try {
            myService.saveTemplate(save_time, name, list, null);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().message(e.toString());
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
    public R api4(@RequestParam("id") String id){
        try{
            myService.deleteTemp(id);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error().message(e.toString());
        }
    }

    @GetMapping("/deleteFormById")
    public R api5(@RequestParam("id") String id){
        try{
            myService.deleteForm(id);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error().message(e.toString());
        }
    }

    @PostMapping("/updateFormById")
    public R api6(@RequestBody Map<String,Object> mp){
        String form_id,time;
        List<Map<String,Object>> list;
        if(mp.get("formId")!=null) form_id = mp.get("formId").toString();
        else return R.error().message("缺少参数formId");
        if(mp.get("subTime")!=null) time = mp.get("subTime").toString();
        else return R.error().message("缺少参数subTime");
        if(mp.get("list")!=null) list = (List<Map<String,Object>>) mp.get("list");
        else return R.error().message("缺少参数list");
        try{
            myService.updateForm(form_id,time,list);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error().message(e.toString());
        }
    }

    @PostMapping("/updateTempById")
    public R api7(@RequestBody Map<String,Object> mp){
        String temp_id,time;
        List<Map<String,Object>> list;
        if(mp.get("tempId")!=null) temp_id = mp.get("tempId").toString();
        else return R.error().message("缺少参数tempId");
        if(mp.get("saveTime")!=null) time = mp.get("saveTime").toString();
        else return R.error().message("缺少参数saveTime");
        if(mp.get("list")!=null) list = (List<Map<String,Object>>) mp.get("list");
        else return R.error().message("缺少参数list");
        try{
            myService.updateTemp(temp_id,time,list);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error().message(e.toString());
        }
    }

    @GetMapping("/getAllForm")
    public List<Map<String,Object>> api8(){
        return myService.getAllForm();
    }

    @GetMapping("/getFormById")
    public List<Map<String,Object>> api9(@RequestParam("id")String id){
        return myService.getFormById(id);
    }
}
