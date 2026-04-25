package hu.progmasters.angularspringexamretakebudget.dto.outgoing;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategorySummaryInfo {

    private String categoryName;
    private Double sumAmount;
}