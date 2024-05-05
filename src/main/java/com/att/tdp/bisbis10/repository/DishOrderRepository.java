package com.att.tdp.bisbis10.repository;


import com.att.tdp.bisbis10.entitys.DishOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishOrderRepository extends JpaRepository<DishOrder, Long> {
}
