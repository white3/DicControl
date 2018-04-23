package Main;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author Menzel3
 * @version 1.0
 * @date 2018-04-21
 */
public class MyFileListener extends FileAlterationListenerAdaptor {
	private static Log log = LogFactory.getLog(MyFileListener.class);
	private int addc = 0; // 添加控制
	private int delc = 0; // 删除控制
	private int changec = 0; // 修改控制

	public MyFileListener() {
		super();
	}
	
	public MyFileListener(int addc, int delc, int changec) {
		super();
		this.addc = addc;
		this.delc = delc;
		this.changec = changec;
	}

	/**
	 * 目录发生变化
	 * 
	 * @param directory
	 *            发生变化的目录
	 */
	@Override
	public void onDirectoryChange(File directory) {
		super.onDirectoryChange(directory);
		System.out.println("[+]文件夹改变:" + directory.getAbsolutePath() + "\n[+]改变时间: "
				+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		log.info("文件夹改变:" + directory.getAbsolutePath());
	}

	/**
	 * 新建目录
	 * 
	 * @param directory
	 *            被新建的目录
	 */
	@Override
	public void onDirectoryCreate(File directory) {
		super.onDirectoryCreate(directory);
		System.out.println("[+]新建目录:" + directory.getAbsolutePath() + "\n[+]改变时间: "
				+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		log.info("新建目录:" + directory.getAbsolutePath());
		if(addc==1)
			directory.delete();
	}

	/**
	 * 删除目录
	 * 
	 * @param directory
	 *            被删除目录
	 */
	@Override
	public void onDirectoryDelete(File directory) {
		super.onDirectoryDelete(directory);
		System.out.println("[+]删除目录:" + directory.getAbsolutePath() + "\n[+]改变时间: "
				+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		log.info("删除目录:" + directory.getAbsolutePath());
	}

	/**
	 * 文件发生变化
	 * 
	 * @param file
	 *            发生变化的文件
	 */
	@Override
	public void onFileChange(File file) {
		super.onFileChange(file);
		System.out.println("[+]修改文件:" + file.getAbsolutePath() + "\n[+]改变时间: "
				+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		log.info("修改文件:" + file.getAbsolutePath());
	}

	/**
	 * 新建文件
	 * 
	 * @param file
	 *            被新建的文件
	 */
	@Override
	public void onFileCreate(File file) {
		super.onFileCreate(file);
		System.out.println("[+]新建文件:" + file.getAbsolutePath() + "\n[+]改变时间: "
				+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		log.info("新建文件:" + file.getAbsolutePath());
	}

	/**
	 * 删除文件
	 * 
	 * @param file
	 *            被删除的文件
	 */
	@Override
	public void onFileDelete(File file) {
		super.onFileDelete(file);
		System.out.println("[+]删除文件:" + file.getAbsolutePath() + "\n[+]改变时间: "
				+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		log.info("删除文件:" + file.getAbsolutePath());
		if(addc==1)
			file.delete();
	}

	/**
	 * 检查文件开始
	 * 
	 * @param observer
	 *            文件观察者
	 */
	@Override
	public void onStart(FileAlterationObserver observer) {
		super.onStart(observer);
		// System.out.println("[+]Menzel3 Listener is online !");
		// log.info("开始检查...");
	}

	/**
	 * 检查文件结束
	 * 
	 * @param observer
	 *            文件观察者
	 */
	@Override
	public void onStop(FileAlterationObserver observer) {
		super.onStop(observer);
		// log.info("结束检查...");
		// System.out.println("[+]Menzel3 will wait for you in next time!");
	}
}