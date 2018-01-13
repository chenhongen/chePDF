package com.che.convert.pdf;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.docx4j.Docx4J;
import org.docx4j.convert.out.FOSettings;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFont;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.jaxb.Context;
import org.docx4j.convert.in.Doc;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.RFonts;

/**
 *
 * @author DGN, CHE
 */
public class DocConverter extends FileConverter {

    public DocConverter(String inputFilePath) {
        super(inputFilePath);
    }

    @Override
    public void convert() {

        InputStream iStream = inStream;
//
//        WordprocessingMLPackage wordMLPackage = null;
//        try {
//            wordMLPackage = getMLPackage(iStream);
//        } catch (Exception ex) {
//            Logger.getLogger(DocConverter.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        //if(wordMLPackage != null) {
//	        try {
//	            Docx4J.toPDF(wordMLPackage, outStream);
//	        } catch (Docx4JException ex) {
//	            Logger.getLogger(DocConverter.class.getName()).log(Level.SEVERE, null, ex);
//	        }
//        //}
        try {
	    	WordprocessingMLPackage wordMLPackage = getMLPackage(iStream);  
	    	Mapper fontMapper = new IdentityPlusMapper();  
	    	String fontFamily = "SimSun";  
	    	  
//	    	Resource fileRource = new ClassPathResource("/C:/Windows/FONTS/百度综艺简体.ttf");  
//	    	String path =  fileRource.getFile().getAbsolutePath();  
//	    	URL fontUrl = new URL("file:"+path);  
//	    	PhysicalFonts.addPhysicalFont(fontUrl);  
	    	  
	    	PhysicalFont simsunFont = PhysicalFonts.get(fontFamily);  
	    	fontMapper.put(fontFamily, simsunFont);  
	    	  
	    	  
	    	RFonts rfonts = Context.getWmlObjectFactory().createRFonts(); // 设置文件默认字体  
	    	rfonts.setAsciiTheme(null);  
	    	rfonts.setAscii(fontFamily);  
	    	wordMLPackage.getMainDocumentPart().getPropertyResolver().getDocumentDefaultRPr().setRFonts(rfonts);  
	    	wordMLPackage.setFontMapper(fontMapper);  
	    	FOSettings foSettings = Docx4J.createFOSettings();  
	    	foSettings.setWmlPackage(wordMLPackage);  
	    	Docx4J.toFO(foSettings, outStream, Docx4J.FLAG_EXPORT_PREFER_XSL); 
        } catch (Exception ex) {
          Logger.getLogger(DocConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected WordprocessingMLPackage getMLPackage(InputStream iStream) throws Exception {
        PrintStream originalStdout = System.out;

        //Disable stdout temporarily as Doc convert produces alot of output
        System.setOut(new PrintStream(new OutputStream() {
            public void write(int b) {
                //DO NOTHING
            }
        }));

       WordprocessingMLPackage mlPackage = Doc.convert(iStream);
        //  WordprocessingMLPackage mlPackage = WordprocessingMLPackage.load(iStream);

        System.setOut(originalStdout);
        return mlPackage;
    }
}
