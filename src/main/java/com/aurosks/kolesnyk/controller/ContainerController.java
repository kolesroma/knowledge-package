package com.aurosks.kolesnyk.controller;

import com.aurosks.kolesnyk.entity.ContainerEntity;
import com.aurosks.kolesnyk.entity.PackageEntity;
import com.aurosks.kolesnyk.service.ContainerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ContainerController {
    private final ContainerService containerService;

    public ContainerController(ContainerService containerService) {
        this.containerService = containerService;
    }

    @GetMapping("/sets")
    public String findAll(Model model) {
        model.addAttribute("containers", containerService.findAll());
        return "container/containers";
    }

    @GetMapping("/sets/json")
    @ResponseBody
    public List<ContainerEntity> findAllJson() {
        return containerService.findAll();
    }

    @PostMapping("/sets")
    @ResponseBody
    public ContainerEntity create(@RequestBody ContainerEntity containerEntity) {
        return containerService.create(containerEntity);
    }

    @PutMapping("/sets/{containerId}")
    @ResponseBody
    public void update(@RequestBody List<PackageEntity> packageEntities,
                       @PathVariable Integer containerId) {
        containerService.update(containerId, packageEntities);
    }

    @GetMapping("/set/{containerId}")
    public String findAll(Model model, @PathVariable Integer containerId) {
        model.addAttribute("containerId", containerId);
        return "container/subpackages";
    }

    @GetMapping("/set/{containerId}/json")
    @ResponseBody
    public List<PackageEntity> findAllJson(@PathVariable Integer containerId) {
        return containerService.findAllById(containerId);
    }

    @PostMapping("/sets/{id}/delete")
    public String delete(@PathVariable Integer id) {
        containerService.deleteById(id);
        return "redirect:/sets?successDeleteId=" + id;
    }
}
