package hu.brendonkomodi.budgettracker.dto.outgoing;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategorySummaryInfo {

    private String categoryName;
    private Double sumAmount;
}