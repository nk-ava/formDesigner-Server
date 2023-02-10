package formDesigner.controller;

import formDesigner.entity.FormInfo;
import formDesigner.entity.TempInfo;

import formDesigner.utils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/temp")
@CrossOrigin
public class TempManageController {
    //新建表单模板
    @PostMapping("/addNewTemp")
    public R addCourseInfo(@RequestBody FormInfo TempInfo) {
        return R.ok();

    }

    //删除表单模板
    @DeleteMapping("/deleteTemp/{id}")
    public R deleteForm(@PathVariable int  id){
        return R.ok();

    }


    //修改表单模板
    @PostMapping("/EditTempInfo")
    public R editTempInfo(@RequestBody TempInfo tempInfo) {
        return R.ok();

    }

    //根据名字条件搜索模板模板
    @GetMapping("/getTempByName")
    public R getTempByName(@RequestParam String name){
        return R.ok();

    }

    //查看表单模板详情
    @GetMapping("/getTempDetail")
    public R getTempDetail(@RequestParam int id){
        return R.ok();

    }

}
