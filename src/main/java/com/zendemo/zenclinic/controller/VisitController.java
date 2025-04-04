package com.zendemo.zenclinic.controller;

import com.zendemo.zenclinic.model.Pet;
import com.zendemo.zenclinic.model.Visit;
import com.zendemo.zenclinic.service.PetService;
import com.zendemo.zenclinic.service.VisitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/owners/{ownerId}/pets/{petId}/visits")
public class VisitController {

    private final VisitService visitService;
    private final PetService petService;

    @Autowired
    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @ModelAttribute("pet")
    public Pet findPet(@PathVariable Long petId) {
        return petService.findById(petId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pet Id:" + petId));
    }

    @GetMapping("/new")
    public String initNewVisitForm(Pet pet, Model model) {
        Visit visit = new Visit();
        visit.setPet(pet);
        visit.setDate(LocalDate.now());
        model.addAttribute("visit", visit);
        return "visits/createOrUpdateVisitForm";
    }

    @PostMapping("/new")
    public String processNewVisitForm(@Valid Visit visit, BindingResult result, Pet pet) {
        if (result.hasErrors()) {
            return "visits/createOrUpdateVisitForm";
        }
        
        visit.setPet(pet);
        visitService.save(visit);
        return "redirect:/owners/{ownerId}";
    }

    @GetMapping("/{visitId}/edit")
    public String initEditVisitForm(@PathVariable Long visitId, Model model) {
        Visit visit = visitService.findById(visitId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid visit Id:" + visitId));
        model.addAttribute("visit", visit);
        return "visits/createOrUpdateVisitForm";
    }

    @PostMapping("/{visitId}/edit")
    public String processEditVisitForm(@Valid Visit visit, BindingResult result, 
                                      @PathVariable Long visitId, Pet pet) {
        if (result.hasErrors()) {
            return "visits/createOrUpdateVisitForm";
        }
        
        visit.setId(visitId);
        visit.setPet(pet);
        visitService.save(visit);
        return "redirect:/owners/{ownerId}";
    }

    @GetMapping("/{visitId}/delete")
    public String deleteVisit(@PathVariable Long visitId) {
        visitService.delete(visitId);
        return "redirect:/owners/{ownerId}";
    }
}