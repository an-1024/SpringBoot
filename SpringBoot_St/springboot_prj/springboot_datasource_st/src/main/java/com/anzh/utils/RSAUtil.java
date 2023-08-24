package com.anzh.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;

@Slf4j
public class RSAUtil {
    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";
    private static final int MAX_ENCRYPT_BLOCK = 64;
    private static final int MAX_DECRYPT_BLOCK = 75;
    private static final int INITIAL_KEY_SIZE = 600;

    public RSAUtil() {
    }

    public static Map<String, Object> genKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(600);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap(2);
        keyMap.put("RSAPublicKey", publicKey);
        keyMap.put("RSAPrivateKey", privateKey);
        return keyMap;
    }

    public static String sign(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = Base64Util.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(privateK);
        signature.update(data);
        return Base64Util.encode(signature.sign());
    }

    public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
        byte[] keyBytes = Base64Util.decode(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(publicK);
        signature.update(data);
        return signature.verify(Base64Util.decode(sign));
    }

    public static String decryptByPrivateKey(String encrypted, String privateKey) throws Exception {
        byte[] encryptedData = Base64Util.decode(encrypted);
        byte[] keyBytes = Base64Util.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(2, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;

        for(int i = 0; inputLen - offSet > 0; offSet = i * 75) {
            byte[] cache;
            if (inputLen - offSet > 75) {
                cache = cipher.doFinal(encryptedData, offSet, 75);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }

            out.write(cache, 0, cache.length);
            ++i;
        }

        byte[] decryptedData = out.toByteArray();
        out.close();
        return new String(decryptedData);
    }

    public static String decryptByPublicKey(String encrypted, String publicKey) throws Exception {
        byte[] encryptedData = Base64Util.decode(encrypted);
        byte[] keyBytes = Base64Util.decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(2, publicK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;

        for(int i = 0; inputLen - offSet > 0; offSet = i * 75) {
            byte[] cache;
            if (inputLen - offSet > 75) {
                cache = cipher.doFinal(encryptedData, offSet, 75);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }

            out.write(cache, 0, cache.length);
            ++i;
        }

        byte[] decryptedData = out.toByteArray();
        out.close();
        return new String(decryptedData);
    }

    public static String encryptByPublicKey(String source, String publicKey) throws Exception {
        byte[] data = source.getBytes();
        byte[] keyBytes = Base64Util.decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(1, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;

        for(int i = 0; inputLen - offSet > 0; offSet = i * 64) {
            byte[] cache;
            if (inputLen - offSet > 64) {
                cache = cipher.doFinal(data, offSet, 64);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }

            out.write(cache, 0, cache.length);
            ++i;
        }

        byte[] encryptedData = out.toByteArray();
        out.close();
        return Base64Util.encode(encryptedData);
    }

    public static String encryptByPrivateKey(String source, String privateKey) throws Exception {
        byte[] data = source.getBytes();
        byte[] keyBytes = Base64Util.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(1, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;

        for(int i = 0; inputLen - offSet > 0; offSet = i * 64) {
            byte[] cache;
            if (inputLen - offSet > 64) {
                cache = cipher.doFinal(data, offSet, 64);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }

            out.write(cache, 0, cache.length);
            ++i;
        }

        byte[] encryptedData = out.toByteArray();
        out.close();
        return Base64Util.encode(encryptedData);
    }

    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key)keyMap.get("RSAPrivateKey");
        return Base64Util.encode(key.getEncoded());
    }

    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key)keyMap.get("RSAPublicKey");
        return Base64Util.encode(key.getEncoded());
    }

    public static void main(String[] args) throws Exception {
        Map<String, Object> keyMap = genKeyPair();
        String privateKey = getPrivateKey(keyMap);
        log.info("privateKey: {}", privateKey); // MIIBhQIBADANBgkqhkiG9w0BAQEFAASCAW8wggFrAgEAAkwAtaRhMsA7zaoJ+B7q5lRlYRzQ1tquSlS8bcq7c+f01+I5wGVA1dO2t/aozQjTR25Xim5p6wsUjFhVjO0d20sMxYFYJfyzmnPTxx4nAgMBAAECSxR0Uk24wANE5qJ+OM1/K3toF0xai83rBN0IO1ut/zBgVUiJHmfyyTyfgfhEdAvgB7e2ANkwm0qRdhdkK9Rqw3+mkzW87UmfIeZxwQImDjOnIsoVnaxw+bKJqXKWp4DpGaxVoFpDTfT8/Qn4Wn5xWP5/eI8CJgzKREeAGC8zCWAfh/6HhbwVraALAfCop7dxDoPtu9nhrm6oIVzpAiYC/ylEcQoQpGOOfPsJc3GQ1OXbEf9bf9B18b00k9cBGW9HjSROxwImCeGarsK/7Hc5nxG9N1/saks1QJhAf3VNXePymupoXwSEhulOHUECJgwtY91v6AFNJe7Jz1IRAjzK07MbJbqFGDPtDtsso3Zx+JlsjGmH
        String publicKey = getPublicKey(keyMap); // MGcwDQYJKoZIhvcNAQEBBQADVgAwUwJMALWkYTLAO82qCfge6uZUZWEc0NbarkpUvG3Ku3Pn9NfiOcBlQNXTtrf2qM0I00duV4puaesLFIxYVYztHdtLDMWBWCX8s5pz08ceJwIDAQAB
        log.info("publicKey: {}", publicKey);
        String MD5_String = "An123456";
        //MD5_String = MD5Util.MD5(MD5_String);  // 签名加密
        System.out.println("MD5加密后：" + MD5_String);
        String encrypt = encryptByPrivateKey(MD5_String, privateKey);
        System.out.println("RSA加密后：" + encrypt);
        String decrypt = decryptByPublicKey(encrypt, publicKey);
        System.out.println("RSA解密后：" + decrypt);
    }
}
