package b;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class QueryFile {


	static String slipName;
	static Writer writer;
	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		
		System.out.println("请输入要查看的文件夹,文件层级通过'\\\\'分层:");
		String tableFile = input.next();
		
		File Tfile = new File(tableFile);
		
		System.out.println("请输入要保存记录的文件名:");
		String fileName = input.next();
		
		StringBuffer writerFileName = new StringBuffer(tableFile.substring(0, 4));
		writerFileName.append(fileName);
		writerFileName.append(".txt");
		
		File WriterFile = new File(writerFileName.toString());
		if(!WriterFile.exists()){
			WriterFile.createNewFile();
		}
		

		writer = new FileWriter(WriterFile);
		
		System.out.println("是否要忽略忽略目录或文件(y/n)");
		if(input.next().equals("y")){
			System.out.println("请输入要忽略的目录或文件名:");
			slipName = input.next();
		}else{
			slipName = "";
		}
		
		try {
			printAllFile(Tfile);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("执行完成,请在相应路径查看文件!");
		
	}
	
	private static void printAllFile(File file) throws IOException{
			
		if(file.exists()){
			File[] fileArray = file.listFiles();
			for(int i=0;i<fileArray.length;i++){
				if(fileArray[i].isDirectory()){		//判断是否是文件夹
					if(!fileArray[i].getName().equals(slipName)){
						printAllFile(fileArray[i]);
					}
				}else{
					if(!slipName.equals("") && slipName.substring(0, 1).equals("*") && fileArray[i].getName().substring(fileArray[i].getName().lastIndexOf(".") + 1, fileArray[i].getName().length()).equals(slipName.substring(slipName.lastIndexOf(".") + 1,slipName.length()))){
					}else{
						if(!fileArray[i].getName().equals(slipName)){
							
							if(fileArray[i].exists() && fileArray[i].isFile()){
								String str = fileArray[i] + "," + (fileArray[i].length()/1024+1) +"KB\r\n";
								System.out.println(str);
								writer.write(str);
							}
						
						}
					}
					
				}
				
			}
		}
	}
	

}
