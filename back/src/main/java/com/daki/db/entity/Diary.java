package com.daki.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "diary")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_no")
    private Long diaryNo;

    @Column(name = "diary_title")
    private String diaryTitle;

    @Column(name = "diary_content", columnDefinition = "LONGTEXT")
    private String diaryContent;

    @Column(name = "diary_date")
    @CreatedDate
    @LastModifiedDate
    private LocalDateTime diaryDate;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;


    public Diary(String diaryTitle, String diaryContent, User user) {
        this.diaryTitle = diaryTitle;
        this.diaryContent = diaryContent;
        this.diaryDate = LocalDateTime.now();
        this.user = user;
    }

    public Diary(Long diaryNo,String diaryTitle, String diaryContent, User user) {
        this.diaryNo = diaryNo;
        this.diaryTitle = diaryTitle;
        this.diaryContent = diaryContent;
        this.diaryDate = LocalDateTime.now();
        this.user = user;
    }
}