package hu.progmasters.angularspringexamretakebudget.dto.outgoing;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ExpenseInfo {

    private Long id;
    private Double amount;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expenseDate;

    private String categoryName;
    private String description;
}