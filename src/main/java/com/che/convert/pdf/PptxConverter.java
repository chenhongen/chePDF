package com.che.convert.pdf;

import java.io.IOException;

import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
/**
 *
 * @author CHE
 */
public class PptxConverter extends PowerPointConverter {

    public PptxConverter(String inputFilePath) {
        super(inputFilePath);
    }

    @Override
    public void convert() {
		try {
			SlideShow<?, ?> ppt = new XMLSlideShow(inStream);
			readPpt(ppt);
			inStream.close();
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
