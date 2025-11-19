package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entily.PlanMasterEntity;

@Repository
public interface PlanMasterrepo extends JpaRepository<PlanMasterEntity, Integer> {

}
