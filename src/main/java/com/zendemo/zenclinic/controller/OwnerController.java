package com.zendemo.zenclinic.controller;

import com.zendemo.zenclinic.model.Owner;
import com.zendemo.zenclinic.service.OwnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public String listOwners(Model model) {
        List<Owner> owners = ownerService.findAll();
        model.addAttribute("owners", owners);
        return "owners/index";
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("owner", new Owner());
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return "owners/createOrUpdateOwnerForm";
        }
        ownerService.save(owner);
        return "redirect:/owners";
    }

    @GetMapping("/{ownerId}")
    public String showOwner(@PathVariable Long ownerId, Model model) {
        Owner owner = ownerService.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid owner Id:" + ownerId));
        model.addAttribute("owner", owner);
        return "owners/ownerDetails";
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
        Owner owner = ownerService.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid owner Id:" + ownerId));
        model.addAttribute("owner", owner);
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable Long ownerId) {
        if (result.hasErrors()) {
            return "owners/createOrUpdateOwnerForm";
        }
        owner.setId(ownerId);
        ownerService.save(owner);
        return "redirect:/owners/{ownerId}";
    }

    @GetMapping("/{ownerId}/delete")
    public String deleteOwner(@PathVariable Long ownerId) {
        ownerService.delete(ownerId);
        return "redirect:/owners";
    }

    @GetMapping("/find")
    public String findOwners(@RequestParam(required = false) String lastName, Model model) {
        if (lastName == null || lastName.isEmpty()) {
            return "owners/findOwners";
        }
        
        List<Owner> results = ownerService.findByLastName(lastName);
        if (results.isEmpty()) {
            return "owners/findOwners";
        } else if (results.size() == 1) {
            return "redirect:/owners/" + results.get(0).getId();
        } else {
            model.addAttribute("owners", results);
            return "owners/ownersList";
        }
    }
}