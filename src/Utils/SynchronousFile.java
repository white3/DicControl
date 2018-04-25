package Utils;

import java.io.*;
import java.util.LinkedList;

public class SynchronousFile {
	private InputStream in = null;
	private OutputStream out = null;

	/**
	 * path 路径下的文件目录复制到 newPath 目录路径中
	 * 
	 * @param path
	 *            被移动目录或旧目录路径
	 * @param newPath
	 *            即将新建的目录路径
	 * @return 如果为 1， 则移动成功，否则失败。
	 */
	public int copyDirectory(String path, String newPath) {
		return copyDirectory(new File(path), new File(newPath));
	}

	/**
	 * oD 文件目录复制到 nD 目录中
	 * 
	 * @param oD
	 *            被移动目录或旧目录
	 * @param nD
	 *            即将新建的目录
	 * @return 如果为 1， 则移动成功，否则失败。
	 */
	public int copyDirectory(File oD, File nD) {
		LinkedList<File> dics = new LinkedList<>();
		LinkedList<File> fils = new LinkedList<>();
		LinkedList<File> newf = new LinkedList<>();
		String tmp = "", ndp = nD.getAbsolutePath().trim(); // tmp 为缓存空间，ndp 为新文件夹的绝对路径
		int odpl = oD.getAbsolutePath().length(); // 被移动文件夹的路径长度
		File[] temp;
		if (!oD.isDirectory())
			return 0;
		nD.mkdir();
		temp = oD.listFiles();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].isDirectory()) {
				dics.add(temp[i]);
				new File(ndp + "\\" + temp[i].getName()).mkdir();
			} else {
				fils.add(temp[i]);
				newf.add(new File(ndp + "\\" + temp[i].getName()));
			}
		}
		// 遍历整个目录，将被复制文件存储于链表 fils 中，新文件存储于链表 newf 中
		while (!dics.isEmpty()) {
			temp = dics.removeFirst().listFiles();
			for (int i = 0; i < temp.length; i++) {
				if (temp[i].isDirectory()) {
					dics.add(temp[i]);
					new File(ndp + "\\" + temp[i].getName()).mkdirs();
				} else {
					fils.add(temp[i]);
					tmp = temp[i].getAbsolutePath();
					newf.add(new File(ndp + tmp.substring(odpl)));
				}
			}
		}
		while (!fils.isEmpty() && !newf.isEmpty())
			SyncFile(fils.removeFirst(), newf.removeFirst());
		return 1;
	}

	/**
	 * 同步fin文件到fout中，若 fin 文件不存在则返回0
	 * 
	 * @param path1
	 * @param path2
	 */
	public int SyncFile(String path1, String path2) {
		return SyncFile(new File(path1), new File(path2));
	}

	/**
	 * 同步fin文件到fout中，若 fin 文件不存在则返回0
	 * 
	 * @param fin
	 * @param fout
	 */
	public int SyncFile(File fin, File fout) {
		byte[] b = new byte[1024];
		int readbytes = -1;
		if (!fin.isFile())
			return 0;
		try {
			fout.createNewFile();
			in = new FileInputStream(fin);
			out = new FileOutputStream(fout);
			while ((readbytes = in.read(b)) != -1) {
				out.write(b);
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			clearIO();
		}
		return 1;
	}

	/**
	 * 清理IO流缓存
	 */
	public void clearIO() {
		if (in != null)
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		if (out != null)
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
