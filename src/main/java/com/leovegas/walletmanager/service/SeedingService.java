package com.leovegas.walletmanager.service;

import com.leovegas.walletmanager.entities.TransactionType;
import com.leovegas.walletmanager.entities.Player;
import com.leovegas.walletmanager.entities.Transaction;
import com.leovegas.walletmanager.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Simin
 * @created 10/09/2022 - 1:23 PM
 */

@Service
public class SeedingService {
    private Player player1;
    private Player player2;
    private Player player3;

    private final PlayerRepository playerRepository;


    public SeedingService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void createPlayer() {

        player1 = Player.builder()
                .name("player1")
                .balance(BigDecimal.valueOf(0.0))
                .build();
        playerRepository.save(player1);

        player2 = Player.builder()
                .name("player2")
                .balance(BigDecimal.valueOf(2000.0))
                .build();
        playerRepository.save(player2);

        player3 = Player.builder()
                .name("player3")
                .balance(BigDecimal.valueOf(0.0))
                .build();
        playerRepository.save(player3);

    }

    public Player createTransaction() {
        player2 = Player.builder()
                .name("player2")
                .balance(BigDecimal.valueOf(2000.0))
                .build();

        List<Transaction> transactions = new ArrayList<>();
        Transaction transactionDebit = Transaction.builder()
                .transactionType(TransactionType.DEBIT)
                .transactionDate(new Date())
                .amount(BigDecimal.valueOf(500))
                .build();
        if (player2.getTransactions() == null)
            transactions.add(transactionDebit);

       // player2.getTransactions().add(transactionDebit);


        Transaction transactionCredit = Transaction.builder()
                .transactionType(TransactionType.CREDIT)
                .transactionDate(new Date())
                .amount(BigDecimal.valueOf(1000))
                .build();
        if (player2.getTransactions() == null)
            transactions.add(transactionCredit);
        player2.setTransactions(transactions);
        return playerRepository.save(player2);
    }
}
