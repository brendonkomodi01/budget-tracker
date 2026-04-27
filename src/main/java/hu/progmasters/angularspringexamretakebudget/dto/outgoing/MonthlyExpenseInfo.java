package hu.progmasters.angularspringexamretakebudget.dto.outgoing;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MonthlyExpenseInfo {

    private int year;
    private int month;
    private Double totalAmount;
}