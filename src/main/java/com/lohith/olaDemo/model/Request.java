package com.lohith.olaDemo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.Date;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "request_db")
@EntityListeners(AuditingEntityListener.class)
public class Request {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

	@NotNull
	private Long customerId;

	private Long driverId = null ;

	@NotBlank
	private String requestStatus;

	@NotNull
	private Date requestTime ;

	private Date accpetedTime = null ;

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

	public Date getRequestTime() {
		return requestTime;
	}

	public Date getAccpetedTime() {
		return accpetedTime;
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

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public void setAccpetedTime(Date accpetedTime) {
		this.accpetedTime = accpetedTime;
	}
	

}
