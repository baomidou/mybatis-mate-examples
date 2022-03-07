package mybatis.mate.ddl.mysql;

import lombok.AllArgsConstructor;
import mybatis.mate.ddl.IDdlGenerator;
import mybatis.mate.toolkit.MpmConstants;

import java.util.function.Function;

@AllArgsConstructor
public class OracleDdlGenerator implements IDdlGenerator {

    @Override
    public boolean existTable(String databaseName, Function<String, Boolean> executeFunction) {
        return executeFunction.apply("SELECT COUNT(1) AS NUM FROM user_tables WHERE table_name='?'");
    }

    @Override
    public String createDdlHistory() {
        StringBuffer sql = new StringBuffer();
        sql.append("CREATE TABLE \"ddl_history\" (");
        sql.append("\"script\" NVARCHAR2(500) NOT NULL,");
        sql.append("\"type\" NVARCHAR2(30) NOT NULL,");
        sql.append("\"version\" NVARCHAR2(30) NOT NULL");
        sql.append(");");
        return sql.toString();
    }

    @Override
    public String selectDdlHistory() {
        return MpmConstants.SELECT_DDL_HISTORY;
    }

    @Override
    public String insertDdlHistory() {
        return MpmConstants.INSERT_DDL_HISTORY;
    }
}
