package com.lohith.olaDemo.model;

import java.util.Date;

public class ResponseEntry {

	  	private Long requestId;

		private Long customerId;

		private Long driverId;

		private String requestStatus;

		private String timeElapsed;
		
		public ResponseEntry(){
			
		}
		
		public ResponseEntry(Request request){
			this.setCustomerId(request.getCustomerId());
			this.setDriverId(request.getDriverId());
			this.setRequestId(request.getRequestId());
			this.setRequestStatus(request.getRequestStatus());
		}
		
		
		public Long getRequestId() {
			return requestId;
		}

		public Long getCustomerId() {
			return customerId;
		}

		public Long getDriverId() {
			return driverId;
		}

		public String getRequestStatus() {
			return requestStatus;
		}

		public String getTimeElapsed() {
			return timeElapsed;
		}

		public void setRequestId(Long requestId) {
			this.requestId = requestId;
		}

		public void setCustomerId(Long customerId) {
			this.customerId = customerId;
		}

		public void setDriverId(Long driverId) {
			this.driverId = driverId;
		}

		public void setRequestStatus(String requestStatus) {
			this.requestStatus = requestStatus;
		}

		public void setTimeElapsed(String timeElapsed) {
			this.timeElapsed = timeElapsed;
		}
		

		
}
