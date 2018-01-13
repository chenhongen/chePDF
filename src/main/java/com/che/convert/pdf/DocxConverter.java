package com.che.convert.pdf;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 *
 * @author DGN
 */
public class DocxConverter extends FileConverter {

    public DocxConverter(String inputFilePath) {
        super(inputFilePath);
    }

    @Override
    public void convert() {
        try {
            XWPFDocument document = new XWPFDocument(inStream);
            PdfOptions options = PdfOptions.create();
            PdfConverter.getInstance().convert(document, outStream, options);

        } catch (Exception e) {
        	
        }
    }
    // https://github.com/opensagres/xdocreport/issues/208
    // NoSuchMethodError: org.apache.poi.POIXMLDocumentPart.getPackageRelationship()
}
