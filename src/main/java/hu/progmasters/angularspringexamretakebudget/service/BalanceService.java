package hu.progmasters.angularspringexamretakebudget.service;

import hu.progmasters.angularspringexamretakebudget.domain.AppUser;
import hu.progmasters.angularspringexamretakebudget.domain.Balance;
import hu.progmasters.angularspringexamretakebudget.repository.AppUserRepository;
import hu.progmasters.angularspringexamretakebudget.repository.BalanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BalanceService {

    private final BalanceRepository balanceRepository;
    private final AppUserRepository appUserRepository;

    private AppUser getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Double getBalance() {
        AppUser user = getCurrentUser();
        return balanceRepository.findByUser(user)
                .map(Balance::getAmount)
                .orElse(0.0);
    }

    public Double setBalance(Double amount) {
        AppUser user = getCurrentUser();
        Balance balance = balanceRepository.findByUser(user)
                .orElse(new Balance());
        balance.setUser(user);
        balance.setAmount(amount);
        balanceRepository.save(balance);
        log.info("Balance updated to: {}", amount);
        return amount;
    }
}