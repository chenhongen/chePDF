package com.che.convert.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;

/**
 * rebey.cn
 * @author CHE, DGN
 */
public abstract class FileConverter {

    protected InputStream inStream;
    protected OutputStream outStream;
    protected String inputFilePath;
    
	public FileConverter(String inputFilePath) {
        this.inputFilePath = inputFilePath;
        
        this.inStream = getInFileStream(inputFilePath);
        
        String outFilePath = inputFilePath.substring(0, inputFilePath.lastIndexOf("."));
        this.outStream = getOutFileStream(outFilePath + ".pdf");
    }

    public abstract void convert();
    
    protected OutputStream getOutFileStream(String outputFilePath) {
        File outFile = new File(outputFilePath);
        FileOutputStream oStream = null;
        try {
            outFile.getParentFile().mkdirs();
            outFile.createNewFile();
            oStream = new FileOutputStream(outFile);
        } catch (NullPointerException e) {
            e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

        return oStream;
    }

    protected InputStream getInFileStream(String inputFilePath) {
        File inFile = new File(inputFilePath);
        FileInputStream iStream = null;
		try {
			iStream = new FileInputStream(inFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        return iStream;
    }
}
