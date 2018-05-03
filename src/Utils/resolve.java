package Utils;

public class resolve {
	public static int num = 0;

	public static void main1(String[] args) {

		Configure.setTempDir("E:\\test");
		Configure.setControlDir("E:\\book");

		System.out.println(path2Path("E:\\book\\aaaa\\aaaa"));
	}

	/**
	 * 将控制路径转换为备份路径
	 * 
	 * @param path
	 * @return
	 */
	public static String path2Path(String path) {
		String ps[] = path.split("\\\\"), result = "";
		int left = Configure.controlDir.getAbsolutePath().split("\\\\").length;
		for (int i = left; i < ps.length; i++)
			result += "\\" + ps[i];
		System.out.println(path + " | " + Configure.tempDir.getAbsolutePath() + result);

		return Configure.tempDir.getAbsolutePath() + result;
		// bug - path.substring(path.indexOf(Configure.controlDir.getName()) +
		// Configure.getControlDir().getName().length());
	}

	/**
	 * 将controlDir中的文件 tmp 映射到tempDir文件中
	 * 
	 * @param path
	 * @return
	 */
	public static String path2Path(String controlDir, String tempDir, String path) {
		String ps[] = path.split("\\\\"), result = "";
		int left = controlDir.split("\\\\").length;
		for (int i = left; i < ps.length; i++)
			result += "\\" + ps[i];

		return tempDir + result;
	}

}
