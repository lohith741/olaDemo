package com.lohith.olaDemo.controller;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lohith.olaDemo.model.Request;
import com.lohith.olaDemo.repository.DriverRepository;
import com.lohith.olaDemo.repository.RequestRepository;

import net.minidev.json.JSONObject;

@RestController
@RequestMapping("/ola")
public class OlaController {

	@Autowired
	DriverRepository driverRepository;
	
	@Autowired
	RequestRepository requestRepository;
	
    @GetMapping("/request")
    public List<Request> getAllRequest() {
        return requestRepository.findAll();
    }
    
    @PostMapping(path = "/customer/{id}" )
    public ResponseEntity<?> createCustomerRequest(@PathVariable(value = "id") Long customerId) {
    	
        Date dateobj = new Date();
    	Request request = new Request();
    	request.setCustomerId(customerId);
    	request.setRequestTime(dateobj);
    	request.setRequestStatus("waiting");
    	requestRepository.save(request);
    	
        JSONObject response = new JSONObject();
        response.put("message: ", "Successfully Inserted!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    
    @PostMapping(path = "/driver/selectRequest" )
    public ResponseEntity<?> submitDriverRequest(@RequestParam(required = false,value = "requestId") Long requestId,@RequestParam(required = false,value = "driverId") Long driverId) {
    	
        Date dateobj = new Date();
    	Request request = new Request();
    	request.setRequestTime(dateobj);
    	request.setRequestStatus("waiting");
    	requestRepository.save(request);
    	
        JSONObject response = new JSONObject();
        response.put("message: ", "Successfully Inserted!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    
    @GetMapping("/driver/{id}")
    public JSONObject getAllDriverData(@PathVariable(value = "id") Long driverId) {
    	List<Request> waitingList = requestRepository.findAllWaiting();
    	List<Request> completedList = requestRepository.findAllCompleted(driverId);
    	List<Request> ongoingList = requestRepository.findAllonGoingByDriver(driverId);
    	
    	JSONObject response = new JSONObject();
    	response.put("waiting", waitingList);
    	response.put("ongoing", ongoingList);
    	response.put("completed", completedList);
    	
        return response;
    }
    
    
    
    
}
