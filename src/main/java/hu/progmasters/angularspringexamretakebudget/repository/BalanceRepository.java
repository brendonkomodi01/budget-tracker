package hu.progmasters.angularspringexamretakebudget.repository;

import hu.progmasters.angularspringexamretakebudget.domain.AppUser;
import hu.progmasters.angularspringexamretakebudget.domain.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {

    Optional<Balance> findByUser(AppUser user);
}