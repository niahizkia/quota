package com.quota.quota.entity;

import com.quota.quota.constant.QuotaStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
public class Transaction  extends BaseEntity {

    @Column(name = "quota_id", nullable = false)
    private UUID quotaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quota_id", insertable = false, updatable = false)
        private Quota quota;

    @Column(name = "account_id", nullable = false)
    private UUID accountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private QuotaStatus status;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    protected Transaction() {
        super();
    }
    public Transaction( UUID quotaId, UUID accountId, QuotaStatus status, OffsetDateTime createdAt){
//        super(tranId);
        this.quotaId = quotaId;
        this.accountId = accountId;
        this.status = status;
        this.createdAt = createdAt;
    }
}
