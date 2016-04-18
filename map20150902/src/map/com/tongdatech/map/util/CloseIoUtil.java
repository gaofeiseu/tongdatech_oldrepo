package com.tongdatech.map.util;

import java.io.Closeable;

public class CloseIoUtil {
	/***
	 * ¹Ø±ÕIOÁ÷
	 * 
	 * @param cls
	 */
	public static void closeAll(Closeable... cls) {

		if (cls != null) {
			for (Closeable cl : cls) {
				try {
					if (cl != null)
						cl.close();
				} catch (Exception e) {

				} finally {
					cl = null;
				}
			}
		}
	}
}
