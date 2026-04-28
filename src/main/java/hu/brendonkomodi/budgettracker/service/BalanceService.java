package hu.brendonkomodi.budgettracker.service;

import hu.brendonkomodi.budgettracker.domain.AppUser;
import hu.brendonkomodi.budgettracker.domain.Balance;
import hu.brendonkomodi.budgettracker.repository.AppUserRepository;
import hu.brendonkomodi.budgettracker.repository.BalanceRepository;
import hu.brendonkomodi.budgettracker.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BalanceService {

    private final BalanceRepository balanceRepository;
    private final AppUserRepository appUserRepository;
    private final ExpenseRepository expenseRepository;

    private AppUser getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<Map<String, Object>> getAllBalances() {
        AppUser user = getCurrentUser();
        return balanceRepository.findAllByUser(user).stream()
                .map(balance -> {
                    double totalExpenses = expenseRepository.findAllByUser(user).stream()
                            .filter(e -> e.getExpenseDate().getYear() == balance.getYear()
                                    && e.getExpenseDate().getMonthValue() == balance.getMonth())
                            .mapToDouble(e -> e.getAmount())
                            .sum();
                    Map<String, Object> result = new HashMap<>();
                    result.put("year", balance.getYear());
                    result.put("month", balance.getMonth());
                    result.put("startingBalance", balance.getAmount());
                    result.put("totalExpenses", totalExpenses);
                    result.put("remaining", balance.getAmount() - totalExpenses);
                    return result;
                })
                .collect(Collectors.toList());
    }

    public Map<String, Object> setBalance(Integer year, Integer month, Double amount) {
        AppUser user = getCurrentUser();
        Balance balance = balanceRepository.findByUserAndYearAndMonth(user, year, month)
                .orElse(new Balance());
        balance.setUser(user);
        balance.setYear(year);
        balance.setMonth(month);
        balance.setAmount(amount);
        balanceRepository.save(balance);
        log.info("Balance set for {}/{}: {}", year, month, amount);

        double totalExpenses = expenseRepository.findAllByUser(user).stream()
                .filter(e -> e.getExpenseDate().getYear() == year
                        && e.getExpenseDate().getMonthValue() == month)
                .mapToDouble(e -> e.getAmount())
                .sum();

        Map<String, Object> result = new HashMap<>();
        result.put("year", year);
        result.put("month", month);
        result.put("startingBalance", amount);
        result.put("totalExpenses", totalExpenses);
        result.put("remaining", amount - totalExpenses);
        return result;
    }
}