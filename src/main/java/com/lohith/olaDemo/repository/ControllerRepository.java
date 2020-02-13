package com.lohith.olaDemo.repository;

import java.util.Date;
import java.util.List;

import com.lohith.olaDemo.model.ResponseEntry;

import net.minidev.json.JSONObject;

public interface ControllerRepository {

	/**
	 * 
	 * @param response
	 * @return
	 */
	List<ResponseEntry> getAllRequests(List<ResponseEntry> response);
	
	/**
	 * 
	 * @param date
	 * @return
	 */
//	public String getTimeElapsed(Date date);
	
	/**
	 * 
	 * @param customerId
	 * @param x
	 * @param y
	 * @param response
	 * @return
	 */
	public JSONObject customerRequest(Long customerId, int x, int y, JSONObject response);
	
	/**
	 * 
	 * @param requestId
	 * @param driverId
	 * @param response
	 * @return
	 */
	public JSONObject driverRequest(Long requestId, Long driverId, JSONObject response);
	
	/**
	 * 
	 * @param driverId
	 * @param response
	 * @return
	 */
	public JSONObject getDriverData(Long driverId, JSONObject response);
}
