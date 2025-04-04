package com.zendemo.zenclinic.repository;

import com.zendemo.zenclinic.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findByPetId(Long petId);
    
    @Query("SELECT v FROM Visit v ORDER BY v.date DESC")
    List<Visit> findRecentVisits();
}