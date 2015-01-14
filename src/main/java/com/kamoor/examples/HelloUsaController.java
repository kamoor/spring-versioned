package com.kamoor.examples;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/v1/hello-usa")
public class HelloUsaController {

	
	@Autowired
	HelloService helloService;

	Logger logger = Logger.getLogger(HelloWorldController.class);

	@RequestMapping(value = "", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody String my() {
		logger.info("Hello USA  ");
		return helloService.test("USA");
	}
}
