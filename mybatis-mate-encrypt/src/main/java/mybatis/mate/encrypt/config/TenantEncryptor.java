package mybatis.mate.encrypt.config;

import mybatis.mate.encrypt.DefaultEncryptor;


// 多租户自定义加解密处理器，算法继承默认算法，自己控制租户是否加解密 @Component 注解必须放在 spring 能够扫描到的目录
// 控制条件一般根据上下文的租户ID自行识别处理
//@Component
public class TenantEncryptor extends DefaultEncryptor {

    @Override
    public boolean executeEncrypt() {
        // 加密 true 执行 false 不执行
        return true;
    }

    @Override
    public boolean executeDecrypt() {
        // 解密 true 执行 false 不执行
        return super.executeDecrypt();
    }
}
