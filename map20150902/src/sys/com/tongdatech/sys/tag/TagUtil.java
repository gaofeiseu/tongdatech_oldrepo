package com.tongdatech.sys.tag;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagUtil {

	public static String unitAdd(String unit, int offest) {

		Pattern p = Pattern.compile("(\\d+)([a-zA-Z]+)");
		Matcher m = p.matcher(unit);
		if (m.matches()) {
			try {
				String numStr = m.group(1);
				int num = Integer.parseInt(numStr);
				String rs = (num + offest) + m.group(2);
				return rs;
			} catch (Exception e) {
				return unit;
			}
		} else
			return unit;
	}

	public static void main(String[] args) {
		//System.out.println(TagUtil.unitAdd("10px", 2));
		UUID x = UUID.randomUUID();
		System.out.println(x.toString());
	}
}
