package com.aurosks.kolesnyk.controller;

import com.aurosks.kolesnyk.entity.PackageEntity;
import com.aurosks.kolesnyk.service.PackageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/kpacs")
public class PackageController {
    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        PackageEntity packageEntity = new PackageEntity();
        model.addAttribute("packageForm", packageEntity);
        return "package/createForm";
    }

    @PostMapping
    public String create(@ModelAttribute PackageEntity packageEntity) {
        packageEntity.setCreatedAt(LocalDate.now());
        return "redirect:/kpacs?successCreateId=" + packageService.create(packageEntity);
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        packageService.deleteById(id);
        return "redirect:/kpacs?successDeleteId=" + id;
    }

    @GetMapping
    public String findALl(Model model) {
        model.addAttribute("packages", packageService.findAll());
        return "package/packages";
    }

}
