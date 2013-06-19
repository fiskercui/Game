package com.road.util;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RSAUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RSAUtil.class);
    /**
     * * 生成密钥对 *
     * 
     * @return KeyPair *
     * @throws EncryptException
     */
    public static KeyPair generateKeyPair() throws Exception
    {
        try
        {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA",
                    new org.bouncycastle.jce.provider.BouncyCastleProvider());

            final int KEY_SIZE = 1024;
            keyPairGen.initialize(KEY_SIZE, new SecureRandom());
            KeyPair keyPair = keyPairGen.generateKeyPair();
            return keyPair;
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception
    {
        ConfigWrapper.init("E:\\m_project\\trunk\\framework\\CommonLib\\config\\server.properties");
        byte[] pubModBytesNew = Base64.decodeBase64(ConfigWrapper.getValue("publicModulus"));
        byte[] pubPubExpBytesNew = Base64.decodeBase64(ConfigWrapper.getValue("publicExponent"));
        byte[] priModBytesNew = Base64.decodeBase64(ConfigWrapper.getValue("privateModulus"));
        byte[] priPriExpBytesNew = Base64.decodeBase64(ConfigWrapper.getValue("privateExponent"));
        RSAPublicKey recoveryPubKey = RSAUtil.generateRSAPublicKey(pubModBytesNew,
                                                                   pubPubExpBytesNew);
        RSAPrivateKey recoveryPriKey = RSAUtil.generateRSAPrivateKey(priModBytesNew,
                                                                     priPriExpBytesNew);
        byte [] test = encrypt(recoveryPubKey, "hello.".getBytes());
        byte [] recover = decrypt(recoveryPriKey, test);
        System.out.println(new String(recover));
    }

    /**
     * 编码
     * 
     * @param request
     * @param para
     * @return
     */
    public static String urlEncode(String para)
    {
        try
        {
            return java.net.URLEncoder.encode(para, "UTF-8");
        }
        catch (Exception e)
        {
            LOGGER.error("编码错误", e);
        }
        return "";
    }

    // 生成公钥
    public static RSAPublicKey generateRSAPublicKey(byte[] modulus, byte[] publicExponent) throws Exception
    {
        KeyFactory keyFac = null;
        try
        {
            keyFac = KeyFactory.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
        }
        catch (NoSuchAlgorithmException ex)
        {
            throw new Exception(ex.getMessage());
        }

        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(publicExponent));
        try
        {
            return (RSAPublicKey) keyFac.generatePublic(pubKeySpec);
        }
        catch (InvalidKeySpecException ex)
        {
            throw new Exception(ex.getMessage());
        }
    }

    // 生成私钥
    public static RSAPrivateKey generateRSAPrivateKey(byte[] modulus, byte[] privateExponent) throws Exception
    {
        KeyFactory keyFac = null;
        try
        {
            keyFac = KeyFactory.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
        }
        catch (NoSuchAlgorithmException ex)
        {
            throw new Exception(ex.getMessage());
        }

        RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(modulus), new BigInteger(privateExponent));
        try
        {
            return (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);
        }
        catch (InvalidKeySpecException ex)
        {
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * Encrypt String.
     * 
     * @return byte[]
     */
    public static byte[] encrypt(RSAPublicKey publicKey, byte[] obj)
    {
        if (publicKey != null)
        {
            try
            {
                Cipher cipher = Cipher.getInstance("RSA");
                // ENCRYPT_MODE : 用于将 cipher 初始化为加密模式的常量。
                cipher.init(Cipher.ENCRYPT_MODE, publicKey);
                return cipher.doFinal(obj);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Basic decrypt method
     * 
     * @return byte[]
     */
    public static byte[] decrypt(RSAPrivateKey privateKey, byte[] obj)
    {
        if (privateKey != null)
        {
            try
            {
                Cipher cipher = Cipher.getInstance("RSA");
                // DECRYPT_MODE : 用于将 cipher 初始化为解密模式的常量。
                cipher.init(Cipher.DECRYPT_MODE, privateKey);
                return cipher.doFinal(obj);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return null;
    }

}
