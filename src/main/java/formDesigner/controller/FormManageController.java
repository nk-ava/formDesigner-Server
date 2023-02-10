package formDesigner.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import formDesigner.entity.Form;
import formDesigner.entity.FormInfo;
import formDesigner.service.FormService;
import formDesigner.utils.R;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/form")
@CrossOrigin
public class FormManageController {
    @Autowired
    private FormService formService;
    //根据名字条件搜索表单
    @GetMapping("/getFormByName")
    public R getFormByName(@RequestParam long current,
                           @RequestParam long limit,
                           @RequestParam String name){
        //创建page对象
        Page<Form> pageForm = new Page<>(current,limit);
        //构建条件
        QueryWrapper<Form> wrapper = new QueryWrapper<>();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name",name);
        }
        formService.page(pageForm,wrapper);
        long total = pageForm.getTotal();//总记录数
        List<Form> records = pageForm.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    //新建表单
    @PostMapping("/addNewForm")
    public R addFormInfo(@RequestBody FormInfo formInfo){
      return R.ok();
    }

    //删除表单
    @DeleteMapping("/deleteForm/{id}")
    public R deleteForm(@PathVariable int  id){
        int result = formService.deleteForm(id);
            return R.ok().code(200).message("删除成功");
    }

    //修改表单
    @PostMapping("/editFormInfo")
    public R editFormInfo(@RequestBody FormInfo formInfo){
        return R.ok();
    }


    //查看表单详情
    @GetMapping("/getFormDetail")
    public R getFormDetail(@RequestParam int id){
        return R.ok();
    }

}
