package com.lohith.olaDemo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lohith.olaDemo.model.ResponseEntry;
import com.lohith.olaDemo.repository.ControllerRepository;
import com.lohith.olaDemo.repository.DriverRepository;
import com.lohith.olaDemo.repository.RefreshRepository;
import com.lohith.olaDemo.repository.RequestRepository;

import net.minidev.json.JSONObject;

@RestController
@CrossOrigin
@RequestMapping("/ola")
public class OlaController {

	@Autowired
	DriverRepository driverRepository;
	
	@Autowired
	RequestRepository requestRepository;
	
	@Autowired
	RefreshRepository refreshRepository;
	
	@Autowired
	ControllerRepository controllerRepository;
    @GetMapping("/request")
    public List<ResponseEntry> getAllRequest() {
    	
    	List<ResponseEntry> response = new ArrayList<ResponseEntry>();
    	response=controllerRepository.getAllRequests(response);
        return response;
    }

    @PostMapping(path = "/customer/rideRequest" )
    public ResponseEntity<?> createCustomerRequest(@RequestParam(value = "customerId") Long customerId,
    		@RequestParam(value = "x") int x, @RequestParam(value = "y") int y) {
    	
    	JSONObject response = new JSONObject();
    	response=controllerRepository.customerRequest(customerId, x, y, response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @RequestMapping(value = "/driver/selectRequest", method = RequestMethod.POST)
    public ResponseEntity<?> submitDriverRequest(@RequestParam(value = "requestId") Long requestId,
    		@RequestParam(value = "driverId") Long driverId) {
    	
     	JSONObject response = new JSONObject();
     	
     	response=controllerRepository.driverRequest(requestId, driverId, response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/driver/{id}")
    public JSONObject getAllDriverData(@PathVariable(value = "id") Long driverId) {
    	
    	JSONObject response = new JSONObject();
    	response=controllerRepository.getDriverData(driverId, response);
    	
        return response;
    }
    
}
