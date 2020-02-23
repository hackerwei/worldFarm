package com.hz.world.common.util.crypt;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.google.gson.GsonBuilder;

/**
 * AES 加密通用类
 * 
 * @author lanchen
 *
 */
public class AESUtil {

	/**
	 * 注意key和加密用到的字符串是不一样的 加密还要指定填充的加密模式和填充模式 AES密钥可以是128或者256，默认 Java 中仅支持 128
	 * 位密钥，当使用 256 位密钥的时候，会报告密钥长度错误 加密模式包括ECB, CBC等
	 * ECB模式是分组的模式，CBC是分块加密后，每块与前一块的加密结果异或后再加密 第一块加密的明文是与IV变量进行异或
	 * 
	 */
	public static final String KEY_ALGORITHM_AES = "AES";
	public static final String CIPHER_ALGORITHM_ECB = "AES/ECB/PKCS5Padding";
	public static final String CIPHER_ALGORITHM_CBC = "AES/CBC/PKCS5Padding";
	public static final String CIPHER_ALGORITHM_CBC_NoPadding = "AES/CBC/NoPadding";

	public static final String CHARSET_UTF8 = "UTF-8";

	/**
	 * IV(Initialization Value)是一个初始值，对于CBC模式来说，它必须是随机选取并且需要保密的
	 * 而且它的长度和密码分组相同(比如：对于AES 128为128位，即长度为16的byte类型数组)
	 * 
	 */

