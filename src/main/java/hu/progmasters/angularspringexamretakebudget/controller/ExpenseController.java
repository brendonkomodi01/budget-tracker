package hu.progmasters.angularspringexamretakebudget.controller;

import hu.progmasters.angularspringexamretakebudget.dto.incoming.ExpenseCreateCommand;
import hu.progmasters.angularspringexamretakebudget.dto.outgoing.CategorySummaryInfo;
import hu.progmasters.angularspringexamretakebudget.dto.outgoing.ExpenseInfo;
import hu.progmasters.angularspringexamretakebudget.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExpenseInfo createExpense(@Valid @RequestBody ExpenseCreateCommand command) {
        return expenseService.createExpense(command);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ExpenseInfo> findAllExpenses() {
        return expenseService.findAllExpenses();
    }

    @GetMapping("/summary")
    @ResponseStatus(HttpStatus.OK)
    public List<CategorySummaryInfo> getSummary() {
        return expenseService.getSummary();
    }
}