package com.che.convert.pdf;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author Uvaraj, CHE
 */
public abstract class ExcelConverter extends FileConverter {
	private static Font catFont;
	private static Font redFont;
	private static Font subFont;
	private static Font smallBold;
	
	public ExcelConverter(String inputFilePath) {
		
        super(inputFilePath);
        
        // 添加中文支持
		BaseFont bfChinese;
		try {
			bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			//BaseFont bfChinese = BaseFont.createFont("C:/Windows/Fonts/simsunb.ttf",BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			catFont = new Font(bfChinese, 18, Font.BOLD);
			redFont = new Font(bfChinese, 12, Font.NORMAL, BaseColor.RED);
			subFont = new Font(bfChinese, 16, Font.BOLD);
			smallBold = new Font(bfChinese, 12, Font.BOLD);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
    }
	
	protected void readSpreadSheet(Workbook workbook) throws IOException,DocumentException {
    	
		Document document = new Document();
		PdfWriter.getInstance(document, outStream);
		document.open();
		addMetaData(document);
		addTitlePage(document);

		Anchor anchor = new Anchor("First Chapter", catFont);
		anchor.setName("First Chapter");

		// Second parameter is the number of the chapter
		Chapter catPart = new Chapter(new Paragraph(anchor), 1);

		Paragraph subPara = new Paragraph("表格", subFont);
		Section subCatPart = catPart.addSection(subPara);
		addEmptyLine(subPara, 5);

		Sheet sheet = workbook.getSheetAt(0);

		// Iterate through each rows from first sheet
		Iterator<Row> rowIterator = sheet.iterator();
			
//		int temp = 0;
		boolean flag = true;
		PdfPTable table = null;

		while (rowIterator.hasNext()) {
		    Row row = rowIterator.next();
		    int cellNumber = 0;
		    int numberOfColumns = 0;
		
		    if (flag) {
                table = new PdfPTable(row.getLastCellNum());
                flag = false;
		    }
   
			// For each row, iterate through each columns
		    Iterator<Cell> cellIterator = row.cellIterator();
		    while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                /// 数字自动补“.0”，这里全转为字符型
            	cell.setCellType(CellType.STRING);

            	cellNumber = checkEmptyCellAndAddCellContentToPDFTable(cellNumber,cell,table);
            	cellNumber++;
//              break;
//                switch (cell.getCellType()) {  
//                case Cell.CELL_TYPE_STRING:
//                    if (temp == 0) {                                 
//                        numberOfColumns = row.getLastCellNum();
//                        PdfPCell c1 = new PdfPCell(new Phrase(
//                                                cell.getStringCellValue()));
//                        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//                        table.addCell(c1);
//                        table.setHeaderRows(1);
//
//                    }else{
//                        cellNumber =checkEmptyCellAndAddCellContentToPDFTable(cellNumber,cell,table);
//                    }                                                         
//                    cellNumber++;
//                    break;
//                       
//                case Cell.CELL_TYPE_NUMERIC:
//                    cellNumber =checkEmptyCellAndAddCellContentToPDFTable(cellNumber,cell,table);
//                    cellNumber++;
//                    break;
//	            }                     
		    }
//		    temp = 1;
		    if(numberOfColumns != cellNumber){
	            for(int i=0;i<(numberOfColumns-cellNumber);i++){
	            	table.addCell(" ");
	            }
		    }
		}
		subCatPart.add(table);
		// Now add all this to the document
		document.add(catPart);
		document.close();
	}
    
    // Add Meta data in the PDF document
	protected static void addMetaData(Document document) {
        document.addTitle("My first PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Uvaraj");
        document.addCreator("Uvaraj");
	}
    
    // Add Title Page in the PDF document
	protected static void addTitlePage(Document document)
            throws DocumentException {
		Paragraph preface = new Paragraph();
		// We add one empty line
		addEmptyLine(preface, 1);
		// Lets write a big header
		preface.add(new Paragraph("Title of the document", catFont));
		
		addEmptyLine(preface, 1);
		// Will create: Report generated by: _name, _date
		preface.add(new Paragraph("Report generated by: " + "Uvaraj" + ", "
		                        + new Date(), smallBold));
		addEmptyLine(preface, 3);
		preface.add(new Paragraph("This document describes something which is very important ",
		                        smallBold));
		
		addEmptyLine(preface, 8);
		
		preface.add(new Paragraph("This document is a preliminary version  ;-).", redFont));
		
		document.add(preface);
		// Start a new page
		document.newPage();
	}
    
    // Add Empty Lines in the PDF document
	protected static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
        	paragraph.add(new Paragraph(" "));
        }
	}
    
    // 
	protected static int checkEmptyCellAndAddCellContentToPDFTable(int cellNumber, Cell cell, PdfPTable table) {
        if (cellNumber == cell.getColumnIndex()) {
            /*if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                table.addCell(Double.toString(cell.getNumericCellValue()));
            }
            if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                table.addCell(cell.getStringCellValue());
            }*/
        	table.addCell(new PdfPCell(new Phrase(cell.getStringCellValue(), subFont )));
        } else {
            while( cellNumber < cell.getColumnIndex()) {
                         
                table.addCell(" ");
                cellNumber++;
                       
            }
            if (cellNumber == cell.getColumnIndex()) {
                /*///if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                if(cell.getCellTypeEnum() == CellType.NUMERIC){
                	table.addCell(Double.toString(cell.getNumericCellValue()));
                }
                //if(cell.getCellType() == Cell.CELL_TYPE_STRING){ // https://stackoverflow.com/questions/47068697/alternate-for-getcelltype-in-poi-3-17-other-than-getcelltypeenum
                if(cell.getCellTypeEnum() == CellType.STRING){
                	table.addCell(cell.getStringCellValue());
                }*/
            	table.addCell(new PdfPCell(new Phrase(cell.getStringCellValue(), subFont )));
            }
            cellNumber = cell.getColumnIndex();
        }         
       
        return cellNumber;
	}
    // basic on https://sites.google.com/site/uvarajjava/home/excel-to-pdf-using-java
    
}
