package mybatis.mate.sm4.config;

import mybatis.mate.annotation.Algorithm;
import mybatis.mate.encrypt.IEncryptor;
import org.apache.ibatis.reflection.MetaObject;

// 自定义加密算法，这里为开启使用默认加密库
//@Component 注解申明会变成全局处理器
public class Sm4Encryptor implements IEncryptor {

    /**
     * 加密
     *
     * @param algorithm  算法
     * @param password   密码
     * @param src        明文
     * @param metaObject {@link org.apache.ibatis.reflection.MetaObject}
     * @return
     */
    @Override
    public String encrypt(Algorithm algorithm, String password, String src, Object metaObject) {
        if (metaObject instanceof MetaObject) {
            MetaObject _metaObject = ((MetaObject) metaObject);
            // 获取待加密对象 name 属性值，注意 src 是注解属性值框架层已经取出来了，这里是查询行对象任意值获取
            System.out.println(_metaObject.getValue("username"));
        }
        return Sm4Util.encrypt(src, password);
    }

    /**
     * 解密
     *
     * @param algorithm  算法
     * @param password   密码
     * @param encrypt    密文
     * @param metaObject {@link org.apache.ibatis.reflection.MetaObject}
     * @return
     */
    @Override
    public String decrypt(Algorithm algorithm, String password, String encrypt, Object metaObject) {
        if (metaObject instanceof MetaObject) {
            MetaObject _metaObject = ((MetaObject) metaObject);
            // 获取待解密对象 username 属性值，注意 encrypt 是注解属性值框架层已经取出来了，这里是查询行对象任意值获取
            System.out.println(_metaObject.getValue("username"));
        }
        return Sm4Util.decrypt(encrypt, password);
    }
}
