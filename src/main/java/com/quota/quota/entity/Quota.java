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
@Table(name = "quota_master")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Quota implements Serializable {

    @Id
    @Column(name = "quota_id", length = 20, nullable = false)
    private String quotaId;

    @Column(name = "quota_name", length = 50, nullable = false)
    private String quotaName;

    @Column(name = "quota_limit")
    private Integer quotaLimit;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    //relationship: One Quota can have many Tran
    @OneToMany(mappedBy = "quota", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}
