package fr.ekwateur.metertocash.model;

import java.util.StringJoiner;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

final class Individual extends Client {
    private String title;
    private String lastName;
    private String firstName;

    public Individual(String reference, String title, String lastName, String firstName) {
        super(reference);
        this.title = title;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Individual that = (Individual) o;

        return new EqualsBuilder().append(title, that.title).append(lastName, that.lastName).append(firstName, that.firstName).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(title).append(lastName).append(firstName).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("title", title)
            .append("lastName", lastName)
            .append("firstName", firstName)
            .toString();
    }
}
