package com.zendemo.zenclinic.service;

import com.zendemo.zenclinic.model.PetType;
import com.zendemo.zenclinic.repository.PetTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetTypeService {

    private final PetTypeRepository petTypeRepository;

    @Autowired
    public PetTypeService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    public List<PetType> findAll() {
        return petTypeRepository.findAll();
    }

    public Optional<PetType> findById(Long id) {
        return petTypeRepository.findById(id);
    }

    public PetType save(PetType petType) {
        return petTypeRepository.save(petType);
    }

    public void delete(Long id) {
        petTypeRepository.deleteById(id);
    }
}