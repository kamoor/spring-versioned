package com.poc.examples;

import org.springframework.stereotype.Service;

import com.poc.version.Versioned;

@Service("helloService")
public class HelloService {

	String message;
	
	@Versioned(feature="module.helloworld", version =1)
	public String test(String msg){
		message = msg;
		return "Version 1: Hello "+ msg;
	}
	
	@Versioned(feature="module.helloworld", version =2)
	public String test2(String msg){
		message = msg;
		return "Version 2: Hola " + msg;
	}
	
	@Versioned(feature="module.helloworld", version =3)
	public String test3(String msg){
		message = msg;
		return "Version 3: Hallo "+ msg;
	}
	
	

}
