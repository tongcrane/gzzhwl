package org.roundface.util;

import java.io.File;
import java.io.FileNotFoundException;

public class FileUtils {
	public static String getPath() throws FileNotFoundException {
		String path = FileUtils.class.getResource("/").getPath();

		File s = new File(path).getParentFile();
		String n = s.getPath() + "/out/";
		File f = new File(n);
		if (f.exists()) {
			f.delete();
		}
		return f.getPath();

	}
}
