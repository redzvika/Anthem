package com.anthem.db.respository;

import com.anthem.db.model.ReportsEntity;
import com.anthem.db.model.RuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReportsRepository extends JpaRepository<ReportsEntity, Integer> {



}
