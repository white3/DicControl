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
		System.out.println("[?] -[a|d|c] [directory] [temp directory]");
		System.out.println("[?] -a control the action of add 增加添加控制");
		System.out.println("[?] -d control the action of delete 增加删除控制");
		System.out.println("[?] -c control the action of change 增加修改控制");
	}

	/**
	 * test
	 */
	// int addc = 1, delc = 1, changec = 1, ques = 0;

	// if (args.length == 3) {
	// dir = args[1];
	// tempPath = args[2];
	// } else if (args.length == 4) {
	// chs = args[1].toCharArray();
	// for (int i = chs.length - 1; i > 0; i--) {
	// switch (chs[i]) {
	// case 'a':
	// addc = 1;
	// break;
	// case 'd':
	// delc = 1;
	// break;
	// case 'c':
	// changec = 1;
	// break;
	// case '-':
	// continue;
	// default:
	// ques = 1;
	// throw new Exception();
	// }
	// }
	// dir = args[2];
	// tempPath = args[3];
	// } else {
	// ques = 1;
	// throw new Exception();
	// }

	/**
	 * 测试文件系统监测
	 * 
	 * @param String[]
	 *            args
	 */
	public static void main(String[] args) {
		long interval = 1000;
		String dir = "", tempPath = "";
		Scanner cin = new Scanner(System.in);
		int addc = 0, delc = 0, changec = 0, ques = 0;
		try {
			System.out.println("[+]Menzel3 Listener is online !");
			help();
			System.out.println("[+]Input the directory: ");
			dir = cin.nextLine();
			final String path = dir;
			System.out.println("[+]Input the temp directory: ");
			tempPath = cin.nextLine();
			System.out.println("[+]Input the select value {adc}, for example: 000.");
			addc = cin.nextInt();
			changec = (addc/10)%10;
			delc = addc%10;
			addc /= 100;
			cin.close();
			if (!new File(dir).isDirectory()) {
				ques = 2;
				throw new Exception();
			}
			Configure.setTempDir(tempPath);
			Configure.setControlDir(path);
			new SynchronousFile().copyDirectory(path, tempPath);

			FileAlterationObserver observer = null;
			try {
				observer = new FileAlterationObserver(path, null, null);
				observer.addListener(new MyFileListener(addc, delc, changec)); // 添加监听器
				FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
				monitor.start();
			} catch (Exception e) {
				log.error("Exception", e);
			}
		} catch (Exception e) {
			if (ques == 1) // 输入格式错误
				help();
			else if (ques == 2) // 目录错误
				System.out.println("[-]Please input the right directory.");
		}
	}
}