package com.quota.quota.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DropdownDTO {
    private Object value;
    private String textContent;

    public static List<DropdownDTO> getRoleDropdown(){
        var dropdownDto = new ArrayList<DropdownDTO>();
        dropdownDto.add(new DropdownDTO( "Administrator", "Administrator"));
        dropdownDto.add(new DropdownDTO( "Salesman", "Salesman"));
        dropdownDto.add(new DropdownDTO( "Finance", "Finance"));
        return dropdownDto;
    }
}
