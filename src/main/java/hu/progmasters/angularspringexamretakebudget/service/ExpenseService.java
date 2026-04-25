package hu.progmasters.angularspringexamretakebudget.service;

import hu.progmasters.angularspringexamretakebudget.domain.Category;
import hu.progmasters.angularspringexamretakebudget.domain.Expense;
import hu.progmasters.angularspringexamretakebudget.dto.incoming.ExpenseCreateCommand;
import hu.progmasters.angularspringexamretakebudget.dto.outgoing.CategorySummaryInfo;
import hu.progmasters.angularspringexamretakebudget.dto.outgoing.ExpenseInfo;
import hu.progmasters.angularspringexamretakebudget.repository.CategoryRepository;
import hu.progmasters.angularspringexamretakebudget.repository.ExpenseRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

    public ExpenseService(ExpenseRepository expenseRepository,
                          CategoryRepository categoryRepository,
                          ModelMapper modelMapper) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public ExpenseInfo createExpense(ExpenseCreateCommand command) {
        Category category = categoryRepository.findById(command.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + command.getCategoryId()));
        Expense expense = new Expense();
        expense.setAmount(command.getAmount());
        expense.setExpenseDate(command.getExpenseDate());
        expense.setCategory(category);
        expense.setDescription(command.getDescription());
        Expense saved = expenseRepository.save(expense);
        log.info("Expense is created");
        return toExpenseInfo(saved);
    }

    public List<ExpenseInfo> findAllExpenses() {
        log.info("Expenses list page is requested");
        return expenseRepository.findAllByOrderByExpenseDateDesc()
                .stream()
                .map(this::toExpenseInfo)
                .collect(Collectors.toList());
    }

    public List<CategorySummaryInfo> getSummary() {
        log.info("Summary page is requested");
        Map<String, Double> summaryMap = expenseRepository.findAll()
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
}