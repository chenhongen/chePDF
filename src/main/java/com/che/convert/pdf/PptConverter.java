package com.che.convert.pdf;

import java.io.IOException;

import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.sl.usermodel.SlideShow;
/**
 *
 * @author CHE
 */
public class PptConverter extends PowerPointConverter {

    public PptConverter(String inputFilePath) {
        super(inputFilePath);
    }

    @Override
    public void convert() {
    	try {
			SlideShow<?, ?> ppt = new HSLFSlideShow(inStream);
			readPpt(ppt);
			inStream.close();
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