	public static byte[] IVPARAMETERS = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };

	public static SecretKey genSecretKey(String privateKey) throws Exception {
		return genSecretKey(privateKey, CHARSET_UTF8);
	}

	public static SecretKey genSecretKey(String privateKey, String charset) throws Exception {
		return new SecretKeySpec(privateKey.getBytes(), KEY_ALGORITHM_AES);
	}

	/**
	 * AES加密
	 * 
	 * @param sourceBytes
	 * @param secretKey
	 * @param mode
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(byte[] sourceBytes, SecretKey secretKey, byte[] ivKey, String mode) throws Exception {

		if (sourceBytes == null || sourceBytes.length == 0) {
			return null;
		}

		if (secretKey == null) {
			return null;
		}

		if (mode == null || mode.length() == 0) {
			mode = KEY_ALGORITHM_AES;
		}

		// 实例化一个加密器
		Cipher cipher = Cipher.getInstance(mode);

		// CBC 模式需要用到IV变量进行异或运算
		if (mode.equals(CIPHER_ALGORITHM_CBC) || mode.equals(CIPHER_ALGORITHM_CBC_NoPadding)) {
			// 默认 Iv
			IvParameterSpec ivParameterSpec = new IvParameterSpec(getIVPARAMETERS());
			if (ivKey != null) {
				ivParameterSpec = new IvParameterSpec(ivKey);
			}

			// 传入加密模式和密钥,初始化一个加密器
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
		} else {
			// 传入加密模式和密钥,初始化一个加密器
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		}

		byte[] rstByte = cipher.doFinal(sourceBytes);

		return byte2hex(rstByte);
	}

	/**
	 * AES解密
	 * 
	 * @param sourceBytes
	 * @param secretKey
	 * @param mode
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(byte[] sourceBytes, SecretKey secretKey, byte[] ivKey, String mode) throws Exception {

		if (sourceBytes == null || sourceBytes.length == 0) {
			return null;
		}

		if (secretKey == null) {
			return null;
		}

		if (mode == null || mode.length() == 0) {
			mode = KEY_ALGORITHM_AES;
		}

		// 实例化一个加密器
		Cipher cipher = Cipher.getInstance(mode);

		// CBC 模式需要用到IV变量进行异或运算
		if (mode.equals(CIPHER_ALGORITHM_CBC) || mode.equals(CIPHER_ALGORITHM_CBC_NoPadding)) {
			// 默认 Iv
			IvParameterSpec ivParameterSpec = new IvParameterSpec(getIVPARAMETERS());
			if (ivKey != null) {
				ivParameterSpec = new IvParameterSpec(ivKey);
			}
			// 传入加密模式和密钥,初始化一个加密器
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
		} else {
			// 传入加密模式和密钥,初始化一个加密器
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
		}

		byte[] rstByte = cipher.doFinal(sourceBytes);

		return new String(rstByte, CHARSET_UTF8);
	}

	/**
	 * 使用AES 算法 加密，默认模式 AES/ECB
	 */
	public static String encryptBaseECBNoPadding(String source, String privateKey, String charset) throws Exception {
		return encrypt(padString(source, charset).getBytes(charset), genSecretKey(privateKey, charset), null,
				KEY_ALGORITHM_AES);
	}

	/**
	 * 使用AES 算法加密，加密模式 AES/ECB/PKCS5Padding
	 * 
	 * @param source
	 * @param privateKey
	 * @param charset
	 * @return
	 */
	public static String encryptBaseECBWithPKCS5Padding(String source, String privateKey, String charset)
			throws Exception {
		return encrypt(source.getBytes(charset), genSecretKey(privateKey, charset), null, CIPHER_ALGORITHM_ECB);
	}

	/**
	 * 使用AES 算法加密,模式 AES/CBC/NoPadding
	 */
	public static String encryptBaseCBCNoPadding(String source, String privateKey, String ivKey, String charset)
			throws Exception {
		return encrypt(padString(source, charset).getBytes(charset), genSecretKey(privateKey, charset),
				ivKey.getBytes(), CIPHER_ALGORITHM_CBC_NoPadding);
	}

	/**
	 * 使用AES 算法加密,模式 AES/CBC/PKCS5Padding
	 * 
	 * @param source
	 * @param privateKey
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public static String encryptBaseCBCWithPKCS5Padding(String source, String privateKey, String ivKey, String charset)
			throws Exception {
		return encrypt(source.getBytes(charset), genSecretKey(privateKey, charset), ivKey.getBytes(charset),
				CIPHER_ALGORITHM_CBC);
	}

	/**
	 * 使用AES 算法解密，默认模式 AES/ECB
	 */
	public static String decryptBaseECBNoPadding(String source, String privateKey, String charset) throws Exception {
		return decrypt(hex2byte(source.getBytes(charset)), genSecretKey(privateKey, charset), null, KEY_ALGORITHM_AES);
	}

	/**
	 * 使用AES 算法解密，模式 AES/ECB/PKCS5Padding
	 * 
	 * @param source
	 * @param privateKey
	 * @param charset
	 * @return
	 */
	public static String decryptBaseECBWithPKCS5Padding(String source, String privateKey, String charset)
			throws Exception {
		return decrypt(hex2byte(source.getBytes(charset)), genSecretKey(privateKey, charset), null,
				CIPHER_ALGORITHM_ECB);
	}

	/**
	 * 使用AES 算法解密,模式 AES/CBC/NoPadding
	 */
	public static String decryptBaseCBCNoPadding(String source, String privateKey, String ivKey, String charset)
			throws Exception {
		return decrypt(hex2byte(source.getBytes(charset)), genSecretKey(privateKey, charset), ivKey.getBytes(charset),
				CIPHER_ALGORITHM_CBC_NoPadding);
	}

	/**
	 * 使用AES 算法解密,模式 AES/CBC/PKCS5Padding
	 * 
	 * @param source
	 * @param privateKey
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public static String decryptBaseCBCWithPKCS5Padding(String source, String privateKey, String ivKey, String charset)
			throws Exception {
		return decrypt(hex2byte(source.getBytes(charset)), genSecretKey(privateKey, charset), ivKey.getBytes(charset),
				CIPHER_ALGORITHM_CBC);
	}

	private static byte[] getIVPARAMETERS() {
		return IVPARAMETERS;
	}

	public static void setIVPARAMETERS(byte[] iVPARAMETERS) {
		IVPARAMETERS = iVPARAMETERS;
	}

	/**
	 * 填充字符串,16位的倍数
	 * 
	 * @param source
	 * @param charset
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static String padString(String source, String charset) throws UnsupportedEncodingException {
		char paddingChar = ' ';
		int size = 16;
		int x = source.getBytes(charset).length % size;
		int padLength = size - x;

		for (int i = 0; i < padLength; i++) {
			source += paddingChar;
		}

		return source;
	}

	public static String byte2hex(byte[] b) {

		if (b == null || b.length == 0) {
			return null;
		}

		StringBuffer hs = new StringBuffer();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs.append("0").append(stmp);
			else
				hs = hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}

	public static byte[] hex2byte(byte[] b) {

		if (b == null || b.length == 0) {
			return null;
		}

		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("the length of the byte[] is not even:[" + b.length + "]");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	public static void main(String[] arags) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("code", "acc931079abd4b3db398d1a32493318060135340284230255");
		jsonMap.put("ts", "1469586665220");

		String temp = new GsonBuilder().disableHtmlEscaping().create().toJson(jsonMap);

		System.out.println(temp);

		String key = "ZsB@z3JH*z^!J4lp";
		String iv = "@3tL6qy`cR*Gvs1P";
		String enCode = "";
		try {
			enCode = AESUtil.encryptBaseCBCNoPadding(temp, key, iv, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(enCode);

		String decode = "";
		try {
			decode = AESUtil.decryptBaseCBCNoPadding(enCode, key, iv, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(decode);

	}

}
