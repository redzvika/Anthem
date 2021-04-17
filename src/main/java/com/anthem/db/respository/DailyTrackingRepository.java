package com.anthem.db.respository;


import com.anthem.db.model.DailyTrackingEntity;
import com.anthem.db.model.ReportsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DailyTrackingRepository extends JpaRepository<DailyTrackingEntity, Integer> {
    DailyTrackingEntity findByUserIdAndDate(int userId,LocalDate localDate);
    List<DailyTrackingEntity> findByDateAndCountIsGreaterThanEqual(LocalDate localDate,int count);


}
