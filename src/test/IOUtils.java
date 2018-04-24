package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtils {
	private static BufferedReader brIn = null;
	private static BufferedWriter bwOut = null;
	private static OutputStream out;
	private static InputStream ins;
	
	public IOUtils(String fileName){
		
	}
	
	public static OutputStream getOut() {
		return out;
	}

	public static void setOut(OutputStream out) {
		IOUtils.out = out;
	}

	public static InputStream getIns() {
		return ins;
	}

	public static void setIns(InputStream ins) {
		IOUtils.ins = ins;
	}

	/**
	 * 向输出流中写入数据
	 * @param out 输出流
	 * @param content 数据
	 * @throws IOException
	 */
	public static void WriteByOutputStream(String content) throws IOException {
		out.write(content.getBytes());
		out.flush(); // flush()方法是必须调用的
	}

	/**
	 * 以某种字符编码读取输入流
	 * @param charset 字符编码格式
	 * @return 流中的数据
	 * @throws IOException
	 */
	public static String ReadByInputStream(String charset) throws IOException {
		byte[] b = new byte[1024];
		int readbytes = -1;
		String stb = "";
		while ((readbytes = ins.read(b)) != -1)
			stb += new String(b, 0, readbytes, charset)+"\n";
		return stb;
	}

	/**
	 * 将数据覆盖写入文件中
	 * @param fileName
	 * @param str
	 * @throws IOException
	 */
	public static void OverWriteFile(String fileName, String str) throws IOException {
		bwOut = new BufferedWriter(new FileWriter(new File(fileName)));
		bwOut.write(str);
		bwOut.close();
	}

	/**
	 * 将数据写入文件末尾处
	 * @param fileName 文件名
	 * @param str 待写入数据
	 * @throws IOException
	 */
	public static void WriteAddToFile(String fileName, String str) throws IOException {
		bwOut = new BufferedWriter(new FileWriter(new File(fileName)));
		bwOut.write(str);
		bwOut.close();
	}

	/**
	 * 以文本格式读取文件所有内容
	 * @param fileName 文件名称
	 * @return 文件内容
	 * @throws IOException
	 */
	public static String ReadFile(String fileName) throws IOException {
		StringBuilder stb = new StringBuilder();
		brIn = new BufferedReader(new FileReader(new File(fileName)));
		String str = "";
		while ((str = brIn.readLine()) != null)
			stb.append(str + "\n");
		brIn.close();
		return stb.toString();
	}

	/**
	 * 删除指定文件
	 * @param file 文件名称
	 */
	public static void Delete(String file) {
		new File(file).delete();
	}
}