package com.anthem.db.respository;

import com.anthem.db.model.UserActionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserActionsRepository extends JpaRepository<UserActionsEntity, Integer> {

    List<UserActionsEntity> getAllByIdIsGreaterThan(int id);

//

//    @Query("SELECT u FROM Users u WHERE u.last_name LIKE :lastName")
//    public List<UserEntity> findByLastName(String lastName);


}
