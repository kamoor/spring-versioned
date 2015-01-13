package com.kamoor.helloworld;

import org.springframework.stereotype.Service;

import com.kamoor.version.Versioned;

@Service
public class HelloService {

	@Versioned(feature="module.helloworld", version =1)
	public String test(){
		return "Hello World Version 1";
	}
	
	@Versioned(feature="module.helloworld", version =2)
	public String test2(){
		return "Hello World Version 2";
	}
}
