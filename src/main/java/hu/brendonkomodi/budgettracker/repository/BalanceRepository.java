package hu.brendonkomodi.budgettracker.repository;

import hu.brendonkomodi.budgettracker.domain.AppUser;
import hu.brendonkomodi.budgettracker.domain.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {

    Optional<Balance> findByUserAndYearAndMonth(AppUser user, Integer year, Integer month);

    List<Balance> findAllByUser(AppUser user);
}