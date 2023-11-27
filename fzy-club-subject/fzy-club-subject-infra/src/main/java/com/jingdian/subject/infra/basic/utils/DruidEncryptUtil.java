package com.jingdian.subject.infra.basic.utils;

import com.alibaba.druid.filter.config.ConfigTools;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @author 冯正阳
 * 数据库加密
 */
public class DruidEncryptUtil {

    private static String publicKey;
    private static String privateKey;
    static {
        try {
            String[] keyPair = ConfigTools.genKeyPair(512);
            privateKey = keyPair[0];
            publicKey = keyPair[1];
            System.out.println("privateKey:" + privateKey);
            System.out.println("publicKey:" + publicKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String encrypt(String plainText) throws Exception {
        String encrypt = ConfigTools.encrypt(privateKey,plainText);
        System.out.println("encrypt:" + encrypt);
        return encrypt;
    }
    public static String decrypt(String encryptText) throws Exception {
        String decrypt = ConfigTools.encrypt(publicKey,encryptText);
        System.out.println("decrypt:" + decrypt);
        return decrypt;
    }

    public static void main(String[] args) throws Exception {
        String encrypt = encrypt("123456");
        System.out.println("encrypt" + encrypt);
    }
}
