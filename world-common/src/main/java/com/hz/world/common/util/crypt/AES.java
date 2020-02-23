package com.hz.world.common.util.crypt;

import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.google.gson.Gson;
import com.hz.world.common.util.DateUtil;
 
 
public class AES {
public static boolean initialized = false;    
    
    /** 
     * AES解密 
     * @param content 密文 
     * @return 
     * @throws InvalidAlgorithmParameterException  
     * @throws NoSuchProviderException  
     */  
    public static byte[] decrypt(byte[] content, byte[] keyByte, byte[] ivByte) throws InvalidAlgorithmParameterException {  
        initialize();  
        try {  
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");  
            Key sKeySpec = new SecretKeySpec(keyByte, "AES");  
              
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIV(ivByte));// 初始化   
            byte[] result = cipher.doFinal(content);  
            return result;  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();    
        } catch (NoSuchPaddingException e) {  
            e.printStackTrace();    
        } catch (InvalidKeyException e) {  
            e.printStackTrace();  
        } catch (IllegalBlockSizeException e) {  
            e.printStackTrace();  
        } catch (BadPaddingException e) {  
            e.printStackTrace();  
        } catch (NoSuchProviderException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return null;  
    }    
      
    public static void initialize(){    
        if (initialized) return;    
        Security.addProvider(new BouncyCastleProvider());    
        initialized = true;    
    }  
    //生成iv    
    public static AlgorithmParameters generateIV(byte[] iv) throws Exception{    
        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");    
        params.init(new IvParameterSpec(iv));    
        return params;    
    }     
 
    public static void main(String[] args) {
    		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		calendar.set(Calendar.HOUR_OF_DAY,4);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.MILLISECOND,0);
		
		try {
		
			Date result = DateUtil.parse("2018-09-23 00:00:00", "yyyy-MM-dd HH:mm:ss");
			long seconds = (calendar.getTimeInMillis() - result.getTime())/1000;
			System.out.println(seconds);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	}

 
}
