package phoneBookApp.entity;

import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * This class represents the entity of contact in our database
 */
@Entity(name = "Contact")
public class Contact {
    @Id
    @GeneratedValue
    private int contact_id;
    @NonNull
    private String phone;
    private String firstName;
    private String surname;

    public Contact(){}

    public Contact(@NonNull String phone,
                   String firstName,
                   String surname){

        this.phone = phone;
        this.firstName = firstName;
        this.surname = surname;
    }

    public int getId() {
        return contact_id;
    }

    @NonNull
    public String  getPhone() {
        return phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setPhone(@NonNull String phone) {
        this.phone = phone;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contact_id=" + contact_id +
                ", phone='" + phone + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
