package com.zendemo.zenclinic.service;

import com.zendemo.zenclinic.model.Specialty;
import com.zendemo.zenclinic.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    @Autowired
    public SpecialtyService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    public List<Specialty> findAll() {
        return specialtyRepository.findAll();
    }

    public Optional<Specialty> findById(Long id) {
        return specialtyRepository.findById(id);
    }

    public Specialty save(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    public void delete(Long id) {
        specialtyRepository.deleteById(id);
    }
}