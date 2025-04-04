package com.zendemo.zenclinic.service;

import com.zendemo.zenclinic.model.Vet;
import com.zendemo.zenclinic.repository.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VetService {

    private final VetRepository vetRepository;

    @Autowired
    public VetService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    public List<Vet> findAll() {
        return vetRepository.findAll();
    }

    public Optional<Vet> findById(Long id) {
        return vetRepository.findById(id);
    }

    public Vet save(Vet vet) {
        return vetRepository.save(vet);
    }

    public void delete(Long id) {
        vetRepository.deleteById(id);
    }
}