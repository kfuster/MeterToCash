package fr.ekwateur.metertocash.calculator;

import fr.ekwateur.metertocash.model.Professional;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ProfessionalBillingCalculator implements BillingCalculator<Professional> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionalBillingCalculator.class);

    private static final Double SALE_LIMIT = 1000000.0;
    private static final BigDecimal ELECTRICITY_PRICE_BELOW_LIMIT = BigDecimal.valueOf(0.118);
    private static final BigDecimal ELECTRICITY_PRICE_ABOVE_LIMIT = BigDecimal.valueOf(0.114);
    private static final BigDecimal GAZ_PRICE_BELOW_LIMIT = BigDecimal.valueOf(0.113);
    private static final BigDecimal GAZ_PRICE_ABOVE_LIMIT = BigDecimal.valueOf(0.111);
    @Override
    public double calculateBilling(Professional client) {
        LOGGER.info("Starting calculation for professional client {}", client.getReference());
        return client.getConsumptions()
            .stream().mapToDouble( c ->
                switch (c.getEnergyType()) {
                    case ELEC -> (client.getRevenue() < SALE_LIMIT ? ELECTRICITY_PRICE_BELOW_LIMIT : ELECTRICITY_PRICE_ABOVE_LIMIT).multiply(BigDecimal.valueOf(c.getAmount())).doubleValue();
                    case GAZ -> (client.getRevenue() < SALE_LIMIT ? GAZ_PRICE_BELOW_LIMIT : GAZ_PRICE_ABOVE_LIMIT).multiply(BigDecimal.valueOf(c.getAmount())).doubleValue();
                })
            .sum();
    }
}
