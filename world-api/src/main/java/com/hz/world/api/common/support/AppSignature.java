package com.hz.world.api.common.support;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hz.world.api.core.domain.dto.SysReturnCode;
import com.hz.world.common.config.AuthConfig;
import com.hz.world.common.util.crypt.MD5Util;

public class AppSignature {

	private static final Logger log = LoggerFactory.getLogger("filter");
	private final String appId;
	private final long random;
	private final String checkSum;

	private static final String DELIMITER = ":";

	public AppSignature(final String appID, final long rand, final String checkSum) {
		this.appId = appID;
		this.random = rand;
		this.checkSum = checkSum;
	}

	public String getAppId() {
		return this.appId;
	}

	public long getRandom() {
		return this.random;
	}

	public String getCheckSum() {
		return this.checkSum;
	}

	public String encode() {
		StringBuilder signature = new StringBuilder(this.appId);
		signature.append(DELIMITER).append(this.random);
		signature.append(DELIMITER).append(this.checkSum);
		return signature.toString();
	}

	public static AppSignature decode(String appSignature) {
		AppSignature result = null;
		if (null != appSignature) {
			String tokens[] = appSignature.split(DELIMITER);
			if (null != tokens && tokens.length == 3) {
				try {
					String appId = tokens[0];
					long random = Long.parseLong(tokens[1]);
					String checkSum = tokens[2];
					result = new AppSignature(appId, random, checkSum);
				} catch (Exception e) {
					return null;
				}
			}
		}
		return result;
	}

	public static AppSignature generate(String appId, long random) throws Exception {
		StringBuilder secret = new StringBuilder(appId);
		secret.append(DELIMITER).append(random);
		secret.append(DELIMITER).append(getSecret(appId));
		String checkSum = MD5Util.md5Hex(secret.toString());

		return new AppSignature(appId, random, checkSum);
	}

	public static String validate(String inputSignature) {
		AppSignature appSignature = AppSignature.decode(inputSignature);
		if (null == appSignature) {
			log.info(String.format("X-Security %s 无法解析", inputSignature));
			return SysReturnCode.INVALID_SECURITY_CODE.toString();
		}
		String secret = getSecret(appSignature.getAppId());
		if (StringUtils.isBlank(secret)) {
			log.info("无效的 AppId: " + appSignature.getAppId());
			return SysReturnCode.INVALID_SECURITY_CODE.toString();
		}

		long now = System.currentTimeMillis();
		long gap = Math.abs(now - appSignature.getRandom()); // 间隔
		String timeLog = String.format("时间间隔=%s 毫秒, 服务器=%s, 客户端=%s", gap, now, appSignature.getRandom());
		if (gap > AuthConfig.MAX_ALLOWED_TIME_GAP_BETWEEN_CLIENT_AND_SERVER) {
			log.error(timeLog + ", 时间间隔超过 >>> " + gap/1000 + " seconds");
	//		return SysReturnCode.TOO_MUCH_TIME_GAP.toString();
		}
		log.info(timeLog);

		StringBuilder signatureData = new StringBuilder(appSignature.getAppId());
		signatureData.append(DELIMITER).append(appSignature.getRandom());
		signatureData.append(DELIMITER).append(secret);
		String hash = MD5Util.md5Hex(signatureData.toString());

		return hash.equals(appSignature.getCheckSum())?null:SysReturnCode.SECURITY_CODE_MISMATCH.toString();
	}

	private static String getSecret(String appId) {
		return AuthConfig.API_KEYS_MAP.get(appId);
	}
	
	public static void main(String args[]) {
		AppSignature appSignature = null;
		long random = System.currentTimeMillis();
		
		try {
		     appSignature = generate("ytJ9drS5oG7fCjV0", random);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("" + appSignature.getAppId() + ":" + appSignature.random + ":" + appSignature.getCheckSum());
	}
}
