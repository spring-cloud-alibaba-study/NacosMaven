package com.js.util.encrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * 加密工具类
 * <p>
 * md5加密出来的长度是32位
 * <p>
 * sha加密出来的长度是40位
 *
 * @author 渡劫 dujie
 */
public final class EncryptUtil {

    private static final String PASSWORD_CRYPT_KEY = "88444488";

    private static final String DES = "DES/ECB/PKCS5Padding";

    private static final Integer EVEN = 2;

    /**
     * 二次加密 先sha-1加密再用MD5加密
     *
     * @param src
     * @return
     */
    public static final String md5AndSha(String src) {
        return md5(sha(src));
    }

    /**
     * 二次加密 先MD5加密再用sha-1加密
     *
     * @param src
     * @return
     */
    public static final String shaAndMd5(String src) {
        return sha(md5(src));
    }

    /**
     * md5加密
     *
     * @param src
     * @return
     */
    public static final String md5(String src) {
        return encrypt(src, "md5");
    }

    /**
     * sha-1加密
     *
     * @param src
     * @return
     */
    public static final String sha(String src) {
        return encrypt(src, "sha-1");
    }

    /**
     * md5或者sha-1加密
     *
     * @param src           要加密的内容
     * @param algorithmName 加密算法名称：md5或者sha-1，不区分大小写
     * @return
     */
    private static final String encrypt(String src, String algorithmName) {
        if (src == null || "".equals(src.trim())) {
            throw new IllegalArgumentException("请输入要加密的内容");
        }
        if (algorithmName == null || "".equals(algorithmName.trim())) {
            algorithmName = "md5";
        }
        String encryptText = null;
        try {
            MessageDigest m = MessageDigest.getInstance(algorithmName);
            m.update(src.getBytes(StandardCharsets.UTF_8));
            byte[] s = m.digest();
            return hex(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptText;
    }

    /**
     * 密码解密
     *
     * @return
     * @throws Exception
     */
    public static final String decrypt(String src) {
        try {
            return new String(decrypt(hex2byte(src.getBytes()), PASSWORD_CRYPT_KEY.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 密码加密
     *
     * @return
     * @throws Exception
     */
    public static final String encrypt(String src) {
        try {
            return byte2hex(encrypt(src.getBytes(), PASSWORD_CRYPT_KEY.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密
     *
     * @param src 数据源
     * @param key 密钥，长度必须是8的倍数
     * @return 返回加密后的数据
     * @throws Exception
     */
    private static final byte[] encrypt(byte[] src, byte[] key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
        // 现在，获取数据并加密正式执行加密操作
        return cipher.doFinal(src);
    }

    /**
     * 解密
     *
     * @param src 数据源
     * @param key 密钥，长度必须是8的倍数
     * @return 返回解密后的原始数据
     * @throws Exception
     */
    private static final byte[] decrypt(byte[] src, byte[] key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建一个DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
        // 现在，获取数据并解密正式执行解密操作
        return cipher.doFinal(src);
    }

    private static final byte[] hex2byte(byte[] b) {
        if ((b.length % EVEN) != 0) {
            throw new IllegalArgumentException("长度不是偶数");
        }
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += EVEN) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }

    /**
     * 二行制转字符串
     *
     * @param b
     * @return
     */
    private static final String byte2hex(byte[] b) {
        StringBuilder sb = new StringBuilder();
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                sb = sb.append("0").append(stmp);
            } else {
                sb = sb.append(stmp);
            }
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 返回十六进制字符串
     *
     * @param arr
     * @return
     */
    private static final String hex(byte[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; ++i) {
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100), 1, 3);
        }
        return sb.toString();
    }

}