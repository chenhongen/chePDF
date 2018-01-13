package com.che.convert.pdf;

import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.text.DocumentException;

/**
 * @author CHE
 */
public class XlsxConverter extends ExcelConverter {
	
	public XlsxConverter(String inputFilePath) {
        super(inputFilePath);
    }

    @Override
    public void convert() {
		try {
			OPCPackage pkg = OPCPackage.open(inputFilePath);
			Workbook workbook = new XSSFWorkbook(pkg);
			pkg.close();
			readSpreadSheet(workbook);
			inStream.close();
			outStream.close();
		} catch (InvalidFormatException | IOException | DocumentException e) {
			e.printStackTrace();
		}
    }
    
}
