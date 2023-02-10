package formDesigner.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import formDesigner.mapper.TempMapper;
import formDesigner.entity.TempInfo;
import formDesigner.service.TempService;
import org.springframework.stereotype.Service;

@Service
public class TempServiceImpl extends ServiceImpl<TempMapper, TempInfo> implements TempService {
}
