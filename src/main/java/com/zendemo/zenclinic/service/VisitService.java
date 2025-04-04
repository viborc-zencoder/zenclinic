package com.zendemo.zenclinic.service;

import com.zendemo.zenclinic.model.Visit;
import com.zendemo.zenclinic.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitService {

    private final VisitRepository visitRepository;

    @Autowired
    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public List<Visit> findAll() {
        return visitRepository.findAll();
    }

    public Optional<Visit> findById(Long id) {
        return visitRepository.findById(id);
    }

    public List<Visit> findByPetId(Long petId) {
        return visitRepository.findByPetId(petId);
    }
    
    public List<Visit> findRecentVisits() {
        return visitRepository.findRecentVisits();
    }

    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }

    public void delete(Long id) {
        visitRepository.deleteById(id);
    }
}