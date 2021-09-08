package com.epam.likarnya.DTO;

import com.epam.likarnya.model.User;

import java.util.Objects;

public class DoctorDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String category;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
        DoctorDTO doctorDTO = (DoctorDTO) o;
        return Objects.equals(id, doctorDTO.id) && Objects.equals(firstName, doctorDTO.firstName) && Objects.equals(lastName, doctorDTO.lastName) && Objects.equals(category, doctorDTO.category) && role == doctorDTO.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, category, role);
    }
}
