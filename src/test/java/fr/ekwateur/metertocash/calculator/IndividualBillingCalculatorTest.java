package fr.ekwateur.metertocash.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import fr.ekwateur.metertocash.model.Consumption;
import fr.ekwateur.metertocash.model.EnergyType;
import fr.ekwateur.metertocash.model.Individual;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IndividualBillingCalculatorTest {
    @InjectMocks
    private IndividualBillingCalculator individualBillingCalculator;

    @Test
    void calculateBilling_mustReturnRightAmount() {
        // Given
        Consumption elecConsumption = new Consumption(EnergyType.ELEC, 100);
        Consumption gazConsumption = new Consumption(EnergyType.GAZ, 100);
        Individual individual = new Individual("EKW78547862", List.of(elecConsumption, gazConsumption), "title", "lastName", "firstName");

        // When
        Double result = individualBillingCalculator.calculateBilling(individual);

        // Then
        assertNotNull(result);
        assertEquals(23.6, result);
    }

}
