package com.lohith.olaDemo.repositoryImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lohith.olaDemo.model.Driver;
import com.lohith.olaDemo.model.DriverResponse;
import com.lohith.olaDemo.model.Request;
import com.lohith.olaDemo.model.ResponseEntry;
import com.lohith.olaDemo.repository.ControllerRepository;
import com.lohith.olaDemo.repository.DriverRepository;
import com.lohith.olaDemo.repository.RefreshRepository;
import com.lohith.olaDemo.repository.RequestRepository;
import net.minidev.json.JSONObject;

@Service
public class ControllerRepositoryServiceImpl implements ControllerRepository {

	@Autowired
	DriverRepository driverRepository;
	
	@Autowired
	RequestRepository requestRepository;
	
	@Autowired
	RefreshRepository refreshRepository;
	
	public List<ResponseEntry> getAllRequests(List<ResponseEntry> response) {
		refreshRepository.refreshOngoingRequests();
    	List<Request> requests=requestRepository.findAll();
    	
    	requests.forEach(request->{
    		ResponseEntry responseEntry=new ResponseEntry(request);
    		String timeElapsed= " ";
    
    		timeElapsed = getTimeElapsed(request.getRequestTime());
			responseEntry.setTimeElapsed(timeElapsed);
			response.add(responseEntry);
    	});
    	
    	return response;
	}
	
	private String getTimeElapsed(Date date) {
		Date currentDate= new Date();
		String timeElapsed="";
		long diff = currentDate.getTime() - date.getTime();
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);
		if(diffDays>0){
			timeElapsed+=diffDays+ " Days ";
		}
		if(diffHours>0){
			timeElapsed+=diffHours+ " Hours ";
		}
		if(diffMinutes>0){
			timeElapsed+=diffMinutes+ " Min ";
		}
		if(diffSeconds>0){
			timeElapsed+=diffSeconds+ " Sec ";
		}
		return timeElapsed;
	}
	
	
	public JSONObject customerRequest(Long customerId, int x, int y, JSONObject response) {
		refreshRepository.refreshOngoingRequests();
    	List<Request> waitingList = requestRepository.findAllWaiting();
    	if(x>5 || x<1 || y>5 || y<1){
    		response.put("message", "Sorry, Unserviceble area");
    	}
    	else{
    	if(waitingList.size()<10){
        Date dateobj = new Date();
    	Request request = new Request();
    	request.setCustomerId(customerId);
    	request.setRequestTime(dateobj);
    	request.setRequestStatus("waiting");
    	request.setX(x);
    	request.setY(y);
    	requestRepository.save(request);
    	        
        response.put("message", "Successfully, Ride is on your way!");
    	}
    	else{
    	response.put("message", "Sorry, Rides not available. try again later!");
    	}
    	}
    	return response;
	}
	
	public JSONObject driverRequest(Long requestId, Long driverId, JSONObject response) {
		refreshRepository.refreshOngoingRequests();
    	List<Request> requests=requestRepository.getRequestById(requestId);
    	Request request=requests.get(0);
    	if(!request.getRequestStatus().equals("waiting")){
    		response.put("message", "Request alredy taken");
    		return response;
    	}
    	List<Driver> drivers = driverRepository.getDriverById(driverId);
    	Driver driver = drivers.get(0);
    	
    	if(!driver.isDriverStatus()){
    		response.put("message", "Please complete the ongoing request");
    		return response;
    	}
    	request.setRequestStatus("ongoing");
    	request.setAccpetedTime(new Date());
    	request.setDriverId(driverId);    	
    	
    	requestRepository.save(request);
    	
    	driver.setDriverStatus(false);
    	driverRepository.save(driver);
    	
        response.put("message", "Successfully submitted request!");
        return response;
	}
    
	
	public JSONObject getDriverData(Long driverId, JSONObject response) {
		refreshRepository.refreshOngoingRequests();
    	List<Request> waitingList = requestRepository.findAllWaiting();
    	List<Request> completedList = requestRepository.findAllCompleted(driverId);
    	List<Request> ongoingList = requestRepository.findAllonGoingByDriver(driverId);
    	
    	List<Request> finalWaitingList=refreshRepository.getTopThreedrivers(driverId,waitingList);
    	List<DriverResponse> waitingResponse=new ArrayList<DriverResponse>();
    	List<DriverResponse> ongoingResponse=new ArrayList<DriverResponse>();
    	List<DriverResponse> completedResponse=new ArrayList<DriverResponse>();
    	
    	finalWaitingList.forEach(request->{
    		DriverResponse driverResponse=new DriverResponse();
    		driverResponse.setRequestId(request.getRequestId());
    		driverResponse.setCustomerId(request.getCustomerId());
    		driverResponse.setRequestedTime(getTimeElapsed(request.getRequestTime()));
    		
    		waitingResponse.add(driverResponse);
    		});
    	
    	ongoingList.forEach(request->{
    		DriverResponse driverResponse=new DriverResponse();
    		driverResponse.setRequestId(request.getRequestId());
    		driverResponse.setCustomerId(request.getCustomerId());
    		driverResponse.setRequestedTime(getTimeElapsed(request.getRequestTime()));
    		driverResponse.setPickedTime(getTimeElapsed(request.getAccpetedTime()));
    		
    		ongoingResponse.add(driverResponse);
    	});
    	
    	completedList.forEach(request->{
    		DriverResponse driverResponse=new DriverResponse();
    		driverResponse.setRequestId(request.getRequestId());
    		driverResponse.setCustomerId(request.getCustomerId());
    		driverResponse.setRequestedTime(getTimeElapsed(request.getRequestTime()));
    		driverResponse.setPickedTime(getTimeElapsed(request.getAccpetedTime()));
    		int addMinuteTime = 5;
    		Date targetTime = request.getAccpetedTime(); //now
    		targetTime = DateUtils.addMinutes(targetTime, addMinuteTime);
    		driverResponse.setCompletedTime(getTimeElapsed(targetTime));
    		
    		completedResponse.add(driverResponse);
    	});
    			
    	
    	
    	response.put("waiting", waitingResponse);
    	response.put("ongoing", ongoingResponse);
    	response.put("completed", completedResponse);
    	
    	return response;
	}
    
}
