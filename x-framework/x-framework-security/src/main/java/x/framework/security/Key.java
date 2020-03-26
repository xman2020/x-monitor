/**
 * xxx
 * Copyright (c) 2012-2016 All Rights Reserved.
 */
package x.framework.security;

/**
 * 密钥
 * 
 * @author xman 2016年4月6日
 */
public class Key {

    /** 密钥 */
    private String key;

    /** 私钥 */
    private String privateKey;

    /** 公钥 */
    private String publicKey;

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the privateKey
     */
    public String getPrivateKey() {
        return privateKey;
    }

    /**
     * @param privateKey the privateKey to set
     */
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    /**
     * @return the publicKey
     */
    public String getPublicKey() {
        return publicKey;
    }

    /**
     * @param publicKey the publicKey to set
     */
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

}
