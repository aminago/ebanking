package com.example.ebankingbackend.services;

import lombok.AllArgsConstructor;
import com.example.ebankingbackend.entities.BankAccount;
import com.example.ebankingbackend.entities.CurrentAccount;
import com.example.ebankingbackend.entities.SavingAccount;
import com.example.ebankingbackend.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class BankService {

    private BankAccountRepository bankAccountRepository;
    public void consulter() {
        BankAccount bankAccount = bankAccountRepository.findById("1ae3f86a-a6f1-4531-af3f-67588cc36937").orElse(null);
        System.out.println("**************************");
        System.out.println(bankAccount.getId());
        System.out.println(bankAccount.getBalance());
        System.out.println(bankAccount.getStatus());
        System.out.println(bankAccount.getCreatedAt());
        System.out.println(bankAccount.getCustomer().getName());
        System.out.println(bankAccount.getClass().getSimpleName());
        if (bankAccount instanceof CurrentAccount) {
            System.out.println("Over Draft -> "+((CurrentAccount) bankAccount).getOverDraft());
        } else if (bankAccount instanceof SavingAccount) {
            System.out.println("Rate -> "+((SavingAccount) bankAccount).getInterestRate());
        }

        bankAccount.getAccountOperations().forEach(operation -> {
            System.out.println(operation.getType()+"\t"+operation.getOperationDate()+"\t"+operation.getAmount());
        });
    }
}