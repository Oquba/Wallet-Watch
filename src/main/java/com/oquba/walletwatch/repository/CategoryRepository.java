package com.oquba.walletwatch.repository;

import com.oquba.walletwatch.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE(c.createdBy.id = 1 OR c.createdBy.id = :userID) ORDER BY c.id")
    List<Category> findAll(Long userID);
    Optional<Category> findById(Long id);
}
