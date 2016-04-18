package com.tongdatech.map_tile.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

public class MapTileUtils {

	public static byte[] image2byte(String path) {
		byte[] data = null;
		FileImageInputStream input = null;
		try {
			input = new FileImageInputStream(new File(path));
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int numBytesRead = 0;
			while ((numBytesRead = input.read(buf)) != -1) {
				output.write(buf, 0, numBytesRead);
			}
			data = output.toByteArray();
			output.close();
			input.close();
		} catch (FileNotFoundException ex1) {
			ex1.printStackTrace();
		} catch (IOException ex1) {
			ex1.printStackTrace();
		}
		return data;
	}

	public static boolean byte2image(byte[] data, String path) {
		boolean if_success = false;
		if (data.length < 3 || path.equals("")){
			if_success = false;
		}else{
			try {
				FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
				imageOutput.write(data, 0, data.length);
				imageOutput.close();
				System.out.println("Make Picture success,Please find image in " + path);
				if_success = true;
			} catch (Exception ex) {
				System.out.println("Exception: " + ex);
				ex.printStackTrace();
				if_success=false;
			}
		}
		return if_success;
	}

}
