package com.aurosks.kolesnyk.controller;

import com.aurosks.kolesnyk.entity.PackageEntity;
import com.aurosks.kolesnyk.service.PackageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "redirect:/kpacs?successCreateId=" + packageService.create(packageEntity);
    }

    @GetMapping
    public String findALl(Model model) {
        model.addAttribute("packages", packageService.findAll());
        return "package/packagesGrid";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        packageService.deleteById(id);
        return "redirect:/kpacs?successDeleteId=" + id;
    }
}
