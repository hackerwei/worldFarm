/*
 * MD5Utils.java Created on 2018年3月8日 上午11:53:08
 * Copyright (c) 2018 wuxf Co.Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hz.world.common.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * md5加密工具类
 * 
 * @author <a href="mailto:wuxf_vip@163.com">wuxf</a>
 * @version 1.0
 */
public class MD5Utils {

    private static final Logger logger = LoggerFactory.getLogger(MD5Utils.class);

    /**
     * MD5加密，32位小写
     * 
     * @param s
     *            原文
     * @return 加密后的文本
     */
    public static String MD5(String s) {
        try {
            byte[] bInput = s.getBytes("UTF-8");

            return MD5(bInput);
        } catch (Exception e) {
            logger.error("======MD5加密失败====", e);
        }
        return null;
    }

    /**
     * MD5加密
     * 
     * @param s
     *            原文
     * @param encoding
     *            编码
     * @return 加密后的文本
     */
    public static String MD5(String s, String encoding) {
        try {
            byte[] bInput = s.getBytes(encoding);

            return MD5(bInput);
        } catch (Exception e) {
            logger.error("======MD5加密失败====", e);
        }
        return null;
    }

    /**
     * MD5加密
     * 
     * @param bytes
     * @return
     */
    public static String MD5(byte[] bytes) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            byte[] b = md.digest();
            int j = b.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = b[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            logger.error("======MD5加密失败====", e);
        }
        return null;
    }

    /**
     * MD5加密
     * 
     * @param s
     * @return byte[]
     */
    public static byte[] Md5Byte(String s) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            return md5.digest(s.getBytes("UTF-8"));
        } catch (Exception e) {
            logger.error("======MD5加密失败====", e);
        }
        return null;
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
