package com.anthem.db.respository;


import com.anthem.db.model.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<TypeEntity, Integer> {
    TypeEntity findByNameIgnoreCase(String name);


//

//    @Query("SELECT u FROM Users u WHERE u.last_name LIKE :lastName")
//    public List<UserEntity> findByLastName(String lastName);


}
