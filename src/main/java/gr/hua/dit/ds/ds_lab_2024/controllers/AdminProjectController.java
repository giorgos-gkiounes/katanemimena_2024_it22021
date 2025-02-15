package gr.hua.dit.ds.ds_lab_2024.controllers;

import gr.hua.dit.ds.ds_lab_2024.entities.project;
import gr.hua.dit.ds.ds_lab_2024.repositories.projectRepository;
import gr.hua.dit.ds.ds_lab_2024.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/projects")
public class AdminProjectController {

    @Autowired
    private projectRepository projectRepository;

    // Show all projects in the admin page
    @GetMapping()
    public String showProjects(Model model) {
        model.addAttribute("projects", projectRepository.findAll());
        return "allProjects/allpro";
    }

    // Toggle visibility of a project
    @PostMapping("/{id}/toggle-visibility")
    public String toggleProjectVisibility(@PathVariable int id, RedirectAttributes redirectAttributes, Model model) {
        projectRepository.findById(id).ifPresent(project -> {
            project.setVisible(!project.isVisible()); // Toggle visibility
            projectRepository.save(project);
        });

        redirectAttributes.addFlashAttribute("message", "Project visibility updated!");
        model.addAttribute("projects", projectRepository.findAll());
        return "allProjects/allpro";
    }
}