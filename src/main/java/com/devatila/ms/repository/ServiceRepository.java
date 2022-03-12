package com.devatila.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devatila.ms.model.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

}
