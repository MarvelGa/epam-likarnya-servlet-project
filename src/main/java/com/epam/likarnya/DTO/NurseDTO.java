package com.epam.likarnya.DTO;

import com.epam.likarnya.model.User;

import java.util.Objects;

public class NurseDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private User.Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User.Role getRole() {
        return role;
    }

    public void setRole(User.Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NurseDTO nurseDOT = (NurseDTO) o;
        return Objects.equals(id, nurseDOT.id) && Objects.equals(firstName, nurseDOT.firstName) && Objects.equals(lastName, nurseDOT.lastName) && role == nurseDOT.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, role);
    }
}
