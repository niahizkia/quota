package com.quota.quota.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "quotas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Quota extends BaseEntity {

    @Column(name = "quota_name", length = 100, nullable = false)
    private String quotaName;

    @Column(name = "max_limit")
    private Integer maxLimit;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    //relationship: One Quota can have many Tran
    @OneToMany(mappedBy = "quota", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}
