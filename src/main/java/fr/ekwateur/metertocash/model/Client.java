package fr.ekwateur.metertocash.model;

import jakarta.validation.constraints.Pattern;
import java.util.List;

public abstract sealed class Client permits Professional, Individual {
    @Pattern(regexp = "^EKW(\\d){8}$")
    private String reference;
    private List<Consumption> consumptions;

    public Client(String reference, List<Consumption> consumptions) {
        this.reference = reference;
        this.consumptions = consumptions;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<Consumption> getConsumptions() {
        return consumptions;
    }

    public void setConsumptions(List<Consumption> consumptions) {
        this.consumptions = consumptions;
    }
}
