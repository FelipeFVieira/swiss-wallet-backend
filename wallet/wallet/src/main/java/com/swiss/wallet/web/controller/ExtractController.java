package com.swiss.wallet.web.controller;

import com.swiss.wallet.entity.Extract;
import com.swiss.wallet.jwt.JwtUserDetails;
import com.swiss.wallet.service.ExtractService;
import com.swiss.wallet.web.dto.ExtractResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v3/extracts")
public class ExtractController {

    private final ExtractService extractService;

    public ExtractController(ExtractService extractService) {
        this.extractService = extractService;
    }

    @GetMapping("/current")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ExtractResponseDto> findExtractByUserLogged(@AuthenticationPrincipal JwtUserDetails userDetails){
        Extract extract = extractService.findByUserLogged(userDetails.getId());
        return ResponseEntity.ok().body(ExtractResponseDto.toExtractResponse(extract));
    }
}
