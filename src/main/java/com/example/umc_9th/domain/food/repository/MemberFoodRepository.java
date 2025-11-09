package com.example.umc_9th.domain.food.repository;

import com.example.umc_9th.domain.food.entity.MemberFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFoodRepository extends JpaRepository<MemberFood, Long> {
}
