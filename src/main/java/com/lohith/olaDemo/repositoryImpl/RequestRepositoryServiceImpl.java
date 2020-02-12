package com.lohith.olaDemo.repositoryImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lohith.olaDemo.model.Driver;
import com.lohith.olaDemo.model.Request;
import com.lohith.olaDemo.repository.DriverRepository;
import com.lohith.olaDemo.repository.RefreshRepository;
import com.lohith.olaDemo.repository.RequestRepository;

public class RequestRepositoryServiceImpl implements RefreshRepository{

	@Autowired
	RequestRepository requestRepository;
	
	@Autowired
	DriverRepository driverRepository;
	
	@Override
	public void refreshOngoingRequests() {
		// TODO Auto-generated method stub
		List<Request> requests =requestRepository.findAllonGoing();
		requests.forEach(request->{
			Date acceptedTime=request.getAccpetedTime();
			String status = request.getRequestStatus();
			Date currentTime= new Date();
			long diff = currentTime.getTime() - acceptedTime.getTime();         
			long diffMinutes = diff / (60 * 1000);         
			if(diffMinutes>5){
				request.setRequestStatus("completed");
				requestRepository.save(request);
				
				Driver driver= driverRepository.getDriverById(request.getDriverId()).get(0);
				driver.setDriverStatus(true);
				driverRepository.save(driver);
			}
		
					
		});
		
	}

	@Override
	public void refreshWaitingRequests() {

		
	}

}
