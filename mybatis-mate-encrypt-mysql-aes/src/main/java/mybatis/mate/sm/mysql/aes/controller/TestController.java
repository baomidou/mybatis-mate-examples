package mybatis.mate.sm.mysql.aes.controller;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import lombok.AllArgsConstructor;
import mybatis.mate.sm.mysql.aes.entity.ComAttr;
import mybatis.mate.sm.mysql.aes.mapper.ComAttrMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * 测试控制器
 */
@RestController
@AllArgsConstructor
public class TestController {
    private ComAttrMapper comAttrMapper;

    @GetMapping("/test")
    public HashMap<String, Object> test() {
        String testId = "10086";
        ComAttr comAttr = comAttrMapper.selectById(testId);
        if (null == comAttr) {
            comAttr = new ComAttr(testId, "测试插入逻辑", "abc@163.com", "15312321111");
            comAttrMapper.insert(comAttr);
        }
        System.err.println(comAttr);

        ComAttr comAttr1 = new LambdaQueryChainWrapper<>(comAttrMapper).eq(ComAttr::getAttrId, testId).one();
        System.out.println(comAttr1.getAttrTitle());

        //输出的attrTitle属性性为解密后的
        return new HashMap<String, Object>(2) {{
            put("dbList", comAttrMapper.selectList(null));
            put("voList", comAttrMapper.selectVO());
        }};
    }

    @GetMapping("/test2")
    public List<ComAttr> test2() {
        return comAttrMapper.selectVO2();
    }
}