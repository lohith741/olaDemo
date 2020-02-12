package com.lohith.olaDemo.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "driver_db")
@EntityListeners(AuditingEntityListener.class)
public class Driver {
	
	@Id
	@NotNull
	private Long driverId;
	
	private boolean driverStatus = true;

	public Long getDriverId() {
		return driverId;
	}

	public boolean isDriverStatus() {
		return driverStatus;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public void setDriverStatus(boolean driverStatus) {
		this.driverStatus = driverStatus;
	}


}
