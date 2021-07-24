package mybatis.mate.dict.config;

import mybatis.mate.annotation.FieldDict;
import mybatis.mate.sets.IDataDict;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DataDict implements IDataDict {

    /**
     * 从数据库或缓存中获取
     */
    private Map<String, String> SEX_MAP = new ConcurrentHashMap<String, String>() {{
        put("0", "女");
        put("1", "男");
    }};

    @Override
    public String getNameByCode(FieldDict fieldDict, String code) {
        System.err.println("字段类型：" + fieldDict.type() + "，编码：" + code);
        return SEX_MAP.get(code);
    }
}
