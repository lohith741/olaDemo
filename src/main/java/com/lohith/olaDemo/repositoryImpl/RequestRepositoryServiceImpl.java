package com.lohith.olaDemo.repositoryImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lohith.olaDemo.model.Driver;
import com.lohith.olaDemo.model.DriverDistance;
import com.lohith.olaDemo.model.Request;
import com.lohith.olaDemo.repository.DriverRepository;
import com.lohith.olaDemo.repository.RefreshRepository;
import com.lohith.olaDemo.repository.RequestRepository;

@Service
public class RequestRepositoryServiceImpl implements RefreshRepository{

	@Autowired
	RequestRepository requestRepository;
	
	@Autowired
	DriverRepository driverRepository;
	
	@Override
	public void refreshOngoingRequests() {
		// TODO Auto-generated method stub
		List<Request> requests =requestRepository.findAllOnGoing();
		requests.forEach(request->{
			Date acceptedTime=request.getAccpetedTime();
			String status = request.getRequestStatus();
			Date currentTime= new Date();
			long diff = currentTime.getTime() - acceptedTime.getTime();         
			long diffMinutes = diff / (60 * 1000);         
			if(diffMinutes>=5){
				request.setRequestStatus("completed");
				requestRepository.save(request);
				
				Driver driver= driverRepository.getDriverById(request.getDriverId()).get(0);
				driver.setDriverStatus(true);
				driverRepository.save(driver);
			}
				
		});
		
	}
	
	@Override
	public List<Request> getTopThreedrivers(long driverId,List<Request> waitingList){
		
		List<Request> customerInRangeList = new ArrayList<Request>();
		
		for (Request request: waitingList) {
			PriorityQueue<DriverDistance> queue = new PriorityQueue<DriverDistance>();
			for(long drivId=1;drivId<=5;drivId++){
				List<Driver> drivers = driverRepository.getDriverById(drivId);
		    	Driver driver = drivers.get(0);
		    	if(driver.isDriverStatus()){
		    		int x=request.getX();
		    		int y=request.getY();
		    		float distance=(float) Math.sqrt((x-drivId)*(x-drivId) + (y-drivId)*(y-drivId));
		    		DriverDistance driverDistance = new DriverDistance(drivId,distance);
		    		queue.add(driverDistance);
		    	}
			}
			int count =0;
			while(!queue.isEmpty() && count<3){
				count++;
				DriverDistance driverDistance = queue.poll();
				if(driverDistance.getKey()==driverId){
					customerInRangeList.add(request);
				}
			}
		}
		return customerInRangeList;

		
	}




}
