package mybatis.mate.sm4.config;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.Security;

/**
 * 国密SM4分组密码算法工具类（对称加密）
 * <p>GB/T 32907-2016 信息安全技术 SM4分组密码算法</p>
 * <p>SM4-ECB-PKCS5Padding</p>
 *
 * @author 参考 https://gitee.com/bbfbbf/sm4-util
 */
public class Sm4Util {
    private static final String ALGORITHM_NAME = "SM4";
    private static final String ALGORITHM_ECB_PKCS5PADDING = "SM4/ECB/PKCS5Padding";
    /**
     * SM4算法目前只支持128位（即密钥16字节）
     */
    private static final int DEFAULT_KEY_SIZE = 128;

    static {
        // 防止内存中出现多次BouncyCastleProvider的实例
        if (null == Security.getProvider(BouncyCastleProvider.PROVIDER_NAME)) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    private Sm4Util() {
    }

    /**
     * 生成密钥
     * <p>建议使用DigestUtil.binToHex将二进制转成HEX</p>
     *
     * @return 密钥16位
     * @throws Exception 生成密钥异常
     */
    public static String generateKey() throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_NAME, BouncyCastleProvider.PROVIDER_NAME);
        kg.init(DEFAULT_KEY_SIZE, new SecureRandom());
        return Hex.toHexString(kg.generateKey().getEncoded());
    }

    /**
     * 加密，SM4-ECB-PKCS5Padding
     *
     * @param data 要加密的明文
     * @param key  密钥
     * @return 加密后的密文
     * @throws Exception 加密异常
     */
    public static String encrypt(String data, String key) {
        byte[] bytes = sm4(Strings.toUTF8ByteArray(data), Hex.decode(key), Cipher.ENCRYPT_MODE);
        return null == bytes ? null : Hex.toHexString(bytes);
    }

    /**
     * 解密，SM4-ECB-PKCS5Padding
     *
     * @param data 要解密的密文
     * @param key  密钥
     * @return 解密后的明文
     * @throws Exception 解密异常
     */
    public static String decrypt(String data, String key) {
        byte[] bytes = sm4(Hex.decode(data), Hex.decode(key), Cipher.DECRYPT_MODE);
        return null == bytes ? null : new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * SM4对称加解密
     *
     * @param input 明文（加密模式）或密文（解密模式）
     * @param key   密钥
     * @param mode  Cipher.ENCRYPT_MODE - 加密；Cipher.DECRYPT_MODE - 解密
     * @return 密文（加密模式）或明文（解密模式）
     * @throws Exception 加解密异常
     */
    private static byte[] sm4(byte[] input, byte[] key, int mode) {
        try {
            SecretKeySpec sm4Key = new SecretKeySpec(key, ALGORITHM_NAME);
            Cipher cipher = Cipher.getInstance(ALGORITHM_ECB_PKCS5PADDING, BouncyCastleProvider.PROVIDER_NAME);
            cipher.init(mode, sm4Key);
            return cipher.doFinal(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}