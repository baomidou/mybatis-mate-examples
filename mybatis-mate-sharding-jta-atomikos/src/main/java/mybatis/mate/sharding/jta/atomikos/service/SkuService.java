package mybatis.mate.sharding.jta.atomikos.service;

import lombok.AllArgsConstructor;
import mybatis.mate.sharding.jta.atomikos.entity.Sku;
import mybatis.mate.sharding.jta.atomikos.mapper.SkuMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SkuService {
    private SkuMapper skuMapper;

    /**
     * 减少库存
     */
    public boolean reduceStock(Long id, Integer stock, boolean error) {
        if(error) {
            throw new RuntimeException("制造一个异常，测试事务");
        }
        Sku sku = new Sku();
        sku.setId(id);
        sku.setStock(stock);
        return skuMapper.updateById(sku) > 0;
    }
}
