package formDesigner.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import formDesigner.entity.Template;
import formDesigner.mapper.TemplateMapper;
import formDesigner.service.TemplateService;
import org.springframework.stereotype.Service;

@Service
public class TemplateServiceImpl extends ServiceImpl<TemplateMapper, Template> implements TemplateService {
}
