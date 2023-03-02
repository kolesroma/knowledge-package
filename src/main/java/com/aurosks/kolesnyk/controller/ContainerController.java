package com.aurosks.kolesnyk.controller;

import com.aurosks.kolesnyk.entity.ContainerEntity;
import com.aurosks.kolesnyk.service.ContainerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    // create container
    // get
    // post


    // map with packages
    // with ticks

    @GetMapping("/set/{containerId}")
    public String findAll(Model model, @PathVariable Integer containerId) {
        model.addAttribute("packages", containerService.findAllById(containerId));
        return "package/packages";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        containerService.deleteById(id);
        return "redirect:/sets?successDeleteId=" + id;
    }
}
