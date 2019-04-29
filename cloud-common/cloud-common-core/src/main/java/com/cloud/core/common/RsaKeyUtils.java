package com.cloud.core.common;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;

/**
 * RSA工具
 *
 * @author : dayun_wang
 */
public class RsaKeyUtils {
    public static String modulus = "93574839027440310914667457357299181895173805515475846585580921814793424814895163341279570080729093771044085680025101076488084187584396042961762545808635439239926321993124420815298473615545878736928849954756393737218880748470425128314481997404582918090870951910248295092131175642568493969205244607832157324647";
    public static String publicExponent = "65537";
    public static String privateExponent = "11845169363438131427256072542779712422026670286347980885209566006614984699702004594034748514422823167441014004325621229695365158920917185290916308040167225167934711213160408237494329747619554233458602617907123802559561743393283878688481663746397104160166206497844179311139958652802463530384228053754142302081";

    /**
     * 公钥加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String data) throws Exception {
        RSAPublicKey pubKey = RsaKeyUtils.getPublicKey(modulus, publicExponent);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);

        int keyLen = pubKey.getModulus().bitLength() / 8;
        String[] datas = splitString(data, keyLen - 11);
        String val = "";
        for (String s : datas) {
            val += bcdToStr(cipher.doFinal(s.getBytes()));
        }
        return val;
    }

    /**
     * 私钥解密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String data) throws Exception {
        RSAPrivateKey priKey = RsaKeyUtils.getPrivateKey(modulus, privateExponent);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);

        int keyLen = priKey.getModulus().bitLength() / 8;
        byte[] bytes1 = data.getBytes();
        byte[] bytes2 = asciiToBcd(bytes1, bytes1.length);
        String val = "";
        byte[][] arrays = splitArray(bytes2, keyLen);
        for (byte[] arr : arrays) {
            val += new String(cipher.doFinal(arr));
        }
        return val;
    }

    /**
     * 生成公钥和私钥
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static HashMap<String, Object> getKeys() throws NoSuchAlgorithmException {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(1024);

        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey pubKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey priKey = (RSAPrivateKey) keyPair.getPrivate();
        hashMap.put("public", pubKey);
        hashMap.put("private", priKey);

        return hashMap;
    }

    /**
     * 使用模和指数生成RSA公钥
     *
     * @param modulus
     * @param exponent
     * @return
     */
    public static RSAPublicKey getPublicKey(String modulus, String exponent) {
        try {
            BigInteger b1 = new BigInteger(modulus);
            BigInteger b2 = new BigInteger(exponent);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(b1, b2);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用模和指数生成私钥
     *
     * @param modulus
     * @param exponent
     * @return
     */
    public static RSAPrivateKey getPrivateKey(String modulus, String exponent) {
        RSAPrivateKey rsaPrivateKey = null;
        try {
            BigInteger b1 = new BigInteger(modulus);
            BigInteger b2 = new BigInteger(exponent);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(b1, b2);
            rsaPrivateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsaPrivateKey;
    }

    /**
     * 公钥加密
     *
     * @param data
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String data, RSAPublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int keyLen = publicKey.getModulus().bitLength() / 8;
        String[] datas = splitString(data, keyLen - 11);
        String val = "";
        for (String s : datas) {
            val += bcdToStr(cipher.doFinal(s.getBytes()));
        }
        return val;
    }

    /**
     * 私钥解密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String data, RSAPrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        int keyLen = privateKey.getModulus().bitLength() / 8;
        byte[] b1 = data.getBytes();
        byte[] b2 = asciiToBcd(b1, b1.length);
        String val = "";
        byte[][] arrays = splitArray(b2, keyLen);
        for (byte[] s : arrays) {
            val += new String(cipher.doFinal(s));
        }
        return val;
    }

    /**
     * ASCII码转BCD码
     *
     * @param ascii
     * @param ascLen
     * @return
     */
    public static byte[] asciiToBcd(byte[] ascii, int ascLen) {
        byte[] bcd = new byte[ascLen / 2];
        int j = 0;
        int k = 2;
        for (int i = 0; i < (ascLen + 1) / k; i++) {
            bcd[i] = asciiToBcd(ascii[j++]);
            bcd[i] = (byte) (((j >= ascLen) ? 0x00 : asciiToBcd(ascii[j++])) + (bcd[i] << 4));
        }
        return bcd;
    }

    public static byte asciiToBcd(byte asc) {
        byte bcd;
        byte e = '0', b = '9', c = 'A', d = 'F', a = 'a', f = 'f';
        if ((asc >= e) && (asc <= b)) {
            bcd = (byte) (asc - e);
        } else if ((asc >= c) && (asc <= d)) {
            bcd = (byte) (asc - c + 10);
        } else if ((asc >= a) && (asc <= f)) {
            bcd = (byte) (asc - a + 10);
        } else {
            bcd = (byte) (asc - 48);
        }
        return bcd;
    }

    /**
     * BCD码转字符串
     *
     * @param bytes
     * @return
     */
    public static String bcdToStr(byte[] bytes) {
        char[] temp = new char[bytes.length * 2];
        char val;
        for (int i = 0; i < bytes.length; i++) {
            val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);
            temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
            val = (char) (bytes[i] & 0x0f);
            temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
        }
        return new String(temp);
    }

    /**
     * 拆分字符串
     *
     * @param string
     * @param len
     * @return
     */
    public static String[] splitString(String string, int len) {
        int x = string.length() / len;
        int y = string.length() % len;
        int z = 0;
        if (y != 0) {
            z = 1;
        }
        String[] strings = new String[x + z];
        String str = "";
        for (int i = 0; i < x + z; i++) {
            if (i == x + z - 1 && y != 0) {
                str = string.substring(i * len, i * len + y);
            } else {
                str = string.substring(i * len, i * len + len);
            }
            strings[i] = str;
        }
        return strings;
    }

    /**
     * 拆分数组
     *
     * @param data
     * @param len
     * @return
     */
    public static byte[][] splitArray(byte[] data, int len) {
        int x = data.length / len;
        int y = data.length % len;
        int z = 0;
        if (y != 0) {
            z = 1;
        }
        byte[][] arrays = new byte[x + z][];
        byte[] arr;
        for (int i = 0; i < x + z; i++) {
            arr = new byte[len];
            if (i == x + z - 1 && y != 0) {
                System.arraycopy(data, i * len, arr, 0, y);
            } else {
                System.arraycopy(data, i * len, arr, 0, len);
            }
            arrays[i] = arr;
        }
        return arrays;
    }
}
