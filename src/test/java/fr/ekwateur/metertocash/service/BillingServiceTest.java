package fr.ekwateur.metertocash.service;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import fr.ekwateur.metertocash.calculator.IndividualBillingCalculator;
import fr.ekwateur.metertocash.calculator.ProfessionalBillingCalculator;
import fr.ekwateur.metertocash.model.Individual;
import fr.ekwateur.metertocash.model.Professional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BillingServiceTest {
    @InjectMocks
    private BillingService billingService;
    @Mock
    private IndividualBillingCalculator individualBillingCalculator;
    @Mock
    private ProfessionalBillingCalculator professionalBillingCalculator;

    @Test
    void calculateBillingAmount_mustCallIndividualBilling_whenIndividualClient() {
        // Given
        Individual individual = new Individual("EKW78547862", emptyList(), "title", "lastName", "firstName");
        when(individualBillingCalculator.calculateBilling(individual)).thenReturn(785.54);

        // When
        Double result = billingService.calculateBillingAmount(individual);

        // Then
        assertNotNull(result);
        assertEquals(785.54, result);
        verify(individualBillingCalculator, times(1)).calculateBilling(individual);
    }

    @Test
    void calculateBillingAmount_mustCallProfessionalBilling_whenProfessionalClient() {
        // Given
        Professional professional = new Professional("EKW78547862", emptyList(), 12345678912324L, "client", 500000.0);
        when(professionalBillingCalculator.calculateBilling(professional)).thenReturn(785.54);

        // When
        Double result = billingService.calculateBillingAmount(professional);

        // Then
        assertNotNull(result);
        assertEquals(785.54, result);
        verify(professionalBillingCalculator, times(1)).calculateBilling(professional);
    }
}
