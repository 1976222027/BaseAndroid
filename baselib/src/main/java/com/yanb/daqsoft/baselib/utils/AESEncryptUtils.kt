package com.yanb.daqsoft.baselib.utils

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * AES加解密算法
 * 此处使用AES-128-CBC加密模式，key需为16位。
 */
class AESEncryptUtils {
    companion object{
        val KEY = "AQ4S10D7d9K8c64D"
        val IV = "dwvNVXzyXiq37u-A"
        @Throws(Exception::class)
        fun Encrypt(word: String): String {
            var word = word
            val raw = KEY.toByteArray()
            val skeySpec = SecretKeySpec(raw, "AES")
            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")//"算法/模式/补码方式"
            val iv = IvParameterSpec(IV.toByteArray())//使用CBC模式，加入iv向量，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv)
            //        byte[] encrypted = cipher.doFinal(word.getBytes());
            word = String(word.toByteArray())
            return Base64.encodeToString(cipher.doFinal(word.toByteArray()), Base64.DEFAULT)
            //        return Base64.encodeBase64String(encrypted);//此处使用BAES64做转码功能，同时能起到2次加密的作用。
        }
    }
}