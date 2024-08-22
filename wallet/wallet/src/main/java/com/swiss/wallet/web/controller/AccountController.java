package com.swiss.wallet.web.controller;

import com.swiss.wallet.entity.Account;
import com.swiss.wallet.jwt.JwtUserDetails;
import com.swiss.wallet.service.AccountService;
import com.swiss.wallet.web.dto.AccountResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v3/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/current")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<AccountResponseDto> getAccountUserCurrent(@AuthenticationPrincipal JwtUserDetails userDetails){
        Account account = accountService.findByUserId(userDetails.getId());
        return ResponseEntity.ok().body(AccountResponseDto.toAccountResponseDto(account));
    }
}
