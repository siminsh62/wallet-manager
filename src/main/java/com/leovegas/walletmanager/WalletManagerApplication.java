package com.leovegas.walletmanager;

import com.leovegas.walletmanager.service.SeedingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class WalletManagerApplication {
    public static final Logger log = LoggerFactory.getLogger(WalletManagerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WalletManagerApplication.class, args);
        log.info("The wallet manager Application has started...");
    }


    @Bean
    public CommandLineRunner run(final SeedingService seedingService) {

        return args -> {
            seedingService.createPlayer();
        };
    }
}
