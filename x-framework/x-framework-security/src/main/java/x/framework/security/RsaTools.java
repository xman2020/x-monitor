/**
 * xxx
 * Copyright (c) 2012-2016 All Rights Reserved.
 */
package x.framework.security;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import x.framework.lang.StringTools;

/**
 * RSA加密工具
 * 
 * @author xman 2016年4月5日
 */
public class RsaTools {

    private static final String RSA         = "RSA";

    private static final String SHA1WithRSA = "SHA1WithRSA";

    /**
     * 生成密钥
     * @param password 密码
     * @return 密钥
     */
    public static Key genKey(String password) {
        Key key = new Key();
        KeyPairGenerator kgen = null;

        try {
            kgen = KeyPairGenerator.getInstance(RSA);
        } catch (NoSuchAlgorithmException e) {
            // no problem
            e.printStackTrace();
        }

        SecureRandom secureRandom = RandomTools.genRandom(password);
        kgen.initialize(1024, secureRandom);
        KeyPair keyPair = kgen.generateKeyPair();
        String privateKey = Base64Tools.encode(keyPair.getPrivate().getEncoded());
        String publicKey = Base64Tools.encode(keyPair.getPublic().getEncoded());
        key.setPrivateKey(privateKey);
        key.setPublicKey(publicKey);

        return key;
    }

    /**
     * 公钥加密
     * @param plainText 原文
     * @param publicKey 公钥
     * @return 密文
     */
    public static String encrypt(String plainText, String publicKey) {
        PublicKey publicKeyObj = loadPublicKey(publicKey);
        Cipher cipher = CipherTools.getCipher(RSA, Cipher.ENCRYPT_MODE, publicKeyObj);
        String cipherText = CipherTools.encrypt(cipher, plainText);

        return cipherText;
    }

    /**
     * 私钥解密
     * @param cipherText 密文
     * @param privateKey 私钥
     * @return 原文
     */
    public static String decrypt(String cipherText, String privateKey) {
        PrivateKey privateKeyObj = loadPrivateKey(privateKey);
        Cipher cipher = CipherTools.getCipher(RSA, Cipher.DECRYPT_MODE, privateKeyObj);
        String plainText = CipherTools.decrypt(cipher, cipherText);

        return plainText;
    }

    /**
     * 签名
     * @param plainText 原文
     * @param privateKey 私钥
     * @return 签名
     */
    public static String sign(String plainText, String privateKey) {
        String sign = null;
        Signature signature = null;

        try {
            signature = Signature.getInstance(SHA1WithRSA);
        } catch (NoSuchAlgorithmException e) {
            // no problem
            e.printStackTrace();
        }

        PrivateKey privateKeyObj = loadPrivateKey(privateKey);
        try {
            signature.initSign(privateKeyObj);
        } catch (InvalidKeyException e) {
            // no problem
            e.printStackTrace();
        }

        try {
            signature.update(StringTools.byteEncode(plainText));
        } catch (SignatureException e) {
            // no problem
            e.printStackTrace();
        }

        // 万恶的checked异常
        try {
            sign = Base64Tools.encode(signature.sign());
        } catch (SignatureException e) {
            // no problem
            e.printStackTrace();
        }

        return sign;
    }

    /**
     * 签名校验
     * @param sign 签名
     * @param plainText 原文
     * @param publicKey 公钥
     * @return 是否通过
     */
    public static boolean verify(String sign, String plainText, String publicKey) {
        boolean verify = false;
        Signature signature = null;

        try {
            signature = Signature.getInstance(SHA1WithRSA);
        } catch (NoSuchAlgorithmException e) {
            // no problem
            e.printStackTrace();
        }

        PublicKey publicKeyObj = loadPublicKey(publicKey);
        try {
            signature.initVerify(publicKeyObj);
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            signature.update(StringTools.byteEncode(plainText));
        } catch (SignatureException e) {
            // no problem
            e.printStackTrace();
        }

        try {
            verify = signature.verify(Base64Tools.decode2Bytes(sign));
        } catch (SignatureException e) {
            // no problem
            e.printStackTrace();
        }

        return verify;
    }

    /**
     * 加载私钥
     * @param privateKey 私钥
     * @return 私钥对象
     */
    private static PrivateKey loadPrivateKey(String privateKey) {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64Tools.decode2Bytes(privateKey));
        KeyFactory keyFactory = null;
        PrivateKey privateKeyObj = null;
        try {
            keyFactory = KeyFactory.getInstance(RSA);
            privateKeyObj = keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e1) {
            // no problem
            e1.printStackTrace();
        } catch (InvalidKeySpecException e1) {
            // no problem
            e1.printStackTrace();
        }
        return privateKeyObj;
    }

    /**
     * 加载公钥
     * @param publicKey 公钥
     * @return 公钥对象
     */
    private static PublicKey loadPublicKey(String publicKey) {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64Tools.decode2Bytes(publicKey));
        KeyFactory keyFactory = null;
        PublicKey publicKeyObj = null;
        try {
            keyFactory = KeyFactory.getInstance(RSA);
            publicKeyObj = keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            // no problem
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            // no problem
            e.printStackTrace();
        }
        return publicKeyObj;
    }

}
