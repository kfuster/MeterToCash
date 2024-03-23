package fr.ekwateur.metertocash.mapper;

import fr.ekwateur.metertocash.dto.ConsumptionDTO;
import fr.ekwateur.metertocash.model.Consumption;
import java.util.List;

public class ConsumptionMapper {
    public static List<Consumption> dtosToModels( List<ConsumptionDTO> consumptions) {
        return consumptions.stream()
            .map(c -> new Consumption(c.energyType(), c.amount()))
            .toList();
    }
}
