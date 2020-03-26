/**
 * xxx
 * Copyright (c) 2012-2016 All Rights Reserved.
 */
package x.framework.security;

/**
 * 签名配置
 * 
 * @author xman 2016年3月29日
 */
public class SignConfig {

    /** 配置名 */
    private String name;

    /** 私钥路径 */
    private String privateKeyPath;

    /** 密钥库密码 */
    private String keyStorePassword;

    /** 密钥密码 */
    private String keyPassword;

    /** 公钥路径 */
    private String publicKeyPath;

    /** 私钥 */
    private String privateKey;

    /** 公钥 */
    private String publicKey;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the privateKeyPath
     */
    public String getPrivateKeyPath() {
        return privateKeyPath;
    }

    /**
     * @param privateKeyPath the privateKeyPath to set
     */
    public void setPrivateKeyPath(String privateKeyPath) {
        this.privateKeyPath = privateKeyPath;
    }

    /**
     * @return the keyStorePassword
     */
    public String getKeyStorePassword() {
        return keyStorePassword;
    }

    /**
     * @param keyStorePassword the keyStorePassword to set
     */
    public void setKeyStorePassword(String keyStorePassword) {
        this.keyStorePassword = keyStorePassword;
    }

    /**
     * @return the keyPassword
     */
    public String getKeyPassword() {
        return keyPassword;
    }

    /**
     * @param keyPassword the keyPassword to set
     */
    public void setKeyPassword(String keyPassword) {
        this.keyPassword = keyPassword;
    }

    /**
     * @return the publicKeyPath
     */
    public String getPublicKeyPath() {
        return publicKeyPath;
    }

    /**
     * @param publicKeyPath the publicKeyPath to set
     */
    public void setPublicKeyPath(String publicKeyPath) {
        this.publicKeyPath = publicKeyPath;
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
