package com.lohith.olaDemo.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface RefreshRepository {

	public void refreshOngoingRequests();
	
}
 