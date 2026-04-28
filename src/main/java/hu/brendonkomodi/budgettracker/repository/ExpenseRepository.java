package hu.brendonkomodi.budgettracker.repository;

import hu.brendonkomodi.budgettracker.domain.AppUser;
import hu.brendonkomodi.budgettracker.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findAllByOrderByExpenseDateDesc();
    List<Expense> findAllByUserOrderByExpenseDateDesc(AppUser user);
    List<Expense> findAllByUser(AppUser user);
}