package com.kamoor.helloworld;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.nube.portal.server.Server;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Server.class)
@WebAppConfiguration
public class HelloTest {
	
	@Test
	public void helloWorld(){
		ResponseEntity<String> entity = new TestRestTemplate().getForEntity("http://localhost:8080/v1/helloworld", String.class);
		Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
	}
}
