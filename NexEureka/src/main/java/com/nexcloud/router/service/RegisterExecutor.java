package com.nexcloud.router.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

/**
 * Service Registry Executor
 * @author Nelson Kim
 *
 */
@Service
@Configuration
@PropertySource("classpath:application.properties")
public class RegisterExecutor {

	private final ExecutorService tPool = Executors.newFixedThreadPool(3);

	@Value("${marathon.tasks.endpoint}")
	private String marathon_tasks_endpoint;

	@Value("${eureka.endpoint}")
	private String eurekaUrl;

	@Value("${eureka.endpoint_test}")
	private String eurekaUrlTest;

	@Value("${op.mode}")
	private String mode;
	
	public void registerExecute(){
		RegisterTask registerTask = new RegisterTask(marathon_tasks_endpoint, eurekaUrl, eurekaUrlTest, mode);
		tPool.execute(registerTask);
	}
}
