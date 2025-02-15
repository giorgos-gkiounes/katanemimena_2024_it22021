package gr.hua.dit.ds.ds_lab_2024.controllers;

import gr.hua.dit.ds.ds_lab_2024.entities.Transaction;
import gr.hua.dit.ds.ds_lab_2024.entities.User;
import gr.hua.dit.ds.ds_lab_2024.repositories.TransactionRepository;
import gr.hua.dit.ds.ds_lab_2024.repositories.UserRepository;
import gr.hua.dit.ds.ds_lab_2024.repositories.projectRepository;
import gr.hua.dit.ds.ds_lab_2024.service.ProjectService;
import gr.hua.dit.ds.ds_lab_2024.service.TransactionService;
import gr.hua.dit.ds.ds_lab_2024.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/transactions")
public class TransactionController {



    @Autowired
    private ProjectService projectService;



    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;




    @RequestMapping()
    public String showTransactions(Model model,@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserFromEmail(userDetails.getUsername());
        model.addAttribute("transactions", transactionService.getTransactionsOfUser(user.getId()));
        return "transaction/transactions";
    }




    @GetMapping("/create")
    public String showTransactionForm(Model model) {
        model.addAttribute("projects", projectService.getProjectsVisible());
        model.addAttribute("transaction", new Transaction());
        return "transaction/transaction";
    }

    @PostMapping("/create")
    public String processTransaction(@ModelAttribute Transaction transaction, RedirectAttributes redirectAttributes, Model model,@AuthenticationPrincipal UserDetails userDetails) {
        // Validate projectId exists
        if (!projectService.existsById(transaction.getProjectId())) {
            redirectAttributes.addFlashAttribute("error", "Invalid project ID!");
            return "transaction/transaction";
        }

        // Save the transaction to the database
        User user = userService.getUserFromEmail(userDetails.getUsername());
        transactionService.save(transaction,user.getId());


        projectService.changeAmount(transaction);




        redirectAttributes.addFlashAttribute("message", "Transaction processed successfully!");

        model.addAttribute("transactions", transactionService.getTransactionsOfUser(user.getId()));
        return "/transaction/transactions";
    }
}
