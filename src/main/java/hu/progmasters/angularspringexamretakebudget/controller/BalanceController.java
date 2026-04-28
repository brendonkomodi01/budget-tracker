package hu.progmasters.angularspringexamretakebudget.controller;

import hu.progmasters.angularspringexamretakebudget.service.BalanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/balance")
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceService balanceService;

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllBalances() {
        log.info("All balances requested");
        return ResponseEntity.ok(balanceService.getAllBalances());
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> setBalance(@RequestBody Map<String, Object> body) {
        log.info("Balance update requested");
        Integer year = (Integer) body.get("year");
        Integer month = (Integer) body.get("month");
        Double amount = ((Number) body.get("amount")).doubleValue();
        return ResponseEntity.ok(balanceService.setBalance(year, month, amount));
    }
}