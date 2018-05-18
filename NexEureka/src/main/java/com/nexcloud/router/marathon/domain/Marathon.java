package com.nexcloud.router.marathon.domain;

import java.util.ArrayList;
import java.util.List;

public class Marathon {

	List<Task> tasks;

	public List<Task> getTasks() {
		if(tasks == null)
			tasks = new ArrayList<>();
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

}
