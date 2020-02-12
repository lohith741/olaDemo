package com.lohith.olaDemo.repository;

public interface RefreshRepository {

	void refreshOngoingRequests();
	
	void refreshWaitingRequests();

}
