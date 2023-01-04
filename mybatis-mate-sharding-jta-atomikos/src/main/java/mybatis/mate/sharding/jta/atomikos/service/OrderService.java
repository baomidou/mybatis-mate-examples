package mybatis.mate.sharding.jta.atomikos.service;

import lombok.AllArgsConstructor;
import mybatis.mate.sharding.jta.atomikos.entity.Order;
import mybatis.mate.sharding.jta.atomikos.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class OrderService {
    public OrderMapper orderMapper;

    /**
     * 下单
     */
    public boolean createOrder(Long skuId, Integer quantity) {
        Order order = new Order();
        order.setSkuId(skuId);
        order.setQuantity(quantity);
        order.setPrice(BigDecimal.valueOf(6000));
        return orderMapper.insert(order) > 0;
    }
}
