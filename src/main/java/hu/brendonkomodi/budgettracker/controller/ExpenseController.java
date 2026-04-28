package hu.brendonkomodi.budgettracker.controller;

import hu.brendonkomodi.budgettracker.dto.incoming.ExpenseCreateCommand;
import hu.brendonkomodi.budgettracker.dto.outgoing.CategorySummaryInfo;
import hu.brendonkomodi.budgettracker.dto.outgoing.ExpenseInfo;
import hu.brendonkomodi.budgettracker.dto.outgoing.MonthlyExpenseInfo;
import hu.brendonkomodi.budgettracker.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/monthly")
    @ResponseStatus(HttpStatus.OK)
    public List<MonthlyExpenseInfo> getMonthlyExpenses() {
        return expenseService.getMonthlyExpenses();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ExpenseInfo updateExpense(@PathVariable Long id, @Valid @RequestBody ExpenseCreateCommand command) {
        return expenseService.updateExpense(id, command);
    }
}