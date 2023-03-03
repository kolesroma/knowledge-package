package com.aurosks.kolesnyk.controller;

import com.aurosks.kolesnyk.entity.PackageEntity;
import com.aurosks.kolesnyk.service.PackageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/kpacs")
public class PackageController {
    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @PostMapping
    @ResponseBody
    public PackageEntity create(@RequestBody PackageEntity packageEntity) {
        return packageService.create(packageEntity);
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
