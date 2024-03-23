package fr.ekwateur.metertocash.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.List;

public record RequestDTO(@NotNull @Pattern(regexp = "^EKW(\\d){8}$") String reference,
                         @NotNull ClientTypeDTO clientType,
                         @NotNull List<ConsumptionDTO> consumptions,
                         Double revenue) {
}
