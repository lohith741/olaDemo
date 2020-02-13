package com.lohith.olaDemo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lohith.olaDemo.model.Request;

@Repository
public interface RefreshRepository {

	public void refreshOngoingRequests();
	

	public List<Request> getTopThreedrivers(long driverId,List<Request> waitingList);
	
}
 