package com.example.umc_9th.domain.food.repository;

import com.example.umc_9th.domain.food.entity.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<FoodCategory, Long> {
    List<FoodCategory> findAllByIdIn(List<Long> ids);
}