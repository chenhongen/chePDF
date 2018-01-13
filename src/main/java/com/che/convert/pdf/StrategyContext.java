package com.che.convert.pdf;

import java.io.IOException;
import java.lang.reflect.Constructor;


public class StrategyContext {
	FileConverter fileConerter;

	/**
	 * 根据输入文件后缀名决策
	 * @param inputFiilePath
	 * @throws IOException 
	 */
	public StrategyContext(String inputFiilePath) {
		try {
			if(inputFiilePath == null || !inputFiilePath.contains(".")) {
				throw new IOException("文件路径有误！");
			}
			
			String suffix = inputFiilePath.toLowerCase().substring(inputFiilePath.lastIndexOf(".") + 1);
			
			Constructor<?> constructor = this.getClass().getClassLoader()
					.loadClass("com.che.convert.pdf."+ upperCase(suffix) + "Converter").getConstructor(String.class);
			
			fileConerter = (FileConverter) constructor.newInstance(inputFiilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public FileConverter getFileConerter() {
		return fileConerter;
	}

	public void setFileConerter(FileConverter fileConerter) {
		this.fileConerter = fileConerter;
	}

	// 首字母大写
	public String upperCase(String str) {  
	    char[] ch = str.toCharArray();  
	    if (ch[0] >= 'a' && ch[0] <= 'z') {  
	        ch[0] = (char) (ch[0] - 32);  
	    }  
	    return new String(ch);  
	}  
}
