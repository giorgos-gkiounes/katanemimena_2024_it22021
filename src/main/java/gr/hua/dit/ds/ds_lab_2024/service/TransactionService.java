package gr.hua.dit.ds.ds_lab_2024.service;

import gr.hua.dit.ds.ds_lab_2024.entities.Transaction;
import gr.hua.dit.ds.ds_lab_2024.entities.project;
import gr.hua.dit.ds.ds_lab_2024.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TransactionService {

    private TransactionRepository transactionRepository;


    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public List<Transaction> findAll(){
        return transactionRepository.findAll();
    }

    @Transactional
    public Transaction save(Transaction transaction,int id){
        transaction.setUserId(id);
        return transactionRepository.save(transaction);
    }

    @Transactional
    public List<Transaction> getTransactions (){
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsOfUser(int userId){
        return  transactionRepository.findByUserId(userId);

    }

}
