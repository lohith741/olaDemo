package com.lohith.olaDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lohith.olaDemo.model.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long>{

}
