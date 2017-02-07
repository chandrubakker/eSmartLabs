package com.sorcererpaws.eSmartLabs.core.entity.validation;

import java.util.List;

public class ValidationResponse {

	private String status;
	private Object object;
	private List<ErrorMessage> errorMessageList;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public List<ErrorMessage> getErrorMessageList() {
		return errorMessageList;
	}
	public void setErrorMessageList(List<ErrorMessage> errorMessageList) {
		this.errorMessageList = errorMessageList;
	}
}
