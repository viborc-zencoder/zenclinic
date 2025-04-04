package com.zendemo.zenclinic.repository;

import com.zendemo.zenclinic.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    List<Owner> findByLastNameContainingIgnoreCase(String lastName);
}