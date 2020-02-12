package com.lohith.olaDemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lohith.olaDemo.model.Driver;
import com.lohith.olaDemo.model.Request;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long>{

	
	@Query(value = "SELECT * FROM driver_db", nativeQuery = true)
	List<Driver> getAllDrivers();
	
	@Query(value = "SELECT * FROM driver_db where driver_id= ?1 ", nativeQuery = true)
	List<Driver> getDriverById(Long driverId);
}
