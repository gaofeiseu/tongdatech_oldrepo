package com.tongdatech.map.applet;

import java.applet.Applet;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class MapCapture extends Applet {
	String savePath = "";

	/**
	 * Constructor of the applet.
	 * 
	 * @exception HeadlessException
	 *                if GraphicsEnvironment.isHeadless() returns true.
	 */
	public MapCapture() {
		super();
	}

	/**
	 * Called by the browser or applet viewer to inform this applet that it is
	 * being reclaimed and that it should destroy any resources that it has
	 * allocated. The <code>stop</code> method will always be called before
	 * <code>destroy</code>.
	 * <p>
	 * 
	 * A subclass of <code>Applet</code> should override this method if it has
	 * any operation that it wants to perform before it is destroyed. For
	 * example, an applet with threads would use the <code>init</code> method to
	 * create the threads and the <code>destroy</code> method to kill them.
	 * <p>
	 */
	@Override
	public void destroy() {
		// Put your code here
	}

	/**
	 * Returns information about this applet. An applet should override this
	 * method to return a <code>String</code> containing information about the
	 * author, version, and copyright of the applet.
	 * <p>
	 * 
	 * @return a string containing information about the author, version, and
	 *         copyright of the applet.
	 */
	@Override
	public String getAppletInfo() {
		return "This is my default applet created by Eclipse";
	}

	/**
	 * Called by the browser or applet viewer to inform this applet that it has
	 * been loaded into the system. It is always called before the first time
	 * that the <code>start</code> method is called.
	 * <p>
	 * 
	 * A subclass of <code>Applet</code> should override this method if it has
	 * initialization to perform. For example, an applet with threads would use
	 * the <code>init</code> method to create the threads and the
	 * <code>destroy</code> method to kill them.
	 * <p>
	 */
	@Override
	public void init() {

	}

	/**
	 * Called by the browser or applet viewer to inform this applet that it
	 * should start its execution. It is called after the <code>init</code>
	 * method and each time the applet is revisited in a Web page.
	 * <p>
	 * 
	 * A subclass of <code>Applet</code> should override this method if it has
	 * any operation that it wants to perform each time the Web page containing
	 * it is visited. For example, an applet with animation might want to use
	 * the <code>start</code> method to resume animation, and the
	 * <code>stop</code> method to suspend the animation.
	 * <p>
	 */
	@Override
	public void start() {
		// Put your code here
	}

	/**
	 * Called by the browser or applet viewer to inform this applet that it
	 * should stop its execution. It is called when the Web page that contains
	 * this applet has been replaced by another page, and also just before the
	 * applet is to be destroyed.
	 * <p>
	 * 
	 * A subclass of <code>Applet</code> should override this method if it has
	 * any operation that it wants to perform each time the Web page containing
	 * it is no longer visible. For example, an applet with animation might want
	 * to use the <code>start</code> method to resume animation, and the
	 * <code>stop</code> method to suspend the animation.
	 * <p>
	 */
	@Override
	public void stop() {
		// Put your code here
	}

	// ��ȡ��Ļ�ض�λ�õ�ͼƬ
	public void mapCapture(String indexXString, String indexYString,
			String widthString, String heightString) throws Exception {
		showSaveDialog();
		Thread.sleep(200);
		Robot robot = new Robot();
		int indexX = Integer.parseInt(indexXString);
		int indexY = Integer.parseInt(indexYString);
		int width = Integer.parseInt(widthString);
		int height = Integer.parseInt(heightString);
		Rectangle area = new Rectangle(indexX, indexY, width, height);
		BufferedImage bufferedImage = robot.createScreenCapture(area);

		// ��ͼƬ����ΪJPEG PNG GIF BMP��ʽ��JPEGΪ���𱣴�
		String format = savePath.split("\\.")[1].toUpperCase();
		System.out.println("�����ʽΪ:" + format);
		File file = new File(savePath);
		ImageIO.write(bufferedImage, format, file);
	}

	// �򿪺ϲ�ͼƬ�����
	public void mapCombine(String softwarePath, String softwareName)
			throws Exception {
		String cmd = softwarePath + "\\" + softwareName;
		System.out.println(cmd);
		// �����뵱ǰ Java Ӧ�ó�����ص�����ʱ����
		Runtime run = Runtime.getRuntime();
		// ������һ��������ִ������
		Process p = run.exec(cmd);// ������һ��������ִ������
	}

	// �򿪱���Ի���
	public void showSaveDialog() {
		JFileChooser chooser = new JFileChooser();
		// ���ñ���Ի���
		chooser.setDialogType(JFileChooser.SAVE_DIALOG);
		int index = chooser.showDialog(null, "����ͼƬ");
		if (index == JFileChooser.APPROVE_OPTION) {
			File f = chooser.getSelectedFile();
			String fileName = chooser.getName(f);
			savePath = chooser.getCurrentDirectory().getAbsolutePath() + "\\"
					+ fileName;
			System.out.println("����·��Ϊ:" + savePath);
		}
	}
}
