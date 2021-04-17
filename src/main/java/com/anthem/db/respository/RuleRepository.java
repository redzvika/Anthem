package com.anthem.db.respository;

import com.anthem.db.model.RuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends JpaRepository<RuleEntity, Integer> {



//

//    @Query("SELECT u FROM Users u WHERE u.last_name LIKE :lastName")
//    public List<UserEntity> findByLastName(String lastName);


}
