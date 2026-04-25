package hu.progmasters.angularspringexamretakebudget.dto.incoming;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryCreateCommand {

    @NotBlank(message = "Category name is mandatory")
    private String name;
}