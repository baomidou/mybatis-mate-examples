package mybatis.mate.datascope.config;

import mybatis.mate.datascope.AbstractDataScopeProvider;
import mybatis.mate.datascope.DataColumnProperty;
import mybatis.mate.datascope.DataScopeProperty;
import mybatis.mate.datascope.IDataScopeProvider;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.PlainSelect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DataScopeConfig {

    @Bean
    public IDataScopeProvider dataScopeProvider() {
        return new AbstractDataScopeProvider() {
            @Override
            protected void setWhere(PlainSelect plainSelect, Object[] args, DataScopeProperty dataScopeProperty) {
                // args 中包含 mapper 方法的请求参数，需要使用可以自行获取
                /*
                    // 测试数据权限，最终执行 SQL 语句
                    SELECT u.* FROM user u WHERE (u.department_id IN ('1', '2', '3', '5'))
                    AND u.mobile LIKE '%1533%'
                 */
                if ("test".equals(dataScopeProperty.getType())) {
                    // 业务 test 类型
                    List<DataColumnProperty> dataColumns = dataScopeProperty.getColumns();
                    for (DataColumnProperty dataColumn : dataColumns) {
                        if ("department_id".equals(dataColumn.getName())) {
                            // 追加部门字段 IN 条件，也可以是 SQL 语句
                            ItemsList itemsList = new ExpressionList(Arrays.asList(
                                    new StringValue("1"),
                                    new StringValue("2"),
                                    new StringValue("3"),
                                    new StringValue("5")
                            ));
                            InExpression inExpression = new InExpression(new Column(dataColumn.getAliasDotName()), itemsList);
                            if (null == plainSelect.getWhere()) {
                                // 不存在 where 条件
                                plainSelect.setWhere(new Parenthesis(inExpression));
                            } else {
                                // 存在 where 条件 and 处理
                                plainSelect.setWhere(new AndExpression(plainSelect.getWhere(), inExpression));
                            }
                        } else if ("mobile".equals(dataColumn.getName())) {
                            // 支持一个自定义条件
                            LikeExpression likeExpression = new LikeExpression();
                            likeExpression.setLeftExpression(new Column(dataColumn.getAliasDotName()));
                            likeExpression.setRightExpression(new StringValue("%1533%"));
                            plainSelect.setWhere(new AndExpression(plainSelect.getWhere(), likeExpression));
                        }
                    }
                }
            }
        };
    }
}
