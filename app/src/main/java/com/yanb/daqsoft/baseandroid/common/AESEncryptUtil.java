package com.yanb.daqsoft.baseandroid.common;

/**
 * AES加解密算法
 * 此处使用AES-128-CBC加密模式，key需为16位。
 * Author znb
 * on 2018/2/12.
 */

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryptUtil {
    /**
     * 密匙
     */
    private static final String KEY = "AQ4S10D7d9K8c64D";

    /**
     * iv向量
     */
    private static final String IV = "dwvNVXzyXiq37u-A";

    /**
     * 加密
     * @param word
     * @return
     */
    public static String Encrypt(String word) throws Exception {
        byte[] raw = KEY.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
        IvParameterSpec iv = new IvParameterSpec(IV.getBytes());//使用CBC模式，加入iv向量，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        word = new String(word.getBytes(), "UTF-8");
        return Base64.encodeToString(cipher.doFinal(word.getBytes()), Base64.DEFAULT);
    }

}
