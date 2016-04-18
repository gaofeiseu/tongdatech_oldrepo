package com.tongdatech.sys.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * ����תƴ��������       <br>
 * @author CLY 
 * @version    2014-4-11 ����11:25:06
 */
public class PinYinUtil {
	/** HanyuPinyinOutputFormat format*/
	private static HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
	
	

	/**
	 * �ַ���ת����ƴ��ȫƴ
	 * @param string
	 * @return ƴ��
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static String getFullPinYin(String s) throws BadHanyuPinyinOutputFormatCombination{
		StringBuilder sb = new StringBuilder();
		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		
		for(int i=0 ; i<s.length() ; i++){
			char word = s.charAt(i);
			String[] temp_py = PinyinHelper.toHanyuPinyinStringArray(word,format);
			if(temp_py == null){
				sb.append(word);
			}else{
				sb.append(temp_py[0]);
			}
		}
		return sb.toString();
	}
	

	/**
	 * �ַ���ת����ƴ������ĸ
	 * @param string
	 * @return ƴ������ĸ
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static String getHeadPinYin(String s) throws BadHanyuPinyinOutputFormatCombination{
		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0 ; i<s.length() ; i++){
			char word = s.charAt(i);
			String[] temp_py = PinyinHelper.toHanyuPinyinStringArray(word,format);
			if(temp_py == null ){
				sb.append(word);
			}else{
				sb.append(temp_py[0].charAt(0));
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination{
		String s = "�Ͼ�������";
		System.out.println(PinYinUtil.getFullPinYin(s));
		System.out.println(PinYinUtil.getHeadPinYin(s));
	}
	
}
