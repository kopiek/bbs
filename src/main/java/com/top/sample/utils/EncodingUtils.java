package com.top.sample.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;

/**
 * @author wangzhikang
 */
public class EncodingUtils {

    private static final String SALT = "this is a salt for this project k!";

    public static String base64Encoding(String textToEncoding) {
        Objects.requireNonNull(textToEncoding, "no data found");
        return Base64.getEncoder().encodeToString(getUtf8Array(textToEncoding));
    }

    public static String base64Decode(String textToDecode) {
        Objects.requireNonNull(textToDecode, "no data found");
        return getStringFromByteArray(Base64.getDecoder().decode(getUtf8Array(textToDecode)));
    }

    public static String md5Encoding(String textToEncoding) {
        Objects.requireNonNull(textToEncoding, "no data found");
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            return getStringFromByteArray(md5.digest(getUtf8Array(textToEncoding + SALT)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] getUtf8Array(String text) {
        return text.getBytes(StandardCharsets.UTF_8);
    }

    private static String getStringFromByteArray(byte... charArray) {
        Objects.requireNonNull(charArray, "charArray must not be null");
        StringBuilder stringBuilder = new StringBuilder();
        for (byte item : charArray) {
            stringBuilder.append(item);
        }
        return stringBuilder.toString();
    }

    public static boolean md5Equals(String newPwd, String oldPwd) {
        String md5 = md5Encoding(newPwd);
        Objects.requireNonNull(md5, "password encoding failed");
        return md5.equals(oldPwd);
    }
}
