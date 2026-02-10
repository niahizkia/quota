package com.quota.quota.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransactionDTO {
    private AccountInformationDTO userInfo;
    private Object tranList;
}
