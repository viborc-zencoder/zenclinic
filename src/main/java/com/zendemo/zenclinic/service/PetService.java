package com.zendemo.zenclinic.service;

import com.zendemo.zenclinic.model.Pet;
import com.zendemo.zenclinic.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;

    @Autowired
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public Optional<Pet> findById(Long id) {
        return petRepository.findById(id);
    }

    public List<Pet> findByOwnerId(Long ownerId) {
        return petRepository.findByOwnerId(ownerId);
    }

    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    public void delete(Long id) {
        petRepository.deleteById(id);
    }
    
    public long count() {
        return petRepository.count();
    }
}