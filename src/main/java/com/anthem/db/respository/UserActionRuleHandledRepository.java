package com.anthem.db.respository;

import com.anthem.db.model.UserActionsRuleHandledEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActionRuleHandledRepository extends JpaRepository<UserActionsRuleHandledEntity, Integer> {



//

//    @Query("SELECT u FROM Users u WHERE u.last_name LIKE :lastName")
//    public List<UserEntity> findByLastName(String lastName);


}
