package com.leovegas.walletmanager.repository;

import com.leovegas.walletmanager.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Simin
 * @created 09/09/2022 - 12:12 AM
 */

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
