package com.lohith.olaDemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lohith.olaDemo.model.Request;


@Repository
public interface RequestRepository extends JpaRepository<Request,Long>{

	@Query(value = "SELECT * FROM request_db  WHERE  request_status = 'waiting'", nativeQuery = true)
	List<Request> findAllWaiting();

	@Query(value = "SELECT * FROM request_db  WHERE  request_status = 'completed' AND driver_id = ?1", nativeQuery = true)
	List<Request> findAllCompleted(Long driverId);
	
	@Query(value = "SELECT * FROM request_db  WHERE  request_status = 'ongoing' AND driver_id = ?1", nativeQuery = true)
	List<Request> findAllonGoingByDriver(Long driverId);
	
	@Query(value = "SELECT * FROM request_db  WHERE  request_status = 'ongoing'", nativeQuery = true)
	List<Request> findAllOnGoing();
	
	@Query(value = "SELECT * FROM request_db  WHERE  request_id = ?1", nativeQuery = true)
	List<Request> getRequestById(Long requestId);

}
