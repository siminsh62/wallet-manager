package com.leovegas.walletmanager.controller;

import com.leovegas.walletmanager.entities.Player;
import com.leovegas.walletmanager.entities.Transaction;
import com.leovegas.walletmanager.service.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/{playerId}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getBalanceByPlayer(@PathVariable Long playerId) {
        return walletService.getBalanceByPlayer(playerId);
    }

    @PutMapping(path="/debit/{playerId}/{debitAmount}")
    @ResponseStatus(HttpStatus.OK)
    public Player makeDebit(@PathVariable Long playerId, @PathVariable BigDecimal debitAmount) {
        return walletService.makeDebit(playerId, debitAmount);
    }

    @PutMapping(path="/credit/{playerId}/{creditAmount}")
    @ResponseStatus(HttpStatus.OK)
    public Player makeCredit(@PathVariable Long playerId, @PathVariable BigDecimal creditAmount) {
        return walletService.makeCredit(playerId, creditAmount);
    }

    @GetMapping(path="/transactions/{playerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> getTransactionsByPlayer(@PathVariable Long playerId) {
        return walletService.getTransactionsByPlayer(playerId);
    }
}
