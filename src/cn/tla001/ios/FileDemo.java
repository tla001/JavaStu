package cn.tla001.ios;

import java.io.File;
import java.io.IOException;

public class FileDemo {
	public static void main(String[] argvs) {

	}

	public void createFile(String filePath) {
		File f = new File(filePath);
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteFile(String filePath) {
		File f = new File(filePath);
		if (f.exists()) {
			f.delete();
		}
	}

	public void createDir(String dir) {
		File f = new File(dir);
		f.mkdir();
	}

	public void listFileNames(String path) {
		File f = new File(path);
		String[] fileNames = f.list();
		for (String fileName : fileNames) {
			System.out.println(fileName);
		}
	}

	public void listFilePaths(String path) {
		File f = new File(path);
		File[] files = f.listFiles();
		for (File file : files) {
			System.out.println(file);
		}
	}

	public boolean isDir(String path) {
		boolean ret = false;

		File f = new File(path);
		if (f.isDirectory()) {
			System.out.println(path + " is a dir");
			ret = true;
		} else {
			System.out.println(path + " is not a dir");
			ret = false;
		}
		return ret;
	}

	public void listAll(String path) {
		File f = new File(path);
		_listAll(f);
	}

	public void _listAll(File file) {
		if (file != null) {
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				if (files != null) {
					for (File f : files) {
						_listAll(f);
					}
				}
			} else {
				System.out.println(file);
			}
		}
	}
}
