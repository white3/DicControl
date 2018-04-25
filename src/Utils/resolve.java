package Utils;

public class resolve {

	/**
	 * 
	 * @param path
	 * @return
	 */
	public static String path2Path(String path) {
		return Configure.tempDir.getAbsolutePath() + path.substring(
				path.indexOf(Configure.controlDir.getName()) + Configure.getControlDir().getName().length());
	}
	
}
