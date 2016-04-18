
package com.tongdatech.sys.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * 安全工具类<br>
 * @author wyk 
 * @version   2009-12-08 下午06:29:48
 */
@SuppressWarnings("restriction")
public class SecurityUtil{

	/**
	 * 把传来的字节数组取底8位转换到十六进制表示的字符串,并返回.
	 * 
	 * @param buffer
	 *            byte[]
	 * @return String
	 */
	public static String toHex(byte[] buffer) {
		String result = "";
		for (int i = 0; i < buffer.length; i++) {
			result += Integer
					.toHexString((0x000000ff & buffer[i]) | 0xffffff00)
					.substring(6)
					+ "-";
		}
		return result.substring(0, result.length() - 1);
	}

	public static byte[] HexStringToByteArray(String str) {
		byte[] buf = new byte[str.length() / 2];
		for (int i = 0; i < buf.length; i++) {
			buf[i] = (byte) (chr2hex(str.charAt(i * 2)) * 0x10 + chr2hex(str
					.charAt(i * 2 + 1)));
		}
		return buf;
	}

	private static byte chr2hex(char chr) {
		switch (chr) {
		case '0':
			return 0x00;
		case '1':
			return 0x01;
		case '2':
			return 0x02;
		case '3':
			return 0x03;
		case '4':
			return 0x04;
		case '5':
			return 0x05;
		case '6':
			return 0x06;
		case '7':
			return 0x07;
		case '8':
			return 0x08;
		case '9':
			return 0x09;
		case 'A':
			return 0x0a;
		case 'B':
			return 0x0b;
		case 'C':
			return 0x0c;
		case 'D':
			return 0x0d;
		case 'E':
			return 0x0e;
		case 'F':
			return 0x0f;
		}
		return 0x00;
	}

	public static void unsignedbyte(byte[] b) {
		int n;
		for (int i = 0; i < b.length; i++) {
			if (b[i] > 0)
				n = (int) b[i];
			else
				n = (256 + b[i]);
			System.out.println(n);
		}
	}

	// 简单加密解密，用于处理显示传输数据
	private final static String pwd1 = "12yL0PDE3tvbqpuOSTj6wQWfRmMJGgkXn5ZUoC7eIrzcFsa8K9Ah4YHxBVlidN";
	private final static String src1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	public static String encrypt1(String str) {
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (src1.indexOf(c) != -1) {
				ret.append(pwd1.charAt(src1.indexOf(c)));
			} else {
				ret.append(c);
			}
		}
		return ret.toString();
	}

	public static String decrypt1(String str) {
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (pwd1.indexOf(c) != -1) {
				ret.append(src1.charAt(pwd1.indexOf(c)));
			} else {
				ret.append(c);
			}
		}
		return ret.toString();
	}

	
	/**
	 * @param str
	 * @return
	 * MD5加密
	 */
	public static String md5(String str) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = str.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char ch[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				ch[k++] = hexDigits[byte0 >>> 4 & 0xf];
				ch[k++] = hexDigits[byte0 & 0xf];
			}
			String dd = new String(ch);
			return dd;
		} catch (Exception e) {
			return null;
		}
	}

	// DES加密，解密
	// -------------DES加密，此方法linux使用时需提高等级---------------------
	private static String KEY_STRING = "eposters";// 密匙必须为八的倍数

//	public static String desen(String str) {
//		Security.addProvider(new com.sun.crypto.provider.SunJCE()); // 提高使用等级
//		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
//		String strOut = "";
//		try {
//			// DES算法要求有一个可信任的随机数源
//			SecureRandom sr = new SecureRandom();
//
//			// 从原始密匙数据创建DESKeySpec对象
//			DESKeySpec dks = new DESKeySpec(KEY_STRING.getBytes());
//
//			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
//			// 一个SecretKey对象
//			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
//			SecretKey key = keyFactory.generateSecret(dks);
//
//			// Cipher对象实际完成加密操作
//			Cipher cipher = Cipher.getInstance("DES");
//
//			// 用密匙初始化Cipher对象
//			cipher.init(Cipher.ENCRYPT_MODE, key, sr);
//
//			// 现在，获取数据并加密
//			byte data[] = str.getBytes(); /* 用某种方法获取数据 */
//
//			// 正式执行加密操作
//			byte encryptedData[] = cipher.doFinal(data);
//
//			strOut = encoder.encode(encryptedData);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return strOut;
//	}

	// -------------DES解密，此方法linux使用时需提高等级---------------------
