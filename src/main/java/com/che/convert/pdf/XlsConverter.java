package com.che.convert.pdf;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import com.itextpdf.text.DocumentException;

/**
 * @author CHE
 */
public class XlsConverter extends ExcelConverter {
	
	public XlsConverter(String inputFilePath) {
        super(inputFilePath);
    }

    @Override
    public void convert() {
    	try {
			Workbook workbook = new HSSFWorkbook(inStream);
			readSpreadSheet(workbook);
			inStream.close();
			outStream.close();
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
		}
    }
    
}
