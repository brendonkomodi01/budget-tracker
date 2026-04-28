package hu.brendonkomodi.budgettracker.service;

import hu.brendonkomodi.budgettracker.domain.AppUser;
import hu.brendonkomodi.budgettracker.domain.Category;
import hu.brendonkomodi.budgettracker.domain.Expense;
import hu.brendonkomodi.budgettracker.dto.incoming.ExpenseCreateCommand;
import hu.brendonkomodi.budgettracker.dto.outgoing.CategorySummaryInfo;
import hu.brendonkomodi.budgettracker.dto.outgoing.ExpenseInfo;
import hu.brendonkomodi.budgettracker.dto.outgoing.MonthlyExpenseInfo;
import hu.brendonkomodi.budgettracker.repository.AppUserRepository;
import hu.brendonkomodi.budgettracker.repository.CategoryRepository;
import hu.brendonkomodi.budgettracker.repository.ExpenseRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final AppUserRepository appUserRepository;
    private final ModelMapper modelMapper;

    public ExpenseService(ExpenseRepository expenseRepository,
                          CategoryRepository categoryRepository,
                          AppUserRepository appUserRepository,
                          ModelMapper modelMapper) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
        this.appUserRepository = appUserRepository;
        this.modelMapper = modelMapper;
    }

    private AppUser getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public ExpenseInfo createExpense(ExpenseCreateCommand command) {
        Category category = categoryRepository.findById(command.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + command.getCategoryId()));
        Expense expense = new Expense();
        expense.setAmount(command.getAmount());
        expense.setExpenseDate(command.getExpenseDate());
        expense.setCategory(category);
        expense.setDescription(command.getDescription());
        expense.setUser(getCurrentUser());
        Expense saved = expenseRepository.save(expense);
        log.info("Expense is created");
        return toExpenseInfo(saved);
    }

    public List<ExpenseInfo> findAllExpenses() {
        log.info("Expenses list page is requested");
        AppUser user = getCurrentUser();
        return expenseRepository.findAllByUserOrderByExpenseDateDesc(user)
                .stream()
                .map(this::toExpenseInfo)
                .collect(Collectors.toList());
    }

    public List<CategorySummaryInfo> getSummary() {
        log.info("Summary page is requested");
        AppUser user = getCurrentUser();
        Map<String, Double> summaryMap = expenseRepository.findAllByUser(user)
                .stream()
                .collect(Collectors.groupingBy(
                        expense -> expense.getCategory().getName(),
                        Collectors.summingDouble(Expense::getAmount)
                ));
        return summaryMap.entrySet()
                .stream()
                .map(entry -> {
                    CategorySummaryInfo info = new CategorySummaryInfo();
                    info.setCategoryName(entry.getKey());
                    info.setSumAmount(entry.getValue());
                    return info;
                })
                .collect(Collectors.toList());
    }

    private ExpenseInfo toExpenseInfo(Expense expense) {
        ExpenseInfo info = new ExpenseInfo();
        info.setId(expense.getId());
        info.setAmount(expense.getAmount());
        info.setExpenseDate(expense.getExpenseDate());
        info.setCategoryName(expense.getCategory().getName());
        info.setDescription(expense.getDescription());
        return info;
    }

    public List<MonthlyExpenseInfo> getMonthlyExpenses() {
        AppUser user = getCurrentUser();
        return expenseRepository.findAllByUser(user)
                .stream()
                .collect(Collectors.groupingBy(
                        expense -> expense.getExpenseDate().getYear() + "-" + expense.getExpenseDate().getMonthValue(),
                        Collectors.summingDouble(Expense::getAmount)
                ))
                .entrySet()
                .stream()
                .map(entry -> {
                    String[] parts = entry.getKey().split("-");
                    MonthlyExpenseInfo info = new MonthlyExpenseInfo();
                    info.setYear(Integer.parseInt(parts[0]));
                    info.setMonth(Integer.parseInt(parts[1]));
                    info.setTotalAmount(entry.getValue());
                    return info;
                })
                .sorted((a, b) -> {
                    if (a.getYear() != b.getYear()) return b.getYear() - a.getYear();
                    return b.getMonth() - a.getMonth();
                })
                .collect(Collectors.toList());
    }

    public void deleteExpense(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Expense not found: " + id));
        expenseRepository.delete(expense);
        log.info("Expense is deleted");
    }

    public ExpenseInfo updateExpense(Long id, ExpenseCreateCommand command) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Expense not found: " + id));
        Category category = categoryRepository.findById(command.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + command.getCategoryId()));
        expense.setAmount(command.getAmount());
        expense.setExpenseDate(command.getExpenseDate());
        expense.setCategory(category);
        expense.setDescription(command.getDescription());
        log.info("Expense is updated");
        return toExpenseInfo(expense);
    }
}