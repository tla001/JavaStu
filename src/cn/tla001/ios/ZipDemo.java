package cn.tla001.ios;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.junit.Test;

public class ZipDemo {
	@Test
	public void test1() throws IOException {
		String path = "D:/a.zip";
		ZipFile zipFile = new ZipFile(path);
		ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(
				path));
		ZipEntry entry = null;
		while ((entry = zipInputStream.getNextEntry()) != null) {
			if (entry.isDirectory()) {
				System.out.println(entry.getName() + " is directory");
			} else {
				InputStream inputStream = zipFile.getInputStream(entry);
				// byte[] buf = new byte[1024];
				// int len;
				// while ((len = inputStream.read(buf)) != -1) {
				// System.out.print(buf);
				// }
				InputStreamReader inputStreamReader = new InputStreamReader(
						inputStream);
				BufferedReader bufferedReader = new BufferedReader(
						inputStreamReader);
				String string = null;
				while ((string = bufferedReader.readLine()) != null) {
					System.out.println(string);
				}
				inputStream.close();
			}
		}
	}
}
