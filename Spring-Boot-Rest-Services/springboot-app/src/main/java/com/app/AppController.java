package com.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "web")
public class AppController {
  
	 @RequestMapping(value = "/test")
	 @ResponseBody
	 public String getResponse() {
		 return "Welcome Spring Boot Users..";
	 }
}
