package com.example.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String blogId;
    private String title;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @ManyToOne
    @JsonIgnore
    private User user;

    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

}
