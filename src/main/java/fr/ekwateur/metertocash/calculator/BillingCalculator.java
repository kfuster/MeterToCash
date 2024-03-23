package fr.ekwateur.metertocash.calculator;

import fr.ekwateur.metertocash.model.Client;

public interface BillingCalculator<T extends Client> {
    double calculateBilling(T consumption);
}
