package test;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

/**
 * 
 * @author Menzel3
 *
 */
public class FileSanner {
	public static File directory;
	// 保存合法文件
	public static ArrayList<String> whiteFiles = new ArrayList<>();
	// 保存合法目录
	public static ArrayList<String> whiteDirectory = new ArrayList<>();
	// 扫描文件的缓存
	public static LinkedList<String> queueDirectory = new LinkedList<>();
	// 扫描目录的缓存
	public static LinkedList<File> queueFiles = new LinkedList<>();
	// 白名单文件
	private static File whiteList = new File("w.me");
	// 警告文件
	public static HashMap<String, Integer> questionFile = new HashMap<>();
	// 0 表示删除，1表示正常，2表示新增文件，3表示被修改

	/**
	 * -k kill 秒杀
	 * -w watch 监控
	 * -r [fileName] remove fileName 从白名单中删除某个文件
	 * -a [fileName] add fileName 从白名单中添加某个文件
	 * -s stop scanner 暂停/开始
	 * @param args
	 */
	public static void main1() {
		/**
		 * 1. 扫描一遍所有目录 2. 将所有文件加入白名单 3. 开始监控目录动态 4. 如果发现目录有增删操作，输出日志
		 */
		FileSanner.directory = new File("E:\\server\\myphp_www\\PHPTutorial\\WWW\\");
		try {
			ArrayList<String> result = initFiles();
			BufferedWriter bwOut = new BufferedWriter(new FileWriter(whiteList));

			for (Iterator<String> iterator = whiteDirectory.iterator(); iterator.hasNext();) {
				String object = iterator.next();
				bwOut.write(object + '\n');
			}

			for (Iterator<String> iterator = whiteFiles.iterator(); iterator.hasNext();) {
				String object = iterator.next();
				bwOut.write(object + '\n');
			}
			bwOut.flush();
			bwOut.close();
			System.out.println("[+]Welcome to the Scanner system");
			System.out.println("[+]System already inited.");
			System.out.println("[+]The file numbers: " + result.size());
			Thread thread = new Thread() {
				public void run() {
					System.out.println("[+]Scanner is Running.");
					while (true) {
						// 首先将第一层目录扫描一遍
						File[] files = directory.listFiles();
						// 遍历扫出的文件数组，如果是文件夹，将其放入到linkedList中稍后处理
						for (int i = 0, j = 0; i < files.length; i++) {
							// System.out.println(files[i].getAbsolutePath());
							if (files[i].isDirectory()) {
								j = whiteDirectory.indexOf(files[i].getAbsolutePath());
								if (j == -1) {
									questionFile.put(files[i].getAbsolutePath(), 2);
									continue;
								}
								queueFiles.add(files[i]);
							} else {
								// 暂时将文件名放入whiteFiles中
								j = whiteFiles.indexOf(files[i].getAbsolutePath());
								if (j == -1) 
									questionFile.put(files[i].getAbsolutePath(), 2);
							}
						}

						// 如果linkedList非空遍历linkedList
						while (!queueFiles.isEmpty()) {
							// 移出linkedList中的第一个
						
							File headDirectory = queueFiles.removeFirst();
							File[] currentFiles = headDirectory.listFiles();
							for (int i = 0, j = 0; j < currentFiles.length; j++) {
								if (currentFiles[j].isDirectory()) {
									// 如果仍然是文件夹，将其放入linkedList中
									i = whiteDirectory.indexOf(currentFiles[j].getAbsolutePath());
									if (i == -1) {
										questionFile.put(currentFiles[j].getAbsolutePath(), 2);
										continue;
									}
									queueFiles.add(currentFiles[j]);
								} else {
									i = whiteFiles.indexOf(currentFiles[j].getAbsolutePath());
									if (i == -1) {
										questionFile.put(currentFiles[j].getAbsolutePath(), 2);
										continue;
									}
								}
							}
						}
						QuestionFileToString();
						try {
							sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};
			thread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 清理问题目录
	 */
	public static void ClearQuestionFile() {
		questionFile.clear();
	}

	/**
	 * 输出问题目录
	 */
	public static void QuestionFileToString() {
		for (Iterator<Entry<String, Integer>> iterator = questionFile.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, Integer> string = iterator.next();
			System.out.println("QF: " + string.getKey() + " QV:" + string.getKey());
		}
	}

	/**
	 * 
	 * @param folderPath
	 *            扫描根目录
	 * @return 所有子文件及目录
	 * @throws Exception
	 */
	public static ArrayList<String> initFiles() throws Exception {
		if (!directory.isDirectory()) {
			throw new Exception('"' + directory.getName() + '"'
					+ " input path is not a Directory , please input the right path of the Directory. ^_^...^_^");
		} else {
			// 首先将第一层目录扫描一遍
			File[] files = directory.listFiles();
			// 遍历扫出的文件数组，如果是文件夹，将其放入到linkedList中稍后处理
			for (int i = 0; i < files.length; i++) {
				// System.out.println(files[i].getAbsolutePath());
				if (files[i].isDirectory()) {
					queueFiles.add(files[i]);
					whiteDirectory.add(files[i].getAbsolutePath());
				} else {
					// 暂时将文件名放入whiteFiles中
					whiteFiles.add(files[i].getAbsolutePath());
				}
			}

			// 如果linkedList非空遍历linkedList
			while (!queueFiles.isEmpty()) {
				// 移出linkedList中的第一个
				File headDirectory = queueFiles.removeFirst();
				File[] currentFiles = headDirectory.listFiles();
				for (int j = 0; j < currentFiles.length; j++) {
					if (currentFiles[j].isDirectory()) {
						// 如果仍然是文件夹，将其放入linkedList中
						queueFiles.add(currentFiles[j]);
						whiteDirectory.add(currentFiles[j].getAbsolutePath());
					} else {
						whiteFiles.add(currentFiles[j].getAbsolutePath());
					}
				}
			}
		}

		return whiteFiles;
	}

	static {
		if (!whiteList.isFile()) {
			try {
				whiteList.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("白名单文件创建失败");
			}
		}
	}
}