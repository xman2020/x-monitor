/**
 * xxx
 * Copyright (c) 2012-2016 All Rights Reserved.
 */
package x.framework.security;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import x.framework.lang.AppException;
import x.framework.lang.StringTools;

/**
 * 签名工具
 * 
 * @author xman 2016年4月5日
 */
public class SignTools {

    /** 签名缓冲池 */
    private static final Map<String, PKCS7Tool> signTools                      = new ConcurrentHashMap<String, PKCS7Tool>();

    /** 验签缓冲池 */
    private static final Map<String, PKCS7Tool> verifyTools                    = new ConcurrentHashMap<String, PKCS7Tool>();

    private static final String                 INIT_PKCS7TOOLS_SIGNER_ERROR   = "INIT_PKCS7TOOLS_SIGNER_ERROR";

    private static final String                 SIGN_ERROR                     = "SIGN_ERROR";

    private static final String                 INIT_PKCS7TOOLS_VERIFIER_ERROR = "INIT_PKCS7TOOLS_VERIFIER_ERROR";

    private static final String                 VERIFY_ERROR                   = "VERIFY_ERROR";

    /**
     * {@inheritDoc}
     */
    public static String sign(SignConfig signConfig, String plainText) {
        PKCS7Tool signTool = signTools.get(signConfig.getName());
        if (signTool == null) {
            try {
                signTool = PKCS7Tool.getSigner(signConfig.getPrivateKeyPath(), signConfig.getKeyStorePassword(),
                        signConfig.getKeyPassword());
                signTools.put(signConfig.getName(), signTool);
            } catch (Throwable t) {
                throw new AppException(INIT_PKCS7TOOLS_SIGNER_ERROR, t);
            }
        }

        String sign = null;
        try {
            sign = signTool.sign(StringTools.byteEncode(plainText));
        } catch (Throwable t) {
            throw new AppException(SIGN_ERROR, t);
        }

        return sign;
    }

    /**
     * {@inheritDoc}
     */
    public static boolean verify(SignConfig signConfig, String plainText, String sign) {
        PKCS7Tool verifyTool = verifyTools.get(signConfig.getName());
        if (verifyTool == null) {
            try {
                verifyTool = PKCS7Tool.getVerifier(signConfig.getPublicKeyPath());
                verifyTools.put(signConfig.getName(), verifyTool);
            } catch (Throwable t) {
                throw new AppException(INIT_PKCS7TOOLS_VERIFIER_ERROR, t);
            }
        }

        boolean verify = false;
        try {
            verifyTool.verify(sign, StringTools.byteEncode(plainText), null);
            verify = true;
        } catch (Throwable t) {
            new AppException(VERIFY_ERROR, t);
            verify = false;
        }

        return verify;
    }



}
