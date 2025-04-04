package com.zendemo.zenclinic.service;

import com.zendemo.zenclinic.model.Owner;
import com.zendemo.zenclinic.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    public Optional<Owner> findById(Long id) {
        return ownerRepository.findById(id);
    }

    public List<Owner> findByLastName(String lastName) {
        return ownerRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    public void delete(Long id) {
        ownerRepository.deleteById(id);
    }
    
    public long count() {
        return ownerRepository.count();
    }
}