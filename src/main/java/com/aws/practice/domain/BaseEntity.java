package com.aws.practice.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter @Setter
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public class BaseEntity {
    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;
    @CreationTimestamp
    @Column(name = "created_time")
    private LocalDateTime createdTime;
    @LastModifiedBy
    @Column(name = "modifed_by")
    private String modifedBy;
    @UpdateTimestamp
    @Column(name = "modifed_time")
    private LocalDateTime modifedTime;
}


