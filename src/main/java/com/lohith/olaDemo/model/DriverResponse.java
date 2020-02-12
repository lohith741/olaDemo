package com.lohith.olaDemo.model;

public class DriverResponse {

    private Long requestId;

	private Long customerId;
	
	private String requestedTime;
	private String pickedTime;
	private String completedTime;
	
	public Long getRequestId() {
		return requestId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public String getRequestedTime() {
		return requestedTime;
	}
	public String getPickedTime() {
		return pickedTime;
	}
	public String getCompletedTime() {
		return completedTime;
	}
	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public void setRequestedTime(String requestedTime) {
		this.requestedTime = requestedTime;
	}
	public void setPickedTime(String pickedTime) {
		this.pickedTime = pickedTime;
	}
	public void setCompletedTime(String completedTime) {
		this.completedTime = completedTime;
	}
	
	
	
}
