package com.che.convert.pdf;

import org.odftoolkit.odfdom.converter.pdf.PdfConverter;
import org.odftoolkit.odfdom.converter.pdf.PdfOptions;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
/**
 *
 * @author DGN
 */
public class OdtConverter extends FileConverter {

    public OdtConverter(String inputFilePath) {
        super(inputFilePath);
    }

    @Override
    public void convert() {
        try {
            OdfTextDocument document = (OdfTextDocument) OdfTextDocument.loadDocument(inStream);
            PdfOptions options = PdfOptions.create();
            PdfConverter.getInstance().convert(document, outStream, options);
        } catch (Exception e) {
        }
    }

}
