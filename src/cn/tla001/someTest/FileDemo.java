package cn.tla001.someTest;

import java.io.File;
import java.io.IOException;

public class FileDemo {
	public static void main(String[] args) {
		FileDemo fileTest = new FileDemo();
		fileTest.loopFiles(".");
	}

	// 创建文件
	public void createFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String fileName = file.getName();
		System.out.println("file name is " + fileName);
		String path_ = file.getPath();
		System.out.println("file path is " + path);
		String absPath = file.getAbsolutePath();
		System.out.println("absolute path is " + absPath);
		String parentName = file.getParent();
		System.out.println("parent name is " + parentName);
		long fileSize = file.length();
		System.out.println("file size is " + fileSize);
		long time = file.lastModified();
		System.out.println("last time to modify :" + time);
	}

	// 创建文件夹
	public void createDir(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.mkdirs();
		}
	}

	// 遍历文件夹中的文件并显示
	public void loopFiles(String path) {
		File file = new File(path);
		File[] files = file.listFiles();
		for (File f : files) {
			if (f.isFile()) {
				System.out.println(f.getName() + "\tis a file");
			}
			if (f.isDirectory()) {
				System.out.println(f.getName()
						+ "\tis a dir,it has files as follow:");
				loopFiles(f.getPath());
			}
		}
	}

	// 拷贝文件
	public void copyFile(String src, String dst) {
		File srcFile = new File(src);
		String toPath = dst;
		File dstFile = new File(toPath);
		if (!dstFile.exists()) {
			dstFile.mkdirs();
		}
		File[] files = srcFile.listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				copyFile(f.getPath(), toPath);
			}
			if (f.isFile()) {
				String tempPath = toPath + "/" + f.getName();
				File fd = new File(tempPath);
				if (!fd.exists()) {
					try {
						fd.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
