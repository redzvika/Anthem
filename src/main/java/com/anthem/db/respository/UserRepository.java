package com.anthem.db.respository;

import com.anthem.db.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    List<UserEntity> findByFirstNameContainingIgnoreCase(String firstName);

    List<UserEntity> findByLastNameContainingIgnoreCase(String lastName);

    List<UserEntity> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName, String lastName);



    @Query("SELECT u \n" +
            "FROM   UserEntity u \n" +
            "WHERE  NOT EXISTS " +
            "(\n" +
            "    SELECT  u.id\n" +
            "    FROM DailyTrackingEntity d\n" +
            "    WHERE u.id = d.userId\n" +
            "    AND d.date= :date\n" +
            "\n)")

    public List<UserEntity> getUserNotInDailyForDate(@Param("date") LocalDate localDate);

}
