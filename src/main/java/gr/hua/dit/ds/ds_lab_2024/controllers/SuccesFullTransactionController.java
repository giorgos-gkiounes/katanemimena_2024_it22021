package gr.hua.dit.ds.ds_lab_2024.controllers;


import gr.hua.dit.ds.ds_lab_2024.entities.Transaction;
import gr.hua.dit.ds.ds_lab_2024.entities.User;
import gr.hua.dit.ds.ds_lab_2024.entities.project;
import gr.hua.dit.ds.ds_lab_2024.repositories.UserRepository;
import gr.hua.dit.ds.ds_lab_2024.service.ProjectService;
import gr.hua.dit.ds.ds_lab_2024.service.TransactionService;
import gr.hua.dit.ds.ds_lab_2024.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Succes")
public class SuccesFullTransactionController {

    @Autowired
    private ProjectService projectService;




    @Autowired
    private UserService userService;



    @RequestMapping()
    public String showTransactions(Model model,@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserFromEmail(userDetails.getUsername());
        model.addAttribute("projects", projectService.GetSucces(user.getId()));
        return "project/successfulProjects";
    }

}