//	public static String desde(String s) {
//		Security.addProvider(new com.sun.crypto.provider.SunJCE());
//		String strOut = "";
//		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
//		try {
//			// DES算法要求有一个可信任的随机数源
//			SecureRandom sr = new SecureRandom();
//
//			// 从原始密匙数据创建一个DESKeySpec对象
//			DESKeySpec dks = new DESKeySpec(KEY_STRING.getBytes());
//
//			// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
//			// 一个SecretKey对象
//			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
//
//			SecretKey key = keyFactory.generateSecret(dks);
//
//			// Cipher对象实际完成解密操作
//			Cipher cipher = Cipher.getInstance("DES");
//
//			// 用密匙初始化Cipher对象
//			cipher.init(Cipher.DECRYPT_MODE, key, sr);
//
//			// 现在，获取数据并解密
//			byte encryptedData[] = decoder.decodeBuffer(s); /* 获得经过加密的数据 */
//
//			// 正式执行解密操作
//			byte decryptedData[] = cipher.doFinal(encryptedData);
//
//			strOut = new String(decryptedData);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return strOut;
//	}

	// 3DES加密，解密
	private static final String Algorithm = "DESede"; // 定义加密算法,可用DES,DESede,Blowfish

	/**
	 * keybyte 加密密钥，长度为24字节 src 被加密的数据缓冲区（源）
	 */
	public static byte[] encryptMode(byte[] keybyte, byte[] ivbyte,
			byte[] srcbyte) {
		try {
			// 生成密钥
			// DESedeKeySpec dks = new DESedeKeySpec(keybyte);
			// SecretKeyFactory keyFactory =
			// SecretKeyFactory.getInstance(Algorithm);
			// SecretKey deskey = keyFactory.generateSecret(dks);

			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			SecureRandom sr = new SecureRandom();// 可省略此参数

			IvParameterSpec ips = new IvParameterSpec(ivbyte);
			// 加密
			Cipher c1 = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			c1.init(Cipher.ENCRYPT_MODE, deskey, ips, sr);
			return c1.doFinal(srcbyte);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	/**
	 * @param keybyte
	 *            加密密钥，长度为24字节
	 * @param src
	 *            加密后的缓冲区
	 * @return   byte[]
	 */
	public static byte[] decryptMode(byte[] keybyte, byte[] ivbyte,
			byte[] srcbyte) {
		try {
			// 生成密钥
			// DESedeKeySpec dks = new DESedeKeySpec(keybyte);
			// SecretKeyFactory keyFactory =
			// SecretKeyFactory.getInstance("DESede");
			// SecretKey deskey = keyFactory.generateSecret(dks);

			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			SecureRandom sr = new SecureRandom();// 可省略此参数
			IvParameterSpec ips = new IvParameterSpec(ivbyte);
			// 解密
			Cipher c1 = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			c1.init(Cipher.DECRYPT_MODE, deskey, ips, sr);
			return c1.doFinal(srcbyte);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// --------------------------3DES加密------------------------------
//	public static String des3en(String src, String key, String iv) {
//		byte[] keybyte = HexStringToByteArray(key);
//		byte[] srcbyte = {};
//		try {
//			srcbyte = src.getBytes("utf-8");
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		byte[] ivbyte = HexStringToByteArray(iv);
//		byte[] tmpiv = { 0, 1, 2, 3, 4, 5, 6, 7 };
//		for (int ii = 0; ii < 8; ii++) {
//			tmpiv[ii] = ivbyte[ii];
//		}
//		byte[] tmpkey = { 0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7, 0, 1,
//				2, 3, 4, 5, 6, 7 };
//		for (int ii = 0; ii < 24; ii++) {
//			tmpkey[ii] = keybyte[ii];
//		}
//		byte[] byte_tmp, byte_t = { 0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5,
//				6, 7, 0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2,
//				3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7,
//				0, 1, 2, 3, 4, 5, 6, 7 };
//		byte_tmp = encryptMode(tmpkey, tmpiv, srcbyte);
//		for (int i = 0; i < 64; i++)
//			byte_t[i] = byte_tmp[i];
//		return Base64.encode(byte_t).replaceAll("\\n", "");
//	}

	// --------------------------3DES解密------------------------------
//	public static String des3de(String src, String key, String iv) {
//		byte[] keybyte = HexStringToByteArray(key);
//		byte[] srcbyte = {};
//		try {
//			srcbyte = Base64.decode(src.replaceAll("\\n", ""));
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		byte[] ivbyte = HexStringToByteArray(iv);
//		byte[] tmpiv = { 0, 1, 2, 3, 4, 5, 6, 7 };
//		for (int ii = 0; ii < 8; ii++) {
//			tmpiv[ii] = ivbyte[ii];
//		}
//		byte[] tmpkey = { 0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7, 0, 1,
//				2, 3, 4, 5, 6, 7 };
//		for (int ii = 0; ii < 24; ii++) {
//			tmpkey[ii] = keybyte[ii];
//		}
//		byte byte_tmp[] = decryptMode(tmpkey, srcbyte, tmpiv);
//		return byte_tmp.toString();
//	}

	// --------------航信GenerateDigest加密-------------------------------
//	public static String GenerateDigest(String str) {
//		byte[] str_byte = {};
//		try {
//			str_byte = str.getBytes("utf-8");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		byte[] str_hash = SecurityUtil.ComputeHash(str_byte);
//		return Base64.encode(str_hash).toString().replaceAll("\\n", "");
//	}

	// -------------hash加密，返回值与c#相同-------------------------------
	public static byte[] ComputeHash(byte[] str) {
		byte[] rs=null;
		MessageDigest m = null;
		String ha = "SHA-1";
		try {
			m = MessageDigest.getInstance(ha);
			m.update(str);
			rs= m.digest();
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		return rs;
	}
}
