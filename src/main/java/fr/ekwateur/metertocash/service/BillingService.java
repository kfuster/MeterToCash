package fr.ekwateur.metertocash.service;

import fr.ekwateur.metertocash.calculator.IndividualBillingCalculator;
import fr.ekwateur.metertocash.calculator.ProfessionalBillingCalculator;
import fr.ekwateur.metertocash.model.Client;
import fr.ekwateur.metertocash.model.Individual;
import fr.ekwateur.metertocash.model.Professional;
import org.springframework.stereotype.Service;

@Service
public class BillingService {

    private IndividualBillingCalculator individualBillingCalculator;
    private ProfessionalBillingCalculator professionalBillingCalculator;

    public BillingService(IndividualBillingCalculator individualBillingCalculator,
                          ProfessionalBillingCalculator professionalBillingCalculator) {
        this.individualBillingCalculator = individualBillingCalculator;
        this.professionalBillingCalculator = professionalBillingCalculator;
    }

    public double calculateBillingAmount(Client client) {
        return switch (client) {
            case Individual individual -> individualBillingCalculator.calculateBilling(individual);
            case Professional professional -> professionalBillingCalculator.calculateBilling(professional);
        };
    }
}
