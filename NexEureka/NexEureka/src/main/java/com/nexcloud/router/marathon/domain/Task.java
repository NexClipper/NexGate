package com.nexcloud.router.marathon.domain;

import java.util.ArrayList;
import java.util.List;

public class Task {

	private String appId;			// app에 매칭
	private String id;				// hostname에 매칭
	private String host;			// appAddr에 매칭
	private String state;			// status에 매칭
	private List<Integer> ports;	// port에 매칭

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Integer> getPorts() {
		if(ports == null)
			ports = new ArrayList<>();
		return ports;
	}

	public void setPorts(List<Integer> ports) {
		this.ports = ports;
	}
}
