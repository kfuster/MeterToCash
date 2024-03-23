package fr.ekwateur.metertocash.model;

import java.util.StringJoiner;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

final class Professional extends Client {
    private Long siretNumber;
    private String businessName;
    private Double revenue;

    public Professional(String reference, Long siretNumber, String businessName, Double revenue) {
        super(reference);
        this.siretNumber = siretNumber;
        this.businessName = businessName;
        this.revenue = revenue;
    }

    public Long getSiretNumber() {
        return siretNumber;
    }

    public void setSiretNumber(Long siretNumber) {
        this.siretNumber = siretNumber;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Professional that = (Professional) o;

        return new EqualsBuilder().append(siretNumber, that.siretNumber).append(businessName, that.businessName).append(revenue, that.revenue).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(siretNumber).append(businessName).append(revenue).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("siretNumber", siretNumber)
            .append("businessName", businessName)
            .append("revenue", revenue)
            .toString();
    }
}
