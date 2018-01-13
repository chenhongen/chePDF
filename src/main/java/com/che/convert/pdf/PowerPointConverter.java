package com.che.convert.pdf;

import java.awt.Dimension;
import java.util.List;

import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.sl.usermodel.SlideShow;

import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 待改进：转换效果不佳，中文未支持
 * @author che
 *
 */
public abstract class PowerPointConverter extends FileConverter {

	public PowerPointConverter(String inputFilePath) {
		super(inputFilePath);
	}
	
	protected void readPpt(SlideShow ppt) {
		Dimension pgsize = ppt.getPageSize();
		Document document = new Document();
	
		try {
	        List<Slide> slide = ppt.getSlides();
	        PdfWriter pdfWriter;
			pdfWriter = PdfWriter.getInstance(document, outStream);
			
	        PdfContentByte cb = new PdfContentByte(pdfWriter);
	        document.open();
	        pdfWriter.clearTextWrap();
	        //  cb.beginText();
	        for (int i = 0; i < slide.size(); i++) {
	            try {
	                PdfGraphics2D graphics4 = (PdfGraphics2D) pdfWriter.getDirectContent().createGraphics((float) pgsize.getWidth(), (float) pgsize.getHeight());
	                slide.get(i).draw(graphics4);
	                graphics4.dispose();
	                document.setPageSize(new Rectangle((float) pgsize.getWidth(), (float) pgsize.getHeight()));
	                document.newPage();
	            } catch (Exception e) {
	            	
	            }
	        }
	
	        // cb.endText();
	        document.close();
	        pdfWriter.close();
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
	}
	    
}
