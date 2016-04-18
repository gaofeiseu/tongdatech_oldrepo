
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
 * ��ȫ������<br>
 * @author wyk 
 * @version   2009-12-08 ����06:29:48
 */
@SuppressWarnings("restriction")
public class SecurityUtil{

	/**
	 * �Ѵ������ֽ�����ȡ��8λת����ʮ�����Ʊ�ʾ���ַ���,������.
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

	// �򵥼��ܽ��ܣ����ڴ�����ʾ��������
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
	 * MD5����
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

	// DES���ܣ�����
	// -------------DES���ܣ��˷���linuxʹ��ʱ����ߵȼ�---------------------
	private static String KEY_STRING = "eposters";// �ܳױ���Ϊ�˵ı���

//	public static String desen(String str) {
//		Security.addProvider(new com.sun.crypto.provider.SunJCE()); // ���ʹ�õȼ�
//		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
//		String strOut = "";
//		try {
//			// DES�㷨Ҫ����һ�������ε������Դ
//			SecureRandom sr = new SecureRandom();
//
//			// ��ԭʼ�ܳ����ݴ���DESKeySpec����
//			DESKeySpec dks = new DESKeySpec(KEY_STRING.getBytes());
//
//			// ����һ���ܳ׹�����Ȼ��������DESKeySpecת����
//			// һ��SecretKey����
//			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
//			SecretKey key = keyFactory.generateSecret(dks);
//
//			// Cipher����ʵ����ɼ��ܲ���
//			Cipher cipher = Cipher.getInstance("DES");
//
//			// ���ܳ׳�ʼ��Cipher����
//			cipher.init(Cipher.ENCRYPT_MODE, key, sr);
//
//			// ���ڣ���ȡ���ݲ�����
//			byte data[] = str.getBytes(); /* ��ĳ�ַ�����ȡ���� */
//
//			// ��ʽִ�м��ܲ���
//			byte encryptedData[] = cipher.doFinal(data);
//
//			strOut = encoder.encode(encryptedData);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return strOut;
//	}

	// -------------DES���ܣ��˷���linuxʹ��ʱ����ߵȼ�---------------------
//	public static String desde(String s) {
//		Security.addProvider(new com.sun.crypto.provider.SunJCE());
//		String strOut = "";
//		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
//		try {
//			// DES�㷨Ҫ����һ�������ε������Դ
//			SecureRandom sr = new SecureRandom();
//
//			// ��ԭʼ�ܳ����ݴ���һ��DESKeySpec����
//			DESKeySpec dks = new DESKeySpec(KEY_STRING.getBytes());
//
//			// ����һ���ܳ׹�����Ȼ��������DESKeySpec����ת����
//			// һ��SecretKey����
//			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
//
//			SecretKey key = keyFactory.generateSecret(dks);
//
//			// Cipher����ʵ����ɽ��ܲ���
//			Cipher cipher = Cipher.getInstance("DES");
//
//			// ���ܳ׳�ʼ��Cipher����
//			cipher.init(Cipher.DECRYPT_MODE, key, sr);
//
//			// ���ڣ���ȡ���ݲ�����
//			byte encryptedData[] = decoder.decodeBuffer(s); /* ��þ������ܵ����� */
//
//			// ��ʽִ�н��ܲ���
//			byte decryptedData[] = cipher.doFinal(encryptedData);
//
//			strOut = new String(decryptedData);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return strOut;
//	}

	// 3DES���ܣ�����
	private static final String Algorithm = "DESede"; // ��������㷨,����DES,DESede,Blowfish

	/**
	 * keybyte ������Կ������Ϊ24�ֽ� src �����ܵ����ݻ�������Դ��
	 */
	public static byte[] encryptMode(byte[] keybyte, byte[] ivbyte,
			byte[] srcbyte) {
		try {
			// ������Կ
			// DESedeKeySpec dks = new DESedeKeySpec(keybyte);
			// SecretKeyFactory keyFactory =
			// SecretKeyFactory.getInstance(Algorithm);
			// SecretKey deskey = keyFactory.generateSecret(dks);

			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			SecureRandom sr = new SecureRandom();// ��ʡ�Դ˲���

			IvParameterSpec ips = new IvParameterSpec(ivbyte);
			// ����
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
	 *            ������Կ������Ϊ24�ֽ�
	 * @param src
	 *            ���ܺ�Ļ�����
	 * @return   byte[]
	 */
	public static byte[] decryptMode(byte[] keybyte, byte[] ivbyte,
			byte[] srcbyte) {
		try {
			// ������Կ
			// DESedeKeySpec dks = new DESedeKeySpec(keybyte);
			// SecretKeyFactory keyFactory =
			// SecretKeyFactory.getInstance("DESede");
			// SecretKey deskey = keyFactory.generateSecret(dks);

			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			SecureRandom sr = new SecureRandom();// ��ʡ�Դ˲���
			IvParameterSpec ips = new IvParameterSpec(ivbyte);
			// ����
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

	// --------------------------3DES����------------------------------
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

	// --------------------------3DES����------------------------------
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

	// --------------����GenerateDigest����-------------------------------
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

	// -------------hash���ܣ�����ֵ��c#��ͬ-------------------------------
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
