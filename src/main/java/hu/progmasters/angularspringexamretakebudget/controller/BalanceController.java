package hu.progmasters.angularspringexamretakebudget.controller;

import hu.progmasters.angularspringexamretakebudget.service.BalanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/balance")
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceService balanceService;

    @GetMapping
    public ResponseEntity<Map<String, Double>> getBalance() {
        log.info("Balance requested");
        return ResponseEntity.ok(Map.of("amount", balanceService.getBalance()));
    }

    @PutMapping
    public ResponseEntity<Map<String, Double>> setBalance(@RequestBody Map<String, Double> body) {
        log.info("Balance update requested");
        Double amount = balanceService.setBalance(body.get("amount"));
        return ResponseEntity.ok(Map.of("amount", amount));
    }
}