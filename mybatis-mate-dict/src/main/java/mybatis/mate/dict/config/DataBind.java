package mybatis.mate.dict.config;

import mybatis.mate.annotation.FieldBind;
import mybatis.mate.sets.IDataBind;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DataBind implements IDataBind {

    /**
     * 从数据库或缓存中获取
     */
    private Map<String, String> SEX_MAP = new ConcurrentHashMap<String, String>() {{
        put("0", "女");
        put("1", "男");
    }};

    /**
     * 重写，获取枚举映射值
     */
    @Override
    public String getNameByCode(FieldBind fieldBind, Object source) {
        System.err.println("字段类型：" + fieldBind.type() + "，编码：" + source);
        return SEX_MAP.get(source);
    }
}
