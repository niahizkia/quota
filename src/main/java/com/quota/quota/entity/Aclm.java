package com.quota.quota.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Entity
@Table(name = "aclm")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aclm implements Serializable {
    @Id
    @Column(name = "acl_id")
    private String alcId;

    @Column(name = "role_id")
    private String roleId;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;
}
