package com.zendemo.zenclinic.controller;

import com.zendemo.zenclinic.model.Vet;
import com.zendemo.zenclinic.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/vets")
public class VetController {

    private final VetService vetService;

    @Autowired
    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping
    public String listVets(Model model) {
        List<Vet> vets = vetService.findAll();
        model.addAttribute("vets", vets);
        return "vets/index";
    }
}