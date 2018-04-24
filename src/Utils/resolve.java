package Utils;

public class resolve {

	/**
	 * 
	 * @param path
	 * @return
	 */
	public static String path2Path(String path) {
		return Configure.getTempPath()
				+ path.substring(path.indexOf(Configure.getControlName() + Configure.controlName.length()));

	}

}
