package mybatis.mate.encrypt.entity.type;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RSATypeHandler extends BaseTypeHandler<String> {
//    @Resource
//    private IEncryptor encryptor;
//    @Resource
//    private EncryptorProperties encryptorProperties;

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {

        String value = parameter;
        // 模拟下文注释内容直接返回解密内容
        if (null != value) {
            value = "test@baomidou.com";
        }
            // 正式环境打开一下代码执行 RSA 解密明文查询，如果数据库是密文不适用该逻辑（除非加密密文不变）
//        if (null != value && encryptor.executeEncrypt()) {
//            try {
//                value = encryptor.decrypt(
//                        Algorithm.RSA,
//                        encryptorProperties.getPassword(),
//                        encryptorProperties.getPrivateKey(),
//                        parameter,
//                        null
//                );
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
        ps.setString(i, value);
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getString(columnName);
    }

    @Override
    public String getNullableResult(ResultSet rs, int i) throws SQLException {
        return rs.getString(i);
    }

    @Override
    public String getNullableResult(CallableStatement cs, int i) throws SQLException {
        return cs.getString(i);
    }
}
