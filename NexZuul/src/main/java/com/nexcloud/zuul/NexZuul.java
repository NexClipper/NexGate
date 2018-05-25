package com.nexcloud.zuul;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class NexZuul {
	
	/**
	 * DC/OS Process Check Bit
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/check")
	public String checkBit(HttpServletRequest request) throws Exception {

		return "";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(NexZuul.class, args);
	}
}
