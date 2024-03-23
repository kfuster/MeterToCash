package fr.ekwateur.metertocash.calculator;

import static org.junit.jupiter.api.Assertions.*;

import fr.ekwateur.metertocash.model.Consumption;
import fr.ekwateur.metertocash.model.EnergyType;
import fr.ekwateur.metertocash.model.Professional;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProfessionalBillingCalculatorTest {
    @InjectMocks
    private ProfessionalBillingCalculator professionalBillingCalculator;

    @Test
    void calculateBilling_mustUseBelowLimitPrices_whenClientRevenueBelowLimit() {
        // Given
        Consumption elecConsumption = new Consumption(EnergyType.ELEC, 100);
        Consumption gazConsumption = new Consumption(EnergyType.GAZ, 100);
        Professional professional = new Professional("EKW78547862", List.of(elecConsumption, gazConsumption), 12345678912324L, "client", 500000.0);

        // When
        Double result = professionalBillingCalculator.calculateBilling(professional);

        // Then
        assertNotNull(result);
        assertEquals(23.1, result);
    }

    @Test
    void calculateBilling_mustUseAboveLimitPrices_whenClientRevenueAboveLimit() {
        // Given
        Consumption elecConsumption = new Consumption(EnergyType.ELEC, 100);
        Consumption gazConsumption = new Consumption(EnergyType.GAZ, 100);
        Professional professional = new Professional("EKW78547862", List.of(elecConsumption, gazConsumption), 12345678912324L, "client", 5000000.0);

        // When
        Double result = professionalBillingCalculator.calculateBilling(professional);

        // Then
        assertNotNull(result);
        assertEquals(22.5, result);
    }
}
