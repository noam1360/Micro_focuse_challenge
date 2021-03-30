package phoneBookApp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import phoneBookApp.dto.ContactDTO;
import phoneBookApp.dto.ContactCreateDTO;
import phoneBookApp.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import phoneBookApp.repository.ContactRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class
 * Communicates with the repository and puts the contact into contactDTO and contactDTO to a contact
 * in the database for safer use
 */
@Service
public class ContactService {
    private final ContactRepository contactRepository;

    //helper function
    public static boolean isNumeric(String str){
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    @Autowired
    public ContactService(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    //creates contact in the database from contactDTO
    public ContactDTO create (ContactCreateDTO contactCreateDTO) throws Exception{
        if(contactCreateDTO.getPhone() == null ||  !isNumeric(contactCreateDTO.getPhone())){
            throw new Exception("Phone Number cannot be Null");
        }
        return toDTO(contactRepository.save(new Contact(
                contactCreateDTO.getPhone(), contactCreateDTO.getFirstName(), contactCreateDTO.getSurname()
            )));
    }

    //turns a contact into contactDTO
    private ContactDTO toDTO (Contact contact){
        return new ContactDTO(contact.getId(), contact.getPhone(), contact.getFirstName(), contact.getSurname());
    }

    //turns list of contacts into list of contactDTO
    private Optional<ContactDTO> toDTO(Optional<Contact> optionalContact){
        if(optionalContact.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(toDTO(optionalContact.get()));
    }


    //finds a contact by its id(unique)
    public Optional<ContactDTO> findByIdAsDTO(int id){
        return toDTO(contactRepository.findById(id));
    }

    //maps contacts to a page
    public Page<ContactDTO> findByPage(Pageable pageable){
        return contactRepository.findAll(pageable).map(this::toDTO);
    }

    //deletes contact by finding its id
    public void deleteById(int id) throws Exception {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if(optionalContact.isEmpty()){
            throw new Exception("Contact Does Not Exist");
        } else {
            contactRepository.deleteById(id);
        }
    }

    //finds contacts by a specific name
    public List<ContactDTO> findByName(String Name) {
        return contactRepository.findContactByFirstName(Name).stream().map(this::toDTO).collect(Collectors.toList());
    }

    //finds all contacts
    public List<ContactDTO> findAll(){
        return contactRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }


}

