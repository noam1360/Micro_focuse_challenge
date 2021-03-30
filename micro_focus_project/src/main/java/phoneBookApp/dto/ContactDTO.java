package phoneBookApp.dto;

import org.springframework.lang.NonNull;

import java.util.Objects;

/**
 * Contact Data Transfer Object
 */
public class ContactDTO {

    private int contact_id;
    private String phone;
    private String first_name;
    private String surname;

    public ContactDTO(int id, String phone,
                   String firstName,
                   String surname){

        this.contact_id = id;
        this.phone = phone;
        this.first_name = firstName;
        this.surname = surname;
    }

    public int getId() {
        return contact_id;
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

    @Override
    public String toString() {
        return "ContactDTO{" +
                "contact_id=" + contact_id +
                ", phone='" + phone + '\'' +
                ", first_name='" + first_name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactDTO)) return false;
        ContactDTO that = (ContactDTO) o;
        return contact_id == that.contact_id &&
                phone.equals(that.phone) &&
                first_name.equals(that.first_name) &&
                Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contact_id, phone, first_name, surname);
    }
}
