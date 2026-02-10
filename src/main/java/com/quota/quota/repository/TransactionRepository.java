package com.quota.quota.repository;

import com.quota.quota.dto.TransactionDetailDTO;
import com.quota.quota.dto.TransactionListDTO;
import com.quota.quota.entity.Quota;
import com.quota.quota.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Query("""
            SELECT new com.quota.quota.dto.TransactionDetailDTO(q.quotaName, t.status, t.createdAt)
            FROM Transaction t
            JOIN t.quota as q
            WHERE q.id = :quotaId
            """)
    public List<TransactionDetailDTO> getTranByQuotaId(@Param("quotaId") UUID quotaId);

    @Query("""
            SELECT new com.quota.quota.dto.TransactionListDTO(t.id, t.quotaId, q.quotaName, t.status, t.createdAt)
            FROM Transaction t
            JOIN t.quota as q
            WHERE t.accountId = :accountId
            """)
    public List<TransactionListDTO> getTranByAccountId(@Param("accountId") UUID accountId);
    List<Transaction> findByAccountId(UUID accountId);

}
