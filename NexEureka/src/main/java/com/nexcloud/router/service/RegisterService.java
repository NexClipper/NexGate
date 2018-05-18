package com.nexcloud.router.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nexcloud.router.marathon.domain.Marathon;
import com.nexcloud.router.marathon.domain.Task;
import com.nexcloud.router.register.domain.Instance;
import com.nexcloud.router.register.domain.Register;
import com.nexcloud.router.util.Util;

/**
 * Service Registry
 * @author Nelson Kim
 *
 */
@Service
@Configuration
@PropertySource("classpath:application.properties")
public class RegisterService {

	private final RestTemplate restTemplate = new RestTemplate();

	@Value("${marathon.tasks.endpoint}")
	private String marathon_tasks_endpoint;

	@Value("${eureka.endpoint}")
	private String eurekaUrl;

	@Value("${eureka.endpoint_test}")
	private String eurekaUrlTest;

	@Value("${op.mode}")
	private String mode;

	public void register() {

		ResponseEntity<Marathon> marathonRes = restTemplate.getForEntity(marathon_tasks_endpoint, Marathon.class);

		Marathon marathon = marathonRes.getBody();
		Instance instance = new Instance();
		Register register = instance.getInstance();
		Map<String, String> metadata = new HashMap<>();
		Map<String, String> leaseInfo = new HashMap<>();

		String eurekaEndpoint = null;
		if ("O".equals(mode))
			eurekaEndpoint = eurekaUrl;
		else
			eurekaEndpoint = eurekaUrlTest;

		leaseInfo.put("durationInSecs", "30");

		for (Task task : marathon.getTasks()) {
			if (task.getPorts().size() <= 2 && "TASK_RUNNING".equals(task.getState().trim())) {
				String[] dummy = task.getAppId().split("/");
				String app = "";
				String name = dummy[dummy.length - 1];

				String prefix = "";
				if (dummy.length > 2) {
					for (int i = 0; i < dummy.length - 1; i++)
						prefix = dummy[i] + "_";
				}
				name = prefix + name;

				for (int i = dummy.length - 1; i > 0; i--) {
					if ("".equals(app))
						app = dummy[i];
					else
						app += "." + dummy[i];
				}
				String ipAddr = task.getHost();

				String hostName = "";
				if ("O".equals(mode))
					hostName = app + ".marathon.mesos"; // Operate config
				else
					hostName = task.getHost(); // Test config

				String status = "UP";
				Map<String, String> dataCenterInfo = new HashMap<>();
				dataCenterInfo.put("@class", "com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo");
				dataCenterInfo.put("name", "MyOwn");

				register.setApp(name);
				register.setIpAddr(ipAddr);
				register.setHostName(hostName);
				register.setStatus(status);
				register.setVipAddress(name);
				register.setDataCenterInfo(dataCenterInfo);

				Map<String, String> port = new HashMap<>();
				for (int i = 0; i < task.getPorts().size(); i++) {
					port.put("$", Integer.toString(task.getPorts().get(i)));
					port.put("@enabled", "true");
					metadata.put("management.port", Integer.toString(task.getPorts().get(i)));

					register.setMetadata(metadata);
					register.setPort(port);
					register.setInstanceId(hostName + ":" + task.getPorts().get(i));
					register.setLeaseInfo(leaseInfo);

					instance.setInstance(register);
					
					HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.APPLICATION_JSON);
					String reqBody = Util.beanToJson(instance);
					
					restTemplate.exchange(eurekaEndpoint+name, HttpMethod.POST, new HttpEntity<String>(reqBody, headers), String.class);
				}
			}
		}
	}
}
