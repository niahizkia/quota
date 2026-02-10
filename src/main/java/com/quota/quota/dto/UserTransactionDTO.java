package com.quota.quota.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserTransactionDTO {
    private UserInformationDTO userInfo;
    private Object tranList;
}
