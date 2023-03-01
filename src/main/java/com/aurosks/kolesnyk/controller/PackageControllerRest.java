package com.aurosks.kolesnyk.controller;

import com.aurosks.kolesnyk.entity.PackageEntity;
import com.aurosks.kolesnyk.service.PackageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class PackageControllerRest {
    private final PackageService packageService;

    public PackageControllerRest(PackageService packageService) {
        this.packageService = packageService;
    }

    @GetMapping("/rest")
    public String root(Model model) {
        packageService.create(new PackageEntity(1, "title", "des", LocalDate.now()));
        return "body returned";
    }
}
