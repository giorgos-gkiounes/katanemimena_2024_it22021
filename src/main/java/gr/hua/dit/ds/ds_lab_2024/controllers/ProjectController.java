package gr.hua.dit.ds.ds_lab_2024.controllers;


import gr.hua.dit.ds.ds_lab_2024.entities.project;
import gr.hua.dit.ds.ds_lab_2024.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("project")
public class ProjectController {


    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping()
    public String showProjects(Model model) {
        model.addAttribute("projects", projectService.getProjectsVisible());
        return "project/projects";
    }


    @GetMapping("/new")
    public String addProject(Model model){
        project project = new project();
        model.addAttribute("project", project);
        return "project/project";

    }


    @PostMapping("/new")
    public String saveProject(@Valid @ModelAttribute("project") project project, BindingResult theBindingResult, Model model) {
        if (theBindingResult.hasErrors()) {
            System.out.println("error");
            return "project/project";
        } else {
            projectService.saveProject(project);
            model.addAttribute("projects", projectService.getProjectsVisible());
            model.addAttribute("successMessage", "project added successfully! wait for verification");
            return "project/projects";
        }

    }



}
