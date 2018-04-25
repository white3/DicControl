package Utils;

import java.io.File;

public class Configure {
	public static File controlDir = null;
	public static File tempDir = null;

	public static File getControlDir() {
		return controlDir;
	}

	public static void setControlDir(File controlDir) {
		Configure.controlDir = controlDir;
		Configure.controlDir.mkdir();
	}

	public static void setControlDir(String controlDir) {
		Configure.controlDir = new File(controlDir);
		Configure.controlDir.mkdir();
	}

	public static File getTempDir() {
		return tempDir;
	}

	public static void setTempDir(File tempDir) {
		Configure.tempDir = tempDir;
		Configure.tempDir.mkdir();
	}

	public static void setTempDir(String tempDir) {
		Configure.tempDir = new File(tempDir);
		Configure.tempDir.mkdir();
	}

}
