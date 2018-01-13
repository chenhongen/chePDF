package com.che;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import com.che.convert.pdf.StrategyContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PdfDemoApplicationTests {

	@Test
	public void contextLoads() throws IOException {
		Resource resource = new ClassPathResource("static/file/1.doc");
		//System.out.println(resource.getFile().getAbsolutePath());
		new StrategyContext(resource.getFile().getAbsolutePath()).getFileConerter().convert();
	}
	
}
