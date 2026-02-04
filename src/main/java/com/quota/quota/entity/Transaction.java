package com.quota.quota.entity;

import com.quota.quota.constant.QuotaStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Entity
@Table(name = "quota_transaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction implements Serializable {

    @Id
    @Column(name = "tran_id", length = 20, nullable = false)
    private String tranId;

    @Column(name = "quota_id", length = 50, nullable = false)
    private String quotaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quota_id", insertable = false, updatable = false)
    private Quota quota;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private QuotaStatus status;

    @Column(name = "taken_by")
    private String takenBy;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    public Transaction(String tranId, String quotaId, QuotaStatus status, String takenBy, OffsetDateTime createdAt){
        this.tranId = tranId;
        this.quotaId = quotaId;
        this.status = status;
        this.takenBy = takenBy;
        this.createdAt = createdAt;
    }
}
