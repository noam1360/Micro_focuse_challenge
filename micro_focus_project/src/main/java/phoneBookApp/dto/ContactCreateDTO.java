package phoneBookApp.dto;

import org.springframework.lang.NonNull;

import java.util.Objects;

/**
 * Contact Data Transfer Object for creating
 */
public class ContactCreateDTO {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactCreateDTO)) return false;
        ContactCreateDTO that = (ContactCreateDTO) o;
        return phone.equals(that.phone) &&
                first_name.equals(that.first_name) &&
                Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, first_name, surname);
    }

    private String phone;
    private String first_name;
    private String surname;

    public ContactCreateDTO( String phone,
                      String firstName,
                      String surname){

        this.phone = phone;
        this.first_name = firstName;
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
