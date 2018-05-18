package com.nexcloud.router.register.domain;

import java.util.HashMap;
import java.util.Map;

public class Register {

	private String instanceId;
	private String app;
	private String ipAddr;
	private String hostName;
	private String status;
	private String vipAddress;
	private Map<String, String> port;
	private Map<String, String> dataCenterInfo;
	private Map<String, String> metadata;
	private Map<String, String> leaseInfo; 
	
	// private String healthCheckUrl = "http://111.111.111.11:9999/health";
	
	public Register() {
		// TODO Auto-generated constructor stub
	}

	public Register(String instanceId, String app, String ipAddr, String hostName, String status, String vipAddress, Map<String, String> port,
			Map<String, String> dataCenterInfo, Map<String, String> metadata) {
		super();
		this.instanceId = instanceId;
		this.app = app;
		this.ipAddr = ipAddr;
		this.hostName = hostName;
		this.status = status;
		this.vipAddress = vipAddress;
		this.port = port;
		this.dataCenterInfo = dataCenterInfo;
		this.metadata = metadata;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Map<String, String> getPort() {
		return port;
	}

	public void setPort(Map<String, String> port) {
		this.port = port;
	}

	public Map<String, String> getDataCenterInfo() {
		return dataCenterInfo;
	}

	public void setDataCenterInfo(Map<String, String> dataCenterInfo) {
		this.dataCenterInfo = dataCenterInfo;
	}

	public String getVipAddress() {
		return vipAddress;
	}

	public void setVipAddress(String vipAddress) {
		this.vipAddress = vipAddress;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}

	public Map<String, String> getLeaseInfo() {
		if(leaseInfo == null)
			leaseInfo = new HashMap<>();
		
		return leaseInfo;
	}

	public void setLeaseInfo(Map<String, String> leaseInfo) {
		this.leaseInfo = leaseInfo;
	}

	/*
	public String getHealthCheckUrl() {
		return healthCheckUrl;
	}

	public void setHealthCheckUrl(String healthCheckUrl) {
		this.healthCheckUrl = healthCheckUrl;
	}
	*/

	@Override
	public String toString() {
		return "Register [app=" + app + ", ipAddr=" + ipAddr + ", hostName=" + hostName + ", status=" + status
				+ ", port=" + port + ", dataCenterInfo=" + dataCenterInfo + "]";
	}

	
}
