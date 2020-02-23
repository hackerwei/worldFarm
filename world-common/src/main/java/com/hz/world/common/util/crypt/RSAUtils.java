package com.hz.world.common.util.crypt;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import org.apache.commons.codec.binary.Base64;

/**
 * 
 * RSA加密明文最大长度117字节，解密要求密文最大长度为128字节，所以在加密和解密的过程中需要分块进行。 RSA加密对明文的长度是有限制的
 * ，如果加密数据过大会抛出异常：javax.crypto.IllegalBlockSizeException
 * 
 * 字符串格式的密钥在未特殊说明情况下都为BASE64编码格式<br/>
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/>
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全
 * 
 * 
 * 
 */

public final class RSAUtils {

	/** */
	/**
	 * 加密算法RSA
	 */
	private static final String KEY_ALGORITHM = "RSA";

	/** */
	/**
	 * 签名算法
	 */
	private static final String SIGNATURE_ALGORITHM = "SHA1withRSA"; // MD5withRSA

	/** */
	/**
	 * 获取公钥的key
	 */
	private static final String PUBLIC_KEY = "RSAPublicKey";

	/** */
	/**
	 * 获取私钥的key
	 */
	private static final String PRIVATE_KEY = "RSAPrivateKey";

	/** */
	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/** */
	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	/** */
	/**
	 * <p>
	 * 生成密钥对(公钥和私钥)
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> genKeyPair() {
		try {
			Map<String, Object> keyMap = new HashMap<String, Object>(2);
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
			keyPairGen.initialize(1024);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
			keyMap.put(PUBLIC_KEY, publicKey);
			keyMap.put(PRIVATE_KEY, privateKey);
			return keyMap;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/** */
	/**
	 * <p>
	 * 用私钥对信息生成数字签名
	 * </p>
	 * 
	 * @param data
	 *            未签名数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * 
	 * @return
	 * @throws Exception
	 */

