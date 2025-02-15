package gr.hua.dit.ds.ds_lab_2024.service;


import gr.hua.dit.ds.ds_lab_2024.entities.Transaction;
import gr.hua.dit.ds.ds_lab_2024.entities.User;
import gr.hua.dit.ds.ds_lab_2024.entities.project;
import gr.hua.dit.ds.ds_lab_2024.repositories.projectRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private projectRepository repository;

    private TransactionService transactionService;

    public ProjectService(projectRepository repository, TransactionService transactionService) {
        this.repository = repository;
        this.transactionService = transactionService;
    }

    @Transactional
    public List<project> getProjects() {
        return repository.findAll();
    }

    @Transactional
    public List<project> getProjectsVisible() {
        return repository.findAllVisible();
    }


    //Προσθέτουμε στο εκάστοτε πρότζεκτ το ποσό(transaction.amount) που έχει το transaction και το βάζουμε στο project.currentAmount
    @Transactional
    public int changeAmount(@ModelAttribute Transaction transaction) {
        repository.findById(transaction.getProjectId()).ifPresent(project -> {
            project.setCurrentAmount(project.getCurrentAmount() + transaction.getAmount());
            repository.save(project);
        });
        return 1;
    }

    @Transactional
    public void saveProject(project project) {
        repository.save(project);
    }

    @Transactional
    public project getProject(Integer projectId) {
        return repository.findById(projectId).get();


    }


    @Transactional
    public boolean existsById(Integer projectId) {
        return repository.existsById(projectId);


    }

    //Θέλουμε να βρούμε τα πρότζεκτ τα οποία είναι πετυχημένα αλλά έχουν και transaction με το id του εκάστοτε συνδεδεμένου χρήστη
    @Transactional
    public List<project> GetSucces(int id){

        List<project> projects = getProjects();
        List<Transaction> transactions =transactionService.getTransactions();


        List<project> succes = new ArrayList<>();
        List<project> succes1 = new ArrayList<>();
        //Αρχικά βρίσκουμε ποια πρότζεκτ έχουν πετύχει τον στόχο τους
        for(int i = 0; i<projects.size(); i++){
            if (projects.get(i).getCurrentAmount() > projects.get(i).getGoalAmount() ){
                succes1.add(projects.get(i));
            }
        }
        //Ελέγχουμε ποια από αυτά έχουν το transaction.ProjectId=project.id
        //Και τα βάζουμε σε μια λίστα
        for(int i = 0; i<succes1.size(); i++){
            for(int j = 0; j<transactions.size(); j++){
                if(transactions.get(j).getProjectId() == succes1.get(i).getId()){
                    if(transactions.get(j).getUserId() ==id){
                        succes.add(succes1.get(i));
                    }
                }
            }
        }
        //Διαγράφουμε τα διπλότυπα
        succes = succes.stream().distinct().collect(Collectors.toList());



        return succes;
    }

}
