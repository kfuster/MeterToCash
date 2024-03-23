package fr.ekwateur.metertocash.dto;

import fr.ekwateur.metertocash.model.EnergyType;
import jakarta.validation.constraints.NotNull;

public record ConsumptionDTO(@NotNull EnergyType energyType,
                             @NotNull double amount) {
}
