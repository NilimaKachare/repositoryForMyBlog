package com.ashish.poc.model;

import java.util.List;

public class UserDataModel {
	private List<Users> users;
	private List<Role> roles;
	private List<Url> urls;
	private List<EnvironmentMaster> environments;
	private List<Module> modules;
	
	private String errorMsg;

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public List<Url> getUrls() {
		return urls;
	}

	public void setUrls(List<Url> urls) {
		this.urls = urls;
	}

	public List<EnvironmentMaster> getEnvironments() {
		return environments;
	}

	public void setEnvironments(List<EnvironmentMaster> environments) {
		this.environments = environments;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

}