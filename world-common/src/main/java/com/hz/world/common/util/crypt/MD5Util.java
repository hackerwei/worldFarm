package com.hz.world.common.util.crypt;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * Title: MD5Utils
 * </p>
 * <p>
 * Description:公用MD5加密类
 * </p>
 *
 * @author phily
 * @version 1.0
 * @date 2009-02-25
 */
public final class MD5Util {

    public static String toMD5(String inString) {
        if (inString == null) {
            return null;
        }
        StringBuffer outStringBuffer = new StringBuffer();
        for (byte b : DigestUtils.md5(inString)) {
            outStringBuffer.append("0123456789abcdef".charAt(0xf & b >> 4)).append("0123456789abcdef".charAt(b & 0xf));
        }
        return outStringBuffer.toString();
    }

    private final static char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private static String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        int t;
        for (int i = 0; i < 16; i++) {
            t = bytes[i];
            if (t < 0)
                t += 256;
            sb.append(hexDigits[(t >>> 4)]);
            sb.append(hexDigits[(t % 16)]);
        }
        return sb.toString();
    }

    public static String md5(String input) throws Exception {
        return code(input, 32);
    }

    public static String code(String input, int bit) throws Exception {
        try {
            MessageDigest md = MessageDigest.getInstance(System.getProperty("MD5.algorithm", "MD5"));
            if (bit == 16)
                return bytesToHex(md.digest(input.getBytes("utf-8"))).substring(8, 24);
            return bytesToHex(md.digest(input.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new Exception("Could not found MD5 algorithm.", e);
        }
    }

    public static byte[] hash(byte[] source) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(source);
            return digest.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String md5Hex(String source) {
        byte[] hashed = hash(source);
        return Hex.encodeHexString(hashed);
    }

    public static String md5Hex(byte[] source) {
        byte[] hashed = hash(source);
        return Hex.encodeHexString(hashed);
    }

    public static byte[] hash(String source) {
        return hash(source.getBytes(Charset.forName("UTF8")));
    }
}
