package fr.ekwateur.metertocash.model;

import jakarta.validation.constraints.Pattern;

public abstract sealed class Client permits Professional, Individual {
    @Pattern(regexp = "^EKW(\\d){8}$")
    private String reference;

    public Client(String reference) {
        this.reference = reference;
    }
}
