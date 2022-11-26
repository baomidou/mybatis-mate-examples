package mybatis.mate.encrypt.config;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * mybatis-plus扩展实现批量插入，只支持mysql
 */
public interface SpiceBaseMapper<T> extends BaseMapper<T> {

    /**
     * 批量插入
     * {@link com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn}
     *
     * @param entityList 要插入的数据
     * @return 成功插入的数据条数
     */
    int insertBatchSomeColumn(@Param("list") List<T> entityList);
}