	public static String sign(String source, String privateKey, String charset) {
		try {
			byte[] keyBytes = decryptBASE64(privateKey);
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
			return sign(source, privateK, charset);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String sign(String source, PrivateKey privateKey, String charset) {
		try {
			byte[] data = null;
			if (charset == null || charset.trim().equals("")) {
				data = source.getBytes();
			} else {
				data = source.getBytes(charset);
			}
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initSign(privateKey);
			signature.update(data);
			return encryptBASE64(signature.sign());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] sign(byte[] data, PrivateKey privateKey) {
		try {
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);// 实例化一个用sha1算法进行散列,用rsa算法进行加密的signature
			signature.initSign(privateKey);
			signature.update(data);
			return signature.sign();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/** */
	/**
	 * <p>
	 * 校验数字签名
	 * </p>
	 * 
	 * @param data
	 *            已签名数据(加密)
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @param sign
	 *            数字签名
	 * 
	 * @return
	 * @throws Exception
	 * 
	 */
	public static boolean verify(String source, String publicKey, String sign, String charset) {
		try {
			byte[] keyBytes = decryptBASE64(publicKey);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			PublicKey publicK = keyFactory.generatePublic(keySpec);
			return verify(source, publicK, sign, charset);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean verify(String source, PublicKey publicKey, String sign, String charset) {
		try {
			byte[] data = null;
			if (charset == null || charset.trim().equals("")) {
				data = source.getBytes();
			} else {
				data = source.getBytes(charset);
			}
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initVerify(publicKey);
			signature.update(data);
			return signature.verify(decryptBASE64(sign));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * 方法用途: <br>
	 * 实现步骤: <br>
	 * 
	 * @param publicKey
	 * @param srcData
	 * @param signData
	 *            签名值即传入的已签名的结果
	 * @return
	 */
	public static boolean verify(PublicKey publicKey, byte[] srcData, byte[] signData) {
		try {
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initVerify(publicKey);
			signature.update(srcData);
			return signature.verify(signData);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/** */
	/**
	 * <p>
	 * 公钥加密
	 * </p>
	 * 
	 * @param data
	 *            源数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static String encryptByPublicKey(byte[] data, String publicKey) {
		try {
			byte[] keyBytes = decryptBASE64(publicKey);
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key publicK = keyFactory.generatePublic(x509KeySpec);
			// 对数据加密
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, publicK);
			int inputLen = data.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段加密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
					cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(data, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_ENCRYPT_BLOCK;
			}
			byte[] encryptedData = out.toByteArray();
			out.close();
			return encryptBASE64(encryptedData);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/** */
	/**
	 * <P>
	 * 私钥解密
	 * </p>
	 * 
	 * @param source
	 *            已加密数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static String decryptByPrivateKey(String source, String privateKey) {
		try {
			byte[] encryptedData = decryptBASE64(source);
			byte[] keyBytes = decryptBASE64(privateKey);
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, privateK);
			int inputLen = encryptedData.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段解密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
					cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_DECRYPT_BLOCK;
			}
			byte[] decryptedData = out.toByteArray();
			out.close();
			return new String(decryptedData);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/** */
	/**
	 * <p>
	 * 私钥加密
	 * </p>
	 * 
	 * @param data
	 *            源数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static String encryptByPrivateKey(String source, String privateKey, String charset) {
		try {
			byte[] data = null;
			if (charset == null || charset.trim().equals("")) {
				data = source.getBytes();
			} else {
				data = source.getBytes(charset);
			}
			byte[] keyBytes = decryptBASE64(privateKey);
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, privateK);
			int inputLen = data.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段加密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
					cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(data, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_ENCRYPT_BLOCK;
			}
			byte[] encryptedData = out.toByteArray();
			out.close();
			return encryptBASE64(encryptedData);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/** */
	/**
	 * <p>
	 * 公钥解密
	 * </p>
	 * 
	 * @param source
	 *            已加密数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static String decryptByPublicKey(String source, String publicKey) {
		try {
			byte[] encryptedData = decryptBASE64(source);
			byte[] keyBytes = decryptBASE64(publicKey);
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key publicK = keyFactory.generatePublic(x509KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, publicK);
			int inputLen = encryptedData.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段解密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
					cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_DECRYPT_BLOCK;
			}
			byte[] decryptedData = out.toByteArray();
			out.close();
			return new String(decryptedData);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/** */
	/**
	 * <p>
	 * 获取私钥
	 * </p>
	 * 
	 * @param keyMap
	 *            密钥对
	 * @return
	 * @throws Exception
	 */
	public static String getPrivateKey(Map<String, Object> keyMap) {
		try {
			Key key = (Key) keyMap.get(PRIVATE_KEY);
			return encryptBASE64(key.getEncoded());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/** */
	/**
	 * <p>
	 * 获取公钥
	 * </p>
	 * 
	 * @param keyMap
	 *            密钥对
	 * @return
	 * @throws Exception
	 */
	public static String getPublicKey(Map<String, Object> keyMap) {
		try {
			Key key = (Key) keyMap.get(PUBLIC_KEY);
			return encryptBASE64(key.getEncoded());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static PublicKey loadPubKey(String pubKey) throws Exception {
		try {
			byte[] keyBytes = decryptBASE64(pubKey);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			return keyFactory.generatePublic(keySpec);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public static PrivateKey loadPriKey(String priKey) throws Exception {
		try {
			byte[] keyBytes = decryptBASE64(priKey);
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
			return privateK;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public static PrivateKey loadPriKey(KeyStore keyStore, String certPwd) {
		try {
			Enumeration aliasenum = keyStore.aliases();
			String keyAlias = null;
			if (aliasenum.hasMoreElements()) {
				keyAlias = (String) aliasenum.nextElement();
			}
			return (PrivateKey) keyStore.getKey(keyAlias, certPwd.toCharArray());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static KeyStore getKeyStore(String pfxkeyfile, String keypwd, String type) {
		try {
			KeyStore ks = null;
			if ("JKS".equals(type)) {
				ks = KeyStore.getInstance(type);
			} else if ("PKCS12".equals(type)) {
				ks = KeyStore.getInstance(type);
			}
			FileInputStream fis = new FileInputStream(pfxkeyfile);
			char[] nPassword = (char[]) null;
			nPassword = (keypwd == null) || ("".equals(keypwd.trim())) ? null : keypwd.toCharArray();
			if (ks != null) {
				ks.load(fis, nPassword);
			}
			fis.close();
			return ks;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static X509Certificate getCert(String path) {
		CertificateFactory cf = null;
		FileInputStream in = null;
		try {
			cf = CertificateFactory.getInstance("X.509");
			in = new FileInputStream(path);
			return (X509Certificate) cf.generateCertificate(in);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	static class CerFilter implements FilenameFilter {

		public boolean isCer(String name) {
			if (name.toLowerCase().endsWith(".cer")) {
				return true;
			}
			return false;
		}

		public boolean accept(File dir, String name) {
			return isCer(name);
		}
	}

	/**
	 * 
	 * 方法用途:key为x509的序列号, value为x509证书 <br>
	 * 实现步骤: <br>
	 * 
	 * @param filePath
	 * @return
	 */
	public static Map<String, X509Certificate> initValidateCertFromDir(String filePath) {
		Map certMap = new HashMap<String, X509Certificate>();

		CertificateFactory cf = null;
		FileInputStream in = null;
		try {
			cf = CertificateFactory.getInstance("X.509");
			File fileDir = new File(filePath);
			File[] files = fileDir.listFiles(new CerFilter());
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				in = new FileInputStream(file.getAbsolutePath());
				X509Certificate validateCert = (X509Certificate) cf.generateCertificate(in);
				certMap.put(validateCert.getSerialNumber().toString(), validateCert);
			}
			return certMap;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Certificate getCertInKeyStore(KeyStore keyStore) {
		try {
			Enumeration aliasenum = keyStore.aliases();
			String keyAlias = null;
			if (aliasenum.hasMoreElements()) {
				keyAlias = (String) aliasenum.nextElement();
			}

			Certificate cert = keyStore.getCertificate(keyAlias);
			return cert;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String getCertId(KeyStore ks) {
		try {
			Enumeration aliasenum = ks.aliases();
			String keyAlias = null;
			if (aliasenum.hasMoreElements()) {
				keyAlias = (String) aliasenum.nextElement();
			}
			X509Certificate cert = (X509Certificate) ks.getCertificate(keyAlias);
			return cert.getSerialNumber().toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] decryptBASE64(String key) throws Exception {
		return (new Base64()).decode(key.getBytes());
	}

	public static String encryptBASE64(byte[] key) throws Exception {
		return new String((new Base64()).encode(key), "UTF-8");
	}

	public static void main(String[] args) throws Exception {
		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDGFNrXJbDBLyzn29iLcVj5VM1uXCTxbOhCdtY8He5rSIY9kAJr4MI6d+L6fi7cQiAilVMrR3QyeFrEeD9d+FSYi4p3Q85S9xTC09Od59Jde+FHD2yuuRSQWC7JPJLtxsGMyGQpM5TndkeOrMUoDuD9CXS896M/pA0r3af1Hua2mQIDAQAB";// null;
		String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMYU2tclsMEvLOfb2ItxWPlUzW5cJPFs6EJ21jwd7mtIhj2QAmvgwjp34vp+LtxCICKVUytHdDJ4WsR4P134VJiLindDzlL3FMLT053n0l174UcPbK65FJBYLsk8ku3GwYzIZCkzlOd2R46sxSgO4P0JdLz3oz+kDSvdp/Ue5raZAgMBAAECgYAR+jqSZD1SizGMKYvRvAvTNTsf1QUVVEkQHzD6a80Dt6VXWQafO9rk65SSH3mpS+cWvG678tQ88GdshW1a3frvZHosNaEkxyLNpr1QftmHP76plCTG3dL1mHxT0TeyFxJRnipc2jJGbedKGOqPTOsNbpsM/tSodB/V1dby975kMQJBAONMLkDQHqXtTeYI3XbGkQ+oABr+IVUy3y5nxNt2kKYSWiLRa5/Hyq/3aCN5PrDj+VTtcDLOF42gqUzXHBcjZP8CQQDfGDVk5J8LGoLOrQBOLtQddwZyWcOCGltzuQ8oqeh1BkfNK6jk7O9RK9Mz4KW7MnRl1MnY52+sBHEbBobLcuxnAkBIVV4C0jhupW483mVb/yT6zoP6ExWYf2/23J8RGFJAB2cX3X1ag8JE7X7+iv8gW+xk0or6IAvuzkoSYgJWerURAkBbsV+AsFaOAb9xorlVaPnVgm4r4ajxeFPOJGmOlQbt0j8AnMk2DgvumMydrd9wKTSeBe9QLw8+7DRwnyYXyIxRAkADYWyfAf5vGeHuYwroKyOAamfCU5yNeMaaQcI3bnIMksEzVtlMMArglo4J28UiK++79Z7GSk55JnQgCkzBBf/9";// null;

		try {
			// Map<String, Object> keyMap = RSAUtils.genKeyPair();
			// publicKey = RSAUtils.getPublicKey(keyMap);
			// privateKey = RSAUtils.getPrivateKey(keyMap);
			System.err.println("公钥:" + publicKey);
			System.err.println("私钥：" + privateKey);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.err.println("公钥加密——私钥解密");
		String source = "这是一行没有任何意义的文字，你看完了等于没看，不是吗？";
		System.out.println("\r加密前文字：\r\n" + source);
		byte[] data = source.getBytes();
		String encodedData = encryptByPublicKey(data, publicKey);
		System.out.println("加密后文字：\r\n" + encodedData);
		String decodedData = decryptByPrivateKey(encodedData, privateKey);
		System.out.println("解密后文字: \r\n" + decodedData);

		System.err.println("私钥加密——公钥解密");
		String source1 = "这是一行测试RSA数字签名的无意义文字";
		System.out.println("原文字：\r\n" + source1);
		String encodedData1 = RSAUtils.encryptByPrivateKey(source1, privateKey, "utf-8");
		System.out.println("加密后：\r\n" + encodedData1);
		String decodedData1 = RSAUtils.decryptByPublicKey(encodedData1, publicKey);
		System.out.println("解密后: \r\n" + decodedData1);

		System.err.println("私钥签名——公钥验证签名");
		String sign = RSAUtils.sign(source, privateKey, "utf-8");
		System.err.println("签名:\r" + sign);
		boolean status = RSAUtils.verify(source, publicKey, sign, "utf-8");
		System.err.println("验证结果:\r" + status);

	}
}
