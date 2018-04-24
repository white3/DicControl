package Main;

import java.io.File;
import java.util.Scanner;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import Utils.Configure;
import Utils.SynchronousFile;

/**
 * 文件系统监测测试类
 * 
 * @author Menzel3
 *
 */
public class Main {
	private static Log log = LogFactory.getLog(Main.class);

	public static void help() {
		System.out.println("[?]Please input like this:");
		System.out.println("[?] -[a|d|c] [directory]");
		System.out.println("[?] -a control the action of add 增加添加控制");
		System.out.println("[?] -d control the action of delete 增加删除控制");
		System.out.println("[?] -c control the action of change 增加修改控制");
	}

	/**
	 * 初始化配置
	 * @param path
	 * @param tempPath
	 */
	public static void init(String path, String tempPath){
		File f = new File(path);
		Configure.setControlName(f.getName());
		Configure.setControlPath(f.getAbsolutePath());
		Configure.setTempPath(tempPath);
		new SynchronousFile().copyDirectory(path, tempPath);
	}
	
	/**
	 * 测试文件系统监测
	 * 
	 * @param String[]
	 *            args
	 */
	public static void main(String[] args) {
		long interval = 1000;
		String dir = "";
		// mzl -[a|d|c] dir
		char[] chs;
		int addc = 0, delc = 0, changec = 0, ques = 0;
		try {
//			if (args.length == 2)
//				dir = args[1];
//			else if (args.length == 3) {
//				chs = args[1].toCharArray();
//				if (chs.length > 4) {
//					ques = 1;
//					throw new Exception();
//				} else
//					for (int i = chs.length - 1; i > 0; i--) {
//						switch (chs[i]) {
//						case 'a':
//							addc = 1;
//							break;
//						case 'd':
//							delc = 1;
//							break;
//						case 'c':
//							changec = 1;
//							break;
//						case '-':
//							continue;
//						default:
//							ques = 1;
//							throw new Exception();
//						}
//					}
//				dir = args[2];
//			}
//			System.out.println("[+]Menzel3 Listener is online !");
//			System.out.println("[+]The directory: ");
//			final String path = dir;
//			if (!new File(dir).isDirectory()) {
//				ques = 2;
//				throw new Exception();
//			}
			/**
			 * test
			 */
			String path = "E:\\code\\Java\\DirectoryControl\\aaaaa";
			String path2 = "E:\\code\\Java\\DirectoryControl\\eeeee";
			init(path, path2);
			
//			FileAlterationObserver observer = null;
//			try {
//				observer = new FileAlterationObserver(path, null, null);
//				observer.addListener(new MyFileListener(addc, delc, changec)); // 添加监听器
//				FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
//				monitor.start();
//			} catch (Exception e) {
//				log.error("Exception", e);
//			}
		} catch (Exception e) {
			if (ques == 1) // 输入格式错误
				help();
			else if (ques == 2) // 目录错误
				System.out.println("[-]Please input the right directory.");
		}
	}
}