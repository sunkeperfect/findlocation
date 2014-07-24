package com.find.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.find.model.BaseResult;
import com.find.model.Location;

@Controller
public class LoginController {
	@RequestMapping({ "/index", "/", "index.html" })
	public String startIndex() {
		System.out.println("success.");
		return "index";
	}

}
