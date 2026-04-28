package hu.progmasters.angularspringexamretakebudget.repository;

import hu.progmasters.angularspringexamretakebudget.domain.AppUser;
import hu.progmasters.angularspringexamretakebudget.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByOrderByNameAsc();

    List<Category> findAllByUserOrderByNameAsc(AppUser user);
}