package fr.ekwateur.metertocash.calculator;

import fr.ekwateur.metertocash.model.Individual;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class IndividualBillingCalculator implements BillingCalculator<Individual> {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndividualBillingCalculator.class);

    private static final BigDecimal ELECTRICITY_PRICE = BigDecimal.valueOf(0.121);
    private static final BigDecimal GAZ_PRICE = BigDecimal.valueOf(0.115);
    public double calculateBilling(Individual client) {
       LOGGER.info("Starting calculation for individual client {}", client.getReference());
       return client.getConsumptions()
            .stream().mapToDouble( c ->
                switch (c.getEnergyType()) {
                    case ELEC -> ELECTRICITY_PRICE.multiply(BigDecimal.valueOf(c.getAmount())).doubleValue();
                    case GAZ -> GAZ_PRICE.multiply(BigDecimal.valueOf(c.getAmount())).doubleValue();
                })
            .sum();
    }
}
