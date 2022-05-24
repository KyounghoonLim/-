package com.daki.db.repository;

import com.daki.db.entity.Diary;
import com.daki.db.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findAllByDiaryDateBetweenOrderByDiaryDateAsc(LocalDateTime start, LocalDateTime end);

    List<Diary> findAllByUserOrderByDiaryDateDesc(User user, Pageable pageable);
}
