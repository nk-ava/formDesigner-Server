package formDesigner.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import formDesigner.entity.*;
import formDesigner.mapper.*;
import formDesigner.utils.CompareList;
import formDesigner.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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
    @Autowired
    private DatePickerMapper datePickerMapper;
    @Autowired
    private TimePickerMapper timePickerMapper;
    @Autowired
    private RateMapper rateMapper;
    @Autowired
    private LinkMapper linkMapper;
    @Autowired
    private InputNumberMapper inputNumberMapper;
    @Autowired
    private CheckBoxMapper checkBoxMapper;
    @Autowired
    private RadioMapper radioMapper;
    @Autowired
    private EditorMapper editorMapper;
    @Autowired
    private ColorPickerMapper colorPickerMapper;
    @Autowired
    private CollapseMapper collapseMapper;
    @Autowired
    private QrCodeMapper qrCodeMapper;
    @Autowired
    private TextMapper textMapper;
    @Autowired
    private BarCodeMapper barCodeMapper;
    @Autowired
    private UploadMapper uploadMapper;
    private List<String> valueNum = Arrays.asList("slider", "rate", "inputNumber");
    private Pattern pattern = Pattern.compile("^\\[(.)*\\]$");

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveTemplate(String updateTime, String name, List<Map<String, Object>> list, String temp_id) throws Exception {
        String id = (temp_id == null ? (new Date()).getTime() + "" : temp_id);
        List<String> components = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> comp = list.get(i);
            String type = (String) comp.get("compIcon");
            if (components.indexOf(type) == -1) components.add(type);
            comp.put("template_id", id);
            comp.put("position", i);
            saveComps(comp, type);
        }
        Template template = new Template(id, name, toFormatDate(Long.parseLong(updateTime)), arrayToString(components));
        templateMapper.insert(template);
        return id;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveForm(String temp_id, String formName, String subTime, List<Map<String, Object>> list, String detail, String name) throws Exception {
        if (temp_id == null) temp_id = saveTemplate(subTime, name == null ? "temp_" + subTime : name, list, null);
        else {
            if (templateMapper.selectList(new QueryWrapper<Template>().eq("id", temp_id)).size() == 0)
                throw new Exception("没有找到[id]：" + temp_id + "的模板,无法插入表单。如果这是新模板不需要传tempId");
        }
        String form_id = (new Date()).getTime() + "";
        formMapper.insert(new Form(form_id, formName, temp_id, toFormatDate(Long.parseLong(subTime)), detail));
        for (Map<String, Object> mp : list) {
            if (mp.get("value") == null) continue;
            String value;
            if (mp.get("value") instanceof String) value = (String) mp.get("value");
            else value = JSON.toJSONString(mp.get("value"));
            dataMapper.insert(new CompValue(form_id, (String) mp.get("id"), value, (String) mp.get("compIcon")));
        }
    }

    @Override
    public List<Map<String, Object>> getAllFormInfo() {
        List<Map<String, Object>> ans = new ArrayList<>();
        List<Template> temp_list = templateMapper.selectList(null);
        for (Template template : temp_list) {
            Map<String, Object> mp = new HashMap<>();
            mp.put("tempId", template.getId());
            mp.put("name", template.getName());
            mp.put("lastModify", template.getDate());
            List<Map<String, Object>> formList = new ArrayList<>();
            List<Map<String, Object>> queryForm = myMapper.findFormByTemp(template.getId());
            for (Map<String, Object> str : queryForm) {
                Map<String, Object> nmp = new HashMap<>();
                nmp.put("id", str.get("id"));
                nmp.put("subTime", str.get("submit_time"));
                String[] comps = template.getComponents().split(",");
                List<Map<String, Object>> compList = getFormComps((String) str.get("id"), template.getId(), comps);
                nmp.put("compList", compList);
                formList.add(nmp);
            }
            mp.put("formList", formList);
            ans.add(mp);
        }
        return ans;
    }

    @Override
    public List<Map<String, Object>> getAllTemp() {
        return myMapper.getAllTemp();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTemp(String temp_id, String time, List<Map<String, Object>> list) throws Exception {
        Template template = templateMapper.selectList(new QueryWrapper<Template>().eq("id", temp_id)).get(0);
        deleteTemp(temp_id);
        saveTemplate(time, template.getName(), list, template.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTemp(String temp_id) throws Exception {
        Template template = templateMapper.selectList(new QueryWrapper<Template>().eq("id", temp_id)).get(0);
        templateMapper.delete(new QueryWrapper<Template>().eq("id", temp_id));
        String[] comps = template.getComponents().split(",");
        for (String comp : comps) deleteComps(comp, temp_id);
        List<Map<String, Object>> list = myMapper.findFormByTemp(temp_id);
        formMapper.delete(new QueryWrapper<Form>().eq("template_id", temp_id));
        for (Map<String, Object> form_id : list)
            dataMapper.delete(new QueryWrapper<CompValue>().eq("form_id", form_id.get("id")));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateForm(String form_id, String time, List<Map<String, Object>> list) throws Exception {
        Form f = new Form();
        f.setId(form_id);
        f.setSubmitTime(toFormatDate(Long.parseLong(time)));
        formMapper.updateById(f);
        for (Map<String, Object> comp : list) {
            if (comp.get("value") == null) continue;
            String value;
            if (comp.get("value") instanceof String) value = (String) comp.get("value");
            else value = JSON.toJSONString(comp.get("value"));
            if (myMapper.updateValue(form_id, (String) comp.get("compIcon"), (String) comp.get("id"), value) == 0) {
                dataMapper.insert(new CompValue(form_id, (String) comp.get("id"), value, (String) comp.get("compIcon")));
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteForm(String form_id) throws Exception {
        formMapper.delete(new QueryWrapper<Form>().eq("id", form_id));
        dataMapper.delete(new QueryWrapper<CompValue>().eq("form_id", form_id));
    }

    @Override
    public List<Map<String, Object>> getAllForm() {
        List<Map<String, Object>> mp = formMapper.selectMaps(null);
        return mp;
    }

    @Override
    public List<Map<String, Object>> getFormById(String id) {
        Form form = formMapper.selectOne(new QueryWrapper<Form>().eq("id", id));
        Template template = templateMapper.selectOne(new QueryWrapper<Template>().eq("id", form.getTemplateId()));
        String[] comps = template.getComponents().split(",");
        return getFormComps(id, template.getId(), comps);
    }

    @Override
    public List<Map<String, Object>> getTempById(String id) {
        Template template = templateMapper.selectById(id);
        String[] comps = template.getComponents().split(",");
        List<Map<String, Object>> list = new ArrayList<>();
        for (String comp : comps) list.addAll(queryComps(comp, id));
        Collections.sort(list, new CompareList());
        list.forEach(item -> {
            item.remove("templateId");
            item.remove("position");
        });
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTempName(String temp_id, String name) throws Exception {
        Template template = new Template();
        template.setId(temp_id);
        template.setName(name);
        templateMapper.updateById(template);
    }

    @Override
    public List<Map<String, Object>> getTempByName(String name) {
        List<Map<String, Object>> mp;
        name = name.trim();
        if (!name.equals("")) mp = myMapper.fuzzyGetTempByName(name);
        else mp = myMapper.getAllTemp();
        for (Map<String, Object> m : mp) {
            m.remove("components");
            List<Map<String, Object>> list = myMapper.findFormByTemp((String) m.get("id"));
            m.put("formList", list);
        }
        return mp;
    }

    @Override
    public void test() {
        System.out.println("success!!");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reFormName(String formId, String name) throws Exception {
        Form form = new Form();
        form.setId(formId);
        form.setName(name);
        formMapper.updateById(form);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reFormDetail(String formId, String detail) throws Exception {
        Form form = new Form();
        form.setId(formId);
        form.setDetail(detail);
        formMapper.updateById(form);
    }

    @Override
    public String uploadImg(MultipartFile multipartFile) throws Exception {
        byte[] bs = multipartFile.getBytes();
        String name = "api_img_" + (new Date()).getTime();
        File file = new File("c:/uploadImg/");
        if (!file.exists()) file.mkdir();
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("c:/uploadImg/" + name))) {
            bos.write(bs);
            return "http://81.68.210.170:8081/FORM/image/"+name;
        }
    }

    @Override
    public byte[] getImage(String name) {
        File file = new File("c:/uploadImg");
        if (!file.exists()) file.mkdir();
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("c:/uploadImg/" + name))) {
            byte[] bytes = new byte[bis.available()];
            bis.read(bytes);
            return bytes;
        } catch (Exception e) {
            return null;
        }
    }

    private String toFormatDate(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time);
        return simpleDateFormat.format(date);
    }

    private void saveComps(Map<String, Object> mp, String type) {
        switch (type) {
            case "button":
                Button button = JSON.parseObject(JSON.toJSONString(mp), Button.class);
                buttonMapper.insert(button);
                break;
            case "switch":
                Switch s = JSON.parseObject(JSON.toJSONString(mp), Switch.class);
                switchMapper.insert(s);
                break;
            case "divider":
                Divider divider = JSON.parseObject(JSON.toJSONString(mp), Divider.class);
                dividerMapper.insert(divider);
                break;
            case "input":
                Input input = JSON.parseObject(JSON.toJSONString(mp), Input.class);
                inputMapper.insert(input);
                break;
            case "select":
                Selector selector = JSON.parseObject(JSON.toJSONString(mp), Selector.class);
                selectorMapper.insert(selector);
                break;
            case "slider":
                Slider slider = JSON.parseObject(JSON.toJSONString(mp), Slider.class);
                sliderMapper.insert(slider);
                break;
            case "textarea":
                Textarea textarea = JSON.parseObject(JSON.toJSONString(mp), Textarea.class);
                textareaMapper.insert(textarea);
                break;
            case "date":
                DatePicker datePicker = JSON.parseObject(JSON.toJSONString(mp), DatePicker.class);
                datePickerMapper.insert(datePicker);
                break;
            case "inputNumber":
                InputNumber inputNumber = JSON.parseObject(JSON.toJSONString(mp), InputNumber.class);
                inputNumberMapper.insert(inputNumber);
                break;
            case "link":
                Link link = JSON.parseObject(JSON.toJSONString(mp), Link.class);
                linkMapper.insert(link);
                break;
            case "rate":
                Rate rate = JSON.parseObject(JSON.toJSONString(mp), Rate.class);
                rateMapper.insert(rate);
                break;
            case "time":
                TimePicker timePicker = JSON.parseObject(JSON.toJSONString(mp), TimePicker.class);
                timePickerMapper.insert(timePicker);
                break;
            case "radio":
                Radio radio = JSON.parseObject(JSON.toJSONString(mp), Radio.class);
                radioMapper.insert(radio);
                break;
            case "checkbox":
                CheckBox checkBox = JSON.parseObject(JSON.toJSONString(mp), CheckBox.class);
                checkBoxMapper.insert(checkBox);
                break;
            case "editor":
                Editor editor = JSON.parseObject(JSON.toJSONString(mp), Editor.class);
                editorMapper.insert(editor);
                break;
            case "colorpicker":
                ColorPicker colorPicker = JSON.parseObject(JSON.toJSONString(mp), ColorPicker.class);
                colorPickerMapper.insert(colorPicker);
                break;
            case "collapse":
                Collapse collapse = JSON.parseObject(JSON.toJSONString(mp), Collapse.class);
                collapseMapper.insert(collapse);
                break;
            case "qrcode":
                QrCode qrCode = JSON.parseObject(JSON.toJSONString(mp), QrCode.class);
                qrCodeMapper.insert(qrCode);
                break;
            case "text":
                Text text = JSON.parseObject(JSON.toJSONString(mp), Text.class);
                textMapper.insert(text);
                break;
            case "barcode":
                BarCode barCode = JSON.parseObject(JSON.toJSONString(mp), BarCode.class);
                barCodeMapper.insert(barCode);
                break;
            case "att":
                Upload upload = JSON.parseObject(JSON.toJSONString(mp), Upload.class);
                uploadMapper.insert(upload);
                break;
            default:
                break;
        }
    }

    private void deleteComps(String type, String temp_id) {
        switch (type) {
            case "button":
                buttonMapper.delete(new QueryWrapper<Button>().eq("template_id", temp_id));
                break;
            case "switch":
                switchMapper.delete(new QueryWrapper<Switch>().eq("template_id", temp_id));
                break;
            case "divider":
                dividerMapper.delete(new QueryWrapper<Divider>().eq("template_id", temp_id));
                break;
            case "input":
                inputMapper.delete(new QueryWrapper<Input>().eq("template_id", temp_id));
                break;
            case "select":
                selectorMapper.delete(new QueryWrapper<Selector>().eq("template_id", temp_id));
                break;
            case "slider":
                sliderMapper.delete(new QueryWrapper<Slider>().eq("template_id", temp_id));
                break;
            case "textarea":
                textareaMapper.delete(new QueryWrapper<Textarea>().eq("template_id", temp_id));
                break;
            case "date":
                datePickerMapper.delete(new QueryWrapper<DatePicker>().eq("template_id", temp_id));
                break;
            case "inputNumber":
                inputNumberMapper.delete(new QueryWrapper<InputNumber>().eq("template_id", temp_id));
                break;
            case "link":
                linkMapper.delete(new QueryWrapper<Link>().eq("template_id", temp_id));
                break;
            case "rate":
                rateMapper.delete(new QueryWrapper<Rate>().eq("template_id", temp_id));
                break;
            case "time":
                timePickerMapper.delete(new QueryWrapper<TimePicker>().eq("template_id", temp_id));
                break;
            case "radio":
                radioMapper.delete(new QueryWrapper<Radio>().eq("template_id", temp_id));
                break;
            case "checkbox":
                checkBoxMapper.delete(new QueryWrapper<CheckBox>().eq("template_id", temp_id));
                break;
            case "editor":
                editorMapper.delete(new QueryWrapper<Editor>().eq("template_id", temp_id));
                break;
            case "colorpicker":
                colorPickerMapper.delete(new QueryWrapper<ColorPicker>().eq("template_id", temp_id));
                break;
            case "collapse":
                collapseMapper.delete(new QueryWrapper<Collapse>().eq("template_id", temp_id));
                break;
            case "qrcode":
                qrCodeMapper.delete(new QueryWrapper<QrCode>().eq("template_id", temp_id));
                break;
            case "text":
                textMapper.delete(new QueryWrapper<Text>().eq("template_id", temp_id));
                break;
            case "barcode":
                barCodeMapper.delete(new QueryWrapper<BarCode>().eq("template_id", temp_id));
                break;
            case "att":
                uploadMapper.delete(new QueryWrapper<Upload>().eq("template_id", temp_id));
                break;
            default:
                break;
        }
    }

    private String arrayToString(List<String> list) {
        String ans = "";
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) ans += ",";
            ans += list.get(i);
        }
        return ans;
    }

    private List<Map<String, Object>> queryComps(String type, String temp_id) {
        switch (type) {
            case "button":
                List<Button> button = buttonMapper.selectList(new QueryWrapper<Button>().eq("template_id", temp_id));
                return (List<Map<String, Object>>) JSONArray.parseArray(JSONObject.toJSONString(button));
            case "switch":
                List<Switch> aSwitch = switchMapper.selectList(new QueryWrapper<Switch>().eq("template_id", temp_id));
                return (List<Map<String, Object>>) JSONArray.parseArray(JSONObject.toJSONString(aSwitch));
            case "divider":
                List<Divider> divider = dividerMapper.selectList(new QueryWrapper<Divider>().eq("template_id", temp_id));
                return (List<Map<String, Object>>) JSONArray.parseArray(JSONObject.toJSONString(divider));
            case "input":
                List<Input> input = inputMapper.selectList(new QueryWrapper<Input>().eq("template_id", temp_id));
                return (List<Map<String, Object>>) JSONArray.parseArray(JSONObject.toJSONString(input));
            case "select":
                List<Selector> selector = selectorMapper.selectList(new QueryWrapper<Selector>().eq("template_id", temp_id));
                return (List<Map<String, Object>>) JSONArray.parseArray(JSONObject.toJSONString(selector));
            case "slider":
                List<Slider> slider = sliderMapper.selectList(new QueryWrapper<Slider>().eq("template_id", temp_id));
                return (List<Map<String, Object>>) JSONArray.parseArray(JSONObject.toJSONString(slider));
            case "textarea":
                List<Textarea> textarea = textareaMapper.selectList(new QueryWrapper<Textarea>().eq("template_id", temp_id));
                return (List<Map<String, Object>>) JSONArray.parseArray(JSONObject.toJSONString(textarea));
            case "date":
                List<DatePicker> datePickerList = datePickerMapper.selectList(new QueryWrapper<DatePicker>().eq("template_id", temp_id));
                return (List<Map<String, Object>>) JSONArray.parseArray(JSONObject.toJSONString(datePickerList));
            case "inputNumber":
                List<InputNumber> inputNumbers = inputNumberMapper.selectList(new QueryWrapper<InputNumber>().eq("template_id", temp_id));
                return (List<Map<String, Object>>) JSONArray.parseArray(JSONObject.toJSONString(inputNumbers));
            case "link":
                List<Link> links = linkMapper.selectList(new QueryWrapper<Link>().eq("template_id", temp_id));
                return (List<Map<String, Object>>) JSONArray.parseArray(JSONObject.toJSONString(links));
            case "rate":
                List<Rate> rates = rateMapper.selectList(new QueryWrapper<Rate>().eq("template_id", temp_id));
                return (List<Map<String, Object>>) JSONArray.parseArray(JSONObject.toJSONString(rates));
            case "time":
                List<TimePicker> timePickers = timePickerMapper.selectList(new QueryWrapper<TimePicker>().eq("template_id", temp_id));
                return (List<Map<String, Object>>) JSONArray.parseArray(JSONObject.toJSONString(timePickers));
            case "radio":
                List<Radio> radios = radioMapper.selectList(new QueryWrapper<Radio>().eq("template_id", temp_id));
                return (List<Map<String, Object>>) JSONArray.parseArray(JSONObject.toJSONString(radios));
            case "checkbox":
                List<CheckBox> checkBoxes = checkBoxMapper.selectList(new QueryWrapper<CheckBox>().eq("template_id", temp_id));
                return (List<Map<String, Object>>) JSONArray.parseArray(JSONObject.toJSONString(checkBoxes));
            case "editor":
                List<Editor> editors = editorMapper.selectList(new QueryWrapper<Editor>().eq("template_id", temp_id));
                return (List<Map<String, Object>>) JSONArray.parseArray(JSONObject.toJSONString(editors));
            case "colorpicker":
                List<ColorPicker> colorPickers = colorPickerMapper.selectList(new QueryWrapper<ColorPicker>().eq("template_id", temp_id));
                return (List<Map<String, Object>>) JSONArray.parseArray(JSONObject.toJSONString(colorPickers));
            case "collapse":
                List<Collapse> collapses = collapseMapper.selectList(new QueryWrapper<Collapse>().eq("template_id", temp_id));
                return (List<Map<String, Object>>) JSONArray.parseArray(JSONObject.toJSONString(collapses));
            case "qrcode":
                List<QrCode> qrCodes = qrCodeMapper.selectList(new QueryWrapper<QrCode>().eq("template_id", temp_id));
                return (List<Map<String, Object>>) JSONArray.parseArray(JSONObject.toJSONString(qrCodes));
            case "text":
                List<Text> texts = textMapper.selectList(new QueryWrapper<Text>().eq("template_id", temp_id));
                return (List<Map<String, Object>>) JSONArray.parseArray(JSONObject.toJSONString(texts));
            case "barcode":
                List<BarCode> barCodes = barCodeMapper.selectList(new QueryWrapper<BarCode>().eq("template_id", temp_id));
                return (List<Map<String, Object>>) JSONArray.parseArray(JSONObject.toJSONString(barCodes));
            case "att":
                List<Upload> uploads = uploadMapper.selectList(new QueryWrapper<Upload>().eq("template_id", temp_id));
                return (List<Map<String, Object>>) JSONArray.parseArray(JSONObject.toJSONString(uploads));
            default:
                return null;
        }
    }

    private List<Map<String, Object>> getFormComps(String form_id, String temp_id, String[] comps) {
        List<Map<String, Object>> compList = new ArrayList<>();
        for (String comp : comps) compList.addAll(queryComps(comp, temp_id));
        Collections.sort(compList, new CompareList());
        compList.forEach(item -> {
            String v = myMapper.getCompValue(form_id, (String) item.get("id"), (String) item.get("compIcon"));
            if (v != null) {
                if (valueNum.contains(item.get("compIcon"))) item.put("value", Double.parseDouble(v));
                else if (pattern.matcher(v).matches()) item.put("value", JSONObject.parseObject(v, ArrayList.class));
                else if (v.equals("false")) item.put("value", false);
                else if (v.equals("true")) item.put("value", true);
                else item.put("value", v);
            }
            item.remove("templateId");
            item.remove("position");
        });
        return compList;
    }
}