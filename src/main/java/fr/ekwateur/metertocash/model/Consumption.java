package fr.ekwateur.metertocash.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Consumption {
    private EnergyType energyType;
    private double amount;

    public Consumption(EnergyType energyType, double amount) {
        this.energyType = energyType;
        this.amount = amount;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Consumption that = (Consumption) o;

        return new EqualsBuilder().append(amount, that.amount).append(energyType, that.energyType).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(energyType).append(amount).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("energyType", energyType)
            .append("amount", amount)
            .toString();
    }
}
