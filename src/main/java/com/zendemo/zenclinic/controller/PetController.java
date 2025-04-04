package com.zendemo.zenclinic.controller;

import com.zendemo.zenclinic.model.Owner;
import com.zendemo.zenclinic.model.Pet;
import com.zendemo.zenclinic.model.PetType;
import com.zendemo.zenclinic.service.OwnerService;
import com.zendemo.zenclinic.service.PetService;
import com.zendemo.zenclinic.service.PetTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    @Autowired
    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid owner Id:" + ownerId));
    }

    @GetMapping("/pets/new")
    public String initCreationForm(Owner owner, Model model) {
        Pet pet = new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return "pets/createOrUpdatePetForm";
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pet", pet);
            return "pets/createOrUpdatePetForm";
        }
        
        pet.setOwner(owner);
        petService.save(pet);
        return "redirect:/owners/{ownerId}";
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable Long petId, Model model) {
        Pet pet = petService.findById(petId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pet Id:" + petId));
        model.addAttribute("pet", pet);
        return "pets/createOrUpdatePetForm";
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(@Valid Pet pet, BindingResult result, Owner owner, @PathVariable Long petId, Model model) {
        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return "pets/createOrUpdatePetForm";
        }
        
        pet.setId(petId);
        pet.setOwner(owner);
        petService.save(pet);
        return "redirect:/owners/{ownerId}";
    }

    @GetMapping("/pets/{petId}/delete")
    public String deletePet(@PathVariable Long petId) {
        petService.delete(petId);
        return "redirect:/owners/{ownerId}";
    }
}