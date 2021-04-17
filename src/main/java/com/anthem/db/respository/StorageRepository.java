package com.anthem.db.respository;


import com.anthem.db.model.StorageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<StorageEntity, String> {
    StorageEntity findByKey(String key);

//

//    @Query("SELECT u FROM Users u WHERE u.last_name LIKE :lastName")
//    public List<UserEntity> findByLastName(String lastName);


}
