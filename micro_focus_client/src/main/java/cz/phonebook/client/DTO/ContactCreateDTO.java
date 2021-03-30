package cz.phonebook.client.DTO;

import java.util.Objects;

public class ContactCreateDTO {

    private final String firstName;
    private final String surname;
    private final String phone;

    public ContactCreateDTO(String firstName,
                            String surname,
                            String phone) {
        this.firstName = firstName;
        this.surname = surname;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactCreateDTO)) return false;
        ContactCreateDTO that = (ContactCreateDTO) o;
        return firstName.equals(that.firstName) &&
                surname.equals(that.surname) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, surname, phone);
    }
}

