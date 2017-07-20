import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;

import org.omg.CORBA.PUBLIC_MEMBER;

public class Main {
	public static String reg = "name";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		extract("C:\\Users\\Administrator\\Desktop\\xxx.txt", reg);
	}

	public static void extract(String filePath, String reg) {
		try {
			String encoding = "UTF-8";
			File file = new File(filePath);
			Writer out = new FileWriter(new File("D:\\1.txt"));
			BufferedWriter writer = new BufferedWriter(out);

			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				boolean isTarget = false;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					if (lineTxt.contains(reg)) {
						isTarget = true;
						writer.write(lineTxt);
					} else if (isTarget) {// 在目标范围内，就打印
						if (lineTxt.isEmpty()) {
							isTarget = false;
						}
						writer.write(lineTxt);
					}else {
						continue;
					} 
					writer.write(System.getProperty("line.separator"));
				}
				read.close();
				writer.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
	}

}
