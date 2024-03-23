package fr.ekwateur.metertocash.mapper;

import static org.junit.jupiter.api.Assertions.*;

import fr.ekwateur.metertocash.dto.ConsumptionDTO;
import fr.ekwateur.metertocash.model.Consumption;
import fr.ekwateur.metertocash.model.EnergyType;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConsumptionMapperTest {

    @Test
    void dtosToModels_mustConvertConsumptionDtosToModels() {
        // Given
        ConsumptionDTO consumptionDTO = new ConsumptionDTO(EnergyType.ELEC, 500);
        ConsumptionDTO consumptionDTO2 = new ConsumptionDTO(EnergyType.GAZ, 500);
        Consumption expectedConsumption = new Consumption(EnergyType.ELEC, 500);
        Consumption expectedConsumption2 = new Consumption(EnergyType.GAZ, 500);


        // When
        List<Consumption> result = ConsumptionMapper.dtosToModels(List.of(consumptionDTO, consumptionDTO2));

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(List.of(expectedConsumption, expectedConsumption2), result);
    }

}
