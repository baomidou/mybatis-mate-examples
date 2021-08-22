package mybatis.mate.datascope.config;

import mybatis.mate.datascope.AbstractDataScopeProvider;
import mybatis.mate.datascope.IDataScopeProvider;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.PlainSelect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class DataScopeConfig {

    @Bean
    public IDataScopeProvider dataScopeProvider() {
        return new AbstractDataScopeProvider() {
            @Override
            protected void setWhere(PlainSelect plainSelect, String whereSegment, Object obj) {
                // 创建IN 表达式
                Set<String> deptIds = new HashSet<>();
                deptIds.add("1");
                deptIds.add("2");
                deptIds.add("3");
                deptIds.add("5");
                // 把集合转变为JSQLParser需要的元素列表
                ItemsList itemsList = new ExpressionList(deptIds.stream().map(StringValue::new).collect(Collectors.toList()));
                //  order_tbl.dept_id IN ('2', '3', '4', '5')
                InExpression inExpression = new InExpression(new Column("department_id"), itemsList);
                plainSelect.setWhere(new AndExpression(plainSelect.getWhere(), inExpression));
            }
        };
    }
}
