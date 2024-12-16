package com.joy.coin.repository;

import com.joy.coin.entity.CoinCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoinCategoryRepository extends JpaRepository<CoinCategory, Long> {

    Optional<CoinCategory> findByCurrency(String Currency);

}
