package formDesigner.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import formDesigner.mapper.FormMapper;
import formDesigner.entity.Form;
import formDesigner.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormServiceImpl  extends ServiceImpl<FormMapper, Form>  implements FormService {

    @Autowired
    private FormMapper formMapper;

    @Override
    public int deleteForm(int id) {
       //int result =  formMapper.deleteForm(id);
       return 1;
    }
}
