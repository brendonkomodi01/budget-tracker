package hu.brendonkomodi.budgettracker.dto.incoming;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ExpenseCreateCommand {

    @NotNull(message = "Amount is mandatory")
    private Double amount;

    @NotNull(message = "Expense date is mandatory")
    private LocalDate expenseDate;

    @NotNull(message = "Category is mandatory")
    private Long categoryId;

    private String description;
}