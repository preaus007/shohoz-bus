package com.touhed.app.bus.repository;

import com.touhed.app.bus.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus,Long>, JpaSpecificationExecutor<Bus> {
}
