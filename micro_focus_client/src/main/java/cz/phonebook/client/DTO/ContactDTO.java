package cz.phonebook.client.DTO;

import java.util.Objects;

public class ContactDTO {

    private final int id;
    private final String firstName;
    private final String surname;
    private final String phone;

    @Override
    public String toString() {
        return "ContactDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public ContactDTO(int id,
                      String firstName,
                      String surname,
                      String phone) {
        this.firstName = firstName;
        this.surname = surname;
        this.phone = phone;
        this.id = id;
    }

    public int getId() {
        return id;
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
        if (!(o instanceof ContactDTO)) return false;
        ContactDTO that = (ContactDTO) o;
        return id == that.id &&
                firstName.equals(that.firstName) &&
                surname.equals(that.surname) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, surname, phone);
    }
}
