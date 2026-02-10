package com.quota.quota.controller;

import com.quota.quota.dto.QuotaSummaryDTO;
import com.quota.quota.dto.QuotaUpsertDTO;
import com.quota.quota.dto.ResponseDTO;
import com.quota.quota.entity.Quota;
import com.quota.quota.service.QuotaAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/quota")
public class AdminController {
    @Autowired
    private QuotaAdminService adminService;


    @PostMapping("/setup")
    public ResponseDTO createQuota(@RequestBody QuotaUpsertDTO request) {
        Quota savedQuota = adminService.createQuota(request);
        ResponseDTO response = new ResponseDTO("00", "Success", savedQuota);
        return response;
    }

    @DeleteMapping("/setup/{id}")
    public ResponseDTO deleteQuota(@PathVariable UUID id) {
        adminService.deleteQuota(id);
        ResponseDTO response = new ResponseDTO("00", "Success", "No content to display.");
        return response;
    }
}
