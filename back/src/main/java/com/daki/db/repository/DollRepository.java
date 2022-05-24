package com.daki.db.repository;

import com.daki.db.entity.Doll;
import com.daki.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DollRepository extends JpaRepository<Doll, Long> {
//    @Override
//    Doll getById(Long aLong);

    Doll findByUser(User user);
}
