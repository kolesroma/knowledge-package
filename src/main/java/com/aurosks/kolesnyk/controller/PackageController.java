package com.aurosks.kolesnyk.controller;

import com.aurosks.kolesnyk.entity.PackageEntity;
import com.aurosks.kolesnyk.service.PackageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/kpacs")
public class PackageController {
    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

//    @GetMapping("/new")
//    public String createForm(Model model) {
//        PackageEntity packageEntity = new PackageEntity();
//        model.addAttribute("packageForm", packageEntity);
//        return "package/createForm";
//    }

    @PostMapping
    public String create(@ModelAttribute PackageEntity packageEntity) {
        return "redirect:/kpacs?successCreateId=" + packageService.create(packageEntity);
    }

    @GetMapping
    public String findALl() {
        return "package/packages";
    }

    @GetMapping("/json")
    @ResponseBody
    public List<PackageEntity> findALlJson() {
        return packageService.findAll();
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        packageService.deleteById(id);
        return "redirect:/kpacs?successDeleteId=" + id;
    }
}
