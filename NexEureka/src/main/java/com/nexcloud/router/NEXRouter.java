package com.nexcloud.router;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexcloud.router.service.RegisterExecutor;


@EnableEurekaServer
@RestController
@SpringBootApplication
public class NEXRouter {
	
	/**
	 * Service Register Using Thread
	 */
	@Autowired
	private RegisterExecutor registerExecutor;
	
	@Scheduled(fixedDelay = 1000*20, initialDelay = 1000)
	public void serviceRegister() {
		try {
			registerExecutor.registerExecute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/*
	@Autowired
	private RegisterService registerService;
	
	@Scheduled(fixedDelay = 1000*20, initialDelay = 1000)
	public void serviceRegister() {
		try {
			registerService.register();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	*/
	
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
		SpringApplication.run(NEXRouter.class, args);
	}
}
