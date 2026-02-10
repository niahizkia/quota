package com.quota.quota.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "acls")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Acls extends BaseEntity {

    @Column(name = "resource_name")
    private String resourceName;

    @Column(name = "role_id")
    private UUID roleId;

    @Column(name = "can_read")
    private Boolean canRead = false;

    @Column(name = "can_write")
    private Boolean canWrite = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;
}
