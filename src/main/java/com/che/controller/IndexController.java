package com.che.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	/**
	 * 访问路径 ../pdf?file=file/1.pdf
	 * @return
	 */
	@RequestMapping("pdf")
	public String pdf() {
		return "web/viewer.html";
	}
	
}
