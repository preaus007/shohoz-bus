package com.touhed.app.bus.repository;

import com.touhed.app.bus.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<Bus,Long>, JpaSpecificationExecutor<Bus> {
    @Query( """
    SELECT b FROM Bus b WHERE b.isDeleted = false
    """ )
    Optional<Bus> findByIdNotDeleted(Long id );
}
