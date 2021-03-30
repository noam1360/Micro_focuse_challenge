package cz.phonebook.client.DTO;

import java.util.ArrayList;
import java.util.List;

public class ContactListDTO {
    private List<ContactDTO> contacts;

    public ContactListDTO(){
        contacts = new ArrayList<>();
    }


    public List<ContactDTO> getContacts() {
        System.out.println(contacts.size());
        return contacts;
    }

    public void setContacts(List<ContactDTO> contacts) {
        this.contacts = contacts;
    }
}
