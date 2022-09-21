package com.leovegas.walletmanager.service;

import com.leovegas.walletmanager.repository.PlayerRepository;
import com.leovegas.walletmanager.repository.TransactionRepository;
import com.leovegas.walletmanager.entities.TransactionType;
import com.leovegas.walletmanager.entities.Player;
import com.leovegas.walletmanager.entities.Transaction;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Service

public class WalletService {
    private final PlayerRepository playerRepository;
    private final TransactionRepository transactionRepository;

    public WalletService(PlayerRepository playerRepository, TransactionRepository transactionRepository) {
        this.playerRepository = playerRepository;
        this.transactionRepository = transactionRepository;
    }

    public BigDecimal getBalanceByPlayer(Long playerId) {

        return playerRepository.findById(playerId)
                .orElseThrow(() -> new ElementNotFoundException("Could not find player with this Id"))
                .getBalance();
    }

    public Player getPlayer(Long playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new ElementNotFoundException("Could not find player with this Id"));
    }

    //TODO
    @Transactional
    public synchronized Player makeDebit(Long playerId, BigDecimal debitAmount) {
        Player player = getPlayer(playerId);
        BigDecimal newBalance = player.getBalance().subtract(debitAmount);
        if (newBalance.compareTo(BigDecimal.valueOf(0.0)) == 0 ||
                newBalance.compareTo(BigDecimal.valueOf(0.0)) > 0) {
            Transaction transaction = createTransaction(debitAmount, TransactionType.DEBIT);
            player.setBalance(newBalance);
            player.getTransactions().add(transaction);
        }
        return playerRepository.save(player);

    }

    @Transactional
    public synchronized Player makeCredit(Long playerId, BigDecimal creditAmount) {
        Player player = getPlayer(playerId);
        Transaction transaction = createTransaction(creditAmount, TransactionType.CREDIT);
        BigDecimal newBalance = player.getBalance().add(creditAmount);
        player.setBalance(newBalance);
        player.getTransactions().add(transaction);
        return playerRepository.save(player);
    }

    private Transaction createTransaction(BigDecimal amount, TransactionType transactionType) {
        return Transaction.builder()
                .transactionType(transactionType)
                .transactionDate(new Date())
                .amount(amount)
                .build();

    }

    public List<Transaction> getTransactionsByPlayer(Long playerId) {
        return getPlayer(playerId).getTransactions();
    }

    public Player creat(Player player) {
        return playerRepository.save(player);
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
}
