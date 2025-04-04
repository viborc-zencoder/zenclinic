package com.zendemo.zenclinic.controller;

import com.zendemo.zenclinic.model.Visit;
import com.zendemo.zenclinic.service.OwnerService;
import com.zendemo.zenclinic.service.PetService;
import com.zendemo.zenclinic.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    private final OwnerService ownerService;
    private final PetService petService;
    private final VisitService visitService;

    @Autowired
    public DashboardController(OwnerService ownerService, PetService petService, VisitService visitService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.visitService = visitService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        long petCount = petService.count();
        long ownerCount = ownerService.count();
        List<Visit> recentVisits = visitService.findRecentVisits().stream().limit(5).toList();

        model.addAttribute("petCount", petCount);
        model.addAttribute("ownerCount", ownerCount);
        model.addAttribute("recentVisits", recentVisits);
        
        return "dashboard";
    }
}