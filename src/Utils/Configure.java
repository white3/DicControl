package Utils;

public class Configure {
	
	public static String tempPath = "";
	public static String controlName = "";
	public static String controlPath = "";
	
	public static String getControlName() {
		return controlName;
	}

	public static void setControlName(String controlName) {
		Configure.controlName = controlName;
	}

	public static String getControlPath() {
		return controlPath;
	}

	public static void setControlPath(String controlPath) {
		Configure.controlPath = controlPath;
	}

	public static String getTempPath() {
		return tempPath;
	}

	public static void setTempPath(String tempPath) {
		Configure.tempPath = tempPath;
	}
	
}
