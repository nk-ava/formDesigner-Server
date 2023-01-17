package com.formDesignerServer.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.formDesignerServer.mapper.*;
import com.formDesignerServer.entity.*;
import com.formDesignerServer.entity.Template;
import com.formDesignerServer.service.MyService;
import com.formDesignerServer.utils.CompareList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.Oneway;
import javax.xml.soap.Text;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class MyServiceImpl implements MyService {
    @Autowired
    private TemplateMapper templateMapper;
    @Autowired
    private ButtonMapper buttonMapper;
    @Autowired
    private SwitchMapper switchMapper;
    @Autowired
    private DataMapper dataMapper;
    @Autowired
    private FormMapper formMapper;
    @Autowired
    private MyMapper myMapper;
    @Autowired
    private DividerMapper dividerMapper;
    @Autowired
    private InputMapper inputMapper;
    @Autowired
    private SelectorMapper selectorMapper;
    @Autowired
    private SliderMapper sliderMapper;
    @Autowired
    private TextareaMapper textareaMapper;
    private Pattern pattern = Pattern.compile("^[-+]?[\\d]+$");

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveTemplate(String updateTime, String name, List<Map<String, Object>> list,String temp_id) throws Exception {
        String id = (temp_id==null?(new Date()).getTime()+"":temp_id);
        List<String> components = new ArrayList<>();
        for(int i=0;i< list.size();i++){
            Map<String,Object> comp = list.get(i);
            String type = (String) comp.get("compIcon");
            if(components.indexOf(type)==-1) components.add(type);
            comp.put("template_id", id);
            comp.put("index",i);
            saveComps(comp,type);
        }
        Template template= new Template(id,name,toFormatDate(Long.parseLong(updateTime)),arrayToString(components));
        templateMapper.insert(template);
        return id;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveForm(String temp_id, String subTime, List<Map<String, Object>> list,String name) throws Exception {
        if (temp_id==null) temp_id = saveTemplate(subTime,name==null?"temp_"+subTime:name, list, null);
        else {
            if(templateMapper.selectList(new QueryWrapper<Template>().eq("id",temp_id)).size()==0)
                throw new Exception("没有找到[id]："+temp_id+"的模板,无法插入表单。如果这是新模板不需要传tempId");
        }
        String form_id = (new Date()).getTime()+"";
        formMapper.insert(new Form(form_id,temp_id,toFormatDate(Long.parseLong(subTime))));
        for(Map<String,Object> mp : list){
            if(mp.get("value")==null) continue;
            String value = mp.get("value").toString();
            dataMapper.insert(new Data(form_id,(String) mp.get("id"),value,(String) mp.get("compIcon")));
        }
    }

    @Override
    public List<Map<String, Object>> getAllFormInfo() {
        List<Map<String,Object>> ans = new ArrayList<>();
        List<Template> temp_list = templateMapper.selectList(null);
        for(Template template : temp_list){
            Map<String,Object> mp = new HashMap<>();
            mp.put("tempId",template.getId());
            mp.put("name",template.getName());
            mp.put("lastModify",template.getDate());
            List<Map<String,Object>> formList = new ArrayList<>();
            List<Map<String,Object>> queryForm = myMapper.findFormByTemp(template.getId());
            for(Map<String,Object> str : queryForm){
                Map<String,Object> nmp = new HashMap<>();
                nmp.put("id",str.get("id"));
                nmp.put("subTime",str.get("submit_time"));
                String[] comps = template.getComponents().split(",");
                List<Map<String, Object>> compList = getFormComps((String) str.get("id"),template.getId(),comps);
                nmp.put("compList",compList);
                formList.add(nmp);
            }
            mp.put("formList",formList);
            ans.add(mp);
        }
        return ans;
    }

    @Override
    public List<Map<String,Object>> getAllTemp() {
        return myMapper.getAllTemp();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTemp(String temp_id, String time, List<Map<String, Object>> list) throws Exception {
        Template template = templateMapper.selectList(new QueryWrapper<Template>().eq("id",temp_id)).get(0);
        deleteTemp(temp_id);
        saveTemplate(time,template.getName(),list,template.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTemp(String temp_id) throws Exception {
        Template template = templateMapper.selectList(new QueryWrapper<Template>().eq("id",temp_id)).get(0);
        templateMapper.delete(new QueryWrapper<Template>().eq("id",temp_id));
        String[] comps = template.getComponents().split(",");
        for(String comp : comps) deleteComps(comp,temp_id);
        List<Map<String,Object>> list = myMapper.findFormByTemp(temp_id);
        formMapper.delete(new QueryWrapper<Form>().eq("template_id",temp_id));
        for(Map<String,Object> form_id : list) dataMapper.delete(new QueryWrapper<Data>().eq("form_id",form_id.get("id")));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateForm(String form_id,String time, List<Map<String, Object>> list) throws Exception {
        Form f = new Form();
        f.setId(form_id);
        f.setSubmitTime(toFormatDate(Long.parseLong(time)));
        formMapper.updateById(f);
        for(Map<String,Object> comp : list){
            if(comp.get("value")==null) continue;
            myMapper.updateValue(form_id,(String) comp.get("compIcon"),(String) comp.get("id"),comp.get("value").toString());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteForm(String form_id) throws Exception {
        formMapper.delete(new QueryWrapper<Form>().eq("id",form_id));
        dataMapper.delete(new QueryWrapper<Data>().eq("form_id",form_id));
    }

    @Override
    public List<Map<String, Object>> getAllForm() {
        List<Map<String,Object>> mp = myMapper.getAllTemp();
        for(Map<String,Object> m : mp){
            List<Map<String, Object>> list = myMapper.findFormByTemp((String) m.get("id"));
            m.put("formList",list);
        }
        return mp;
    }

    @Override
    public List<Map<String, Object>> getFormById(String id) {
        Form form = formMapper.selectOne(new QueryWrapper<Form>().eq("id",id));
        Template template = templateMapper.selectOne(new QueryWrapper<Template>().eq("id",form.getTemplateId()));
        String[] comps = template.getComponents().split(",");
        return getFormComps(id,template.getId(),comps);
    }

    @Override
    public void test() {
        System.out.println("success!!");
    }

    private String toFormatDate(long time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date(time);
        return simpleDateFormat.format(date);
    }
    private void saveComps(Map<String,Object> mp,String type){
        switch (type){
            case "button":
                Button button = JSON.parseObject(JSON.toJSONString(mp),Button.class);
                buttonMapper.insert(button);
                break;
            case "switch":
                Switch s = JSON.parseObject(JSON.toJSONString(mp),Switch.class);
                switchMapper.insert(s);
                break;
            case "divider":
                Divider divider = JSON.parseObject(JSON.toJSONString(mp),Divider.class);
                dividerMapper.insert(divider);
                break;
            case "input":
                Input input = JSON.parseObject(JSON.toJSONString(mp),Input.class);
                inputMapper.insert(input);
                break;
            case "select":
                Selector selector = JSON.parseObject(JSON.toJSONString(mp),Selector.class);
                selectorMapper.insert(selector);
                break;
            case "slider":
                Slider slider = JSON.parseObject(JSON.toJSONString(mp),Slider.class);
                sliderMapper.insert(slider);
                break;
            case "textarea":
                Textarea textarea = JSON.parseObject(JSON.toJSONString(mp),Textarea.class);
                textareaMapper.insert(textarea);
                break;
            default:
                break;
        }
    }
    private void deleteComps(String type,String temp_id){
        switch (type){
            case "button":
                buttonMapper.delete(new QueryWrapper<Button>().eq("template_id",temp_id));
                break;
            case "switch":
                switchMapper.delete(new QueryWrapper<Switch>().eq("template_id",temp_id));
                break;
            case "divider":
                dividerMapper.delete(new QueryWrapper<Divider>().eq("template_id",temp_id));
                break;
            case "input":
                inputMapper.delete(new QueryWrapper<Input>().eq("template_id",temp_id));
                break;
            case "select":
                selectorMapper.delete(new QueryWrapper<Selector>().eq("template_id",temp_id));
                break;
            case "slider":
                sliderMapper.delete(new QueryWrapper<Slider>().eq("template_id",temp_id));
                break;
            case "textarea":
                textareaMapper.delete(new QueryWrapper<Textarea>().eq("template_id",temp_id));
                break;
            default:break;
        }
    }
    private String arrayToString(List<String> list){
        String ans = "";
        for(int i=0;i<list.size();i++){
            if(i!=0) ans+= ",";
            ans+=list.get(i);
        }
        return ans;
    }
    private List<Map<String,Object>> queryComps(String type,String temp_id){
        switch (type){
            case "button":
                List<Button> button = buttonMapper.selectList(new QueryWrapper<Button>().eq("template_id",temp_id));
                return (List<Map<String,Object>>) JSONArray.parseArray(JSONObject.toJSONString(button));
            case "switch":
                List<Switch> aSwitch = switchMapper.selectList(new QueryWrapper<Switch>().eq("template_id",temp_id));
                return (List<Map<String,Object>>)JSONArray.parseArray(JSONObject.toJSONString(aSwitch));
            case "divider":
                List<Divider> divider = dividerMapper.selectList(new QueryWrapper<Divider>().eq("template_id",temp_id));
                return (List<Map<String,Object>>)JSONArray.parseArray(JSONObject.toJSONString(divider));
            case "input":
                List<Input> input = inputMapper.selectList(new QueryWrapper<Input>().eq("template_id",temp_id));
                return (List<Map<String,Object>>)JSONArray.parseArray(JSONObject.toJSONString(input));
            case "select":
                List<Selector> selector = selectorMapper.selectList(new QueryWrapper<Selector>().eq("template_id",temp_id));
                return (List<Map<String,Object>>)JSONArray.parseArray(JSONObject.toJSONString(selector));
            case "slider":
                List<Slider> slider = sliderMapper.selectList(new QueryWrapper<Slider>().eq("template_id",temp_id));
                return (List<Map<String,Object>>)JSONArray.parseArray(JSONObject.toJSONString(slider));
            case "textarea":
                List<Textarea> textarea = textareaMapper.selectList(new QueryWrapper<Textarea>().eq("template_id",temp_id));
                return (List<Map<String,Object>>)JSONArray.parseArray(JSONObject.toJSONString(textarea));
            default:
                return null;
        }
    }
    private List<Map<String,Object>> getFormComps(String form_id,String temp_id,String[] comps){
        List<Map<String, Object>> compList = new ArrayList<>();
        for(String comp : comps) compList.addAll(queryComps(comp,temp_id));
        Collections.sort(compList, new CompareList());
        compList.forEach(item->{
            String v = myMapper.getCompValue(form_id,(String) item.get("id"),(String) item.get("compIcon"));
            if(v.equals("false")) item.put("value",false);
            else if(v.equals("true")) item.put("value",true);
            else if(pattern.matcher(v).matches()) item.put("value",Integer.parseInt(v));
            else item.put("value",v);
            item.remove("template_id");
            item.remove("position");
        });
        return compList;
    }
}
